package GameObjects;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class DynamicGameObject extends GameObject {
    double movementSpeed;
    Point2D direction;
    private Health objectHealth;
    private boolean alive;
    private int damage;

    /*
     *  Constructor that creates a GameObject with movementSpeed, direction vector, health and damage;
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

    /*
    *   Abstract method implementesd in subclasses to determine how to update the Objects
    *   in main game-loop.
    */
    public abstract void update(double time);

    /*
    *   If a DynamicGameObject intersects another GameObject, the DGO is repelled back
    *   to ensure objects don't overlap or pass straight through each other.
    */
    public void collisionHandling(GameObject other) {
        if(intersects(other)) {
            double distance = getCenter().distance(other.getCenter());
            double overlap = getImage().getWidth()/2 + other.getImage().getWidth()/2 - distance;
            direction = getCenter().subtract(other.getCenter());
            direction = direction.normalize().multiply(overlap/2);
            point = point.add(direction);
            setPos(point.getX(), point.getY());
        }
    }

    /*
     *   Changes the health value of this object upon collison with another GameObject
     *   The observable health value of this object will then be notified
     */
    public void changeHealth(GameObject other, int healthValue) {
        if (intersects(other))
            objectHealth.setHealth(objectHealth.getHealth() + healthValue);
    }

    public int getDamage() {
        return -damage;
    }

    public boolean getAlive() {
        return alive;
    }
}
