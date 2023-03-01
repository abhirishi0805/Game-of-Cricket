package com.tekion.gameofcricket.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OngoingMatchData {

    private Innings firstInnings;
    private Innings secondInnings;
    @Autowired
    private ApplicationContext applicationContext;

    public OngoingMatchData() {
    }

    public Innings getFirstInnings() {
        return firstInnings;
    }

    public void setFirstInnings(Innings firstInnings) {
        this.firstInnings = firstInnings;
    }

    public Innings getSecondInnings() {
        return secondInnings;
    }

    public void setSecondInnings(Innings secondInnings) {
        this.secondInnings = secondInnings;
    }

    public void resetInnings() {
        firstInnings = (Innings) applicationContext.getBean("innings");
        secondInnings = (Innings) applicationContext.getBean("innings");
    }
}
