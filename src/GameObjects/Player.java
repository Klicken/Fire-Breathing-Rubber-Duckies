package GameObjects;

import Game.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import static java.lang.Math.abs;

/**
 * Extends the DynamicGameObject class. This is a singleton class used to for managing the main character
 * controlled by the player.
 *
 * @author Martin Karlsson
 * @version 2018-02-21
 */
public class Player extends DynamicGameObject {
    private static Player instance = null;
    private static boolean up, down, left, right;
    private boolean facingRight;
    private Timeline timeline;
    private boolean allowShooting;
    private int shootX;
    private int shootY;

    /** The maxmiumum health value of this object*/
    int maxHealth;

    /**
     * Allocates a new Player object which processes keyboard inputs for movement related actions.
     * Also handles animations for the player character and shooting with arrow keys.
     *
     * @param image         Image to load into the ImageView
     * @param x             x coordinate
     * @param y             y coordinate
     * @param movementSpeed The speed at which this object moves
     * @param health        The health value to initiallize this object with
     * @param damage        The damage value to initiallize this object with
     * @see DynamicGameObject
     */
    private Player(Image image, double x, double y, double movementSpeed, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
        maxHealth = health;
        up = false;
        down = false;
        left = false;
        right = false;
        Image runLeft = new Image("/resources/animations/left/run_left.gif", 60, 0,true, false);
        Image runRight = new Image("/resources/animations/right/run_right.gif", 60, 0,true, false);
        Image idleLeft = new Image("/resources/animations/left/idle_left.png", 60, 0,true, false);
        Image idleRight = new Image("/resources/animations/right/idle_right.png", 60, 0,true, false);
        facingRight = true;
        allowShooting = true;
        shootX = 0;
        shootY = 0;

        this.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode()) {
                        case W: up = true;
                            if (facingRight) {
                                    setImage(runRight);
                                }
                                else {
                                    setImage(runLeft);
                                }
                            break;
                        case S: down = true;
                            if (facingRight) {
                                    setImage(runRight);
                                }
                                else {
                                    setImage(runLeft);
                                }
                            break;
                        case A: left = true;
                            if (right) {
                                    setImage(idleRight);
                                }
                                else {
                                    facingRight = false;
                                    setImage(runLeft);
                                }
                            break;
                        case D: right = true;
                            if (left) {
                                    setImage(idleLeft);
                                }
                                else {
                                    facingRight = true;
                                    setImage(runRight);
                                }
                            break;
                    }
                }
        );

        this.addEventHandler(KeyEvent.KEY_RELEASED,
                event -> {
                    switch (event.getCode()) {
                        case W: up = false;
                            if (!down && !left && !right) {
                                    if (facingRight) {
                                        setImage(idleRight);
                                    }
                                    else {
                                        setImage(idleLeft);
                                    }
                                }
                            break;
                        case S: down = false;
                            if (!up && !left && !right) {
                                    if (facingRight) {
                                        setImage(idleRight);
                                    }
                                    else {
                                        setImage(idleLeft);
                                    }
                                }
                            break;
                        case A: left = false;
                            if (!up && !down && !right) {
                                    if (facingRight) {
                                        setImage(idleRight);
                                    }
                                    else {
                                        setImage(idleLeft);
                                    }
                                }
                            if (right) {
                                    setImage(runRight);
                                }
                            break;
                        case D: right = false;
                            if (!up && !down && !left) {
                                    if (facingRight) {
                                        setImage(idleRight);
                                    }
                                    else {
                                        setImage(idleLeft);
                                    }
                                }
                            if (left) {
                                    setImage(runLeft);
                                }
                            break;
                    }
                }
        );

        /*
         *  Shooting with arrow keys
        this.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode()) {
                        case UP: shootY = -1; break;
                        case DOWN: shootY = 1; break;
                        case LEFT: shootX = -1; break;
                        case RIGHT: shootX = 1; break;
                    }
                }
        );

        this.addEventHandler(KeyEvent.KEY_RELEASED,
                event -> {
                    switch (event.getCode()) {
                        case UP: shootY = 0; break;
                        case DOWN: shootY = 0; break;
                        case LEFT: shootX = 0; break;
                        case RIGHT: shootX = 0; break;
                    }
                }
        );
        */
    }

    /*
     * Shoots a projectile from the Player and disables shooting with arrow keys for a very short duration.
     *
     * @param x The x-axis direction of the projectile
     * @param y The y-axis direction of the projectile
    private void shoot(int x, int y){
        Projectile p = new Projectile(new Image("/resources/animations/projectiles/ball.png", 25, 0, true, false), Player.getInstance().getX() + 15, Player.getInstance().getY() + 15, movementSpeed + 300, new Point2D(Player.getInstance().getX() + x, Player.getInstance().getY() + y), 1, 1);
        ((Group) Main.getStage().getScene().getRoot()).getChildren().add(p);
        Main.getGameHandler().getProjectiles().add(p);
        allowShooting = false;
        timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                e -> allowShooting = true));
        timeline.play();
    }
    */

    /**
     * Used in the game's pause function to make sure the player doesn't continue moving upon
     * resuming the game if a button was pressed but not released during the pause operation.
     */
    public static void stop() {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    /**
     * Gets the singleton instance of this class.
     *
     * @return The singleton instance of this class
     */
    public static Player getInstance() {
        return instance;
    }

    /**
     * Gets the maxHealth of the object instance.
     *
     * @return The value of the maxHealth variable
     */
    public int getMaxHealth(){ return maxHealth; }

    /**
     * Sets the singleton instance of this class to null.
     */
    public static void nullInstance() {
        instance = null;
    }

    /**
     * Singleton constructor for the Player class.
     *
     * @param image         Image to load into the ImageView
     * @param x             x coordinate
     * @param y             y coordinate
     * @param movementSpeed The speed at which this object moves
     * @param health        The health value to initiallize this object with
     * @param damage        The damage value to initiallize this object with
     * @return The singleton instance variable of this class
     */
    public static Player createInstance(Image image, double x, double y, double movementSpeed, int health, int damage) {
        if(instance == null)
            instance = new Player(image, x, y, movementSpeed, health, damage);
        return instance;
    }

    /**
     * Updates the current position of the player based on keyboard input or collisions with other GameObjects.
     *
     * @param time the time between the current and previos frame
     */
    @Override
    public void update(double time) {
        direction = new Point2D(0,0);

        if(up) direction = direction.add(new Point2D(0,-1));
        if(down) direction = direction.add(new Point2D(0,1));
        if(left) direction = direction.add(new Point2D(-1,0));
        if(right) direction = direction.add(new Point2D(1,0));

        constrainToWindow();


        /*
         *  Slows the players movement when moving diagonaly to compensate for multiple speed additions.
         */
        if (abs(direction.getX()) + abs(direction.getY()) > 1)
            setPos(getX() + direction.getX() * movementSpeed * 0.7 * time, getY() + direction.getY() * movementSpeed * 0.7 * time);
        else
            setPos(getX() + direction.getX() * movementSpeed * time, getY() + direction.getY() * movementSpeed * time);

        if(getHealthValue() > maxHealth)
            objectHealth.setHealth(maxHealth);

        /*Shoots a projectile from the Player if any arrow key is pressed or held down.
        if (!(shootX == 0 && shootY == 0) && allowShooting)
            shoot(shootX, shootY);
         */
    }

    /**
     * Constrains the player object to the size of the window, so it can't leave the screen.
     */
    private void constrainToWindow(){
        if(getX() < 0) setPos(0, getY());
        if(getX() > 1280 - getImage().getWidth()) setPos(1280 - getImage().getWidth(), getY());
        if(getY() < 0) setPos(getX(), 0);
        if(getY() > 720 - getImage().getHeight() - 12) setPos(getX(), 720 - getImage().getHeight() - 12);
    }
}
