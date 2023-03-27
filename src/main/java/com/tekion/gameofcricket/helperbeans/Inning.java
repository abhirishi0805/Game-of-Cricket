package com.tekion.gameofcricket.helperbeans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This is a bean to temporarily store innings related data
 * It is non-final as there is a scope to extend it for different innings in different kinds of matches
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Inning {

    private int runsScored = 0;
    private int ballsThrown = 0;
    private int wicketsFallen = 0;

    public Inning() {
    }

    public Inning(int runsScored, int ballsThrown, int wicketsFallen) {
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
