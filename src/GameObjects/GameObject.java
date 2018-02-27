package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The top class of the GameObject hierarchy.
 * Extends the ImageView class in order to make use of the JavaFX node system, the node system is then used to
 * render and update all the objects in the main game-loop found in the GameHandler class.
 *
 * A positon is also added to the class to keep track of it's 2D position vector.
 *
 * @see ImageView
 * @see Point2D
 *
 * @author Tobias Rosengren
 * @version 2018-02-21
 */
public class GameObject extends ImageView {
    /** The top left corner position vector of this object*/
    Point2D positon;

    /**
     * Allocates a new GameObject with the associated image loaded into the ImageView and
     * sets the cartesian coordinates of the Object to x and y.
     *
     * @param image Image to load into the ImageView
     * @param x     x coordinate
     * @param y     y coordinate
     * @see ImageView
     */
    GameObject(Image image, double x, double y) {
        super.setImage(image);
        setX(x);
        setY(y);
        positon = new Point2D(x, y);
    }

    /**
     * Sets the position of the GameObject (The top-left corner)
     * and updates it's position vector.
     *
     * @param x     The new x coordinate
     * @param y     The new y coordinate
     */
    void setPos(double x, double y) {
        setX(x);
        setY(y);
        positon = new Point2D(x, y);
    }

    /**
     * Returns the 2D vector that represents this objects current position.
     *
     * @return The current position of this object
     * @see Point2D
     */
    public Point2D getPositon() {
        return positon;
    }

    /**
     * Calculates the center positon of the Image assoiated with the GameObject.
     *
     * @return The center of this GameObject represented by a 2D vector
     * @see Point2D
     */
    public Point2D getCenter() {
        return new Point2D(getX() + getImage().getWidth()/2, getY() + getImage().getWidth()/2);
    }

    /**
     * Determines if two gameObjects overlap based on their radius.
     * The radius is calculated according to the width of the image.
     *
     * @param other The GameObject used for comparison
     * @return True if this object overlaps with the other GameObject
     */
    public boolean intersects(GameObject other) {
        double distance = getCenter().distance(other.getCenter());
        return (distance < getImage().getWidth()/2 + other.getImage().getWidth()/2) ;
    }
}
