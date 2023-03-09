package com.tekion.gameofcricket.utility.exceptionhandling;

import com.tekion.gameofcricket.utility.Constants;
import com.tekion.gameofcricket.utility.DateUtils;
import com.tekion.gameofcricket.utility.requestbody.CreateTeamRequestBody;
import com.tekion.gameofcricket.utility.requestbody.PlayMatchRequestBody;
import com.tekion.gameofcricket.utility.requestbody.PlayerRequestBody;
import com.tekion.gameofcricket.utility.requestbody.TeamRequestBody;
import org.bson.types.ObjectId;

public class InputVerifier {

    public static void validatePlayerId(String playerId) {
        if (!ObjectId.isValid(playerId)) {
            throw new InvalidObjectIdException("Invalid player ID",
                    "invalid hexadecimal representation of an " + "ObjectId: [" + playerId + "]");
        }
    }

    public static void validateTeamId(String teamId) {
        if (!ObjectId.isValid(teamId)) {
            throw new InvalidObjectIdException("Invalid team ID",
                    "invalid hexadecimal representation of an " + "ObjectId: [" + teamId + "]");
        }
    }

    public static void validateMatchId(String matchId) {
        if (!ObjectId.isValid(matchId)) {
            throw new InvalidObjectIdException("Invalid match ID",
                    "invalid hexadecimal representation of an " + "ObjectId: [" + matchId + "]");
        }
    }

    public static void validatePlayerRequestBody(PlayerRequestBody requestBody) {
        if (requestBody.getPlayerName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'playerName' field");
        }
    }

    public static void validateTeamRequestBody(TeamRequestBody requestBody) {
        if (requestBody.getTeamName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'teamName' field");
        }
    }

    public static void validateCreateTeamRequestBody(CreateTeamRequestBody requestBody) {
        if (requestBody.getTeamName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'teamName' field");
        }
        if (requestBody.getPlayerNames() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'playerNames' field");
        }
        if (requestBody.getPlayerNames().size() != Constants.TEAM_SIZE.value()) {
            throw new InvalidRequestBodyException("Team must contain " + Constants.TEAM_SIZE.value() + " players");
        }
    }

    public static void validatePlayMatchRequestBody(PlayMatchRequestBody requestBody) {
        if (requestBody.getTeam1Name() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'team1Name' field");
        }
        if (requestBody.getTeam2Name() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'team2Name' field");
        }
    }

    public static void validateDate(String date) {
        if (!DateUtils.isValidFormat(date)) {
            throw new InvalidDateException("Invalid date format! Use " + DateUtils.getDateFormat());
        }

        // TODO : check if date is valid
        // for example - 30-02-2023 is invalid
    }
}
