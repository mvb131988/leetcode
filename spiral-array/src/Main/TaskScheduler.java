package Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {

	public static void main(String[] args) {
		//char[] tasks = {'A','A','A','B','A','C','A','D','A','E','F','F','G','B','L'}; 
		//char[] tasks = new char[] {'A', 'B', 'A', 'A', 'B', 'B'};
		//char[] tasks = {'A', 'A', 'B', 'A', 'B'};
		
		char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E'};
		
		int n = 2;
		
		System.out.println(new TaskScheduler().sequence(tasks, n));
		
		System.out.println((int)'A');
	}
	
	public String sequence(char[] tasks, int idle) {
		String output = "";
		
		int[] iTasks = new int[26];
		for(char c: tasks) {
			iTasks[c - 'A'] += 1;
		}

		Queue<Tuple> heap = new PriorityQueue<>(new TupleComparator());
		for (char c = 'A'; c <= 'Z'; c++) {
			if (iTasks[c - 'A'] != 0) {
				heap.add(new Tuple(c, iTasks[c - 'A']));
			}
		}
		
		while (!heap.isEmpty()) {
			List<Tuple> temp = new ArrayList<>();
			int time = 0;
			while (time <= idle) {
				Tuple current = heap.poll();
				if(current != null) {
					current.counter--;
					temp.add(current);
					output += current.c + " ";
				} else {
					// idle
					boolean localIdle = false;
					for(Tuple t: temp) {
						if(t.counter > 0) {
							localIdle = true;
						}
					}
					if(localIdle) {
						output += "idle ";
					}
				}
				time++;
			}

			for(Tuple t: temp) {
				if(t.counter > 0) {
					heap.add(t);
				}
			}
			
		}
		
		return output;
	} 

	class Tuple {
		char c;
		int counter;
		
		Tuple(char c, int counter) {
			this.c = c;
			this.counter = counter;
		}
		
	}
	
	class TupleComparator implements Comparator<Tuple> {
		
		public int compare(Tuple t1, Tuple t2) {
			if(t1.counter == t2.counter) {
				return 0;
			}
			return t1.counter > t2.counter ? -1: 1;
		}
		
	}
	
}
