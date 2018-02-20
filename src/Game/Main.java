package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The starting point of the program.
 */
public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;
    private static Stage stage;
    private static GameHandler game;

    /**
     * Launches a standalone application.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method is called after the system is ready for the application to begin running.
     * Sets the title, width, height and center of the stage(Window).
     * Removes resizablility of the stage and sets the starting scene. Changes the mouse pointer of the scene to a crosshair.
     * Instantiates a GameHandler and calls its start method.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        primaryStage.setTitle("Fire Breathing Rubber Duckies");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        //Stop the process when the window is closed
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        Parent startMenu = null;
        try {
            startMenu = FXMLLoader.load(getClass().getResource("/resources/fxml/Start.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(startMenu);
        scene.setCursor(Cursor.CROSSHAIR);

        primaryStage.setScene(scene);
        primaryStage.show();

        game = new GameHandler();
        game.start();
    }

    /**
     * @return The stage(Window)
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * @return The GameHandler instantiated in the start method
     */
    public static GameHandler getGameHandler() {
        return game;
    }
}
