class CricketGame {
    private final int MATCH_LENGTH_IN_OVERS = 2;
    private Team teamA, teamB;
    private Team battingTeam, bowlingTeam;

    public void setUpGame() {
        System.out.println("Enter first team details --");
        teamA = new Team();
        System.out.println("\n--------------------------------------------------\n");    // for better presentation
        System.out.println("Enter second team details --");
        teamB = new Team();
        System.out.println("\n\n\n-------------------------------------------------------------------------------\n\n\n");     // for better visuals
    }

    public void startMatch() {
        System.out.println("MATCH STARTS");
        toss();
        System.out.println("\nInnings 1 - " + battingTeam.getName() + " will bat & " + bowlingTeam.getName() + " will bowl\n");
        simulateInnings(false);
        swapTeamStatus();
        Utility.pauseExecution(1500);
        System.out.println("\nInnings 2 - Now, " + battingTeam.getName() + " will bat & " + bowlingTeam.getName() + " will bowl\n");
        simulateInnings(true);
        Utility.pauseExecution(1500);
        displayResult();
    }

    private void toss() {
        System.out.println("Toss is taking place...");

        // 0 --> team A bats,   1 --> team B bats
        int tossResults = (int) (Math.random() * 2);

        if(tossResults == 0) {
            battingTeam = teamA;
            bowlingTeam = teamB;
        }
        else {
            battingTeam = teamB;
            bowlingTeam = teamA;
        }
    }

    private void simulateInnings(boolean isChasing) {
        int ballsThrown = 0;

        Utility.pauseExecution(1000);
        System.out.println(battingTeam.getCurrentBatsman().getName() + " has come to bat");

        while (ballsThrown < MATCH_LENGTH_IN_OVERS * 6) {
            // pause momentarily at every ball
            Utility.pauseExecution(1200);

            // 0..6 --> equal run scored,   7 --> wicket falls
            int randomNumber = (int) (Math.random() * 8);
            String message;
            ++ballsThrown;

            if(randomNumber == 7) {
                message = "\t... wicket was lost :-( \t";
                battingTeam.setWicketsLost(battingTeam.getWicketsLost() + 1);
                if(battingTeam.getWicketsLost() < battingTeam.getTEAM_SIZE()) {
                    message = message + "\n" + battingTeam.getCurrentBatsman().getName() + " has come to bat";
                }
            }
            else {
                battingTeam.setRunsScored(battingTeam.getRunsScored() + randomNumber);
                message = switch (randomNumber) {
                    case 0 -> "\t... dot ball";
                    case 4 -> "\t... a FOUR was hit :-)";
                    case 6 -> "\t... a SIX was hit :-)";
                    case 1 -> "\t... " + randomNumber + " run scored";
                    default -> "\t... " + randomNumber + " runs scored";
                };
            }

            System.out.println("" + (ballsThrown / 6) + "." + (ballsThrown % 6) + " - " + battingTeam.getRunsScored() + "/" + battingTeam.getWicketsLost() + message);
            if((battingTeam.getWicketsLost() == battingTeam.getTEAM_SIZE()) || (isChasing && battingTeam.getRunsScored() > bowlingTeam.getRunsScored()))   break;
        }

        showInningsSummary();
    }

    private void swapTeamStatus() {
        Team temp = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
    }

    private void showInningsSummary() {
        System.out.println("\nEnd of Innings !");
        Utility.pauseExecution(500);
        System.out.println(battingTeam.getName() + " : " + battingTeam.getRunsScored() + "/" + battingTeam.getWicketsLost());
    }

    private void displayResult() {
        if(teamA.getRunsScored() > teamB.getRunsScored()) {
            System.out.println("\n\nHURRAY... " + teamA.getName() + " wins!");
        }
        else if(teamA.getRunsScored() < teamB.getRunsScored()) {
            System.out.println("\n\nHURRAY... " + teamB.getName() + " wins!");
        }
        else {
            System.out.println("\n\nThe match ended as a tie");
        }
    }
}
