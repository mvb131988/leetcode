package Main;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

	public static void main(String[] args) {
//		List<Interval> intervals = new ArrayList<>();
//		intervals.add(new Interval(1,2));
//		intervals.add(new Interval(3,5));
//		intervals.add(new Interval(6,7));
//		intervals.add(new Interval(8,10));
//		intervals.add(new Interval(12,16));

//		List<Interval> intervals = new ArrayList<>();
//		intervals.add(new Interval(1, 2));
//		intervals.add(new Interval(3, 5));
//		intervals.add(new Interval(8, 9));
//		intervals.add(new Interval(10, 11));
		
//		List<Interval> intervals = new ArrayList<>();
//		intervals.add(new Interval(1, 2));
//		intervals.add(new Interval(3, 5));
//		intervals.add(new Interval(8, 9));
//		intervals.add(new Interval(7, 10));
		
//		List<Interval> intervals = new ArrayList<>();
//		intervals.add(new Interval(1, 2));
//		intervals.add(new Interval(3, 5));
//		intervals.add(new Interval(7, 8));
//		intervals.add(new Interval(9, 12));
		
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(5, 12));
		
		Interval i = new Interval(4,9);
		
		List<Interval> newIntervals = new InsertInterval().insert(intervals, i);
		
		for(Interval in: newIntervals) {
			System.out.println("[" + in.start + "," + in.end + "]");
		}
	}
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> output = new ArrayList<>();
		
		int iStart = -1;
		int iEnd = -1;
		
		for (int i = 0; i < intervals.size(); i++) {
			if ((intervals.get(i).start <= newInterval.start && intervals.get(i).end >= newInterval.start) || 
				(intervals.get(i).start > newInterval.start && intervals.get(i).end <= newInterval.end)){
				iStart = i;
				break;
			}
		}
		
		for (int i = 0; i < intervals.size(); i++) {
			if (intervals.get(i).start > newInterval.end) {
				iEnd = i;
				break;
			}
		}

		int start = -1;
		int end = -1;
		
		if(iStart == -1) {
			start = newInterval.start;
		} else {
			start = newInterval.start < intervals.get(iStart).start ? newInterval.start : intervals.get(iStart).start;
		}
		
		if(iEnd == -1) {
			end = newInterval.end > intervals.get(intervals.size() - 1).end ? newInterval.end : intervals.get(intervals.size() - 1).end;
		} else {
			end = newInterval.end > intervals.get(iEnd - 1).end ? newInterval.end : intervals.get(iEnd - 1).end;
		}
		
		for(int i=0; i<intervals.size(); i++) {
			if(intervals.get(i).end < start) {
				output.add(intervals.get(i));
			} else {
				break;
			}
		}
		
		output.add(new Interval(start, end));
		
		for(int i=0; i<intervals.size(); i++) {
			if(intervals.get(i).start > end) {
				output.add(intervals.get(i));
			}
		}
		
		return output;
	}
	
	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
	
}
