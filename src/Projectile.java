import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class Projectile extends DynamicGameObject
{
    private double angle;
    private int damage;

    public Projectile(Point2D position, Image image, double speed, int damage)
    {
        super(position, image, speed);
        this.damage = damage;
    }

    public Projectile(Point2D position, Hitbox hitbox, Image image, double speed, int damage)
    {
        super(position, hitbox, image, speed);
        this.damage = damage;
    }
}
