package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Extends the DynamicGameObject class.
 * Creates a projectile object and calculates it's trajectory using the getDirection() method.
 */

public class Projectile extends DynamicGameObject {
    private Point2D mousePos;
    private Point2D direction;

    /**
     *
     * @param image
     * @param x
     * @param y
     * @param movementSpeed
     * @param mousePos
     * @param health
     * @param damage
     */

    public Projectile(Image image, double x, double y, double movementSpeed, Point2D mousePos, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
        this.mousePos = mousePos;
        direction = getDirection();
    }


    /**
     * Calculates a direction-vector for the projectile to travel based on the players current position
     * and where the mouse was clicked.
     * @return the aforementioned vector.
     */
    private Point2D getDirection() {
        return mousePos.subtract(Player.getInstance().getPositon()).normalize();
    }


    /**
     * Updates the position of the projectile based on it's speed and direction vector after
     * being fired by the player/monster.
     * @param time
     */
    @Override
    public void update(double time) {
        setPos(getX() +  direction.getX() * movementSpeed * time,
                getY() +  direction.getY() * movementSpeed * time);

    }

    /**
     * Calculates a boolean depending on if the projectile is located outside of the screen.
     * @return True if the projectile is located outside of the screen and otherwise false.
     */
    public boolean outOfBounds() {
        return (getX() > 1340 || getX() < -60 || getY() > 780 || getY() < -60);

    }
}
