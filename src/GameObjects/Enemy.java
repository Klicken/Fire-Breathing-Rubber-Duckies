package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Enemy extends DynamicGameObject implements AI {

    public Enemy(Image image, double x, double y, double movementSpeed, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
    }

    /*
    *   Updates the position of the enemy based on simple AI-behaviour.
    */
    @Override
    public void update(double time) {
        Point2D direction = seekPlayer();
        setPos(getX() +  direction.getX() * movementSpeed * time,
                getY() +  direction.getY() * movementSpeed * time);
    }

    /*
    *   Calculates a direction vector for the Enemy to follow based on the players position.
    *   The enemy then will seek out the player to damage it on physical contact.
    */
    @Override
    public Point2D seekPlayer() {
        return Player.getInstance().getPositon().subtract(getPositon()).normalize();
    }
}
