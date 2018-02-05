package GameObjects;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import static java.lang.Math.abs;

public class Player extends DynamicGameObject
{
    private static Player instance = null;
    private static boolean up, down, left, right;
    double dualKeyMovementSpeed = movementSpeed * 0.7;

    /*
     *  Constructor that creates a DynamicGameObject that has eventhandlers,
     *  which processes keyboard inputs for movement related actions.
     */
    private Player(Image image, double x, double y, double movementSpeed)
    {
        super(image, x, y, movementSpeed);
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


    public static Player createInstance(Image image, double x, double y, double movementSpeed)
    {
        if(instance == null)
            instance = new Player(image, x, y, movementSpeed);
        return instance;
    }

    /*
     *  Updates the position of the Player.
     */

    @Override
    public void update(double time)
    {
        vx = 0;
        vy = 0;

        if(up) vy -= 1;
        if(down) vy += 1;
        if(left) vx -= 1;
        if(right) vx += 1;

        /*
         *  Slows the players movement when moving diagonaly to compensate for multiple speed additions.
         */
        if (abs(vx) + abs(vy) > 1)
            setPos(getX() + vx * dualKeyMovementSpeed * time, getY() + vy * dualKeyMovementSpeed * time);
        else
            setPos(getX() + vx * movementSpeed * time, getY() + vy * movementSpeed * time);
    }
}
