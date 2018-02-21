package GameObjects;

import javafx.scene.image.Image;

/*
 *   Attributes that can be upgraded, associated value for random generation:
 *
 *   Health - 0
 *   Max Health - 1
 *   Damage - 2
 *   Movement speed - 3
 *   Projectile Speed
 *   Reduce Player Size
 *   Shield
 *   Invincibility, kill enemies on impact
 */

/**
 * This class is used for creating powerup items that can be collected by the player upon collision.
 * Upgrades are applied to the player to increase different power related attributes.
 *
 * @see Player
 *
 * @author Tobias Rosengren
 * @version 2018-02-21
 */
public class PowerUp extends GameObject{
    private int upgradeValue;
    private int upgradeType;

    /**
     *   Constructs a random PowerUp object.
     *
     *   @param image the image of the power up
     *   @param x x position of the power up
     *   @param y y position of the power up
     */
    public PowerUp(Image image, double x, double y) {
        super(image, x, y);
        upgradeType = randomWithRange(0, 3);
        switch(upgradeType) {
            case 0: setImage(new Image("resources/powerups/Healing.png"));
                    upgradeValue = 20;
                    break;
            case 1: setImage(new Image("resources/powerups/maxHealth.png"));
                upgradeValue = 20;
                break;

            case 2: setImage(new Image("resources/powerups/damageUp.png"));
                upgradeValue = 1;
                break;

            case 3: setImage(new Image("resources/powerups/speedUp.png"));
                upgradeValue = 20;
                break;
        }
    }

    /**
     * Generate a random number between the range of the parameters
     *
     * @param max max value of the range to be generated between
     * @param min min value of the range to be generated between
     * @return a random int in a range between min and max
     */
    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    /**
     * Check if the player intersects with power up and apply it to the player
     *
     * @return true if a Player intersects this PowerUp object
     */
    public boolean collectPowerUp() {
        if(intersects(Player.getInstance())){
            switch(upgradeType){
                case 0: Player.getInstance().objectHealth.setHealth(Player.getInstance().objectHealth.getHealth() + upgradeValue);
                        break;

                case 1: Player.getInstance().maxHealth += upgradeValue;
                        Player.getInstance().objectHealth.setHealth(Player.getInstance().objectHealth.getHealth() + upgradeValue);
                        break;

                case 2: Player.getInstance().damage += upgradeValue;
                        break;

                case 3: Player.getInstance().movementSpeed += upgradeValue;
                        break;
            }
            return true;
        }
        return false;
    }
}
