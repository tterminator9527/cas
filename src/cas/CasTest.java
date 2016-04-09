package cas;

import runnablePak.RunnableWithCAS;
import runnablePak.RunnableWithoutCAS;

public class CasTest {
	public static void main(String[] args) {
		doWithThread();
		doWithRunnable();
	}
	
	public static void doWithThread(){
		//模拟10000个进程操作不安全的计数器并打印结果
				UnsafeCounter unsafeCounter = new UnsafeCounter();
				for(int i = 1; i <= 10000; i++){
					Thread t = new ThreadIncWithUnsafe(unsafeCounter);
					t.start();
				}
				System.out.println("doWithThread, unsafe result: " + unsafeCounter.getValue());//多次运行计数器值均可能出现不同，但这是正常的
				
				//模拟10000个进程操作安全的计数器并打印结果
				SafeCounter safeCounter = new SafeCounter();
				for(int j = 1; j <= 10000; j++){
					Thread T = new ThreadIncWithSafe(safeCounter);
					T.start();
				}
				System.out.println("doWithThread, safe result: " + safeCounter.getValue());//多次运行计数器应该相同，但是为何不相同？？？？
	}

	public static void doWithRunnable(){
		for(int i = 1; i <= 10000; i++){
			Thread t = new Thread(new RunnableWithoutCAS());
			t.start();
		}
		System.out.println("doWithRunnable,unsafe result: " + RunnableWithoutCAS.unsafeCounter.getValue());
		
		for(int i = 1; i <= 10000; i++){
			Thread t = new Thread(new RunnableWithCAS());
			t.start();
		}
		System.out.println("doWithRunnable,safe result:   " + RunnableWithCAS.safeCounter.getValue());
	}
}
