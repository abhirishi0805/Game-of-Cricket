package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.helperbeans.Inning;
import com.tekion.gameofcricket.helperbeans.OngoingMatchData;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Stat;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.PlayMatchResponseDto;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;
import com.tekion.gameofcricket.utility.DateUtils;
import com.tekion.gameofcricket.utility.enums.MatchResult;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.tekion.gameofcricket.utility.Constants.MATCH_LENGTH_IN_BALLS;
import static com.tekion.gameofcricket.utility.Constants.TEAM_SIZE;

/**
 * This is a concrete implementation for the PlayMatchService interface
 */
@Service
public final class PlayMatchServiceImpl implements PlayMatchService {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayMatchServiceImpl.class);
    @Autowired
    private TeamService teamService;
    @Autowired
    private StatService statService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private OngoingMatchData matchData;
    private Match match;
    private Team team1, team2;
    private Map<ObjectId, Stat> playerMatchStatMap;

    @Override
    public PlayMatchResponseDto playMatch(Team team1, Team team2) {
        LOGGER.info("Match requested : " + team1.getTeamName() + " vs " + team2.getTeamName());
        this.team1 = team1;
        this.team2 = team2;
        configureMatchData();
        generatePlayerStatMap();
        simulateInnings();
        generateResult();
        storeMatchData();
        return getResponseDTO();
    }

    private void configureMatchData() {
        match = new Match(ObjectId.get(), team1.getId(), team2.getId(), DateUtils.getCurrentDate());
        matchData.resetInnings();
    }

    private void generatePlayerStatMap() {
        playerMatchStatMap = new HashMap<>();
        team1.getPlayerIds().forEach(playerId -> playerMatchStatMap.put(playerId,
                new Stat(playerId, team1.getId(), team2.getId(), match.getId())));
        team2.getPlayerIds().forEach(playerId -> playerMatchStatMap.put(playerId,
                new Stat(playerId, team2.getId(), team1.getId(), match.getId())));
    }

    private void simulateInnings() {
        // first inning
        simulateInningsHelper(matchData.getFirstInning(), team1, team2, Integer.MAX_VALUE);
        // second inning
        simulateInningsHelper(matchData.getSecondInning(), team2, team1,
                matchData.getFirstInning().getRunsScored() + 1);
    }

    private void simulateInningsHelper(Inning currentInning, Team battingTeam, Team bowlingTeam, int target) {
        int ballsSimulated = 0;

        while (ballsSimulated++ < MATCH_LENGTH_IN_BALLS) {
            // every batting team player bats in order, every bowling team player take turn to bowl an over
            ObjectId batsmanId = battingTeam.getPlayerIds().get(currentInning.getWicketsFallen());
            ObjectId bowlerId = bowlingTeam.getPlayerIds().get((currentInning.getBallsThrown() / 6) % TEAM_SIZE);

            // 0..6 --> equal run scored,   7 --> wicket falls
            int outcome = ThreadLocalRandom.current().nextInt(8);
            currentInning.addBall(outcome);

            updateBattingFigure(playerMatchStatMap.get(batsmanId), outcome);
            updateBowlingFigure(playerMatchStatMap.get(bowlerId), outcome);

            // batting team gets all out or chasing team achieves the target
            if (currentInning.getWicketsFallen() == TEAM_SIZE || currentInning.getRunsScored() >= target) {
                break;
            }
        }

        LOGGER.info(battingTeam.getTeamName() + " : " + currentInning.getRunsScored() + '/' +
                    currentInning.getWicketsFallen());
    }

    private void updateBattingFigure(Stat stat, int outcome) {
        stat.setBallsFaced(stat.getBallsFaced() + 1);
        if (outcome != 7) {
            stat.setRunsScored(stat.getRunsScored() + outcome);
        }
        if (outcome == 6) {
            stat.setSixesHit(stat.getSixesHit() + 1);
        }
        if (outcome == 4) {
            stat.setFoursHit(stat.getFoursHit() + 1);
        }
    }

    private void updateBowlingFigure(Stat stat, int outcome) {
        stat.setBallsThrown(stat.getBallsThrown() + 1);
        if (outcome == 7) {
            stat.setWicketsTaken(stat.getWicketsTaken() + 1);
        } else {
            stat.setRunsConceded(stat.getRunsConceded() + 1);
        }
    }

    private void generateResult() {
        if (matchData.getFirstInning().getRunsScored() > matchData.getSecondInning().getRunsScored()) {
            LOGGER.info("Result : " + team1.getTeamName() + " won!");
            match.setResult(MatchResult.TEAM_1_WON);
        } else if (matchData.getFirstInning().getRunsScored() < matchData.getSecondInning().getRunsScored()) {
            LOGGER.info("Result : " + team2.getTeamName() + " won!");
            match.setResult(MatchResult.TEAM_2_WON);
        } else {
            LOGGER.info("Result : Match drawn!");
            match.setResult(MatchResult.DRAW);
        }
    }

    private void storeMatchData() {
        matchService.addMatch(match);
        updateTeamDataPostMatch();
        playerMatchStatMap.forEach((playerId, playerMatchStat) -> {
            statService.addPlayerMatchStat(playerMatchStat);
            updatePlayerDataPostMatch(playerId, playerMatchStat);
        });
    }

    private void updateTeamDataPostMatch() {
        if (match.getResult() == MatchResult.TEAM_1_WON) {
            team1.setGamesWon(team1.getGamesWon() + 1);
            team2.setGamesLost(team2.getGamesLost() + 1);
        } else if (match.getResult() == MatchResult.TEAM_2_WON) {
            team1.setGamesLost(team1.getGamesLost() + 1);
            team2.setGamesWon(team2.getGamesWon() + 1);
        } else {
            team1.setGamesDrawn(team1.getGamesDrawn() + 1);
            team2.setGamesDrawn(team2.getGamesDrawn() + 1);
        }
        teamService.updateTeam(team1);
        teamService.updateTeam(team2);
    }

    private void updatePlayerDataPostMatch(ObjectId playerId, Stat stat) {
        Player player = playerService.getPlayerById(playerId);
        player.setGamesPlayed(player.getGamesPlayed() + 1);
        player.setTotalRunsScored(player.getTotalRunsScored() + stat.getRunsScored());
        player.setTotalWicketsTaken(player.getTotalWicketsTaken() + stat.getWicketsTaken());
        playerService.updatePlayer(player);
    }

    private PlayMatchResponseDto getResponseDTO() {
        String firstInnings = team1.getTeamName() + " : " + matchData.getFirstInning().getRunsScored() + '/' +
                              matchData.getFirstInning().getWicketsFallen();
        String secondInnings = team2.getTeamName() + " : " + matchData.getSecondInning().getRunsScored() + '/' +
                               matchData.getSecondInning().getWicketsFallen();
        String result = match.getResult() == MatchResult.TEAM_1_WON ? team1.getTeamName() + " won!"
                                                                    : (match.getResult() == MatchResult.TEAM_2_WON ?
                                                                       team2.getTeamName() + " won!" : "Match drawn!");

        return new PlayMatchResponseDto(firstInnings, secondInnings, result);
    }
}
