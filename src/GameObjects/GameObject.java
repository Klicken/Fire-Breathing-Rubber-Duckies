package GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
*   The top class of the GameObject hierarchy. GameObject -> DynamicGameObject -> (Player, Enemy, Projectile).
*   Extends the ImageView class in order to make use of the JavaFX node system, the node system is then used to
*   render and update all the objects in the main game-loop found in the GameHandler class.
*
*   A positon is also added to the class to keep track of it's 2D position vector.
*/
public class GameObject extends ImageView {
    Point2D positon;

    /*
     *  Constructor that creates an object at given position, dimensions and hitbox are set according to
     *  the dimensions of the image-file.
     */
    GameObject(Image image, double x, double y) {
        super.setImage(image);
        setX(x);
        setY(y);
        positon = new Point2D(x, y);
    }

    /*
     *  Sets the position of the GameObject (The top-left corner)
     *  and updates it's position vector.
     */
    void setPos(double x, double y) {
        setX(x);
        setY(y);
        positon = new Point2D(x, y);
    }

    public Point2D getPositon() {
        return positon;
    }

    /*
    *   Calculates the center positon of the Image assoiated with the GameObject
    */
    public Point2D getCenter() {
        return new Point2D(getX() + getImage().getWidth()/2, getY() + getImage().getWidth()/2);
    }

    /*
    *   Determines if two gameObjects overlap based on their radius. The radius is calculated according
    *   to the width of the image.
    */
    public boolean intersects(GameObject other) {
        double distance = getCenter().distance(other.getCenter());
        return (distance < getImage().getWidth()/2 + other.getImage().getWidth()/2) ;
    }
}
