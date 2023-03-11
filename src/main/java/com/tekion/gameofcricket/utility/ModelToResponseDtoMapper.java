package com.tekion.gameofcricket.utility;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.utility.responsebody.PlayerResponseDTO;

public class ModelToResponseDtoMapper {

    public static PlayerResponseDTO mapPlayer(Player player) {
        return new PlayerResponseDTO(player.getPlayerName(), player.getGamesPlayed(), player.getTotalRunsScored(),
                player.getTotalWicketsTaken());
    }
}
