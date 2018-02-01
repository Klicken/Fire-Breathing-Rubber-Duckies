import javafx.geometry.Point2D;

public interface AI
{
    Point2D seekPlayer(Point2D enemyPos, Player player);
}
