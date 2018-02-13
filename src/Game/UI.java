package Game;

import GameObjects.Player;
import javafx.scene.text.Text;

public class UI extends Text {

    public UI(double x, double y, String text){
        super(x, y, text);
    }

    public static String playerHealthString(){
        return Integer.toString(Player.getInstance().getHealthValue());
    }

    public void updateText(String newText)
    {
        setText(newText);
    }
}
