package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class DynamicGameObject extends GameObject
{
    double movementSpeed;
    Point2D direction;


    /*
     *  Constructor that creates a GameObject with movementSpeed and velocity vector(vx, vy);
     */

    public DynamicGameObject(Image image, double x, double y, double movementSpeed)
    {
        super(image, x, y);
        this.movementSpeed = movementSpeed;
        direction = new Point2D(0, 0);
    }

    public abstract void update(double time);
}
