package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameObject extends ImageView
{
    Point2D point;

    /*
     *  Constructor that creates an object at given position, dimensions and hitbox are set according to
     *  the dimensions of the image-file.
     */

    public GameObject(Image image, double x, double y)
    {
        super.setImage(image);
        setX(x);
        setY(y);
        point = new Point2D(x, y);
    }

    /*
     *  Sets the position of the GameObject (The top-left corner).
     */

    public void setPos(double x, double y)
    {
        setX(x);
        setY(y);
        point = new Point2D(x, y);
    }

    public Point2D getPoint()
    {
        return point;
    }
}
