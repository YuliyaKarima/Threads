import factory.DetailsSupplier;
import factory.RobotDetailsFactory;
import factory.DetailsStore;
import robot.Robot;

public class Main {
    public static void main(String[] args) {

        DetailsStore store = new DetailsStore();

        Runnable firstRobot = new Robot(RobotDetailsFactory.getRoboLeg(), RobotDetailsFactory.getRoboLeg(),
                RobotDetailsFactory.getRobotHead(), RobotDetailsFactory.getRobotBody(),
                RobotDetailsFactory.getRobotHand(), RobotDetailsFactory.getRobotHand(), store);
        Runnable secondRobot = new Robot(RobotDetailsFactory.getRoboLeg(), RobotDetailsFactory.getRoboLeg(),
                RobotDetailsFactory.getRobotHead(), RobotDetailsFactory.getRobotBody(),
                RobotDetailsFactory.getRobotHand(), RobotDetailsFactory.getRobotHand(), store);
        Runnable thisdRobot = new Robot(RobotDetailsFactory.getRoboLeg(), RobotDetailsFactory.getRoboLeg(),
                RobotDetailsFactory.getRobotHead(), RobotDetailsFactory.getRobotBody(),
                RobotDetailsFactory.getRobotHand(), RobotDetailsFactory.getRobotHand(), store);
        Runnable factory = new DetailsSupplier(store, 140);

        Thread robotOne = new Thread(firstRobot, "First Robot");
        Thread robotTwo = new Thread(secondRobot, "Second Robot");
        Thread robotThree = new Thread(thisdRobot, "Third Robot");
        Thread factoryThread = new Thread(factory, "factory");

        factoryThread.start();
        robotOne.start();
        robotTwo.start();
        robotThree.start();
    }
}
