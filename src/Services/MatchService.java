package Services;

import Models.Ball;
import Models.Innings;
import Models.Match;
import Models.Team;
import Others.Constants;
import Others.Utility;
import Views.MatchView;
import Views.TeamView;

import java.util.ArrayList;
import java.util.HashMap;

public class MatchService {
    private static MatchService matchServiceInstance;

    private MatchService() { }

    public static MatchService getInstance() {
        if (matchServiceInstance == null) {
            matchServiceInstance = new MatchService();
        }
        return matchServiceInstance;
    }

    public void manageMatch(ArrayList<Team> teamsList) {
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

        addMatchDataToRepository(matchToPlay);
    }

    private Team selectTeam(String prompt, ArrayList<Team> teamsList) {
        String userInput = Utility.getUserInput(prompt);

        // TODO - check for invalid input

        // corresponding index in teamList = serial number - 1
        return teamsList.get(Integer.parseInt(userInput) - 1);
    }

    private void simulateMatch(Match match) {
        // simulate first innings - team A bats, team B bowls
        simulateInnings(match.getFirstInnings(), match.getTeamA(), match.getTeamB(), Integer.MAX_VALUE);

        // simulate second innings - team B bats, team A bowls
        simulateInnings(match.getSecondInnings(), match.getTeamB(), match.getTeamA(), match.getFirstInnings().getRunsScored() + 1);

        Utility.printAndNextLine("Please wait while the match is being played...\n");
        Utility.pauseExecution(2000);

        MatchView.getInstance().showMatchSummary(match);
    }

    private void simulateInnings(Innings innings, Team battingTeam, Team bowlingTeam, int target) {
        while (innings.getBallList().size() < Constants.MATCH_LENGTH_IN_OVERS * 6) {
            // TODO - past performance based outcome
            // 0..6 --> equal run scored,   7 --> wicket falls
            int outcome = (int) (Math.random() * 8);

            // every batting player bats in order, every bowling player take turn to bowl an over
            int batsmanID = battingTeam.getPlayerIDs().get(innings.getWicketsLost());
            int bowlerID = bowlingTeam.getPlayerIDs().get((innings.getBallList().size() / 6) % Constants.TEAM_SIZE);

            Ball currentBall = new Ball(batsmanID, bowlerID, outcome);
            updateInnings(innings, currentBall);

            // batting team gets all out or achieves the target
            if ((outcome == 7 && innings.getWicketsLost() == Constants.TEAM_SIZE) || (innings.getRunsScored() >= target))
                break;
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

    private void addMatchDataToRepository(Match matchPlayed) {
        HashMap<Integer, Integer> runsScoredMap = new HashMap<>();
        HashMap<Integer, Integer> wicketsTakenMap = new HashMap<>();

        matchPlayed.getFirstInnings().getBallList().forEach((ball) -> updateMatchDataMaps(ball, runsScoredMap, wicketsTakenMap));
        matchPlayed.getSecondInnings().getBallList().forEach((ball) -> updateMatchDataMaps(ball, runsScoredMap, wicketsTakenMap));

        PlayerService playerService = PlayerService.getInstance();
        runsScoredMap.forEach(playerService::addBattingFigure);
        wicketsTakenMap.forEach(playerService::addBowlingFigure);

        updateTeamStats(matchPlayed);
    }

    private void updateMatchDataMaps(Ball ball, HashMap<Integer, Integer> runsScoredMap, HashMap<Integer, Integer> wicketsTakenMap) {
        if (ball.getOutcome() == 7) {
            wicketsTakenMap.put(ball.getBowlerID(), wicketsTakenMap.getOrDefault(ball.getBowlerID(), 0) + 1);
        } else {
            runsScoredMap.put(ball.getBatsmanID(), runsScoredMap.getOrDefault(ball.getBatsmanID(), 0) + ball.getOutcome());
            // to handle when bowler doesn't take any wicket
            if (!wicketsTakenMap.containsKey(ball.getBowlerID())) {
                wicketsTakenMap.put(ball.getBowlerID(), 0);
            }
        }
    }

    private void updateTeamStats(Match matchPlayed) {
        TeamService teamService = TeamService.getInstance();
        if (matchPlayed.getFirstInnings().getRunsScored() > matchPlayed.getSecondInnings().getRunsScored()) {
            teamService.incrementGamesWon(matchPlayed.getTeamA().getTeamID());
            teamService.incrementGamesLost(matchPlayed.getTeamB().getTeamID());
        } else if (matchPlayed.getFirstInnings().getRunsScored() < matchPlayed.getSecondInnings().getRunsScored()) {
            teamService.incrementGamesWon(matchPlayed.getTeamB().getTeamID());
            teamService.incrementGamesLost(matchPlayed.getTeamA().getTeamID());
        } else {
            teamService.incrementGamesDrawn(matchPlayed.getTeamA().getTeamID());
            teamService.incrementGamesDrawn(matchPlayed.getTeamB().getTeamID());
        }
    }
}
