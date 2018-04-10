package ch.fhnw.richards.lecture08_threads;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelfStartingThread extends Thread {

	public static void main(String[] args) {
		new SelfStartingThread("woof");
	}

	public SelfStartingThread(String name) {
		super(name);
		this.start();
	}

	public void run() {
		while (true) {
			LocalDateTime time = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			System.out.println(time.format(formatter));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
