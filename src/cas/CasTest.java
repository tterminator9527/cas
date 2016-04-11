package cas;

import java.util.concurrent.CountDownLatch;

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
	
	/**
	 * 使用CountDownLatch保证所有子进程完成后再输出结果
	 * */
	public static void doWithRunnable(){
		//操作非线程安全计数器
		final CountDownLatch unsafeEndGate = new CountDownLatch(10000);
		for(int i = 1; i <= 10000; i++){
			Thread t = new Thread(){
				public void run(){
					try{
						try{
							new RunnableWithoutCAS().run();
						}finally{
							unsafeEndGate.countDown();
						}
					}catch(Exception e){
						
					}
				}
			};
			t.start();
		}
//		waitSubThreadComplete();//使main线程等待它启动的所有子进程完成后，打印计数器结果
		try {
			unsafeEndGate.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("doWithRunnable,unsafe result: " + RunnableWithoutCAS.getUnsafeCounter().getValue());
		
		//操作线程安全计数器
		final CountDownLatch safeEndGate = new CountDownLatch(10000);
		for(int i = 1; i <= 10000; i++){
			Thread t = new Thread(){
				public void run(){
					try{
						try{
							new RunnableWithCAS().run();
						}finally{
							safeEndGate.countDown();
						}
					}catch(Exception e){
						
					}
				}
			};
			t.start();
		}
//		waitSubThreadComplete();
		try {
			safeEndGate.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
