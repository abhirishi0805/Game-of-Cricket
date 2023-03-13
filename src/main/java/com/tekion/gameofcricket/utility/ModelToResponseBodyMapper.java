package com.tekion.gameofcricket.utility;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;

/**
 * This class maps the model instances to corresponding response body to hide sensitive information
 */
public final class ModelToResponseBodyMapper {

    // private constructor to make this class non-instantiable
    private ModelToResponseBodyMapper() {
    }

    public static PlayerResponseDto mapPlayer(Player player) {
        return new PlayerResponseDto(player.getPlayerName(), player.getGamesPlayed(), player.getTotalRunsScored(),
                player.getTotalWicketsTaken());
    }
}
