package counter;

/***
 * 线程安全计数器使用的计数类
 * @author zq
 *
 */
public class ValueWithCas {
	private int count;
	
	//模拟CAS实现计数值增加
	public synchronized int increaseCountWithCas(int exceptValue, int newValue){
		int oldValue = count;
		if(oldValue == exceptValue){
			count = newValue;
		}
		return oldValue;
	}
	
	public synchronized int getCount(){
		return count;
	}
}
