package Game;

import GameObjects.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
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
    private static ArrayList<PowerUp> powerUps;

    private static GameGenerator generator;
    private Group root;
    private static int score;
    private Parent gameover;

    // UI elements
    private UI playerHealthUI;
    private UI playerLevelUI;
    private UI playerScoreUI;
    private UI playerDamageUI;

    public GameHandler() {
        stage = Main.getStage();
        enemies = new ArrayList<Enemy>();
        projectiles = new ArrayList<Projectile>();
        powerUps = new ArrayList<PowerUp>();
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
            if (!resumed) {
                resumeGame();
            }
            if (score % 11 == 0) {
                score++;
                generator.startNextLevel();
                generator.play();
            }
            // UPDATE
            player.requestFocus();
            update(elapsedTime / 1000000000);
            addToRoot();

            // Set the root to GameOver.fxml when Player is dead
            if(!player.getAlive()){
                try {
                    generator.pause();
                    gameover = FXMLLoader.load(getClass().getResource("/resources/GameOver.fxml"));
                    stage.getScene().setRoot(gameover);
                    ((Label)stage.getScene().lookup("#Score")).setText("Score: " + Integer.toString(score));
                } catch (Exception e){
                    System.err.println(e);
                }
            }

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
    public void initGame() {
        endGame();
        player = Player.createInstance(new Image("resources/penguin0.png"), 500, 400, 200, 100, 0);
        generator.startLevel();

        //Initiate UI elements
        initUIelements();

    }

    public void endGame() {
        /*
         * Removes old objects
         */
        Player.nullInstance();
        enemies.clear();
        projectiles.clear();
        powerUps.clear();
        generator = new GameGenerator();
        score = 1;
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
        addUIElementsToRoot();
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
        for (PowerUp powerUp: powerUps){
            if(powerUp.getParent() == null)
                root.getChildren().add(powerUp);
        }
    }
    private void update(double time) {

        player.update(time);
        player.constrainToWindow();

        int enemyIndex = enemies.size() - 1;
        for (int i = 0; i <= enemyIndex; enemyIndex--) {
            Enemy getEnemy = enemies.get(enemyIndex);

            //Update enemy positions
            enemies.get(enemyIndex).update(time);

            //Check collisions between player and enemies, handle health accordingly
            player.collisionHandling(getEnemy);
            player.changeHealth(getEnemy, getEnemy.getDamage());

            // Update UI elements
            updateUI();

            // Checks the collision between enemies so they won't overlap
            int otherEnemyIndex = enemies.size() - 1;
            for (int k = 0; k <= otherEnemyIndex; otherEnemyIndex--){
                Enemy getOtherEnemy = enemies.get(otherEnemyIndex);
                if (!(getOtherEnemy == getEnemy))
                    getEnemy.collisionHandling(getOtherEnemy);
            }
            /*
             * Checks the collision between enemies and projectiles.
             * Sets the health for projectiles to zero when they collide with an enemy
             * Damages the enemy depending on the projectile damage.
             */
            int projectileIndex = projectiles.size() - 1;
            for (int j = 0; j <= projectileIndex; projectileIndex--) {
                Projectile getProjectile = projectiles.get(projectileIndex);
                if(getEnemy.intersects(getProjectile)) {
                    getEnemy.changeHealth(getProjectile, getProjectile.getDamage() + player.getDamage());
                    ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(getProjectile);
                    getProjectiles().remove(getProjectile);

                }
            }
            /*
             * Removes enemies when their alive boolean is false
             */
            if (!getEnemy.getAlive()) {
                score++;
                ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(getEnemy);

                // Chance of enemy dropping a PowerUp item
                if(PowerUp.randomWithRange(1, 10) > 7) {
                    GameHandler.powerUps.add(new PowerUp(null, getEnemy.getPositon().getX(), getEnemy.getPositon().getY()));
                }
                GameHandler.enemies.remove(getEnemy);
            }

        }
        /*
         * Removes projectiles when their health reaches zero or they leave the screen.
         */
        int projectileIndex = projectiles.size() - 1;
        for (int j = 0; j <= projectileIndex; projectileIndex--) {
            Projectile getProjectile = projectiles.get(projectileIndex);
            getProjectile.update(time);
            if (getProjectile.outOfBounds() || !getProjectile.getAlive()) {
                ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(getProjectile);
                getProjectiles().remove(getProjectile);
            }
        }

        /*
        *   Checks if the player has collected a powerUp item. Powerup is then applied to the player
        *   and removed from the screen and powerUp list.
        */
        int powerUpIndex = powerUps.size() - 1;
        for (int k = 0; k <= powerUpIndex; powerUpIndex--){
            PowerUp getPowerUp = powerUps.get(powerUpIndex);
            if(getPowerUp.collectPowerUp()){
                ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(getPowerUp);
                powerUps.remove(getPowerUp);
            }
        }
    }


    public ArrayList<Projectile> getProjectiles () {
        return projectiles;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public GameGenerator getGenerator() {
        return generator;
    }

    public int getScore() {
        return score;
    }

    /*
    * These three methods are for initializing, updating and displaying the UI elements.
    */
    private void initUIelements(){
        int xOffset = 15;
        int yOffset = 15;
        playerHealthUI = new UI(xOffset, yOffset * 1, UI.playerHealthString());
        playerDamageUI = new UI(xOffset, yOffset * 2, UI.playerDamageString());
        playerLevelUI = new UI(xOffset, yOffset * 3, UI.currentLevelString());
        playerScoreUI = new UI(xOffset, yOffset * 4, UI.playerScoreString());
    }

    private void updateUI(){
        playerHealthUI.updateText(UI.playerHealthString());
        playerLevelUI.updateText(UI.currentLevelString());
        playerScoreUI.updateText(UI.playerScoreString());
        playerDamageUI.updateText(UI.playerDamageString());
    }

    private void addUIElementsToRoot(){
        root.getChildren().add(playerHealthUI);
        root.getChildren().add(playerLevelUI);
        root.getChildren().add(playerScoreUI);
        root.getChildren().add(playerDamageUI);
    }
}