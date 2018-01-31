import javafx.geometry.Point2D;
import static java.lang.Math.abs;
import javafx.scene.image.Image;

/*
*   A class for creating hitboxes which can be used for collison detection
*   between different game objects.
*/

/*
*   position: The coordinate of the top left corner of the hitbox (x, y)
*   width: Sets the width of the hitbox (left to right)
*   height: Sets the height of the hitbox (top to bottom)
*/

public class Hitbox
{
    private Point2D position;
    private double width;
    private double height;

    /*
    *   Constructor that takes an x- and y-coordinate to set the top left corner position
    *   of the hitbox. width and height can be set manually.
    */
    public Hitbox(Point2D position, double width, double height)
    {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    /*
    *   Checks if this hitbox intersects another hitbox.
    *   If it does, the method returns true, else false.
    */

    public boolean intersects(Hitbox other)
    {
        return (abs(position.getX() - other.position.getX()) * 2 < (width + other.width)) &&
                (abs(position.getY() - other.position.getY()) * 2 < (height + other.height));
    }

    public void setPos(Point2D pos)
    {
        position = pos;
    }
}
