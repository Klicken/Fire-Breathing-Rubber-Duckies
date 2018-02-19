package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;
    private static Stage stage;
    private static GameHandler game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        primaryStage.setTitle("Fire Breathing Rubber Duckies");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        //Stop the process when the window is closed
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        Parent startMenu = FXMLLoader.load(getClass().getResource("/resources/fxml/Start.fxml"));

        Scene scene = new Scene(startMenu);
        scene.setCursor(Cursor.CROSSHAIR);

        primaryStage.setScene(scene);
        primaryStage.show();

        game = new GameHandler();
        game.start();
    }

    public static Stage getStage() {
        return stage;
    }
    public static GameHandler getGameHandler() {
        return game;
    }
}
