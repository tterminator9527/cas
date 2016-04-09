package runnablePak;

import cas.UnsafeCounter;

public class RunnableWithoutCAS implements Runnable {
	public static UnsafeCounter unsafeCounter = new UnsafeCounter();
	
	@Override
	public void run() {
		unsafeCounter.increase();
	}

}
