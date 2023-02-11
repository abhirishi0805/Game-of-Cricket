package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.others.Ball;
import com.tekion.gameofcricket.others.Innings;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.others.Constants;
import com.tekion.gameofcricket.others.Utility;
import com.tekion.gameofcricket.views.MatchView;
import com.tekion.gameofcricket.views.TeamView;

import java.util.HashMap;
import java.util.List;

public class MatchService {

    private static MatchService matchService;

    private MatchService() {
    }

    public static MatchService getInstance() {
        if (matchService == null) {
            matchService = new MatchService();
        }
        return matchService;
    }

    public void organizeMatch(List<Team> teamsList) {
        TeamView.getInstance().showListOfTeams(teamsList);

        Team teamA = selectTeam("\nEnter serial number of the first team : ", teamsList);
        Team teamB = selectTeam("Enter serial number of the second team : ", teamsList);

        if (teamA != null && teamB != null) {
            Utility.printAndNextLine("\n" + teamA.getTeamName() + " vs " + teamB.getTeamName());
        } else {
            Utility.printAndNextLine("Some error occurred !");
            return;
        }

        // TODO - edge case when both teams are the same

        Match matchToPlay = new Match(teamA, teamB);
        simulateMatch(matchToPlay);
        handlePostMatchEvents(matchToPlay);
    }

    private void handlePostMatchEvents(Match matchPlayed) {
        HashMap<Integer, Integer> runsScoredMap = new HashMap<>();
        HashMap<Integer, Integer> wicketsTakenMap = new HashMap<>();

        matchPlayed.getFirstInnings().getBallList()
                   .forEach((ball) -> updateMatchDataMaps(ball, runsScoredMap, wicketsTakenMap));
        matchPlayed.getSecondInnings().getBallList()
                   .forEach((ball) -> updateMatchDataMaps(ball, runsScoredMap, wicketsTakenMap));

        addMatchDataToRepository(matchPlayed, runsScoredMap, wicketsTakenMap);

        MatchView.getInstance().showMatchScoreBoard(matchPlayed, runsScoredMap, wicketsTakenMap);
    }

    private Team selectTeam(String prompt, List<Team> teamsList) {
        String userInput = Utility.getUserInput(prompt);

        // TODO - check for invalid input

        // corresponding index in teamList = serial number - 1
        return teamsList.get(Integer.parseInt(userInput) - 1);
    }

    private void simulateMatch(Match match) {
        Utility.printAndNextLine("Please wait while the match is being played...\n");
        Utility.pauseExecution(2000);

        // simulate first innings - team A bats, team B bowls
        simulateInnings(match.getFirstInnings(), match.getTeamA(), match.getTeamB(), Integer.MAX_VALUE);

        // simulate second innings - team B bats, team A bowls
        simulateInnings(match.getSecondInnings(), match.getTeamB(), match.getTeamA(),
                match.getFirstInnings().getRunsScored() + 1);
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

    private void addMatchDataToRepository(Match matchPlayed, HashMap<Integer, Integer> runsScoredMap,
                                          HashMap<Integer, Integer> wicketsTakenMap) {
        updatePlayerStats(runsScoredMap, wicketsTakenMap);
        updateTeamStats(matchPlayed);
    }

    private void updateMatchDataMaps(Ball ball, HashMap<Integer, Integer> runsScoredMap,
                                     HashMap<Integer, Integer> wicketsTakenMap) {
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

    private void updatePlayerStats(HashMap<Integer, Integer> runsScoredMap, HashMap<Integer, Integer> wicketsTakenMap) {
        PlayerService playerService = PlayerService.getInstance();
        runsScoredMap.forEach(playerService::addBattingFigure);
        wicketsTakenMap.forEach(playerService::addBowlingFigure);
    }

    private void updateTeamStats(Match matchPlayed) {
        TeamService teamService = TeamService.getInstance();
        if (matchPlayed.getFirstInnings().getRunsScored() > matchPlayed.getSecondInnings().getRunsScored()) {
            teamService.incrementGamesWon(matchPlayed.getTeamA().getTeamId());
            teamService.incrementGamesLost(matchPlayed.getTeamB().getTeamId());
        } else if (matchPlayed.getFirstInnings().getRunsScored() < matchPlayed.getSecondInnings().getRunsScored()) {
            teamService.incrementGamesWon(matchPlayed.getTeamB().getTeamId());
            teamService.incrementGamesLost(matchPlayed.getTeamA().getTeamId());
        } else {
            teamService.incrementGamesDrawn(matchPlayed.getTeamA().getTeamId());
            teamService.incrementGamesDrawn(matchPlayed.getTeamB().getTeamId());
        }
        teamService.updateTeamPoints(matchPlayed.getTeamA().getTeamId());
        teamService.updateTeamPoints(matchPlayed.getTeamB().getTeamId());
    }
}
