package GameObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
