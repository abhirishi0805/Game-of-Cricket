package com.tekion.gameofcricket.utility;

import com.tekion.gameofcricket.requestbody.CreateTeamRequestDto;
import com.tekion.gameofcricket.requestbody.PlayMatchRequestDto;
import com.tekion.gameofcricket.requestbody.PlayerRequestDto;
import com.tekion.gameofcricket.requestbody.TeamRequestDto;
import com.tekion.gameofcricket.utility.exceptionhandling.InvalidDateException;
import com.tekion.gameofcricket.utility.exceptionhandling.InvalidObjectIdException;
import com.tekion.gameofcricket.utility.exceptionhandling.InvalidRequestBodyException;
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
        if (requestBody.getPlayerName().isBlank()) {
            throw new InvalidRequestBodyException("playerName cannot be blank");
        }
    }

    public static void validateTeamRequestBody(TeamRequestDto requestBody) {
        if (requestBody.getTeamName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'teamName' field");
        }
        if (requestBody.getTeamName().isBlank()) {
            throw new InvalidRequestBodyException("teamName cannot be blank");
        }
    }

    public static void validateCreateTeamRequestBody(CreateTeamRequestDto requestBody) {
        if (requestBody.getTeamName() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'teamName' field");
        }
        if (requestBody.getTeamName().isBlank()) {
            throw new InvalidRequestBodyException("teamName cannot be blank");
        }
        if (requestBody.getPlayerNames() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'playerNames' field");
        }
        if (requestBody.getPlayerNames().size() != Constants.TEAM_SIZE) {
            throw new InvalidRequestBodyException("Team must contain " + Constants.TEAM_SIZE + " players");
        }
        requestBody.getPlayerNames().forEach(playerName -> {
            if (playerName.isBlank()) {
                throw new InvalidRequestBodyException("player name cannot be blank");
            }
        });
    }

    public static void validatePlayMatchRequestBody(PlayMatchRequestDto requestBody) {
        if (requestBody.getTeam1Name() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'team1Name' field");
        }
        if (requestBody.getTeam2Name() == null) {
            throw new InvalidRequestBodyException("Request body must contain 'team2Name' field");
        }
        if (requestBody.getTeam1Name().isBlank() || requestBody.getTeam2Name().isBlank()) {
            throw new InvalidRequestBodyException("teamName cannot be blank");
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
