import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.awt.*;

public class Enemy extends DynamicGameObject implements AI
{

    public Enemy(Image image, double x, double y, double movementSpeed)
    {
        super(image, x, y, movementSpeed);
    }

    @Override
    public void updateEnemy(double time)
    {
        Point2D direction = seekPlayer()
    }

    @Override
    public Point2D seekPlayer(Player player)
    {
        Point2D enemyPos = new Point2D(this.getX(), this.getY());
        Point2D playerPos = new Point2D(player.getX(), player.getY());
        Point2D direction = playerPos.subtract(enemyPos).normalize();
        return direction;
    }


}
