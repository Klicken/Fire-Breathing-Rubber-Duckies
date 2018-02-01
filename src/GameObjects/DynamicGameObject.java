package GameObjects;

import javafx.scene.image.Image;

public abstract class DynamicGameObject extends GameObject
{
    protected double movementSpeed;
    protected double vx, vy;

    /*
     *  Constructor that creates a GameObject with movementSpeed and velocity vector(vx, vy);
     */

    public DynamicGameObject(Image image, double x, double y, double movementSpeed)
    {
        super(image, x, y);
        this.movementSpeed = movementSpeed;
        vx = 0;
        vy = 0;
    }

    public abstract void update(double time);
}
