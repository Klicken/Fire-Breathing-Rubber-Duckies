import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class DynamicGameObject extends GameObject
{
    private Point2D direction;
    private double speed;

    public DynamicGameObject(Point2D position, Image image, Point2D direction, double speed)
    {
        super(position, image);
        this.direction = direction;
        this.speed = speed;
    }

    public DynamicGameObject(Point2D position, Hitbox hitbox, Image image)
    {
        super(position, hitbox, image);
        this.direction = direction;
        this.speed = speed;
    }
}
