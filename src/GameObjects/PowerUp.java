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

public class PowerUp extends GameObject{
    private int upgradeValue;
    private int upgradeType;

    /*
    *   Constructs a random PowerUp object.
    */
    public PowerUp(Image image, double x, double y) {
        super(image, x, y);
        upgradeType = randomWithRange(0, 3);
        switch(upgradeType) {
            case 0: setImage(new Image("resources/Healing.png"));
                    upgradeValue = 20;
                    break;
            case 1: setImage(new Image("resources/maxHealth.png"));
                upgradeValue = 20;
                break;

            case 2: setImage(new Image("resources/damageUp.png"));
                upgradeValue = 1;
                break;

            case 3: setImage(new Image("resources/speedUp.png"));
                upgradeValue = 20;
                break;
        }
    }

    /*
    *   Returns a random int in a range between min and max
    */
    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    /*
    *   Returns true if a Player intersects this PowerUp object, PowerUp is also applied to the player.
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
