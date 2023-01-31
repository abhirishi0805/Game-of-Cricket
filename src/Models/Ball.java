package Models;

public class Ball {
    private final int batsmanID;
    private final int bowlerID;
    private final int outcome;    // 0..6 --> equal runs scored, 7 --> wicket falls

    public Ball(int batsmanID, int bowlerID, int outcome) {
        this.batsmanID = batsmanID;
        this.bowlerID = bowlerID;
        this.outcome = outcome;
    }

    public int getBatsmanID() {
        return batsmanID;
    }

    public int getBowlerID() {
        return bowlerID;
    }

    public int getOutcome() {
        return outcome;
    }
}
