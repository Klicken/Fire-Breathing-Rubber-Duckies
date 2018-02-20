package GameObjects;

import javafx.geometry.Point2D;

/**
 * Abstract methods in this class are used to implement simple AI-behaviour in the Enemy class.
 *
 * @see Enemy
 */
public interface AI {
    /**
     * Used for implemetning 2D vector caclculations in the Enemy class,
     * specifically for calculating a direction vector to be used for vector additions.
     *
     * @return A 2D vector representing a direction
     */
    Point2D seekPlayer();
}
