package com.tekion.gameofcricket.utility;

public enum Constants {
    MATCH_LENGTH_IN_OVERS(10), MATCH_LENGTH_IN_BALLS(MATCH_LENGTH_IN_OVERS.getValue() * 6), TEAM_SIZE(5);

    private final int value;

    Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
