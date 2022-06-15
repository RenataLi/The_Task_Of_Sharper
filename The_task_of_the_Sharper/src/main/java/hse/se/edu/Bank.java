package hse.se.edu;

import java.util.Vector;

import static java.lang.Math.random;

/**
 * A class of bank that stores data on cheaters and honest players.
 * Performs the function of a croupier.
 */
public class Bank {
    /**
     * Honest players.
     */
    Vector<Player> players;
    /**
     * Cheaters.
     */
    Vector<Schuler> schulers;
    /**
     * Shows the number of points that are given.
     */
    long given;

    /**
     * Constructor of Bank.
     */
    public Bank() {
        given = 0;
        players = new Vector<Player>();
        schulers = new Vector<Schuler>();
    }

    /**
     * Getter of given.
     *
     * @return num of poiunts that given.
     */
    long getGiven() {
        return given;
    }

    /**
     * Creating a honest player.
     *
     * @return player.
     */
    Player createPlayer() {
        Player player = new Player(this);
        player.setId(players.size());
        players.add(player);
        return player;
    }

    /**
     * Getting random player of random count of points.
     *
     * @return player.
     */
    Player getRandomPlayer() {
        int index = (int) (Math.random() * (players.size() - 0.00001));
        return players.elementAt(index);
    }

    /**
     * Creating a cheater(Schuler).
     *
     * @return - new schuler.
     */
    Schuler createSchuler() {
        Schuler schuler = new Schuler(this);
        schuler.setId(schulers.size());
        schulers.add(schuler);
        return schuler;
    }

    /**
     * Pricess of getting card of a player.
     *
     * @return - value of card.
     */
    public synchronized int getCard() {
        int value = (int) (random() * 10.9999);
        given += value;
        return value;
    }

    /**
     * Printing scores of players.
     */
    public void printScores() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player #" + i + " : " + players.elementAt(i).getBalance());
        }
        for (int i = 0; i < schulers.size(); i++) {
            System.out.println("Schuler #" + i + " : " + schulers.elementAt(i).getBalance());
        }
        Player maxPlayer = new Player(this);
        Schuler maxShuler = new Schuler(this);
        for (Player player : players) {
            if (player.balance > maxPlayer.balance) {
                maxPlayer.balance = player.balance;
                maxPlayer.id = player.id;
            }
        }
        for (Schuler schuler : schulers) {
            if (schuler.balance > maxShuler.balance) {
                maxShuler.balance = schuler.balance;
                maxShuler.id = schuler.id;
            }
        }
        int maxBalance = 0;
        // Printing max scores from all schulers ond players.
        if (maxShuler.balance > maxPlayer.balance) {
            maxBalance = maxShuler.balance;
        }
        else if (maxShuler.balance < maxPlayer.balance) {
            maxBalance = maxPlayer.balance;
        }
        for (Player player:players) {
            if(player.balance==maxBalance){
                System.out.println("Player #" + maxPlayer.id + " win!");
            }
        }
        for (Schuler schuler:schulers) {
            if(schuler.balance==maxBalance){
                System.out.println("Shuler #" + maxShuler.id + " win!");
            }
        }
    }
}
