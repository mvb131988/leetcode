package Main;

import java.util.HashMap;
import java.util.Map;

// Wrong solution. Mistake in assumption that index map contains series of values such that
// if ai is not min or max of this series then exist aj and ak such that ai-1 = aj and ai+1 = ak.
// However in case of update value in the middle if series is updated and then is possible that 
// ai without aj or ak appears.
public class LruCacheProblemAttempt1 {

	public static void main(String[] args) {
		LruCache cache = new LruCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    					// evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    					// evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
		
		
		System.out.println("=========================");
		System.out.println("7 0 1 2 0 3 0 4 2 3 0 3 2");
		System.out.println("=========================");
		cache = new LruCache(4);
		cache.put(7,7);
		cache.put(0,0);
		cache.put(1,1);
		cache.put(2,2);
		cache.put(0,0);
		cache.put(3,3);
		cache.put(0,0);
		cache.put(4,4);
//		cache.put(2,2);cache.put(3,3);cache.put(0,0);cache.put(3,3);
//		cache.put(2,2);
	}

	public static class LruCache {

		private Map<Integer, Integer> values;

		// timestamp -> key
		private Map<Integer, Integer> indexToValue;

		// key -> timestamp
		private Map<Integer, Integer> valueToIndex;
		
		private int minTimestamp;
		
		private int maxTimestamp;
		
		private int capacity;
		
		public LruCache(int capacity) {
			values = new HashMap<>();
			indexToValue = new HashMap<>();
			valueToIndex = new HashMap<>();
			
			minTimestamp = 0;
			maxTimestamp = 0;
			
			this.capacity = capacity;
		}

		public int get(int key) {
			Integer value = values.get(key);
			if(value == null) {
				return -1;
			}
			
			update(key);
			
			return value;
		}

		private void update(int key) {
			int timestamp = maxTimestamp;
			
			int oldTimestamp = valueToIndex.get(key);
			if(oldTimestamp == minTimestamp) {
				minTimestamp++;
			}
			
			indexToValue.remove(oldTimestamp);
			indexToValue.put(timestamp, key);
			valueToIndex.put(key, timestamp);
			
			maxTimestamp++;
		}
		
		private void add(int key, int value) {
			int timestamp = maxTimestamp;
			indexToValue.put(timestamp, key);
			valueToIndex.put(key, timestamp);
			values.put(key, value);
			maxTimestamp++;
		}
		
		private void evict(int timestamp) {
			int value = indexToValue.get(timestamp);
			indexToValue.remove(timestamp);
			valueToIndex.remove(value);
			values.remove(value);
			
			System.out.println("EVICT:" + value);
		}
		
		public void put(int key, int value) {
			Integer persistedValue = values.get(key);
			
			//update 
			if(persistedValue != null) {
				update(key);
				return;
			} 
			
			//or insert when capacity limit isn't reached
			if(persistedValue == null && values.size() < capacity) {
				add(key, value);
				return;
			}
			
			//evict least recently used value
			evict(minTimestamp);
			minTimestamp++;
			add(key, value);
		}

	}

}
