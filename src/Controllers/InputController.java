package Controllers;

import Game.*;
import GameObjects.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InputController {

    /*
     *  pauses the game on the press of the ESCAPE-key by loading in the Pause.fxml and using it as root for the scene.
     */
    public void pause(KeyEvent event) throws Exception{
        if(event.getCode() == KeyCode.ESCAPE){
            Main.getGameHandler().getGenerator().pause();
            Node source = (Node) event.getSource();
            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/Pause.fxml"));
            source.getScene().setRoot(root);
        }
    }

    /*
    *   Fires a projectile from the player when the mouse is pressed.
    */
    public void shoot(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            Projectile p = new Projectile(new Image("/resources/animations/projectiles/ball.png",25,0,true,false), Player.getInstance().getX() + 15, Player.getInstance().getY() + 15, 400,new Point2D(event.getX() - 25,event.getY() - 25), 1, 1);
            ((Group)Main.getStage().getScene().getRoot()).getChildren().add(p);
            Main.getGameHandler().getProjectiles().add(p);
        }
    }
}
