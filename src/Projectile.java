import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class Projectile extends DynamicGameObject
{
    private double angle;
    private int damage;


    public Projectile(Point2D position, Image image, Point2D direction, double speed, double angle, int damage)
    {
        super(position, image, direction, speed);
        this.angle = angle;
        this.damage = damage;
    }

    public Projectile(Point2D position, Hitbox hitbox, Image image, double angle, int damage,double speed)
    {
        super(position, hitbox, image, speed);
        this.angle = angle;
        this.damage = damage;

    }
}
