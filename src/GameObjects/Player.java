package GameObjects;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import static java.lang.Math.abs;

public class Player extends DynamicGameObject
{
    private static Player instance = null;
    private static boolean up, down, left, right;
    private double dualKeyMovementSpeed = movementSpeed * 0.7;
    private Health playerHealth = new Health();
    /*
     *  Constructor that creates a DynamicGameObject that has eventhandlers,
     *  which processes keyboard inputs for movement related actions.
     */
    private Player(Image image, double x, double y, double movementSpeed, int health)
    {
        super(image, x, y, movementSpeed, health);
        up = false;
        down = false;
        left = false;
        right = false;

        this.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode())
                    {
                        case W: up = true; break;
                        case S: down = true; break;
                        case A: left = true; break;
                        case D: right = true; break;
                    }
                }
        );

        this.addEventHandler(KeyEvent.KEY_RELEASED,
                event -> {
                    switch (event.getCode())
                    {
                        case W: up = false; break;
                        case S: down = false; break;
                        case A: left = false; break;
                        case D: right = false; break;
                    }
                }
        );
    }

    public static void stop()
    {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    public static Player getInstance()
    {
        return instance;
    }

    public static void nullInstance()
    {
        instance = null;
    }

    public static Player createInstance(Image image, double x, double y, double movementSpeed, int health)
    {
        if(instance == null)
            instance = new Player(image, x, y, movementSpeed, health);
        return instance;
    }

    /*
     *  Updates the position of the Player.
     */

    @Override
    public void update(double time)
    {
        direction = new Point2D(0,0);



        if(up) direction = direction.add(new Point2D(0,-1));
        if(down) direction = direction.add(new Point2D(0,1));
        if(left) direction = direction.add(new Point2D(-1,0));
        if(right) direction = direction.add(new Point2D(1,0));

        /*
         *  Slows the players movement when moving diagonaly to compensate for multiple speed additions.
         */

        if (abs(direction.getX()) + abs(direction.getY()) > 1)
            setPos(getX() + direction.getX() * dualKeyMovementSpeed * time, getY() + direction.getY() * dualKeyMovementSpeed * time);
        else
            setPos(getX() + direction.getX() * movementSpeed * time, getY() + direction.getY() * movementSpeed * time);
        }
}
