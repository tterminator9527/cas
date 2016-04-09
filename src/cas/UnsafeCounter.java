package cas;

/***
 * 非线程线程安全的计数器
 * @author zq
 *
 */
public class UnsafeCounter {
	private ValueWithoutCas valueWithoutCas = new ValueWithoutCas();
	
	public int getValue(){
		return valueWithoutCas.getCount();
	}
	
	public int increase(){
		valueWithoutCas.increaseCount();
		return valueWithoutCas.getCount();
	}
}
