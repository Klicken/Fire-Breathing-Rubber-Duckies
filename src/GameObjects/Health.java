package GameObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
*   An implementation of a health class which uses the observable behaviour
*   to observe whenever the wrapped value of the class changes. This is used
*   to control the removal of objects from the screen/memory.
*/
public class Health {
    private IntegerProperty health = new SimpleIntegerProperty();

    public final int getHealth() {
        return health.get();
    }

    public final void setHealth(int value) {
       health.set(value);
    }

    public IntegerProperty healthProperty() {
        return health;
    }
}
