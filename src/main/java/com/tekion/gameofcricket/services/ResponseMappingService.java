package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.MatchResponseDto;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;
import com.tekion.gameofcricket.responsebody.TeamResponseDto;

/**
 * This interface declares methods to map model instances to corresponding response body to hide sensitive information
 */
public interface ResponseMappingService {

    PlayerResponseDto mapPlayer(Player player);

    TeamResponseDto mapTeam(Team team);

    MatchResponseDto mapMatch(Match match);
}
