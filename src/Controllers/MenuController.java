package Controllers;

import Game.GameHandler;
import Highscore.HighScoreManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Highscore.*;
import java.util.ArrayList;

public class MenuController {

    /*
     *  source finds the source of the button that is clicked.
     *  With source it finds the scene and sets the root to a new Parent.(Parent is what's inside setRoot())
     *  The Parent uses the id of a button to choose the correct fxml file to load.
     */
    public void menuButton(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/"+ source.getId() +".fxml"));
        source.getScene().setRoot(root);
        if(source.getId().equals("Scoreboard")){
            ArrayList<Highscore> highscores = new HighScoreManager().readHighscoreFromServer();
            for(int i = 0; i < highscores.size() ;i++){
                ((GridPane)stage.getScene().lookup("#table")).add(new Label(highscores.get(i).getName()), 1, i);
                ((GridPane)stage.getScene().lookup("#table")).add(new Label(Integer.toString(highscores.get(i).getScore())), 2, i);
            }
        }
        root.requestFocus();
    }

    public void startGame(ActionEvent event) throws Exception {
        GameHandler.initGame();
        menuButton(event);
    }

    public void stopGame(ActionEvent event) throws Exception {
        GameHandler.endGame();
        menuButton(event);
    }

    /*
     *  quitButton is called when the quit button in the start menu is clicked.
     */
    public void quitButton() {
        System.exit(0);
    }

}
