import java.util.*;

public class CountMap<K, V> extends HashMap<K, V>{
	private static int count = 0;
	public static int getCount() {
		return count;
	}

	public V get(Object key) {
		count++;
		return super.get(key);
	}

	public V put(K key, V value) {
		count++;
		return super.put(key, value);
	}
}