package runnablePackage;

import counter.UnsafeCounter;

/***
 * 模拟线程:操作非线程安全计数器
 * @author zq
 */
public class RunnableWithoutCAS implements Runnable {
	private static UnsafeCounter unsafeCounter = new UnsafeCounter();
	
	@Override
	public void run() {
		unsafeCounter.increase();
	}

	public static UnsafeCounter getUnsafeCounter(){
		return unsafeCounter;
	}
}
