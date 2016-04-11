package runnablePackage;

import counter.SafeCounter;

/***
 * 模拟线程:操作线程安全计数器
 * @author zq
 */
public class RunnableWithCAS implements Runnable {
	private static SafeCounter safeCounter = new SafeCounter();
	
	@Override
	public void run() {
		safeCounter.increase();
	}
	
	public static SafeCounter getSafeCounter(){
		return safeCounter;
	}
}
