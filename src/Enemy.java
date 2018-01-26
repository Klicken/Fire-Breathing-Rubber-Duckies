import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Enemy extends DynamicGameObject
{
    private int health;

    public Enemy(Point2D position, Image image, double speed, int health)
    {
        super(position, image, speed);
        this.health = health;
    }

    public Enemy(Point2D position, Hitbox hitbox, Image image, double speed, int health)
    {
        super(position, hitbox, image, speed);
        this.health = health;
    }
}
