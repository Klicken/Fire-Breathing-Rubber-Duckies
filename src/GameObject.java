import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameObject extends ImageView
{
    private Hitbox hitbox;

    /*
    *   Constructor that creates a GameObject at given position, dimensions and hitbox are set according to
    *   the dimensions of the image-file.
    */

    public GameObject(Image image, double x, double y)
    {
        super.setImage(image);
        hitbox = new Hitbox(new Point2D(this.getX(), this.getY()), image.getWidth(), image.getHeight());
        setPos(x, y);
    }

    public void setPos(double x, double y)
    {
        this.setX(x);
        this.setY(y);
        hitbox.setPos(new Point2D(x, y));
    }
}
