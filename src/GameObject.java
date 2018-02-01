import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameObject extends ImageView
{
    private Hitbox hitbox;
    private Point2D point;

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
        hitbox = new Hitbox(point, image.getWidth(), image.getHeight());
    }

    /*
     *  Sets the position of the GameObject (The top-left corner).
     */

    public void setPos(double x, double y)
    {
        setX(x);
        setY(y);
        point = new Point2D(x, y);
        hitbox.setPos(point);
    }

    public Hitbox getHitbox()
    {
        return hitbox;
    }

    public Point2D getPoint()
    {
        return point;
    }
}
