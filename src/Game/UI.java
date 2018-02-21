package Game;

import GameObjects.Player;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;

/**
 * Class used to create and display simple text based UI-elements onto the
 * main game window. Extends the Text class.
 *
 * @see Text
 *
 * @author Tobias Rosengren
 * @version 2018-02-21
 */
public class UI extends Text {

    /**
     * Allocates a new UI object and sets the the x- and y coordinate to where it should be
     * displayed in the window. Text is set based on the String parameter.
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param text  text to display
     */
    public UI(double x, double y, String text){
        super(x, y, text);
        this.setFill(Color.WHITE);
    }

    /**
     * Gets the string representation of the Players current and maximum health.
     *
     * @return The string representation of the Players current and maximum health
     */
    public static String playerHealthString(){
        return "HP: " + Integer.toString(Player.getInstance().getHealthValue()) + "/" + Player.getInstance().getMaxHealth();
    }

    /**
     * Gets the string representation of the current game level.
     *
     * @return The string representation of current game level
     */
    public static String currentLevelString(){
        return "Level: " + Integer.toString(Main.getGameHandler().getCurrentLvl());
    }

    /**
     * Gets the string representation of the players current score.
     *
     * @return The string representation of players current score
     */
    public static String playerScoreString(){
        return "Score: " + Integer.toString(Main.getGameHandler().getScore());
    }

    /**
     * Gets the string representation of the players current damage.
     *
     * @return The string representation of the players current damage
     */
    public static String playerDamageString(){
        return "Damage: " + Integer.toString(Player.getInstance().getDamage() * -1 + 1);
    }

    /**
     * Updates the text of this UI object with the new String value.
     *
     * @param newText   The new string to be displayed
     */
    public void updateText(String newText) {
        setText(newText);
    }
}
