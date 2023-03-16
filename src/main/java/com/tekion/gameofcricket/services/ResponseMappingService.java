package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Stat;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.MatchResponseDto;
import com.tekion.gameofcricket.responsebody.StatResponseDto;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;
import com.tekion.gameofcricket.responsebody.TeamResponseDto;

/**
 * This interface declares methods to map model instances to corresponding response body to hide sensitive information
 * These mappings are generally done before returning any data to the client
 */
public interface ResponseMappingService {

    /**
     * Maps a Player to corresponding PlayerResponseDto
     *
     * @param player Player instance to be mapped
     * @return corresponding PlayerResponseDto instance
     */
    PlayerResponseDto mapPlayer(Player player);

    /**
     * Maps a Team to corresponding TeamResponseDto
     *
     * @param team Team instance to be mapped
     * @return corresponding TeamResponseDto instance
     */
    TeamResponseDto mapTeam(Team team);

    /**
     * Maps a Match to corresponding MatchResponseDto
     *
     * @param match Match instance to be mapped
     * @return corresponding MatchResponseDto instance
     */
    MatchResponseDto mapMatch(Match match);

    /**
     * Maps a Stat to corresponding StatResponseDto
     *
     * @param stat Stat instance to be mapped
     * @return corresponding StatResponseDto instance
     */
    StatResponseDto mapStat(Stat stat);
}
