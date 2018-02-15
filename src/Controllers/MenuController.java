package Controllers;

import Highscore.HighScoreManager;
import Game.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Highscore.*;
import java.util.ArrayList;

public class MenuController {

    /*
     *  Source finds the source of the button that is clicked.
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
            for(int i = 0; i < highscores.size() && i < 10 ;i++){
                GridPane gp = ((GridPane)stage.getScene().lookup("#table"));
                Highscore score = highscores.get(i);
                gp.add(new Label(score.getName()), 1, i);
                gp.add(new Label(Integer.toString(score.getScore())), 2, i);
            }
        }
        root.requestFocus();
    }

    public void startGame(ActionEvent event) throws Exception {
        Main.getGameHandler().initGame();
        menuButton(event);
    }

    public void stopGame(ActionEvent event) throws Exception {
        Main.getGameHandler().endGame();
        menuButton(event);
    }


    public void submitButton(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        TextField getName = (TextField)(stage.getScene().lookup("#inputName"));
        HighScoreManager h = new HighScoreManager();
        h.writeHighScoreToServer(new Highscore(Main.getGameHandler().getScore(),getName.getText()));
        menuButton(event);
    }

    /*
     *  quitButton is called when the quit button in the start menu is clicked.
     */
    public void quitButton() {
        System.exit(0);
    }

}
