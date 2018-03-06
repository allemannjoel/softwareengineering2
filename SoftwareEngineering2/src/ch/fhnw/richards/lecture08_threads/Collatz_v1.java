package ch.fhnw.richards.lecture08_threads;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This example create two threads that simultaneously work with the same ArrayList. Since ArrayList
 * is not thread-safe, we must synchronize the critical parts of the operations.
 * 
 * We first initialize the list with 1000 elements; each initialized to a random integer value
 * between 10 and 1000009.
 * 
 * One thread chooses a random element from the list whose value is greater than 1, and changes its
 * value; this continues as long as the list contains any elements at all. The modification: if odd,
 * multiply by 3n+1; if even, divide by 2. In the long run, the element will reach the value 1. This
 * is known as the Collatz Conjecture.
 * 
 * The other thread traverses the thread using a iterator, and removes any elements with the value
 * of 1.
 * 
 * In the end, the list should be empty; both threads then terminate.
 * 
 * This version of the program has no synchronization, and uses explicit try/catch clauses to catch
 * and report exceptions.
 */
public class Collatz_v1 {
	private ArrayList<Long> numbers = new ArrayList<>();
	private Thread modifier = new Modifier(numbers);
	private Thread cleaner = new Cleaner(numbers);

	public static void main(String[] args) {
		Collatz_v1 collatz = new Collatz_v1();
		collatz.go();
	}

	public Collatz_v1() {
		for (int i = 0; i < 1000; i++) {
			Long newValue = new Long((long) (Math.random() * 1000000 + 10));
			numbers.add(newValue);
		}
	}

	private void go() {
		modifier.start();
		cleaner.start();
	}

	// Pick and modify a single, random entry. Continue until there are no entries left to modify.
	// Note that we must check size inside the loop, as it may be changed before we enter the
	// synchronized block.
	private static class Modifier extends Thread {
		private ArrayList<Long> numbers;
		private long count = 0;

		public Modifier(ArrayList<Long> numbers) {
			super("Modifier");
			this.numbers = numbers;
		}

		@Override
		public void run() {
			try {
				while (numbers.size() > 0) {
					count++;

					int size = numbers.size();

					// Choose value to modify, and modify it
					int index = (int) (Math.random() * size);
					Long value = numbers.get(index);
					if (value > 1)
						numbers.set(index, modifyValue(value));
				}
				System.out.println("Modifier finished after " + count + " executions");
			} catch (Exception e) {
				System.out.println("Modifier crashed: " + e.toString());
			}
		}

		private Long modifyValue(Long oldValue) {
			if (oldValue % 2 == 0) {
				return oldValue / 2;
			} else {
				return 3 * oldValue + 1;
			}
		}
	}

	// Remove all entries with a value of 1, using an iterator
	private static class Cleaner extends Thread {
		private ArrayList<Long> numbers;
		private long count = 0;

		public Cleaner(ArrayList<Long> numbers) {
			super("Cleaner");
			this.numbers = numbers;
		}

		@Override
		public void run() {
			try {
				while (numbers.size() > 0) {
					count++;

					for (Iterator<Long> i = numbers.iterator(); i.hasNext();) {
						Long value = i.next();
						if (value == 1)
							i.remove();
					}

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
					}
				}
				System.out.println("Cleaner finished after " + count + " executions");
			} catch (Exception e) {
				System.out.println("Cleaner crashed: " + e.toString());
			}
		}
	}
}
