package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.Repository;
import com.tekion.gameofcricket.others.Ball;
import com.tekion.gameofcricket.others.Innings;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.others.Constants;
import com.tekion.gameofcricket.others.Utility;
import com.tekion.gameofcricket.views.MatchView;

import java.util.HashMap;
import java.util.List;

public class MatchService {

    private List<Team> teamsList;
    private Match currentMatch;
    private HashMap<Integer, Integer> runsScoredMap, wicketsTakenMap;

    public void organizeMatch() {
        new TeamService().showListOfTeams();

        teamsList = Repository.getInstance().getTeamsList();
        Team teamA = selectTeam("\nEnter serial number of the first team : ");
        Team teamB = selectTeam("Enter serial number of the second team : ");

        if (teamA != null && teamB != null) {
            Utility.printAndNextLine("\n" + teamA.getTeamName() + " vs " + teamB.getTeamName());
        } else {
            Utility.printAndNextLine("Some error occurred !");
            return;
        }

        // TODO - edge case when both teams are the same

        currentMatch = new Match(teamA, teamB);
        simulateMatch();
        handlePostMatchEvents();
    }

    private void handlePostMatchEvents() {
        runsScoredMap = new HashMap<>();
        wicketsTakenMap = new HashMap<>();

        currentMatch.getFirstInnings().getBallList().forEach(this::updateMatchDataMaps);
        currentMatch.getSecondInnings().getBallList().forEach(this::updateMatchDataMaps);

        addMatchDataToRepository();

        new MatchView().showMatchScoreBoard(currentMatch, runsScoredMap, wicketsTakenMap);
    }

    private Team selectTeam(String prompt) {
        String userInput = Utility.getUserInput(prompt);

        // TODO - check for invalid input

        // corresponding index in teamList = serial number - 1
        return teamsList.get(Integer.parseInt(userInput) - 1);
    }

    private void simulateMatch() {
        Utility.printAndNextLine("Please wait while the match is being played...\n");
        Utility.pauseExecution(2000);

        // simulate first innings - team A bats, team B bowls
        simulateInnings(currentMatch.getFirstInnings(), currentMatch.getTeamA(), currentMatch.getTeamB(),
                Integer.MAX_VALUE);

        // simulate second innings - team B bats, team A bowls
        simulateInnings(currentMatch.getSecondInnings(), currentMatch.getTeamB(), currentMatch.getTeamA(),
                currentMatch.getFirstInnings().getRunsScored() + 1);
    }

    private void simulateInnings(Innings innings, Team battingTeam, Team bowlingTeam, int target) {
        while (innings.getBallList().size() < Constants.MATCH_LENGTH_IN_OVERS * 6) {
            // TODO - past performance based outcome
            // 0..6 --> equal run scored,   7 --> wicket falls
            int outcome = (int) (Math.random() * 8);

            // every batting player bats in order, every bowling player take turn to bowl an over
            int batsmanId = battingTeam.getPlayerIds().get(innings.getWicketsLost());
            int bowlerId = bowlingTeam.getPlayerIds().get((innings.getBallList().size() / 6) % Constants.TEAM_SIZE);

            Ball currentBall = new Ball(batsmanId, bowlerId, outcome);
            updateInnings(innings, currentBall);

            // batting team gets all out or achieves the target
            if ((outcome == 7 && innings.getWicketsLost() == Constants.TEAM_SIZE) ||
                (innings.getRunsScored() >= target)) {
                break;
            }
        }
    }

    private void updateInnings(Innings innings, Ball ball) {
        innings.getBallList().add(ball);
        if (ball.getOutcome() == 7) {
            innings.setWicketsLost(innings.getWicketsLost() + 1);
        } else {
            innings.setRunsScored(innings.getRunsScored() + ball.getOutcome());
        }
    }

    private void addMatchDataToRepository() {
        updatePlayerStats();
        updateTeamStats();
    }

    private void updateMatchDataMaps(Ball ball) {
        if (ball.getOutcome() == 7) {
            wicketsTakenMap.put(ball.getBowlerId(), wicketsTakenMap.getOrDefault(ball.getBowlerId(), 0) + 1);
        } else {
            runsScoredMap.put(ball.getBatsmanId(),
                    runsScoredMap.getOrDefault(ball.getBatsmanId(), 0) + ball.getOutcome());
            // to handle when bowler doesn't take any wicket
            if (!wicketsTakenMap.containsKey(ball.getBowlerId())) {
                wicketsTakenMap.put(ball.getBowlerId(), 0);
            }
        }
    }

    private void updatePlayerStats() {
        PlayerService playerService = new PlayerService();
        runsScoredMap.forEach(playerService::addBattingFigure);
        wicketsTakenMap.forEach(playerService::addBowlingFigure);
    }

    private void updateTeamStats() {
        TeamService teamService = new TeamService();
        if (currentMatch.getFirstInnings().getRunsScored() > currentMatch.getSecondInnings().getRunsScored()) {
            teamService.incrementGamesWon(currentMatch.getTeamA().getTeamId());
            teamService.incrementGamesLost(currentMatch.getTeamB().getTeamId());
        } else if (currentMatch.getFirstInnings().getRunsScored() < currentMatch.getSecondInnings().getRunsScored()) {
            teamService.incrementGamesWon(currentMatch.getTeamB().getTeamId());
            teamService.incrementGamesLost(currentMatch.getTeamA().getTeamId());
        } else {
            teamService.incrementGamesDrawn(currentMatch.getTeamA().getTeamId());
            teamService.incrementGamesDrawn(currentMatch.getTeamB().getTeamId());
        }
        teamService.updateTeamPoints(currentMatch.getTeamA().getTeamId());
        teamService.updateTeamPoints(currentMatch.getTeamB().getTeamId());
    }
}
