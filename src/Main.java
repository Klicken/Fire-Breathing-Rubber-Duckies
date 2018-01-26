import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;

public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Fire Breathing Rubber Duckies");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        Parent startMenu = FXMLLoader.load(getClass().getResource("resources/Start.fxml"));

        Scene scene = new Scene(startMenu);

        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animator = new AnimationTimer() {
            long lastTime = System.nanoTime(); //This is called once

            @Override
            public void handle(long now) {
                // FPS COUNTER
                //double elapsedTime = now - lastTime;
                //lastTime = now;
                //double fps = 1000000000 / elapsedTime;

                //System.out.println("FPS: " + fps);

                // UPDATE

                // RENDER

            }

        };
        animator.start();
    }

}
