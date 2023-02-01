package Models;

public class Ball {
    private int batsmanID;
    private int bowlerID;
    private int outcome;    // 0..6 --> equal runs scored, 7 --> wicket falls

    // to restrict instantiation without field data
    private Ball() { }

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
