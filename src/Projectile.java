import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Projectile extends DynamicGameObject
{

    private double angle;
    private Point2D mousePos;

    public Projectile(Image image, double x, double y, double movementSpeed, Point2D mousePos)
    {
        super(image, x, y, movementSpeed);
        this.mousePos = mousePos;

    }

    public void angle()
    {
        angle = Player.getInstance().getPoint().angle(mousePos);

    }

    @Override
    public void update(double time)
    {
        double direction = angle;
        setPos(getX() +  direction * movementSpeed * time,
                getY() +  direction * movementSpeed * time);
    }

}
