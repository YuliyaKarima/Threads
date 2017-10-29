package factory;

import java.util.Random;

/**
 * Class produces new Robot elements in separate thread
 */
public class DetailsSupplier implements Runnable {
    private Random random = new Random(4);
    private DetailsStore store;
    private int limit;

    public DetailsSupplier(DetailsStore store, int limit) {
        this.limit = limit;
        this.store = store;
    }

    @Override
    public void run() {
        while (limit > 0) {
            for (int i = 0; i < 4; i++) {
                switch (random.nextInt(4)) {
                    case (0):
                        store.addElement(RobotDetailsFactory.getRobotBody());
                        break;
                    case (1):
                        store.addElement(RobotDetailsFactory.getRobotHead());
                        break;
                    case (2):
                        store.addElement(RobotDetailsFactory.getRobotHand());
                        break;
                    case (3):
                        store.addElement(RobotDetailsFactory.getRoboLeg());
                        break;
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            limit -= 4;
        }
    }
}
