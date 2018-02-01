import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameHandler extends AnimationTimer {
    long lastTime;
    final int TARGET_FPS = 60;
    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    private Stage stage;

    private boolean initialized = false;
    private Player player;
    private ArrayList<Enemy> enemies;
    private static ArrayList<Projectile> projectiles;

    public GameHandler(){
        stage = Main.getStage();
        enemies = new ArrayList<Enemy>();
        projectiles = new ArrayList<Projectile>();
        lastTime = System.nanoTime();
    }


    /*
     * This is the game loop.
     */
    @Override
    public void handle(long now)
    {
        if(stage.getScene().getRoot() instanceof Group)
        {
            if(!initialized)
                initGame();

            // FPS COUNTER
            double elapsedTime = now - lastTime;
            lastTime = now;
            double fps = 1000000000 / elapsedTime;
            //System.out.println("FPS: " + fps);

            // UPDATE
            player.requestFocus();
            update(elapsedTime / 1000000000);

            // SLEEP
            try
            {
                if(lastTime-System.nanoTime() + OPTIMAL_TIME >= 0)
                Thread.sleep( (lastTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void initGame()
    {
        player = Player.createInstance(new Image("resources/penguin.png"), 500, 400, 200);
        Enemy enemy1 = new Enemy(new Image("resources/penguin.png"), 100, 200, 100);
        enemies.add(enemy1);

        ((Group)stage.getScene().getRoot()).getChildren().add(player);
        for (Enemy enemy: enemies) {
            ((Group)stage.getScene().getRoot()).getChildren().add(enemy);
        }
        initialized = true;
    }

    private void update(double time)
    {
        player.update(time);

        for (Enemy enemy: enemies) {
            enemy.update(time);
            System.out.println(player.intersects(enemy.getBoundsInLocal()));
        }
        for (Projectile projectile: projectiles) {
            projectile.update(time);
        }
    }

    public static ArrayList<Projectile> getProjectiles()
    {
        return projectiles;
    }
}
