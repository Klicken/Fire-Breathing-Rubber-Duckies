package GameObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An implementation of a health class which uses the observable behaviour
 * to observe whenever the wrapped value of the class changes. This is used
 * to control the removal of objects from the screen/memory.
 *
 * @author Tobias Rosengren
 * @version 2018-02-21
 */
public class Health {
    private IntegerProperty health = new SimpleIntegerProperty();

    /**
     * @return health for a DynamicGameObject as an int
     * @see IntegerProperty
     */
    public final int getHealth() {
        return health.get();
    }

    /**
     * Sets the health of a DynamicGameObject to value
     * @param value the amount of HP we want a DynamicGameObject to have
     */
    public final void setHealth(int value) {
        health.set(value);
    }

    /**
     * @return health of a DynamicGameObject as an Integerproperty
     * @see IntegerProperty
     */
    public IntegerProperty healthProperty() {
        return health;
    }
}
