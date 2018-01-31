import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class DynamicGameObject extends GameObject
{
    private double movementSpeed;
    private Point2D direction;

    public DynamicGameObject(Image image, double movementSpeed)
    {
        super(image);
        this.movementSpeed = movementSpeed;
        direction = new Point2D(0,0);
    }

    public void update(double time)
    {
        setPos(new Point2D(this.getX(), this.getY()).add(direction).multiply(movementSpeed));
    }

    public void setDirectionX(double x)
    {
        direction = new Point2D(x, direction.getY());
    }
    public void setDirectionY(double y)
    {
        direction = new Point2D(direction.getX(), y);
    }
}
