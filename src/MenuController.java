import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MenuController
{

    /*
     *  source finds the source of the button that is clicked.
     *  With source it finds the scene and sets the root to a new Parent.(Parent is what's inside setRoot())
     *  The Parent uses the id of a button to choose the correct fxml file to load.
     */
    public void menuButton(ActionEvent event) throws Exception
    {
        Node source = (Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("resources/"+ source.getId() +".fxml"));
        source.getScene().setRoot(root);
        root.requestFocus();
    }
    /*
     *  quitButton is called when the quit button in the start menu is clicked.
     *  stage uses source to find the current scene and then finds the window to find the stage (primaryStage in Main.java).
     *  stage.close() terminates the process.
     */
    public void quitButton(ActionEvent event)
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
