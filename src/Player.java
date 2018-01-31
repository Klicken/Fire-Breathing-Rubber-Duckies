import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Player extends DynamicGameObject
{

    public Player(Image image, double x, double y, double movementSpeed)
    {
        super(image, x, y, movementSpeed);

        this.addEventHandler(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode())
                    {
                        case W: this.setDirectionY(-1); break;
                        case S: this.setDirectionY(1); break;
                        case A: this.setDirectionX(-1); break;
                        case D: this.setDirectionX(1); break;
                    }
                }
        );
        this.addEventHandler(KeyEvent.KEY_RELEASED,
                event -> {
                    switch (event.getCode())
                    {
                        case W: this.setDirectionY(0); break;
                        case S: this.setDirectionY(0); break;
                        case A: this.setDirectionX(0); break;
                        case D: this.setDirectionX(0); break;
                    }
                }
        );

    }
}
