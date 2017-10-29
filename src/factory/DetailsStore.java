package factory;

import robot.RobotElement;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Stores robot details
 * and provides methods for putting and getting details
 *
 * @param <E>
 */
public class DetailsStore<E extends RobotElement> {
    private List<E> elements;
    private Logger logger = Logger.getLogger(DetailsStore.class);

    public DetailsStore() {
        elements = new LinkedList<>();
    }

    /**
     * Take robot detail of certain type from the store
     *
     * @param aClass robot detail class
     * @return detail object
     */
    public synchronized E getElement(Class<E> aClass) {
        E element = null;
        if (!elements.isEmpty()) {
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getClass() == aClass) {
                    element = elements.remove(i);
                    logger.info(Thread.currentThread().getName() + " gets " + element.getClass().getName() + " detail");
                    break;
                }
            }
        }
        return element;
    }

    /**
     * Puts detail object
     *
     * @param element robot detail
     */
    public void addElement(E element) {
        elements.add(element);
        logger.info(Thread.currentThread().getName() + " puts " + element.getClass().getName() + " detail");
    }
}
