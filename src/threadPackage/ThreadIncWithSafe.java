package threadPackage;

import counter.SafeCounter;

/***
 * 模拟线程:操作线程安全计数器
 * @author zq
 */
public class ThreadIncWithSafe extends Thread{
	SafeCounter safeCounter;
	public ThreadIncWithSafe(SafeCounter safeCounter){
		super();
		this.safeCounter = safeCounter;
	}
	public void run(){
		safeCounter.increase();
	}
}
