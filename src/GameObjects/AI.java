package GameObjects;

import javafx.geometry.Point2D;

/*
 * Abstract methods in the class are used to implement simple AI-behaviour in the Enemy class.
 */
public interface AI {
    Point2D seekPlayer();
}
