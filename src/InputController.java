import GameObjects.Projectile;
import GameObjects.Player;

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

public class InputController
{

    /*
     *  pauses the game on the press of the ESCAPE-key by loading in the Pause.fxml and using it as root for the scene.
     */
    public void pause(KeyEvent event) throws Exception{
        if(event.getCode() == KeyCode.ESCAPE){
            Node source = (Node) event.getSource();
            Parent root = FXMLLoader.load(getClass().getResource("resources/Pause.fxml"));
            source.getScene().setRoot(root);
        }
    }
    public void shoot(MouseEvent event) throws Exception{
        if(event.getButton() == MouseButton.PRIMARY)
        {
            System.out.println("PANG");
            Projectile p = new Projectile(new Image("resources/apple.png"), Player.getInstance().getX(), Player.getInstance().getY(), 400,new Point2D(event.getX(),event.getY()), 1, -1);
            ((Group)Main.getStage().getScene().getRoot()).getChildren().add(p);
            GameHandler.getProjectiles().add(p);
        }
    }
}
