package ch.fhnw.richards.lecture08_threads;

public class CounterThread2 extends Thread {
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; ++i) {
			threads[i] = new CounterThread2("Counter-" + i);
		}
		for (Thread t : threads) {
			t.start();
		}
		// Sometimes the console loses the last lines of output
		// Write a few extra, when all threads are done
		boolean done = false;
		while (!done) {
			boolean alive = false;
			for (int i = 0; i < 10; ++i) {
				if (threads[i].isAlive()) alive = true;
			}
			done = !alive;
		}
		System.out.println("End - end - end");
	}

	// Counter variable
	private static volatile int counter;
	
	// Object attributes
	private String threadName;

	public CounterThread2(String name) {
		super(name);
		this.threadName = name;
	}

	public void run() {
		for (int i = 0; i < 100000; i++) {
			counter++;
			System.out.println("Count " + counter + " (" + threadName + ")");
		}
	}
}