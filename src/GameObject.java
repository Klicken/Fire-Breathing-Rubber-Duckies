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

    public GameObject(Image image)
    {
        super.setImage(image);
        hitbox = new Hitbox(new Point2D(this.getX(), this.getY()), image.getWidth(), image.getHeight());
    }

    public void setPos(Point2D pos)
    {
        this.setX(pos.getX());
        this.setY(pos.getY());
        hitbox.setPos(pos);
    }
}
