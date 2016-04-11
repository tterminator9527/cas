package threadPackage;

import counter.UnsafeCounter;

/***
 * 模拟线程:操作非线程安全计数器
 * @author zq
 */
public class ThreadIncWithUnsafe extends Thread {
	UnsafeCounter unsafeCounter;
	public ThreadIncWithUnsafe(UnsafeCounter unsafeConunter){
		super();
		this.unsafeCounter = unsafeConunter;
	}
	public void run(){
		unsafeCounter.increase();
	}
}
