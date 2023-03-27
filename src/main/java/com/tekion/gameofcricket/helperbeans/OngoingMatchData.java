package com.tekion.gameofcricket.helperbeans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * This is a bean to temporarily store match data while it is being played
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class OngoingMatchData {

    @Autowired
    private ApplicationContext applicationContext;
    @Getter
    @Setter
    private Inning firstInning;
    @Getter
    @Setter
    private Inning secondInning;

    public OngoingMatchData() {
    }

    public void resetInnings() {
        firstInning = applicationContext.getBean(Inning.class);
        secondInning = applicationContext.getBean(Inning.class);
    }
}
