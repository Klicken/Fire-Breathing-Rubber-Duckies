import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class Projectile extends DynamicGameObject
{
    private Point2D angle;
    private int damage;


    public Projectile(Point2D position, Image image, Point2D direction, double speed, Point2D angle, int damage)
    {
        super(position, image, direction, speed);
        this.angle = angle;
        this.damage = damage;
    }

    public Projectile(Point2D position, Hitbox hitbox, Image image, Point2D angle, int damage,double speed)
    {
        super(position, hitbox, image, speed);
        this.angle = angle;
        this.damage = damage;

    }
}
