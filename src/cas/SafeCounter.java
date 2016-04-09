package cas;

/***
 * 基于CAS实现的非阻塞线程安全计数器
 * @author zq
 *
 */
public class SafeCounter {
	private ValueWithCas valueWithCas = new ValueWithCas();
	
	public int getValue(){
		return valueWithCas.getCount();
	}
	
	public int increase(){
		int v;
		do{
			v = valueWithCas.getCount();
		}while(v != valueWithCas.increaseCountWithCas(v, v + 1));
		return v + 1;
	}
}
