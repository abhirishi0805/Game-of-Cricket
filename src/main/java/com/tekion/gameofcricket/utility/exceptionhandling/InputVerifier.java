package com.tekion.gameofcricket.utility.exceptionhandling;

import com.tekion.gameofcricket.utility.Constants;
import com.tekion.gameofcricket.utility.DateUtils;
import com.tekion.gameofcricket.requestbody.CreateTeamRequestDto;
import com.tekion.gameofcricket.requestbody.PlayMatchRequestDto;
import com.tekion.gameofcricket.requestbody.PlayerRequestDto;
import com.tekion.gameofcricket.requestbody.TeamRequestDto;
import org.bson.types.ObjectId;

/**
 * This validates all the inputs that are received from client during API calls
 * If anything is invalid then it throws the corresponding exception
 */
public final class InputVerifier {

    // private constructor to make this class non-instantiable
    private InputVerifier() {
    }

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

    public static void validatePlayerRequestBody(PlayerRequestDto requestBody) {
        if (requestBody.getPlayerName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'playerName' field");
        }
    }

    public static void validateTeamRequestBody(TeamRequestDto requestBody) {
        if (requestBody.getTeamName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'teamName' field");
        }
    }

    public static void validateCreateTeamRequestBody(CreateTeamRequestDto requestBody) {
        if (requestBody.getTeamName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'teamName' field");
        }
        if (requestBody.getPlayerNames() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'playerNames' field");
        }
        if (requestBody.getPlayerNames().size() != Constants.TEAM_SIZE) {
            throw new InvalidRequestBodyException("Team must contain " + Constants.TEAM_SIZE + " players");
        }
    }

    public static void validatePlayMatchRequestBody(PlayMatchRequestDto requestBody) {
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
