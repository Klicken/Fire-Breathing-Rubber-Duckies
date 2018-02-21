package Game;

import GameObjects.Enemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * A class to generate a new game with enemies, new levels and spawn the enemies.
 *
 * @author Martin Karlsson
 * @version 2018-02-21
 */

public class GameGenerator {
    private ArrayList<Enemy> levelContent;
    private Timeline timeline;
    private int randomX;
    private int randomY;
    private int level;
    private int count;

    /**
     * Constructor of gamegenerator, initializes levelContent, count and level.
     */
    public GameGenerator() {
        levelContent = new ArrayList<>();
        count = 0;
        level = 0;
    }

    /**
     * Fill an ArrayList with 10 enemies with different colors depending on the level with random spawnpositions.
     * There are 6 spawnpositions.
     */
    private void fillLevelContent() {
        levelContent.clear();

        for (int j = 0; j < 10; j++){
            randomX = (int)(Math.random() * 3);
            randomY = (int)(Math.random() * 2);
            levelContent.add(new Enemy(new Image("resources/enemies/blob"+ level%10 +".gif", 40 + Math.random() * 20, 0, true,false),
                                          (randomX * 700) - 60, (randomY * 800) - 60, 100 + level * 10, 2 + level, 10 + level));
        }
    }

    /**
     * Setup the enemies for the level, start the level and spawn one enemy every second for 10 seconds.
     */
    public void startLevel() {
        fillLevelContent();
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
        Main.getGameHandler().getEnemies().add(levelContent.get(count));
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
