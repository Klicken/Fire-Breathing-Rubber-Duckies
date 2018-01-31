import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class DynamicGameObject extends GameObject
{
    private double movementSpeed;
    private double vx, vy;

    public DynamicGameObject(Image image, double x, double y, double movementSpeed)
    {
        super(image, x, y);
        this.movementSpeed = movementSpeed;
        vx = 0;
        vy = 0;
    }

    public void update(double time)
    {
        this.setPos(this.getX() + vx * movementSpeed * time, this.getY() + vy * movementSpeed * time);
    }

    public void setDirectionX(double x)
    {
        vx = x;
    }
    public void setDirectionY(double y)
    {
        vy = y;
    }
}
