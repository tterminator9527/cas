package cas;

/***
 * 模拟操作非线程安全计数器的线程
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
