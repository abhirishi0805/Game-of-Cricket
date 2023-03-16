package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.helperbeans.Innings;
import com.tekion.gameofcricket.helperbeans.OngoingMatchData;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Stat;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.PlayMatchResponseDto;
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
    @Autowired
    private Match match;
    private Team team1, team2;
    private Map<ObjectId, Stat> playerMatchStatMap;

    @Override
    public PlayMatchResponseDto playMatch(Team team1, Team team2) {
        LOGGER.info("Match requested : " + team1.getTeamName() + " vs " + team2.getTeamName());
        this.team1 = team1;
        this.team2 = team2;
        configureMatchData();
        matchData.resetInnings();
        generatePlayerStatMap();
        simulateInnings(true);
        simulateInnings(false);
        generateResult();
        storeMatchData();
        return getResponseDTO();
    }

    private void configureMatchData() {
        match.setId(ObjectId.get());
        match.setTeam1Id(team1.getId());
        match.setTeam2Id(team2.getId());
        match.setMatchDate(DateUtils.getCurrentDate());
    }

    private void generatePlayerStatMap() {
        playerMatchStatMap = new HashMap<>();
        team1.getPlayerIds().forEach(playerId -> {
            Stat stat = Stat.builder().playerId(playerId).teamId(team1.getId()).opponentTeamId(team2.getId())
                            .matchId(match.getId()).build();
            playerMatchStatMap.put(playerId, stat);
        });
        team2.getPlayerIds().forEach(playerId -> {
            Stat stat = Stat.builder().playerId(playerId).teamId(team2.getId()).opponentTeamId(team1.getId())
                            .matchId(match.getId()).build();
            playerMatchStatMap.put(playerId, stat);
        });
    }

    private void simulateInnings(boolean isFirstInnings) {
        Innings currentInnings = isFirstInnings ? matchData.getFirstInnings() : matchData.getSecondInnings();
        Team battingTeam = isFirstInnings ? team1 : team2;
        Team bowlingTeam = isFirstInnings ? team2 : team1;

        int target = isFirstInnings ? Integer.MAX_VALUE : matchData.getFirstInnings().getRunsScored() + 1;

        int ballsSimulated = 0;
        while (ballsSimulated++ < MATCH_LENGTH_IN_BALLS) {
            // every batting team player bats in order, every bowling team player take turn to bowl an over
            ObjectId batsmanId = battingTeam.getPlayerIds().get(currentInnings.getWicketsFallen());
            ObjectId bowlerId = bowlingTeam.getPlayerIds().get((currentInnings.getBallsThrown() / 6) % TEAM_SIZE);

            // 0..6 --> equal run scored,   7 --> wicket falls
            int outcome = (int) (Math.random() * 8);
            currentInnings.addBall(outcome);

            updateBattingFigure(playerMatchStatMap.get(batsmanId), outcome);
            updateBowlingFigure(playerMatchStatMap.get(bowlerId), outcome);

            // batting team gets all out or chasing team achieves the target
            if (currentInnings.getWicketsFallen() == TEAM_SIZE || currentInnings.getRunsScored() >= target) {
                break;
            }
        }
        LOGGER.info(battingTeam.getTeamName() + " : " + currentInnings.getRunsScored() + '/' +
                    currentInnings.getWicketsFallen());
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
        if (matchData.getFirstInnings().getRunsScored() > matchData.getSecondInnings().getRunsScored()) {
            LOGGER.info("Result : " + team1.getTeamName() + " won!");
            match.setResult(MatchResult.TEAM_1_WON);
        } else if (matchData.getFirstInnings().getRunsScored() < matchData.getSecondInnings().getRunsScored()) {
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

        String firstInnings = team1.getTeamName() + " : " + matchData.getFirstInnings().getRunsScored() + '/' +
                              matchData.getFirstInnings().getWicketsFallen();
        String secondInnings = team2.getTeamName() + " : " + matchData.getSecondInnings().getRunsScored() + '/' +
                               matchData.getSecondInnings().getWicketsFallen();
        String result = match.getResult() == MatchResult.TEAM_1_WON ? team1.getTeamName() + " won!"
                                                                    : (match.getResult() == MatchResult.TEAM_2_WON ?
                                                                       team2.getTeamName() + " won!" : "Match drawn!");

        return PlayMatchResponseDto.builder().firstInnings(firstInnings).secondInnings(secondInnings).result(result)
                                   .build();
    }
}
