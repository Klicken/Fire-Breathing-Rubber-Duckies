package Game;

import GameObjects.Enemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class to generate a new game with enemies, new levels, start and pause the game
 */

public class GameGenerator {
    private int level;
    private ArrayList<Enemy> tmpArray;
    private HashMap<Integer, ArrayList<Enemy>> levelContent;
    private Timeline timeline;

    private int count;
    private int randomX;
    private int randomY;

    /**
     * Constructor of gamegenerator, initialize the first level and fills a hashmap with levels and enemies.
     */
    public GameGenerator() {
        count = 0;
        level = 0;
        tmpArray = new ArrayList<>();
        levelContent = new HashMap<>();
        fillLevelContent();
    }

    /**
     * Fill a hashmap with level and enemies inside every level with random spawnpositions. The key will be decided by
     * level, and every level will contain 10 enemies.
     */
    private void fillLevelContent() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++){
                randomX = (int)(Math.random() * 2);
                randomY = (int)(Math.random() * 2);
                tmpArray.add(new Enemy(new Image("resources/enemies/blob"+ i%10 +".gif", 40 + Math.random() * 20, 0, true,false), randomX * 1200, randomY * 680, 100*(i+1)/2, 5, 10));
            }

            levelContent.put(i, tmpArray);
            tmpArray = new ArrayList<>();
        }
    }

    /**
     * Start the level, spawn one enemy every 10th second.
     */
    public void startLevel() {
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                e -> spawn()));
        timeline.setCycleCount(10);
    }

    /**
     * Increase level by one and call on startLevel().
     */
    public void startNextLevel() {
        level++;
        startLevel();
    }

    /**
     * Checks which level, get the enemies from that level and add it to the enemylist in gamehandler to be added
     * to the running game.
     */
    private void spawn() {
        Main.getGameHandler().getEnemies().add(levelContent.get(level).get(count));
        count++;
        if(count == 10)
        {
            count = 0;
        }
    }

    /**
     * Pause the timline for spawn of enemies
     */
    public void pause() {
        timeline.pause();
    }

    /**
     * Start the timeline for spawn of enemies
     */
    public void play() {
        timeline.play();
    }
}
