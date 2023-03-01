package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.helper.Innings;
import com.tekion.gameofcricket.helper.OngoingMatchData;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.repositories.MatchRepository;
import com.tekion.gameofcricket.utility.Constants;
import com.tekion.gameofcricket.utility.DateUtils;
import com.tekion.gameofcricket.utility.MatchResult;
import org.apache.catalina.core.ApplicationContext;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerMatchStatService playerMatchStatService;
    private Match match;
    @Autowired
    private OngoingMatchData matchData;
    private Team team1, team2;
    private Map<ObjectId, PlayerMatchStat> playerMatchStatMap;

    @Override
    public void addMatch(Match match) {
        matchRepository.save(match);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(ObjectId id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public List<Match> getMatchByDate(Date date) {
        return matchRepository.findMatchesByMatchDate(date);
    }

    @Override
    public List<Match> getMatchByTeam(ObjectId teamId) {
        return matchRepository.findMatchesByTeam1IdOrTeam2Id(teamId);
    }

    @Override
    public void playMatch(ObjectId team1Id, ObjectId team2Id) {
        match = new Match(ObjectId.get(), team1Id, team2Id, DateUtils.getCurrentDate());
        team1 = teamService.getTeamById(team1Id);
        team2 = teamService.getTeamById(team2Id);
        matchData.resetInnings();
        generatePlayerStatMap();
        simulateInnings(true, Integer.MAX_VALUE);
        simulateInnings(false, matchData.getFirstInnings().getRunsScored() + 1);
        generateResult();
        storeMatchData();
    }

    private void generatePlayerStatMap() {
        playerMatchStatMap = new HashMap<>();
        team1.getPlayerIds().forEach(playerId -> playerMatchStatMap.put(playerId,
                new PlayerMatchStat(playerId, match.getId(), team1.getId())));
        team2.getPlayerIds().forEach(playerId -> playerMatchStatMap.put(playerId,
                new PlayerMatchStat(playerId, match.getId(), team2.getId())));
    }

    private void simulateInnings(boolean isFirstInnings, int target) {
        Innings currentInnings = isFirstInnings ? matchData.getFirstInnings() : matchData.getSecondInnings();
        Team battingTeam = isFirstInnings ? team1 : team2;
        Team bowlingTeam = isFirstInnings ? team2 : team1;

        int ballsSimulated = 0;
        while (ballsSimulated++ < Constants.MATCH_LENGTH_IN_BALLS) {
            // every batting team player bats in order, every bowling team player take turn to bowl an over
            ObjectId batsmanId = battingTeam.getPlayerIds().get(currentInnings.getWicketsFallen());
            ObjectId bowlerId = bowlingTeam.getPlayerIds()
                                           .get((currentInnings.getBallsThrown() / 6) % Constants.TEAM_SIZE);

            // 0..6 --> equal run scored,   7 --> wicket falls
            int outcome = (int) (Math.random() * 8);
            currentInnings.addBall(outcome);

            playerMatchStatService.updateBattingFigure(playerMatchStatMap.get(batsmanId), outcome);
            playerMatchStatService.updateBowlingFigure(playerMatchStatMap.get(bowlerId), outcome);

            // batting team gets all out or chasing team achieves the target
            if (currentInnings.getWicketsFallen() == Constants.TEAM_SIZE || currentInnings.getRunsScored() >= target) {
                break;
            }
        }
    }

    private void generateResult() {
        if (matchData.getFirstInnings().getRunsScored() > matchData.getSecondInnings().getRunsScored()) {
            match.setResult(MatchResult.TEAM_1_WON);
        } else if (matchData.getFirstInnings().getRunsScored() < matchData.getSecondInnings().getRunsScored()) {
            match.setResult(MatchResult.TEAM_2_WON);
        } else {
            match.setResult(MatchResult.DRAW);
        }
    }

    private void storeMatchData() {
        addMatch(match);
        teamService.updateTeamDataPostMatch(team1, team2, match.getResult());
        playerMatchStatMap.forEach((key, value) -> {
            playerMatchStatService.addPlayerMatchStat(value);
            playerService.updatePlayerDataPostMatch(key, value);
        });
    }
}
