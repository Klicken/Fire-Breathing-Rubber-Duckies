import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class DynamicGameObject extends GameObject
{
    private Point2D direction;
    private double speed;

    public DynamicGameObject(Point2D position, Image image, double speed)
    {
        super(position, image);
        this.direction = new Point2D(0,0);
        this.speed = speed;
    }

    /*
     *   Constructor that creates a DynamicGameObject at given position, dimensions are set according to the dimensions
     *   of the image and the hitbox is set independantly. If you for instance want a hitbox of different size then
     *   the DynamicGameObjects image.
     */

    public DynamicGameObject(Point2D position, Hitbox hitbox, Image image, double speed)
    {
        super(position, hitbox, image);
        this.direction = new Point2D(0,0);;
        this.speed = speed;
    }
}
