package cas;

public class CasTest {
	public static void main(String[] args) {
		//模拟10000个进程操作不安全的计数器并打印结果
		UnsafeCounter unsafeCounter = new UnsafeCounter();
		for(int i = 1; i <= 10000; i++){
			Thread t = new ThreadIncWithUnsafe(unsafeCounter);
			t.start();
		}
		System.out.println(unsafeCounter.getValue());//多次运行计数器值均可能出现不同，但这是正常的
		
		//模拟10000个进程操作安全的计数器并打印结果
		SafeCounter safeCounter = new SafeCounter();
		for(int j = 1; j <= 10000; j++){
			Thread T = new ThreadIncWithSafe(safeCounter);
			T.start();
		}
		System.out.print(safeCounter.getValue());//多次运行计数器应该相同，但是为何不相同？？？？
	}

}
