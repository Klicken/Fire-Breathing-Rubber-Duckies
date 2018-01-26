import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MenuController
{

    /*
     *  source finds the source of the button that is clicked(Every button calls for a function).
     *  With source it finds the scene and sets the root to a new Parent.
     */
    public void returnButton(ActionEvent event) throws Exception
    {
        Node source = (Node) event.getSource();
        source.getScene().setRoot(FXMLLoader.load(getClass().getResource("resources/Start.fxml")));
    }

    public void playButton(ActionEvent event) throws Exception
    {
        Node source = (Node) event.getSource();
        source.getScene().setRoot(FXMLLoader.load(getClass().getResource("resources/Game.fxml")));
    }

    public void controlsButton(ActionEvent event) throws Exception
    {
        Node source = (Node) event.getSource();
        source.getScene().setRoot(FXMLLoader.load(getClass().getResource("resources/Controls.fxml")));
    }

    public void scoreButton(ActionEvent event) throws Exception
    {
        Node source = (Node) event.getSource();
        source.getScene().setRoot(FXMLLoader.load(getClass().getResource("resources/Scoreboard.fxml")));
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
