package hse.se.edu;

/**
 * Class of Schuler(cheater).
 */
public class Schuler extends Player {
    /**
     * Constructor of schuler.
     *
     * @param bank - bank of game.
     */
    public Schuler(Bank bank) {
        super(bank);
    }

    /**
     * Process of taking card of schuler.
     *
     * @return num of points that schuler take.
     */
    public int takeCard() {
        double probability = Math.random();
        if (probability <= 0.4) {
            int howMuch = 0;
            try {
                Player p = bank.getRandomPlayer();
                long sleepTime = (int) (Math.random() * (300 - 180) + 180);
                howMuch += p.stealBalance();
                balance += howMuch;
                //System.out.println("Schuler #"+id+"steal scores: "+howMuch+"and sleeping "+sleepTime);
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                System.err.println("Exception player#" + id + " :");
                e.printStackTrace(System.err);
            }
            return howMuch;
        } else {
            return super.takeCard();
        }
    }

}
