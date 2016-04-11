package cas;

import counter.SafeCounter;
import counter.UnsafeCounter;
import runnablePackage.RunnableWithCAS;
import runnablePackage.RunnableWithoutCAS;
import threadPackage.ThreadIncWithSafe;
import threadPackage.ThreadIncWithUnsafe;

public class CasTest {
	public static void main(String[] args) {
		doWithThread();
		System.out.println();
		doWithRunnable();
	}
	
	public static void doWithThread(){
		//操作非线程安全计数器
		UnsafeCounter unsafeCounter = new UnsafeCounter();
		for(int i = 1; i <= 10000; i++){
			Thread t = new ThreadIncWithUnsafe(unsafeCounter);
			t.start();
		}
		waitSubThreadComplete();
		System.out.println("doWithThread, unsafe result: " + unsafeCounter.getValue());
		
		//操作线程安全计数器
		SafeCounter safeCounter = new SafeCounter();
		for(int j = 1; j <= 10000; j++){
			Thread T = new ThreadIncWithSafe(safeCounter);
			T.start();
		}
		waitSubThreadComplete();
		System.out.println("doWithThread, safe result: " + safeCounter.getValue());
	}

	public static void doWithRunnable(){
		//操作非线程安全计数器
		for(int i = 1; i <= 10000; i++){
			Thread t = new Thread(new RunnableWithoutCAS());
			t.start();
		}
		waitSubThreadComplete();//使main线程等待它启动的所有子进程完成后，打印计数器结果
		System.out.println("doWithRunnable,unsafe result: " + RunnableWithoutCAS.getUnsafeCounter().getValue());
		
		//操作线程安全计数器
		for(int i = 1; i <= 10000; i++){
			Thread t = new Thread(new RunnableWithCAS());
			t.start();
		}
		waitSubThreadComplete();
		System.out.println("doWithRunnable,safe result:   " + RunnableWithCAS.getSafeCounter().getValue());
	}
	
	/***
	 * 使main线程等待它启动的所有子进程完成
	 */
	public static void waitSubThreadComplete(){
		try {
			Thread.currentThread().join(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
