package Game;

import GameObjects.Enemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class GameGenerator {
    private int level;
    private ArrayList<Enemy> tmpArray;
    private HashMap<Integer, ArrayList<Enemy>> levelContent;
    private Timeline timeline;

    private int count;
    private int randomX;
    private int randomY;

    public GameGenerator() {
        count = 0;
        level = 0;
        tmpArray = new ArrayList<>();
        levelContent = new HashMap<>();
        fillLevelContent();
    }

    public void startLevel() {
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                e -> spawn()));
        timeline.setCycleCount(10);
    }

    public void startNextLevel() {
        level++;
        startLevel();
    }

    private void spawn() {
        Main.getGameHandler().getEnemies().add(levelContent.get(level).get(count));
        count++;
        if(count == 10)
        {
            count = 0;
        }
    }

    private void fillLevelContent() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++){
                randomX = (int)(Math.random() * 2);
                randomY = (int)(Math.random() * 2);
                tmpArray.add(new Enemy(new Image("resources/enemies/penguin"+ i%4 +".png"), randomX * 1200, randomY * 680, 100*(i+1)/2, 5, 10));
            }

            levelContent.put(i, tmpArray);
            tmpArray = new ArrayList<>();
        }
    }

    public void pause() {
        timeline.pause();
    }

    public void play() {
        timeline.play();
    }

    public int getLevel(){
        return level;
    }
}
