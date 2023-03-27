package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Stat;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.MatchResponseDto;
import com.tekion.gameofcricket.responsebody.StatResponseDto;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;
import com.tekion.gameofcricket.responsebody.TeamResponseDto;
import com.tekion.gameofcricket.utility.enums.MatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a concrete implementation for the ModelToResponseMappingService interface
 */
@Service
public final class ResponseMappingServiceImpl implements ResponseMappingService {

    @Autowired
    private PlayerService playerService;
    @Autowired
    @Lazy
    private TeamService teamService;

    @Override
    public PlayerResponseDto mapPlayer(Player player) {
        return new PlayerResponseDto(
                player.getPlayerName(),
                player.getTotalRunsScored(),
                player.getTotalWicketsTaken(),
                player.getGamesPlayed()
        );
    }

    @Override
    public TeamResponseDto mapTeam(Team team) {
        List<String> players = team.getPlayerIds().stream()
                                   .map(playerId -> playerService.getPlayerById(playerId).getPlayerName())
                                   .collect(Collectors.toList());
        return new TeamResponseDto(
                team.getTeamName(),
                players,
                team.getGamesWon(),
                team.getGamesDrawn(),
                team.getGamesLost()
        );
    }

    @Override
    public MatchResponseDto mapMatch(Match match) {
        String team1Name = teamService.getTeamById(match.getTeam1Id()).getTeamName();
        String team2Name = teamService.getTeamById(match.getTeam2Id()).getTeamName();
        String result = match.getResult() == MatchResult.TEAM_1_WON ?
                        team1Name + " won!" : (match.getResult() == MatchResult.TEAM_2_WON ?
                                               team2Name + " won!" : "Match drawn!");
        return new MatchResponseDto(
                team1Name,
                team2Name,
                result,
                match.getMatchDate()
        );
    }

    @Override
    public StatResponseDto mapStat(Stat stat) {
        String teamName = teamService.getTeamById(stat.getTeamId()).getTeamName();
        String opponentTeamName = "NA";
        // for backward compatibility - initially opponent wasn't being stored
        if (stat.getOpponentTeamId() != null) {
            opponentTeamName = teamService.getTeamById(stat.getOpponentTeamId()).getTeamName();
        }
        return StatResponseDto.builder().team(teamName).opponent(opponentTeamName).runsScored(stat.getRunsScored())
                              .ballsFaced(stat.getBallsFaced()).sixesHit(stat.getSixesHit())
                              .foursHit(stat.getFoursHit()).ballsThrown(stat.getBallsThrown())
                              .runsConceded(stat.getRunsConceded()).wicketsTaken(stat.getWicketsTaken()).build();
    }
}
