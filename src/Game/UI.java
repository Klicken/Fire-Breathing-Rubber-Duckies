package Game;

import GameObjects.Player;
import javafx.scene.text.Text;

public class UI extends Text {

    public UI(double x, double y, String text){
        super(x, y, text);
    }

    public static String playerHealthString(){
        return "HP: " + Integer.toString(Player.getInstance().getHealthValue()) + "/" + Player.getInstance().getMaxHealth();
    }

    public static String currentLevelString(){
        return "Level: " + Integer.toString(Main.getGameHandler().getCurrentLvl());
    }

    public static String playerScoreString(){
        return "Score: " + Integer.toString(Main.getGameHandler().getScore());
    }

    public static String playerDamageString(){
        return "Damage: " + Integer.toString(Player.getInstance().getDamage() * -1 + 1);
    }

    public void updateText(String newText) {
        setText(newText);
    }
}
