package GameObjects;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Extends the GameObject class and is abstract. Used to define GameObjects with the properties
 * movement, health, damage and a sense of direction. These properties are then used to determine when objects
 * should come in and out of existance, how much an object should damage other objects of this class and how
 * Health is handled accordingly.
 *
 * @see Health
 * @see GameObject
 */
public abstract class DynamicGameObject extends GameObject {
    double movementSpeed;
    Point2D direction;
    Health objectHealth;
    private boolean alive;
    int damage;

    /**
     * Allocates a new DynamicGameObject. Also adds a changeListener to the
     * Health variable of this particular instance. This is used to determine when an
     * object has lost all of it's health and is then considered dead. This state can then be used
     * to determine when this object should go out of existance.
     *
     * @param image         Image to load into the ImageView
     * @param x             x coordinate
     * @param y             y coordinate
     * @param movementSpeed The speed at which this object moves
     * @param health        The health value to initiallize this object with
     * @param damage        The damage value to initiallize this object with
     * @see GameObject
     */
    DynamicGameObject(Image image, double x, double y, double movementSpeed, int health, int damage) {
        super(image, x, y);
        this.movementSpeed = movementSpeed;
        direction = new Point2D(0, 0);
        objectHealth = new Health();
        objectHealth.setHealth(health);
        alive = true;
        this.damage = damage;

        /*
        *   Adds a changeListener to the integer value wrapped by the health class.
        *   Changes the state of DynamicGameObjects to alive or not, this is used
        *   to remove object instances from the screen.
        */
        objectHealth.healthProperty().addListener((ChangeListener<? super Number>)
                (value, oldValue, newValue) -> {
                    //System.out.println(oldValue);
                    if ((int)newValue <= 0) {
                        alive = false;
                    }
                });
    }

    /**
     * Abstract method implementesd in subclasses to determine how to update the Objects
     * in the GameHandler class.
     *
     * @param time the time between the current and previos frame
     * @see Game.GameHandler
     */
    public abstract void update(double time);

    /**
     * If a DynamicGameObject intersects another GameObject, this object is repelled back
     * to ensure objects don't overlap or pass straight through each other.
     *
     * @param other The other GameObject used for comparison.
     */
    public void collisionHandling(GameObject other) {
        if(intersects(other)) {
            double distance = getCenter().distance(other.getCenter());
            double overlap = getImage().getWidth()/2 + other.getImage().getWidth()/2 - distance;
            direction = getCenter().subtract(other.getCenter());
            direction = direction.normalize().multiply(overlap/2);
            positon = positon.add(direction);
            setPos(positon.getX(), positon.getY());
            }
    }

    /**
     * Knocks this object back by n pixels in relation to the direction it was hit from.
     *
     * @param other The other GameObject used for determining the direction of knockback.
     */
    public void knockBack(GameObject other, int n) {
            direction = getCenter().subtract(other.getCenter());
            direction = direction.normalize().multiply(n);
            positon = positon.add(direction);
            setPos(positon.getX(), positon.getY());
    }

    /**
     * Changes the health value of this object and knocks this object back upon collison with another GameObject.
     * The observable health value of this object will then be notified.
     *
     * @param other         The other GameObject used for determining how much damage should be taken by this object
     * @param healthValue   The health to be removed or added to this object
     * @see Health
     */
    public void changeHealth(GameObject other, int healthValue) {
        if (intersects(other)) {
            objectHealth.setHealth(objectHealth.getHealth() + healthValue);
            if(this instanceof Player){
                knockBack(other, 25);
            }
        }
    }

    /**
     * Gets the damage value of this object instance.
     *
     * @return The value of the damage variable of this object
     */
    public int getDamage() {
        return -damage;
    }

    /**
     * Gets the alive value of the object instance.
     *
     * @return The value of the alive variable
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * Gets the HealthValue of the object instance.
     *
     * @return The value of the HealthValue variable
     */
    public int getHealthValue()
    {
        return objectHealth.getHealth();
    }
}
