package Game;

import GameObjects.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * A class to handle the main game loop.
 *
 * @Author Martin Karlsson, Anton Wester, Oscar Nilsson & Tobias Rosengren.
 */

public class GameHandler extends AnimationTimer {
    private long lastTime;
    private final int TARGET_FPS = 60;
    private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    private Stage stage;

    private Timeline timeline;
    private ArrayList<Timeline> pUpTimers;

    private boolean resumed = false;
    private static Player player;

    private static ArrayList<Enemy> enemies;
    private static ArrayList<Projectile> projectiles;
    private static ArrayList<PowerUp> powerUps;

    private static GameGenerator generator;
    private Group root;
    private static int score;
    private Parent gameover;
    private int currentLvl;
    private int previousLvl;

    // UI elements
    private UI playerHealthUI;
    private UI playerLevelUI;
    private UI playerScoreUI;
    private UI playerDamageUI;

    private Sound bgMusic;


    /**
     * The constructor for the GameHandler.
     * Initializes the window, lists for gameobjects, timers and variables for keeping track of level handling.
     */


    public GameHandler() {
        stage = Main.getStage();
        enemies = new ArrayList<Enemy>();
        projectiles = new ArrayList<Projectile>();
        powerUps = new ArrayList<PowerUp>();
        lastTime = System.nanoTime();
        generator = new GameGenerator();
        pUpTimers = new ArrayList<>();
        score = 0;
        currentLvl = 1;
        previousLvl = 1;
        bgMusic = null;
    }

    /**
     * The main game loop.
     *
     * @param now The current time
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
            if (currentLvl != previousLvl) {
                previousLvl = currentLvl;
                generator.startNextLevel();
                Label levelLabel = (Label)(stage.getScene().lookup("#Level"));
                levelLabel.setText("Level " + currentLvl);
                levelLabel.setVisible(true);
                PauseTransition visiblePause = new PauseTransition(
                        Duration.seconds(3)
                );
                visiblePause.setOnFinished(
                        event -> levelLabel.setVisible(false)
                );
                visiblePause.play();
            }
            // UPDATE
            player.requestFocus();
            update(elapsedTime / 1000000000);
            addToRoot();

            // Set the root to GameOver.fxml when Player is dead
            if(!player.getAlive()){
                try {
                    generator.pause();
                    for(Timeline t : pUpTimers) {
                        t.pause();
                    }
                    gameover = FXMLLoader.load(getClass().getResource("/resources/fxml/GameOver.fxml"));
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

    /**
     * Initializes the game.
     */
    public void initGame() {
        endGame();
        player = Player.createInstance(new Image("/resources/animations/right/idle_right.png", 60, 0,true, false), 500, 400, 200, 100, 0);
        generator.startLevel();

        //Initiate UI elements
        initUIelements();

        //Initiate gamesound
        if(bgMusic == null)
            bgMusic = new Sound("gamemusic.wav", 1.0, 10, AudioClip.INDEFINITE);
    }

    /**
     * Removes old objects when game is ended and resets certain variables.
     */
    public void endGame() {
        /*
         * Removes old objects
         */
        Player.nullInstance();
        enemies.clear();
        projectiles.clear();
        powerUps.clear();
        generator = new GameGenerator();
        score = 0;
        currentLvl = 1;
        previousLvl = 1;
    }

    /*
     * Stops the Player's movement to prevent movement without pressed keys after resuming.
     * Adds the nodes(GameObjects) to the root of the scene.
     */

    /**
     * Resumes the timers for powerups and the generator.
     * Resets the root.
     */
    private void resumeGame() {
        Player.stop();
        generator.resume();
        for(Timeline t : pUpTimers)
            t.play();
        if(root != null)
            root.getChildren().clear();
        root = (Group)stage.getScene().getRoot();
        root.getChildren().add(player);
        addUIElementsToRoot();
        resumed = true;
    }

    /**
     * Adds gameobjects from lists filled with gameobjects to the root of the game screen.
     */
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

