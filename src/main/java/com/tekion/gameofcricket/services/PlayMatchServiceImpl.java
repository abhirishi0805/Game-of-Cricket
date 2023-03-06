package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.helper.Innings;
import com.tekion.gameofcricket.helper.OngoingMatchData;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.utility.Constants;
import com.tekion.gameofcricket.utility.DateUtils;
import com.tekion.gameofcricket.utility.MatchResult;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayMatchServiceImpl implements PlayMatchService {

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerMatchStatService playerMatchStatService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private OngoingMatchData matchData;
    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayMatchServiceImpl.class);
    private Match match;
    private Team team1, team2;
    private Map<ObjectId, PlayerMatchStat> playerMatchStatMap;

    @Override
    public void playMatch(ObjectId team1Id, ObjectId team2Id) {
        team1 = teamService.getTeamById(team1Id);
        team2 = teamService.getTeamById(team2Id);
        LOGGER.info("Match requested : " + team1.getTeamName() + " vs " + team2.getTeamName());
        match = getNewMatchBean();
        matchData.resetInnings();
        generatePlayerStatMap();
        simulateInnings(true);
        simulateInnings(false);
        generateResult();
        storeMatchData();
    }

    private Match getNewMatchBean() {
        Match match = (Match) applicationContext.getBean("match");
        match.setId(ObjectId.get());
        match.setTeam1Id(team1.getId());
        match.setTeam2Id(team2.getId());
        match.setMatchDate(DateUtils.getCurrentDate());
        return match;
    }

    private void generatePlayerStatMap() {
        playerMatchStatMap = new HashMap<>();
        team1.getPlayerIds().forEach(playerId -> {
            PlayerMatchStat playerMatchStat = (PlayerMatchStat) applicationContext.getBean("playerMatchStat");
            playerMatchStat.setPlayerId(playerId);
            playerMatchStat.setTeamId(team1.getId());
            playerMatchStat.setMatchId(match.getId());
            playerMatchStatMap.put(playerId, playerMatchStat);
        });
        team2.getPlayerIds().forEach(playerId -> {
            PlayerMatchStat playerMatchStat = (PlayerMatchStat) applicationContext.getBean("playerMatchStat");
            playerMatchStat.setPlayerId(playerId);
            playerMatchStat.setTeamId(team2.getId());
            playerMatchStat.setMatchId(match.getId());
            playerMatchStatMap.put(playerId, playerMatchStat);
        });
    }

    private void simulateInnings(boolean isFirstInnings) {
        Innings currentInnings = isFirstInnings ? matchData.getFirstInnings() : matchData.getSecondInnings();
        Team battingTeam = isFirstInnings ? team1 : team2;
        Team bowlingTeam = isFirstInnings ? team2 : team1;

        int target = isFirstInnings ? Integer.MAX_VALUE : matchData.getFirstInnings().getRunsScored() + 1;

        int ballsSimulated = 0;
        while (ballsSimulated++ < Constants.MATCH_LENGTH_IN_BALLS.getValue()) {
            // every batting team player bats in order, every bowling team player take turn to bowl an over
            ObjectId batsmanId = battingTeam.getPlayerIds().get(currentInnings.getWicketsFallen());
            ObjectId bowlerId = bowlingTeam.getPlayerIds()
                                           .get((currentInnings.getBallsThrown() / 6) % Constants.TEAM_SIZE.getValue());

            // 0..6 --> equal run scored,   7 --> wicket falls
            int outcome = (int) (Math.random() * 8);
            currentInnings.addBall(outcome);

            playerMatchStatService.updateBattingFigure(playerMatchStatMap.get(batsmanId), outcome);
            playerMatchStatService.updateBowlingFigure(playerMatchStatMap.get(bowlerId), outcome);

            // batting team gets all out or chasing team achieves the target
            if (currentInnings.getWicketsFallen() == Constants.TEAM_SIZE.getValue() ||
                currentInnings.getRunsScored() >= target) {
                break;
            }
        }
    }

    private void generateResult() {
        if (matchData.getFirstInnings().getRunsScored() > matchData.getSecondInnings().getRunsScored()) {
            LOGGER.info(team1.getTeamName() + " won!");
            match.setResult(MatchResult.TEAM_1_WON);
        } else if (matchData.getFirstInnings().getRunsScored() < matchData.getSecondInnings().getRunsScored()) {
            LOGGER.info(team2.getTeamName() + " won!");
            match.setResult(MatchResult.TEAM_2_WON);
        } else {
            LOGGER.info("Match drawn!");
            match.setResult(MatchResult.DRAW);
        }
    }

    private void storeMatchData() {
        matchService.addMatch(match);
        teamService.updateTeamDataPostMatch(team1, team2, match.getResult());
        playerMatchStatMap.forEach((key, value) -> {
            playerMatchStatService.addPlayerMatchStat(value);
            playerService.updatePlayerDataPostMatch(key, value);
        });
    }
}
