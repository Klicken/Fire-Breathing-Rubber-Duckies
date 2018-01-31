import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class Main extends Application {

    final int WIDTH = 1280;
    final int HEIGHT = 720;
    private ArrayList<Node> nodeList = new ArrayList<Node>();
    private static Stage stage;
    private boolean initialized = false;

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

        DynamicGameObject player1 = initPlayer();

        nodeList.add(player1);

        AnimationTimer animator = new AnimationTimer()
        {
            long lastTime = System.nanoTime(); //This is called once
            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
            
            @Override
            public void handle(long now)
            {
                if(primaryStage.getScene().getRoot() instanceof Group)
                {
                    if(!initialized)
                        initGame();

                    // FPS COUNTER
                    double elapsedTime = now - lastTime;
                    lastTime = now;
                    double fps = 1000000000 / elapsedTime;
                    //System.out.println("FPS: " + fps);

                    // UPDATE
                    player1.requestFocus();
                    player1.update(elapsedTime / 1000000000);

                    // SLEEP
                    try
                    {
                        Thread.sleep( (lastTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        };
        animator.start();
    }

    public static Stage getStage()
    {
        return stage;
    }

    private void initGame()
    {
        for (Node n: nodeList) {
            ((Group)getStage().getScene().getRoot()).getChildren().add(n);
        }
        initialized = true;
    }

    private DynamicGameObject initPlayer()
    {
        DynamicGameObject player = new DynamicGameObject(new Image("resources/penguin.png"), 1);
        player.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode())
                    {
                        case W: player.setDirectionY(-1); break;
                        case S: player.setDirectionY(1); break;
                        case A: player.setDirectionX(-1); break;
                        case D: player.setDirectionX(1); break;
                    }
                }
        );
        player.addEventHandler(KeyEvent.KEY_RELEASED,
                event -> {
                    switch (event.getCode())
                    {
                        case W: player.setDirectionY(0); break;
                        case S: player.setDirectionY(0); break;
                        case A: player.setDirectionX(0); break;
                        case D: player.setDirectionX(0); break;
                    }
                }
        );

        return player;
    }
}
