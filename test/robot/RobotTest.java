package robot;

import factory.DetailsStore;
import factory.RobotDetailsFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotTest {
    DetailsStore store;
    Runnable firstRobot;
    Runnable secondRobot;
    Thread robotOneThread;
    Thread robotTwoThread;
    int robotCount;

    @Before
    public void init() {
        store = new DetailsStore();
        robotCount = 5;
        for (int i = 0; i < robotCount; i++) {
            store.addElement(RobotDetailsFactory.getRobotHead());
            store.addElement(RobotDetailsFactory.getRobotHand());
            store.addElement(RobotDetailsFactory.getRobotHand());
            store.addElement(RobotDetailsFactory.getRobotBody());
            store.addElement(RobotDetailsFactory.getRoboLeg());
            store.addElement(RobotDetailsFactory.getRoboLeg());
        }
        firstRobot = new Robot(RobotDetailsFactory.getRoboLeg(), RobotDetailsFactory.getRoboLeg(),
                RobotDetailsFactory.getRobotHead(), RobotDetailsFactory.getRobotBody(),
                RobotDetailsFactory.getRobotHand(), RobotDetailsFactory.getRobotHand(), store);
        robotOneThread = new Thread(firstRobot, "Robot one");
        secondRobot = new Robot(RobotDetailsFactory.getRoboLeg(), RobotDetailsFactory.getRoboLeg(),
                RobotDetailsFactory.getRobotHead(), RobotDetailsFactory.getRobotBody(),
                RobotDetailsFactory.getRobotHand(), RobotDetailsFactory.getRobotHand(), store);
        robotTwoThread = new Thread(secondRobot, "Robot two");
    }

    @Test
    public void run() throws Exception {
    }

    @Test
    public void twoRobotsMustTogetherCountCertainRobotNumber() throws Exception {
        //given
        //when
        robotOneThread.start();
        robotTwoThread.start();
        robotOneThread.join();
        robotTwoThread.join();
        //than
        int firstActual = ((Robot) firstRobot).robotCount();
        int secondActual = ((Robot) secondRobot).robotCount();
        assertEquals("Test failed", robotCount, firstActual+secondActual);
    }

}