import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class GameObject
{
    private int width;
    private int height;
    private Point2D position;
    private Hitbox hitbox;
    private Image image;

    /*
    *   Constructor that creates a GameObject at given position, dimensions and hitbox are set according to
    *   the dimensions of the image-file.
    */

    public GameObject(Point2D position, Image image)
    {
        this.image = image;
        this.width = (int)image.getWidth();
        this.height = (int)image.getHeight();
        this.position = position;
        this.hitbox = new Hitbox(position, this.width, this.height);
    }

    /*
    *   Constructor that creates a GameObject at given position, dimensions are set according to the dimensions
    *   of the image and the hitbox is set independantly. If you for instance want a hitbox of different size then
    *   the GameObjects image.
    */

    public GameObject(Point2D position, Hitbox hitbox, Image image)
    {
        this.image = image;
        this.width = (int)image.getWidth();
        this.height = (int)image.getHeight();
        this.position = position;
        this.hitbox = hitbox;
    }
}
