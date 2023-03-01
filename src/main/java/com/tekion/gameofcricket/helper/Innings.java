package com.tekion.gameofcricket.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Innings {

    @Value("0")
    private int runsScored;
    @Value("0")
    private int ballsThrown;
    @Value("0")
    private int wicketsFallen;

    public Innings() {
    }

    public Innings(int runsScored, int ballsThrown, int wicketsFallen) {
        this.runsScored = runsScored;
        this.ballsThrown = ballsThrown;
        this.wicketsFallen = wicketsFallen;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getBallsThrown() {
        return ballsThrown;
    }

    public void setBallsThrown(int ballsThrown) {
        this.ballsThrown = ballsThrown;
    }

    public int getWicketsFallen() {
        return wicketsFallen;
    }

    public void setWicketsFallen(int wicketsFallen) {
        this.wicketsFallen = wicketsFallen;
    }

    public void addBall(int outcome) {
        ++ballsThrown;
        if (outcome == 7) {
            ++wicketsFallen;
        } else {
            runsScored += outcome;
        }
    }
}
