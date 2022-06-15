package hse.se.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SchulerTest {
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void takeCard() throws InterruptedException {
        Vector<Thread> threads = new Vector<Thread>();
        for (int i = 0; i < 1; i++) {
            Player player = bank.createPlayer();
            player.setId(i);
            assert (player.takeCard() >= 0 && player.takeCard() <= 10);
            threads.add(new Thread(player));
        }
        for (int i = 0; i < 3; i++) {
            Schuler schuler = bank.createSchuler();
            schuler.setId(i);
            assert (schuler.takeCard() >= 0 && schuler.takeCard() <= 10);
            threads.add(new Thread(schuler));
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.elementAt(i).start();
        }
        try {
            for (int i = 0; i < threads.size(); i++) {
                threads.elementAt(i).join();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return;
        }
    }
}