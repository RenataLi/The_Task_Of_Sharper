package hse.se.edu;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Bank bank;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @org.junit.jupiter.api.Test
    void setId() {
        Player player1 = bank.createPlayer();
        assertEquals(player1.id, 0);
        for (int i = 0; i < 100; i++) {
            Player player = bank.createPlayer();
            player.setId(i);
            assertEquals(player.id, i);
        }
    }

    @org.junit.jupiter.api.Test
    void takeCard() {
        for (int i = 0; i < 100; i++) {
            Player player = bank.createPlayer();
            player.setId(i);
            assert (player.takeCard() >= 0 && player.takeCard() <= 10);
        }
    }
    @org.junit.jupiter.api.Test
    void stealBalance() {
        Vector<Thread> threads = new Vector<Thread>();
        for (int i = 0; i < 100; i++) {
            Schuler schuler = bank.createSchuler();
            schuler.setId(i);
            assert (schuler.stealBalance() >= 0 && schuler.stealBalance() <= 8);
            threads.add(new Thread(schuler));
        }
    }
}