package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.repositories.PlayerRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.tekion.gameofcricket.utility.RandomFactory.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository mockPlayerRepository;
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        playerService = new PlayerServiceImpl(mockPlayerRepository);
    }

    @Test
    void getAllPlayers_with_no_players() {
        List<Player> players = List.of();
        when(mockPlayerRepository.findAll()).thenReturn(players);
        assertEquals("empty list was not returned", playerService.getAllPlayers(), players);
        playerService.getAllPlayers();
    }

    @Test
    void getAllPlayers_with_three_players() {
        List<Player> players = Arrays.asList(generateRandomPlayer(), generateRandomPlayer(), generateRandomPlayer());
        when(mockPlayerRepository.findAll()).thenReturn(players);
        assertEquals("all players were not fetched", playerService.getAllPlayers(), players);
    }

    @Test
    void getPlayerByName_when_player_exists() {
        Player player = generateRandomPlayer();
        when(mockPlayerRepository.findPlayerByPlayerNameEqualsIgnoreCase(player.getPlayerName())).thenReturn(player);
        assertEquals("wrong player returned", player, playerService.getPlayerByName(player.getPlayerName()));
    }

    @Test
    void getPlayerByName_when_player_does_not_exist() {
        String nonExistentPlayerName = generateRandomName();
        Assertions.assertThrows(NoSuchElementException.class, () -> playerService.getPlayerByName(nonExistentPlayerName));
    }

    @Test
    void getPlayerById_when_player_exists() {
        Player player = generateRandomPlayer();
        when(mockPlayerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        assertEquals("wrong player returned", player, playerService.getPlayerById(player.getId()));
    }

    @Test
    void getPlayerById_when_player_does_not_exist() {
        ObjectId nonExistentPlayerId = generateRandomId();
        Assertions.assertThrows(NoSuchElementException.class, () -> playerService.getPlayerById(nonExistentPlayerId));
    }
}