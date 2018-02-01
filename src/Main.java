import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;
    private static Stage stage;

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

        Parent startMenu = FXMLLoader.load(getClass().getResource("resources/Start.fxml"));

        Scene scene = new Scene(startMenu);

        primaryStage.setScene(scene);
        primaryStage.show();

        GameHandler game = new GameHandler();
        game.start();
    }

    public static Stage getStage()
    {
        return stage;
    }
}
