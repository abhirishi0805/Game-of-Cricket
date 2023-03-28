package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        playerService = new PlayerServiceImpl(playerRepository);
    }

    @Test
    void getAllPlayers() {
        playerService.getAllPlayers();
        verify(playerRepository).findAll();
    }

    @Test
    void getPlayerByName() {
        Player player = new Player("Rohit");
        when(playerRepository.findPlayerByPlayerNameEqualsIgnoreCase("Rohit")).thenReturn(player);
        assertEquals("get player by name", player, playerService.getPlayerByName("Rohit"));
    }

    @Test
    void getPlayerById() {
        Player player = new Player("Rohit");
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        assertEquals("get player by id", player, playerService.getPlayerById(player.getId()));
    }
}