    /**
     * Updates the Player and all the lists with projectiles, enemies, powerups and timers.
     * Handles collision between gameobjects and constrains the player to the window.
     * Adds and removes gameobjects from their respective list.
     *
     * @param time the time between the current and previous frame.
     */
    private void update(double time) {
        player.update(time);
        // Update UI elements
        updateUI();

        int enemyIndex = enemies.size() - 1;
        for (int i = 0; i <= enemyIndex; enemyIndex--) {
            Enemy enemy = enemies.get(enemyIndex);

            //Update enemy positions
            enemies.get(enemyIndex).update(time);

            //Check collisions between player and enemies, handle health accordingly
            player.collisionHandling(enemy);
            player.changeHealth(enemy, enemy.getDamage());


            // Checks the collision between enemies so they won't overlap
            int otherEnemyIndex = enemies.size() - 1;
            for (int k = 0; k <= otherEnemyIndex; otherEnemyIndex--){
                Enemy otherEnemy = enemies.get(otherEnemyIndex);
                if (!(otherEnemy == enemy))
                    enemy.collisionHandling(otherEnemy);
            }
            /*
             * Checks the collision between enemies and projectiles.
             * Sets the health for projectiles to zero when they collide with an enemy
             * Damages the enemy depending on the projectile damage.
             */
            int projectileIndex = projectiles.size() - 1;
            for (int j = 0; j <= projectileIndex; projectileIndex--) {
                Projectile projectile = projectiles.get(projectileIndex);
                if(enemy.intersects(projectile)) {
                    enemy.changeHealth(projectile, projectile.getDamage() + player.getDamage());
                    root.getChildren().remove(projectile);
                    projectiles.remove(projectile);
                }
            }
            /*
             * Removes enemies when their alive boolean is false
             */
            if (!enemy.getAlive()) {
                root.getChildren().remove(enemy);
                enemies.remove(enemy);
                score++;

                // Chance of enemy dropping a PowerUp item
                if(PowerUp.randomWithRange(1, 10) > 7) {
                    powerUps.add(new PowerUp(null, enemy.getPositon().getX(), enemy.getPositon().getY()));
                }
                if(score % 10 == 0)
                    currentLvl++;
            }
        }
        /*
         * Removes projectiles when their health reaches zero or they leave the screen.
         */
        int projectileIndex = projectiles.size() - 1;
        for (int j = 0; j <= projectileIndex; projectileIndex--) {
            Projectile projectile = projectiles.get(projectileIndex);
            projectile.update(time);
            if (projectile.outOfBounds() || !projectile.getAlive()) {
                root.getChildren().remove(projectile);
                projectiles.remove(projectile);
            }
        }

        /*
        *   Checks if the player has collected a powerUp item. Powerup is then applied to the player
        *   and removed from the screen and powerUp list.
        */
        int powerUpIndex = powerUps.size() - 1;
        for (int k = 0; k <= powerUpIndex; powerUpIndex--){
            PowerUp getPowerUp = powerUps.get(powerUpIndex);
            timeline = new Timeline(new KeyFrame(
                    Duration.millis(6000),
                    e -> removePowerUp(getPowerUp)));
            pUpTimers.add(timeline);
            timeline.play();
            if(getPowerUp.collectPowerUp()){
                removePowerUp(getPowerUp);
            }
        }
    }
    /**
     * Returns the active list of projectiles.
     *
     * @return the list of active projectiles
     */
    public ArrayList<Projectile> getProjectiles () {
        return projectiles;
    }
    /**
     * Returns the active list of enemies.
     *
     * @return the list of active enemies.
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns the generator.
     *
     * @return the generator.
     */
    public GameGenerator getGenerator() {
        return generator;
    }

    /**
     * Returns the score varaible.
     *
     * @return the score variable.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the current level.
     *
     * @return The current level.
     */
    public int getCurrentLvl() {
        return currentLvl;
    }

    /**
     * Returns the timers used to remove the PowerUps.
     *
     * @return timers for removing PowerUps.
     */
    public ArrayList<Timeline> getpUpTimers() {
        return pUpTimers;
    }

    /**
     * Removes PowerUps.
     *
     * @param p is the PowerUp that's removed.
     */
    public void removePowerUp(PowerUp p) {
        ((Group) Main.getStage().getScene().getRoot()).getChildren().remove(p);
        powerUps.remove(p);
    }

    /**
     * Get the background music
     *
     * @return the background music
     */
    public Sound getBackgroundMusic(){
        return bgMusic;
    }

    /*
    * These three methods are for initializing, updating and displaying the UI elements.
    */

    /**
     * Initializes UI elements.
     */
    private void initUIelements(){
        int xOffset = 15;
        int yOffset = 20;
        playerHealthUI = new UI(xOffset, yOffset * 1, UI.playerHealthString());
        playerDamageUI = new UI(xOffset, yOffset * 2, UI.playerDamageString());
        playerLevelUI = new UI(xOffset, yOffset * 3, UI.currentLevelString());
        playerScoreUI = new UI(xOffset, yOffset * 4, UI.playerScoreString());
    }

    /**
     * Updates the UI elements.
     */
    private void updateUI(){
        playerHealthUI.updateText(UI.playerHealthString());
        playerLevelUI.updateText(UI.currentLevelString());
        playerScoreUI.updateText(UI.playerScoreString());
        playerDamageUI.updateText(UI.playerDamageString());
    }

    /**
     * Adds UI elements to root.
     */
    private void addUIElementsToRoot(){
        root.getChildren().add(playerHealthUI);
        root.getChildren().add(playerLevelUI);
        root.getChildren().add(playerScoreUI);
        root.getChildren().add(playerDamageUI);
    }
}