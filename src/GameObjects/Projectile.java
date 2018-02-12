package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Projectile extends DynamicGameObject {
    private Point2D mousePos;
    private Point2D direction;

    public Projectile(Image image, double x, double y, double movementSpeed, Point2D mousePos, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
        this.mousePos = mousePos;
        direction = getDirection();
    }

    public double getAngle() {
        return Player.getInstance().getPoint().angle(mousePos);
    }

    public Point2D getDirection() {
        return mousePos.subtract(Player.getInstance().getPoint()).normalize();
    }

    @Override
    public void update(double time) {
        setPos(getX() +  direction.getX() * movementSpeed * time,
                getY() +  direction.getY() * movementSpeed * time);

    }

    public boolean outOfBounds() {
        return (getX() > 1340 || getX() < -60 || getY() > 780 || getY() < -60);

    }
}
