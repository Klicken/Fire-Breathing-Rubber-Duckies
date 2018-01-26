import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Player extends DynamicGameObject
{
    private int health;

    /*
     *   Creates a Player object with a given position and speed. The hitbox is created according to the
     *   dimensions of the loaded image. The speed variable is used for multiplication with the direction vector.
     *   Health is set independantly.
     */

    public Player(Point2D position, Image image, double speed, int health)
    {
        super(position, image, speed);
        this.health = health;
    }

    /*
     *   Creates an enemy object with a given position and speed. Creates a custom hitbox.
     *   The speed variable is used for multiplication with the direction vector.
     *   Health is set independantly.
     */

    public Player(Point2D position, Hitbox hitbox, Image image, double speed, int health)
    {
        super(position, hitbox, image, speed);
        this.health = health;
    }
}
