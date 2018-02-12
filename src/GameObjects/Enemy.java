package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Enemy extends DynamicGameObject implements AI {

    public Enemy(Image image, double x, double y, double movementSpeed, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
    }

    @Override
    public void update(double time) {
        Point2D direction = seekPlayer();
        setPos(getX() +  direction.getX() * movementSpeed * time,
                getY() +  direction.getY() * movementSpeed * time);
    }

    @Override
    public Point2D seekPlayer() {
        return Player.getInstance().getPoint().subtract(getPoint()).normalize();
    }


}
