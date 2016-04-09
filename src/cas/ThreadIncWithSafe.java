package cas;

/***
 * 模拟操作线程安全计数器的线程
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
