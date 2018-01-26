import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class Projectile extends DynamicGameObject
{
    private double angle;
    private int damage;

    /*
     *   Constructor that creates a Projectile object with a given damage value.
     *   Position, dimensions and hitbox are set according to
     *   the dimensions of the image-file.
     *   speed is set independantly.
     */

    public Projectile(Point2D position, Image image, double speed, int damage)
    {
        super(position, image, speed);
        this.damage = damage;
    }

    /*
     *   Constructor that creates a Projectile object with a given damage value.
     *  Position and dimensions are set according to the dimensions
     *   of the image, speed and hitbox are set independantly. If you for instance want a hitbox of different size then
     *   the Projectile image.
     */
    public Projectile(Point2D position, Hitbox hitbox, Image image, double speed, int damage)
    {
        super(position, hitbox, image, speed);
        this.damage = damage;
    }
}
