package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.awt.*;

public abstract class DynamicGameObject extends GameObject {
    double movementSpeed;
    Point2D direction;


    /*
     *  Constructor that creates a GameObject with movementSpeed and velocity vector(vx, vy);
     */

    public DynamicGameObject(Image image, double x, double y, double movementSpeed) {
        super(image, x, y);
        this.movementSpeed = movementSpeed;
        direction = new Point2D(0, 0);
    }

    public abstract void update(double time);

    public void collisionDetection(GameObject other)
    {
        double distance = getCenter().distance(other.getCenter());
        if(distance < getImage().getWidth()/2 + other.getImage().getWidth()/2)
        {
            double overlap = getImage().getWidth()/2 + other.getImage().getWidth()/2 - distance;
            direction = getCenter().subtract(other.getCenter());
            direction = direction.normalize().multiply(overlap/2);
            point = point.add(direction);
            setPos(point.getX(), point.getY());

        }
    }
}
