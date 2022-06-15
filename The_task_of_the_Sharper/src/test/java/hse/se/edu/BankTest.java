package hse.se.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void getGiven() {
        assertEquals(0, bank.getGiven());
        int given = 0;
        for (int i = 0; i < 10; i++) {
            given += bank.getCard();
        }
        assertEquals(given, bank.getGiven());
    }

    @Test
    void createPlayer() {
        for (int i = 0; i < 20; i++) {
            Player player = bank.createPlayer();
            player.setId(i);
            assertEquals(player.id, i);
        }
    }

    @Test
    void getRandomPlayer() {
        for (int i = 0; i < 20; i++) {
            Player player = bank.createPlayer();
            player.setId(i);
        }
        Player randomPlayer = bank.getRandomPlayer();
        assert (randomPlayer.id >= 0 && randomPlayer.id < 20);
    }

    @Test
    void createSchuler() {
        for (int i = 0; i < 20; i++) {
            Schuler schuler = bank.createSchuler();
            schuler.setId(i);
            assertEquals(schuler.id, i);
        }
    }

    @Test
    void getCard() {
        for (int i = 0; i < 100; i++) {
            int value = bank.getCard();
            assert (value >= 0 && value <= 10);
        }
    }

    @Test
    void printScores() {
        Player player1 = bank.createPlayer();
        Player player2 = bank.createPlayer();
        Player player3 = bank.createPlayer();
        Player player4 = bank.createPlayer();
        Schuler schuler1 = bank.createSchuler();
        Schuler schuler2 = bank.createSchuler();
        Schuler schuler3 = bank.createSchuler();
        player1.setId(0);
        player2.setId(1);
        player3.setId(2);
        player4.setId(3);
        schuler1.setId(0);
        schuler2.setId(1);
        schuler3.setId(2);
        bank.printScores();
    }
}