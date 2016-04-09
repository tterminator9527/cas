package runnablePak;

import cas.SafeCounter;

public class RunnableWithCAS implements Runnable {
	public static SafeCounter safeCounter = new SafeCounter();
	
	@Override
	public void run() {
		safeCounter.increase();
	}

}
