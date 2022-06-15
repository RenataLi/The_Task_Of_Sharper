package hse.se.edu;

import java.lang.Thread;

/**
 * Class of honest player.
 */
public class Player implements Runnable {

    /**
     * rounded time.
     */
    public static final int roundTime = 10;
    /**
     * instance of Bank.
     */
    protected Bank bank;
    /**
     * Id of player.
     */
    protected int id;

    /**
     * Constructor of player.
     *
     * @param bank bank.
     */
    public Player(hse.se.edu.Bank bank) {
        this.bank = bank;
    }

    /**
     * Setter of Id.
     *
     * @param value - id.
     */
    public void setId(int value) {
        id = value;
    }

    /**
     * Process of taking card of player.
     *
     * @return number of points of card.
     */
    public int takeCard() {
        int howMuch = bank.getCard();
        balance += howMuch;
        //System.out.println("Player #"+id+" take scores:" +howMuch);
        return howMuch;
    }

    /**
     * Current balance of player.
     */
    protected int balance;

    /**
     * Game of player.
     */
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - startTime < roundTime * 1000) {
                int waitTime = (int) (Math.random() * 100 + 100);
                Thread.sleep(waitTime);
                //System.out.println("Player #"+id+" is waiting:" +waitTime);
                takeCard();
            }
        } catch (Exception e) {
            System.err.println("Exception player#" + id + " :");
            e.printStackTrace(System.err);
        }
    }

    /**
     * Getter of balance.
     *
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Stealing balance,that is calling by schuler!
     *
     * @return amount of points that steal s schuler from current honest player.
     */
    public synchronized int stealBalance() {
        int ammount = (int) (Math.random() * 8.999);
        if (ammount > balance) {
            ammount = balance;
        }
        balance -= ammount;
        return ammount;
    }

}
