package robot;

import factory.DetailsStore;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Robot that can produce other robots from robot details
 *
 * @param <E> robot component type
 */
public class Robot<E extends RobotElement> implements Runnable {
    private static Logger logger = Logger.getLogger(Robot.class);
    private RobotElement rightLeg;
    private RobotElement leftLeg;
    private RobotElement head;
    private RobotElement body;
    private RobotElement rightHand;
    private RobotElement leftHand;
    private DetailsStore store;
    private List<RobotLeg> legs = new LinkedList<>();
    private List<RobotHand> hands = new LinkedList<>();
    private List<RobotHead> heads = new LinkedList<>();
    private List<RobotBody> bodies = new LinkedList<>();
    private List<Robot> robots = new LinkedList<>();
    private boolean detailTaken;

    public Robot(RobotElement rightLeg, RobotElement leftLeg, RobotElement head,
                 RobotElement body, RobotElement rightHand, RobotElement leftHand, DetailsStore store) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.body = body;
        this.rightLeg = rightLeg;
        this.leftLeg = leftLeg;
        this.head = head;
        this.store = store;
    }

    /**
     * Method in which current robot object construct other robots
     */
    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            bringElement();
            if (isComplete()) {
                constructRobot();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Robot " + Thread.currentThread().getName() + " has " + heads.size() + " heads, "
                + bodies.size() + " bodies, " + legs.size() + " legs and " + heads.size() + " hands");
        logger.info("Robot " + Thread.currentThread().getName() + " built " + robotCount() + " robots");
    }

    /**
     * Construct a robot from details
     */
    public void constructRobot() {
        Robot robot = new Robot(legs.remove(1), legs.remove(0), heads.remove(0),
                bodies.remove(0), hands.remove(1), hands.remove(0), store);
        robots.add(robot);
    }

    /**
     * Check if count of robot details is enough yet to construct a robot
     *
     * @return true if it's time to construct a robot
     */
    public boolean isComplete() {
        return (legs.size() >= 2 && hands.size() >= 2 && bodies.size() >= 1 && heads.size() >= 1);
    }

    /**
     * According to construction strategy, robot will analyze which detail is need at the moment
     * to construct a robot. Firstly, a robot will bring all the missing details. If it is no suitable at
     * the moment detail in the store, the robot will take any detail. In case there is no
     * detail in the store at the moment, the robot will return with no detail.
     */
    public void bringElement() {
        if (heads.size() < 1) {
            takeDetail(RobotHead.class);
        } else if (bodies.size() < 1) {
            takeDetail(RobotBody.class);
        } else if (hands.size() < 2) {
            takeDetail(RobotHand.class);
        } else if (legs.size() < 2) {
            takeDetail(RobotLeg.class);
        } else {
            if (!detailTaken) {
                takeDetail(RobotHead.class);
            }
            if (!detailTaken) {
                takeDetail(RobotBody.class);
            }
            if (!detailTaken) {
                takeDetail(RobotHand.class);
            }
            if (!detailTaken) {
                takeDetail(RobotLeg.class);
            }
        }
    }

    /**
     * Robot takes certain detail from the store and puts it to a list of corresponding type
     *
     * @param aClass class of robot detail object
     */
    public void takeDetail(Class<? extends RobotElement> aClass) {
        E element = (E) store.getElement(aClass);
        if (element == null) {
            detailTaken = false;
        } else {
            if (aClass == RobotHead.class) {
                heads.add((RobotHead) element);
            } else if (aClass == RobotLeg.class) {
                legs.add((RobotLeg) element);
            } else if (aClass == RobotHand.class) {
                hands.add((RobotHand) element);
            } else if (aClass == RobotBody.class) {
                bodies.add((RobotBody) element);
            }
            detailTaken = true;
        }
    }

    /**
     * Count how much robots were constructed by this robot
     *
     * @return
     */
    public int robotCount() {
        return robots.size();
    }
}
