package counter;

/***
 * 非线程安全计数器使用的计数类
 * @author zq
 *
 */
public class ValueWithoutCas {
	private int count;
	
	//不安全的计数值增加
	public void increaseCount(){
		count++;
	}
	public int getCount(){
		return count;
	}
}
