import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;

public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;
    private static final Canvas canvas = new Canvas(1280,720);

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
            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

            @Override
            public void handle(long now) {
                if(primaryStage.getScene().getRoot() instanceof Group){
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.setFill(Color.BLUE);
                    gc.fillRect(200,200,40,40);

                    // FPS COUNTER
                    double elapsedTime = now - lastTime;
                    lastTime = now;
                    double fps = 1000000000 / elapsedTime;

                    System.out.println("FPS: " + fps);

                    // UPDATE

                    // RENDER

                    // SLEEP
                    try{
                        Thread.sleep( (lastTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        animator.start();
    }

    public static Canvas getCanvas(){
        return canvas;
    }
}
