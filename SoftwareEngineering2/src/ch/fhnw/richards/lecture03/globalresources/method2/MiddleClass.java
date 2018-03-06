package ch.fhnw.richards.lecture03.globalresources.method2;

public class MiddleClass {
	public MiddleClass() {
	}

	// Display an instance of LastClass, which
	// also needs access to our shared resource
	public void go() {
		LastClass lc = new LastClass();
	}
}
