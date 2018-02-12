package Game;

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
    private static GameGenerator generator;
    private Group root;
    private int score;

    public GameHandler() {
        stage = Main.getStage();
        enemies = new ArrayList<Enemy>();
        projectiles = new ArrayList<Projectile>();
        lastTime = System.nanoTime();
        generator = new GameGenerator();
        score = 1;
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
            if (score % 11 == 0 && score != 0) {
                score++;
                generator.startNextLevel();
                generator.play();
            }
            // UPDATE
            player.requestFocus();
            update(elapsedTime / 1000000000);
            addToRoot();

            // SLEEP
            try {
                if (lastTime - System.nanoTime() + OPTIMAL_TIME >= 0)
                    Thread.sleep((lastTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
            resumed = false;
    }

    /*
     * Is called from MenuController
     */
    public static void initGame() {
        endGame();
        player = Player.createInstance(new Image("resources/penguin0.png"), 500, 400, 200, 100, -50);
        generator.startLevel();
    }

    public static void endGame() {
        /*
         * Removes old objects
         */
        Player.nullInstance();
        enemies.clear();
        projectiles.clear();
    }

    /*
     * Stops the Player's movement to prevent movement without pressed keys after resuming.
     * Adds the nodes(GameObjects) to the root of the scene.
     */
    private void resumeGame() {
        Player.stop();
        generator.play();
        if(root != null)
            root.getChildren().clear();
        root = (Group)stage.getScene().getRoot();
        root.getChildren().add(player);
        resumed = true;
    }

    private void addToRoot() {
        for (Enemy enemy: enemies) {
            if(enemy.getParent() == null)
                root.getChildren().add(enemy);
        }
        for (Projectile projectile: projectiles) {
            if(projectile.getParent() == null)
                root.getChildren().add(projectile);
        }
    }
    private void update(double time) {

        player.update(time);
        //if(!player.getAlive())


        int enemyIndex = enemies.size() - 1;
        for (int i = 0; i <= enemyIndex; enemyIndex--) {

            enemies.get(enemyIndex).update(time);
            player.collisionHandling(enemies.get(enemyIndex));
            player.changeHealth(enemies.get(enemyIndex), enemies.get(enemyIndex).getDamage());

            // Checks the collision between enemies so they won't overlap
            int otherEnemyIndex = enemies.size() - 1;
            for (int k = 0; k <= otherEnemyIndex; otherEnemyIndex--){
                if (!(enemies.get(otherEnemyIndex) == enemies.get(enemyIndex)))
                    enemies.get(enemyIndex).collisionHandling(enemies.get(otherEnemyIndex));
            }
            /*
             * Checks the collision between enemies and projectiles.
             * Sets the health for projectiles to zero when they collide with an enemy
             * Damages the enemy depending on the projectile damage.
             */
            int projectileIndex = projectiles.size() - 1;
            for (int j = 0; j <= projectileIndex; projectileIndex--) {
                if(enemies.get(enemyIndex).intersects(projectiles.get(projectileIndex))) {
                    enemies.get(enemyIndex).changeHealth(projectiles.get(projectileIndex), projectiles.get(projectileIndex).getDamage());
                    ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(projectiles.get(projectileIndex));
                    GameHandler.getProjectiles().remove(projectiles.get(projectileIndex));

                }
            }
            /*
             * Removes enemies when their alive boolean is false
             */
            if (!enemies.get(enemyIndex).getAlive()) {
                score++;
                ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(enemies.get(enemyIndex));
                GameHandler.enemies.remove(enemies.get(enemyIndex));
            }

        }
        /*
         * Removes projectiles when their health reaches zero or they leave the screen.
         */
        int projectileIndex = projectiles.size() - 1;
        for (int j = 0; j <= projectileIndex; projectileIndex--) {
            projectiles.get(projectileIndex).update(time);
            if (projectiles.get(projectileIndex).outOfBounds() || !projectiles.get(projectileIndex).getAlive()) {
                ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(projectiles.get(projectileIndex));
                GameHandler.getProjectiles().remove(projectiles.get(projectileIndex));
            }
        }

    }


    public static ArrayList<Projectile> getProjectiles () {
        return projectiles;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public GameGenerator getGenerator() {
        return generator;
    }
}