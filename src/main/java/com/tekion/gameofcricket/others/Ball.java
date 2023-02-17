package com.tekion.gameofcricket.others;

public class Ball {

    private int batsmanId;
    private int bowlerId;
    private int outcome;    // 0..6 --> equal runs scored, 7 --> wicket falls

    // to restrict instantiation without field data
    private Ball() {
    }

    public Ball(int batsmanId, int bowlerId, int outcome) {
        this.batsmanId = batsmanId;
        this.bowlerId = bowlerId;
        this.outcome = outcome;
    }

    public int getBatsmanId() {
        return batsmanId;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public int getOutcome() {
        return outcome;
    }
}
