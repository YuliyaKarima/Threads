package factory;

import robot.*;

/**
 * Provides static methods for getting Robot elements
 */
public class RobotDetailsFactory {

    public static RobotElement getRobotBody() {
        return new RobotBody();
    }

    public static RobotElement getRobotHand() {
        return new RobotHand();
    }

    public static RobotElement getRobotHead() {
        return new RobotHead();
    }

    public static RobotElement getRoboLeg() {
        return new RobotLeg();
    }
}
