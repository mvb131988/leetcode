package Main;

import java.util.ArrayList;
import java.util.List;

public class CalendarConflict {

	public static void main(String[] args) {
		
		int[][] calendar = {{1,2,1},
							{3,5,2},
							{4,6,3},
							{7,10,4},
							{8,11,5},
							{10,12,6},
							{13,14,7},
							{13,14,8}};
		
		Integer[] conflicts = new CalendarConflict().find(calendar);
		
		for(int i=0; i<conflicts.length; i++) {
			System.out.print(conflicts[i] + " ");
		}
	}
	
	public Integer[] find(int[][] calendar) {
		
		List<Integer> tempConflicts = new ArrayList<>();
		List<Integer> conflicts = new ArrayList<>();
		
		int maxEnd = calendar[0][1];
		tempConflicts.add(calendar[0][2]);
		
		for(int i=1; i<calendar.length; i++) {
			if(maxEnd <= calendar[i][0]) {
				//no conflicts case
				
				//flush previously found conflicts
				if(tempConflicts.size() > 1) {
					conflicts.addAll(tempConflicts);
				}
				
				tempConflicts.removeAll(tempConflicts);
				
			}
			
			maxEnd = maxEnd > calendar[i][1] ? maxEnd : calendar[i][1];
			tempConflicts.add(calendar[i][2]);
		}
		
		if(tempConflicts.size() > 1) {
			conflicts.addAll(tempConflicts);
		}
		
		return conflicts.toArray(new Integer[conflicts.size()]);
	}
	
}
