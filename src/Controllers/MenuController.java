package Controllers;

import Game.Main;
import Game.Sound;
import Highscore.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class used to handle what menu buttons do.
 *
 * @author Martin Karlsson
 * @version 2018-02-21
 */
public class MenuController {

    /**
     * Sets the root of the scene to the corresponding ID of the menu button.
     *
     * @param event ActionEvent generated when pressing a menu button
     */
    public void menuButton(ActionEvent event) {

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/fxml/"+ source.getId() +".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Initializes a new game session and calls for the method menuButton with the parameter event.
     *
     * @param event ActionEvent generated when pressing a menu button
     */
    public void startGame(ActionEvent event) {
        Main.getGameHandler().initGame();
        menuButton(event);
    }

    /**
     * Ends the current game session and calls for the method menuButton with the parameter event.
     *
     * @param event ActionEvent generated when pressing a menu button
     */
    public void stopGame(ActionEvent event) {
        Main.getGameHandler().endGame();
        menuButton(event);
    }

    /**
     * Sends the name and score from the "Game Over" screen to the highscore server.
     *
     * @param event ActionEvent generated when pressing a menu button
     */
    public void submitButton(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        TextField getName = (TextField)(stage.getScene().lookup("#inputName"));
        HighScoreManager h = new HighScoreManager();
        h.writeHighScoreToServer(new Highscore(Main.getGameHandler().getScore(),getName.getText()));
        menuButton(event);
    }

    public void toggleSound(ActionEvent event) {
        if(Sound.getMute() == true)
        {
            Sound.setMute(false);
        }
        else
        Sound.setMute(true);
    }

    /**
     *  Terminates the currently running Java Virtual Machine.
     *  quitButton is called when the quit button in the start menu is clicked.
     */
    public void quitButton() {
        System.exit(0);
    }

}
