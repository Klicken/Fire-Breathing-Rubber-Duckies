import GameObjects.*;

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

    private boolean resumed = false;
    private static Player player;
    private static ArrayList<Enemy> enemies;
    private static ArrayList<Projectile> projectiles;

    public GameHandler() {
        stage = Main.getStage();
        enemies = new ArrayList<Enemy>();
        projectiles = new ArrayList<Projectile>();
        lastTime = System.nanoTime();
    }

    /*
     * This is the game loop.
     */
    @Override
    public void handle(long now) {
        // FPS COUNTER
        double elapsedTime = now - lastTime;
        lastTime = now;
        double fps = 1000000000 / elapsedTime;
        //System.out.println("FPS: " + fps);

        if (stage.getScene().getRoot() instanceof Group) {
            if (!resumed)
                resumeGame();

            // UPDATE
            player.requestFocus();
            update(elapsedTime / 1000000000);

            // SLEEP
            try {
                if (lastTime - System.nanoTime() + OPTIMAL_TIME >= 0)
                    Thread.sleep((lastTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else
            resumed = false;
    }

    /*
     * Is called from MenuController
     */
    public static void initGame() {
        /*
         * Start by removing old objects
         */
        Player.nullInstance();
        enemies.clear();
        projectiles.clear();

        player = Player.createInstance(new Image("resources/penguin.png"), 500, 400, 400, 100, 0);
        Enemy enemy1 = new Enemy(new Image("resources/penguin.png"), 600, 200, 70, 10, -1);
        Enemy enemy2 = new Enemy(new Image("resources/penguin.png"), 200, 200, 80, 10, -1);
        Enemy enemy3 = new Enemy(new Image("resources/penguin.png"), 300, 200, 100, 10, -1);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
    }

    /*
     * Stops the Player's movement to prevent movement without pressed keys after resuming.
     * Adds the nodes(GameObjects) to the root of the scene.
     */
    private void resumeGame() {
        Player.stop();
        ((Group) stage.getScene().getRoot()).getChildren().add(player);
        for (Enemy enemy : enemies) {
            ((Group) stage.getScene().getRoot()).getChildren().add(enemy);
        }
        for (Projectile projectile : projectiles) {
            ((Group) stage.getScene().getRoot()).getChildren().add(projectile);
        }
        resumed = true;
    }

    private void update(double time) {

        player.update(time);
        //if(!player.getAlive())


        int enemyIndex = enemies.size() - 1;
        int projectileIndex = projectiles.size() - 1;
        int otherEnemyIndex = enemies.size() - 1;
        for (int i = 0; i <= enemyIndex; enemyIndex--) {

            enemies.get(enemyIndex).update(time);
            player.collisionHandling(enemies.get(enemyIndex));
            player.changeHealth(enemies.get(enemyIndex), enemies.get(enemyIndex).getDamage());

            for (int k = 0; k <= otherEnemyIndex; otherEnemyIndex--){

                if (!(enemies.get(otherEnemyIndex)== enemies.get(enemyIndex)))
                enemies.get(otherEnemyIndex).collisionHandling(enemies.get(enemyIndex));
            }

                //System.out.println(enemies.get(enemyIndex).getAlive());
                //System.out.print(enemies.get(enemyIndex).getInstanceHealth());
                if (!enemies.get(enemyIndex).getAlive()) {
                    ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(enemies.get(enemyIndex));
                    GameHandler.enemies.remove(enemies.get(enemyIndex));
                }

        }

        for (int j = 0; j <= projectileIndex; projectileIndex--) {
            projectiles.get(projectileIndex).update(time);
            if (projectiles.get(projectileIndex).removeProjectile() || !projectiles.get(projectileIndex).getAlive()) {
                ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(projectiles.get(projectileIndex));
                GameHandler.getProjectiles().remove(projectiles.get(projectileIndex));
            }
        }

    }

        public static ArrayList<Projectile> getProjectiles ()
        {
            return projectiles;
        }
    }


