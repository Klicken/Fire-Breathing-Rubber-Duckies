package GameObjects;

import Game.Sound;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


/**
 * Extends the DynamicGameObject class.
 * Creates a projectile object and calculates it's trajectory using the getDirection() method.
 *
 * @author Anton Wester
 * @version 2018-02-21
 */

public class Projectile extends DynamicGameObject {
    private Point2D mousePos;
    private Point2D direction;

    /**
     * Creates a new Projectile which calculates a vector between the player and the mouse position
     * to move in a certain direction.
     *
     * @param image         Image to load into the ImageView
     * @param x             x coordinate
     * @param y             y coordinate
     * @param movementSpeed The speed at which this object moves
     * @param mousePos      A vector for the position of the mouse when a projectile is created
     * @param health        The health value to initiallize this object with
     * @param damage        The damage value to initiallize this object with
     * @see DynamicGameObject
     */
    public Projectile(Image image, double x, double y, double movementSpeed, Point2D mousePos, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
        this.mousePos = mousePos;
        direction = getDirection();
        new Sound("shot.mp3", 0.15, 1, 1);
    }

    /**
     * Calculates a direction-vector for the projectile to travel based on the players current position
     * and where the mouse was clicked.
     *
     * @return The aforementioned vector.
     */
    private Point2D getDirection() {
        return mousePos.subtract(Player.getInstance().getPositon()).normalize();
    }
    
    /**
     * Updates the position of the projectile based on it's speed and direction vector after
     * being fired by the player/monster.
     *
     * @param time The time between the current and previous frame
     */
    @Override
    public void update(double time) {
        setPos(getX() +  direction.getX() * movementSpeed * time,
                getY() +  direction.getY() * movementSpeed * time);

    }

    /**
     * Calculates a boolean depending on if the projectile is located outside of the screen.
     *
     * @return True if the projectile is located outside of the screen and otherwise false.
     */
    public boolean outOfBounds() {
        return (getX() > 1340 || getX() < -60 || getY() > 780 || getY() < -60);

    }
}
