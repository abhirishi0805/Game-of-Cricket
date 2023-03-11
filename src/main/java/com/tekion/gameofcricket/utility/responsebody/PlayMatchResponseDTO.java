package com.tekion.gameofcricket.utility.responsebody;

import org.springframework.stereotype.Component;

/**
 * This represents the response body returned whenever client requests to play a match
 */
@Component
public final class PlayMatchResponseDTO {
    private String firstInnings;
    private String secondInnings;
    private String result;

    public PlayMatchResponseDTO() {
    }

    public String getFirstInnings() {
        return firstInnings;
    }

    public void setFirstInnings(String firstInnings) {
        this.firstInnings = firstInnings;
    }

    public String getSecondInnings() {
        return secondInnings;
    }

    public void setSecondInnings(String secondInnings) {
        this.secondInnings = secondInnings;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
