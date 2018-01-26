import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Player extends DynamicGameObject
{
    private int health;

    public Player(Point2D position, Image image, double speed, int health)
    {
        super(position, image, speed);
        this.health = health;
    }

    public Player(Point2D position, Hitbox hitbox, Image image, double speed, int health)
    {
        super(position, hitbox, image, speed);
        this.health = health;
    }
}
