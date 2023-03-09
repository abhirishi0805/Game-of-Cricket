package com.tekion.gameofcricket.utility;

import org.bson.types.ObjectId;

public class InputVerifier {

    public static void verifyPlayerId(String playerId) {
        if (!ObjectId.isValid(playerId)) {
            throw new InvalidObjectIdException("Invalid player ID",
                    "invalid hexadecimal representation of an " + "ObjectId: [" + playerId + "]");
        }
    }

    public static void verifyTeamId(String teamId) {
        if (!ObjectId.isValid(teamId)) {
            throw new InvalidObjectIdException("Invalid team ID",
                    "invalid hexadecimal representation of an " + "ObjectId: [" + teamId + "]");
        }
    }

    public static void verifyMatchId(String matchId) {
        if (!ObjectId.isValid(matchId)) {
            throw new InvalidObjectIdException("Invalid match ID",
                    "invalid hexadecimal representation of an " + "ObjectId: [" + matchId + "]");
        }
    }
}
