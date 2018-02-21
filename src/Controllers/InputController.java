package Controllers;

import Game.*;
import GameObjects.*;
import javafx.animation.Timeline;
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

import java.io.IOException;

/**
 * A class used to handle the press of the ESCAPE-key and primary mouse button when in the "Game" screen.
 */
public class InputController {

    /**
     * Pauses the game on the press of the ESCAPE-key by loading in the Pause.fxml and using it as root for the scene.
     *
     * @param event The key event generated, used for identifying which key was pressed.
     * @see KeyEvent
     */
    public void pause(KeyEvent event){
        try {
            if (event.getCode() == KeyCode.ESCAPE) {
                Main.getGameHandler().getGenerator().pause();
                for(Timeline t : Main.getGameHandler().getpUpTimers()){
                    t.pause();
                }
                Node source = (Node) event.getSource();
                Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/Pause.fxml"));
                source.getScene().setRoot(root);
            }
        }catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * Fires a projectile from the player when the mouse is pressed.
     *
     * @param event The mouse event generated, used to find the mouse position to calculate
     *              the projectile trajectory between the player who fired the projectile and
     *              the location of the mouse press
     * @see MouseEvent
     */
    public void shoot(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            Projectile p = new Projectile(new Image("/resources/animations/projectiles/ball.png",25,0,true,false), Player.getInstance().getX() + 15, Player.getInstance().getY() + 15, 400,new Point2D(event.getX() - 25,event.getY() - 25), 1, 1);
            ((Group)Main.getStage().getScene().getRoot()).getChildren().add(p);
            Main.getGameHandler().getProjectiles().add(p);
        }
    }
}
