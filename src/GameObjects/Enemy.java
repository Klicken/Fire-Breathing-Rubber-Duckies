package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Extends the DynamicGameObject class.
 * Creates an Enemy object and calculates it's direction based on a vector from the seekPlayer() method.
 */

public class Enemy extends DynamicGameObject implements AI {

    /**
     * Creates a new enemy which calculates a vector between the player and this enemy for movement related actions
     * @param image         Image to load into the ImageView
     * @param x             x coordinate
     * @param y             y coordinate
     * @param movementSpeed The speed at which this object moves
     * @param health        The health value to initiallize this object with
     * @param damage        The damage value to initiallize this object with
     * @see DynamicGameObject
     */
    public Enemy(Image image, double x, double y, double movementSpeed, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
    }


    /**
     * Updates the position of the enemy based on simple AI-behaviour.
     * @param time the time between the current and previous frame
     */
    @Override
    public void update(double time) {
        Point2D direction = seekPlayer();
        setPos(getX() +  direction.getX() * movementSpeed * time,
                getY() +  direction.getY() * movementSpeed * time);
    }



    /**
     * Calculates a direction vector for the Enemy to follow based on the players position.
     * The enemy then will seek out the player to damage it on physical contact.
     * @return the aforementioned vector.
     * @see Point2D
     */
    @Override
    public Point2D seekPlayer() {
        return Player.getInstance().getPositon().subtract(getPositon()).normalize();
    }
}

