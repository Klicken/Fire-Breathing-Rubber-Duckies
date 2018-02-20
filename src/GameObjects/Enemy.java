package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Extends the DynamicGameObject class.
 * Creates an Enemy object and calculates it's direction based on a vector from the seekPlayer() method.
 */

public class Enemy extends DynamicGameObject implements AI {

    /**
     *
     * @param image
     * @param x
     * @param y
     * @param movementSpeed
     * @param health
     * @param damage
     */
    public Enemy(Image image, double x, double y, double movementSpeed, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
    }


    /**
     * Updates the position of the enemy based on simple AI-behaviour.
     * @param time
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
     */
    @Override
    public Point2D seekPlayer() {
        return Player.getInstance().getPositon().subtract(getPositon()).normalize();
    }
}
