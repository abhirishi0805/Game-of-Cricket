package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.PlayerMatchStat;

public interface PlayerMatchStatService {

    public void updateBattingFigure(PlayerMatchStat playerMatchStat, int outcome);

    public void updateBowlingFigure(PlayerMatchStat playerMatchStat, int outcome);

    void addPlayerMatchStat(PlayerMatchStat playerMatchStat);
}
