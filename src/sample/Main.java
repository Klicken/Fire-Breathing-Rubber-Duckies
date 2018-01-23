package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;

public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = WIDTH * 9/16;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Basic JavaFX demo");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.centerOnScreen();

        Group root = new Group();
        Scene scene = new Scene( root );

        primaryStage.setScene( scene );
        primaryStage.show();
        //Testing change 1.0

        AnimationTimer animator = new AnimationTimer() {
            long lastTime = System.nanoTime(); //This is called once

            @Override
            public void handle(long now) {
                // FPS COUNTER
                double elapsedTime = now - lastTime;
                lastTime = now;
                double fps = 1000000000 / elapsedTime;

                System.out.println("FPS: " + fps);

                // UPDATE

                // RENDER

            }

        };
        animator.start();
    }

}
