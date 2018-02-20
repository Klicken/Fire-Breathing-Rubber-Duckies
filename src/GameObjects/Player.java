package GameObjects;

import Game.Main;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import static java.lang.Math.abs;

public class Player extends DynamicGameObject {
    private static Player instance = null;
    private static boolean up, down, left, right;
    private boolean facingRight;
    int maxHealth = 100;

    /*
     *  Constructor that creates a DynamicGameObject that has eventhandlers,
     *  which processes keyboard inputs for movement related actions.
     */
    private Player(Image image, double x, double y, double movementSpeed, int health, int damage) {
        super(image, x, y, movementSpeed, health, damage);
        up = false;
        down = false;
        left = false;
        right = false;
        Image runLeft = new Image("/resources/animations/left/run_left.gif", 60, 0,true, false);
        Image runRight = new Image("/resources/animations/right/run_right.gif", 60, 0,true, false);
        Image idleLeft = new Image("/resources/animations/left/idle_left.png", 60, 0,true, false);
        Image idleRight = new Image("/resources/animations/right/idle_right.png", 60, 0,true, false);
        facingRight = true;

        this.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode()) {
                        case W: up = true;
                                if (facingRight)
                                    setImage(runRight);
                                else
                                    setImage(runLeft);
                                break;
                        case S: down = true;
                                if (facingRight)
                                    setImage(runRight);
                                else
                                    setImage(runLeft);
                                break;
                        case A: left = true;
                                if (right)
                                    setImage(idleRight);
                                else {
                                    facingRight = false;
                                    setImage(runLeft);
                                }
                                break;
                        case D: right = true;
                                if (left)
                                    setImage(idleLeft);
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
                                    if (facingRight)
                                        setImage(idleRight);
                                    else
                                        setImage(idleLeft);
                                }
                                break;
                        case S: down = false;
                                if (!up && !left && !right) {
                                    if (facingRight)
                                        setImage(idleRight);
                                    else
                                        setImage(idleLeft);
                                }
                                break;
                        case A: left = false;
                                if (!up && !down && !right) {
                                    if (facingRight)
                                        setImage(idleRight);
                                    else
                                        setImage(idleLeft);
                                }
                                if (right)
                                    setImage(runRight);
                                break;
                        case D: right = false;
                                if (!up && !down && !left) {
                                    if (facingRight)
                                        setImage(idleRight);
                                    else
                                        setImage(idleLeft);
                                }
                                if (left)
                                    setImage(runLeft);
                                break;
                    }
                }
        );

        /*
         *  Shooting with arrow keys
         */
        this.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode()) {
                        case UP: shoot(0,-1); break;
                        case DOWN: shoot(0,1); break;
                        case LEFT: shoot(-1,0); break;
                        case RIGHT: shoot(1,0); break;
                    }
                }
        );
    }

    /*
     *  Shooting with arrow keys
     */
    private void shoot(int x, int y){
        Projectile p = new Projectile(new Image("/resources/animations/projectiles/ball.png",25,0,true,false), Player.getInstance().getX() + 15, Player.getInstance().getY() + 15, 400,new Point2D(Player.getInstance().getX() + x, Player.getInstance().getY() + y), 1, 1);
        ((Group)Main.getStage().getScene().getRoot()).getChildren().add(p);
        Main.getGameHandler().getProjectiles().add(p);
    }

    /*
    *   Used in the game's pause function to make sure the player doesn't continue moving upon
    *   resuming the game if a button was pressed but not released during the pause operation
    */
    public static void stop() {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    public static Player getInstance() {
        return instance;
    }

    public int getMaxHealth(){ return maxHealth; }

    public static void nullInstance() {
        instance = null;
    }

    /*
    *   Singleton constructor for the Player class
    */
    public static Player createInstance(Image image, double x, double y, double movementSpeed, int health, int damage) {
        if(instance == null)
            instance = new Player(image, x, y, movementSpeed, health, damage);
        return instance;
    }

    /*
     *  Updates the position of the Player.
     */

    @Override
    public void update(double time) {
        direction = new Point2D(0,0);

        if(up) direction = direction.add(new Point2D(0,-1));
        if(down) direction = direction.add(new Point2D(0,1));
        if(left) direction = direction.add(new Point2D(-1,0));
        if(right) direction = direction.add(new Point2D(1,0));

        /*
         *  Slows the players movement when moving diagonaly to compensate for multiple speed additions.
         */

        if (abs(direction.getX()) + abs(direction.getY()) > 1)
            setPos(getX() + direction.getX() * movementSpeed * 0.7 * time, getY() + direction.getY() * movementSpeed * 0.7 * time);
        else
            setPos(getX() + direction.getX() * movementSpeed * time, getY() + direction.getY() * movementSpeed * time);

        if(getHealthValue() > maxHealth)
            objectHealth.setHealth(maxHealth);
    }

    /*
    *   Constrains the player object to the size of the window, so you can't leave the screen.
    */
    public void constrainToWindow(){
        if(getX() < 0) setPos(0, getY());
        if(getX() > 1280 - getImage().getWidth()) setPos(1280 - getImage().getWidth(), getY());
        if(getY() < 0) setPos(getX(), 0);
        if(getY() > 720 - getImage().getHeight() - 12) setPos(getX(), 720 - getImage().getHeight() - 12);
    }
}
