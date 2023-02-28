package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.repositories.PlayerMatchStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchStatServiceImpl implements PlayerMatchStatService {

    @Autowired
    private PlayerMatchStatRepository playerMatchStatRepository;
}
