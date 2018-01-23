package sample;

import javafx.geometry.Point2D;
import static java.lang.Math.abs;
import javafx.scene.image.Image;

public class Hitbox
{
    private Point2D position;
    int width;
    int height;

    public Hitbox(int x, int y, int width, int height)
    {
        position = new Point2D(x, y);
        this.width = width;
        this.height = height;
    }

    public Hitbox(int x, int y, Image img)
    {
        position = new Point2D(x, y);
        this.width = (int)img.getWidth();
        this.height = (int)img.getHeight();
    }

    public boolean intersects(Hitbox other)
    {
        return (abs(position.getX() - other.position.getX()) * 2 < (width + other.width)) &&
                (abs(position.getY() - other.position.getY()) * 2 < (height + other.height));
    }
}
