import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
}
