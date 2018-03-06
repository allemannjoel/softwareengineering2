package ch.fhnw.richards.lecture08_threads.JavaFX_Counter.WrongThread;

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
            OurTask task = new OurTask();
            new Thread(task, "Simulated work").start();
        } else {
            stop = true;
        }
    }

    private class OurTask extends Task<Void> {
        @Override
        protected Void call() throws Exception {
            while (!stop) {
                value.set(value.get() + 1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            return null;
        }
    }
}
