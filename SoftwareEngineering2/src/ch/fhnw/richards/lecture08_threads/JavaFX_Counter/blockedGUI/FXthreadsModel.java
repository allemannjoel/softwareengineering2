package ch.fhnw.richards.lecture08_threads.JavaFX_Counter.blockedGUI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;

public class FXthreadsModel {
    private SimpleIntegerProperty value;
    private volatile boolean stop = true;
    
    protected FXthreadsModel() {
        value = new SimpleIntegerProperty(0);
    }

    public SimpleIntegerProperty getValue() {
        return value;
    }
    
    public void startStop() {
        if (stop) {
            stop = false;
            while (!stop) {
                value.set(value.get() + 1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        } else {
            stop = true;
        }
    }
}
