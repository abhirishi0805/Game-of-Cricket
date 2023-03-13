package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.MatchResponseDto;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;
import com.tekion.gameofcricket.responsebody.TeamResponseDto;
import com.tekion.gameofcricket.utility.enums.MatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * This is a concrete implementation for the ModelToResponseMappingService interface
 */

@Service
public final class ResponseMappingServiceImpl implements ResponseMappingService {
    @Autowired
    @Lazy
    private TeamService teamService;

    // private constructor to make this class non-instantiable
    private ResponseMappingServiceImpl() {
    }

    @Override
    public PlayerResponseDto mapPlayer(Player player) {
        return new PlayerResponseDto(player.getPlayerName(), player.getGamesPlayed(), player.getTotalRunsScored(),
                player.getTotalWicketsTaken());
    }

    @Override
    public TeamResponseDto mapTeam(Team team) {
        return null;
    }

    @Override
    public MatchResponseDto mapMatch(Match match) {
        String team1Name = teamService.getTeamById(match.getTeam1Id()).getTeamName();
        String team2Name = teamService.getTeamById(match.getTeam2Id()).getTeamName();
        String result = match.getResult() == MatchResult.TEAM_1_WON ? team1Name + " won!" :
                        (match.getResult() == MatchResult.TEAM_2_WON ? team2Name + " won!" : "Match drawn!");
        return new MatchResponseDto(team1Name, team2Name, result, match.getMatchDate());
    }
}
