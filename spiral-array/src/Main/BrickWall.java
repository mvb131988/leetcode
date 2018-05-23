package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {

	public static void main(String[] args) {
		int[][] input = new int[][]{{1,2,2,1},
									{3,1,2},
									{1,3,2},
									{2,4},
									{3,1,2},
									{1,3,1,1}};
									
		List<List<Integer>> input1 = new ArrayList<>();
		for(int i=0; i< input.length; i++) {
			List<Integer> row = new ArrayList<>();
			input1.add(row);
			for(int j=0; j< input[i].length; j++) {
				row.add(input[i][j]);
			}
		}
		
		System.out.println(new BrickWall().leastBricks(input1));
	}

	public int leastBricks(List<List<Integer>> wall) {
        if(wall.size() == 0) return 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(List<Integer> list : wall){
            int length = 0;
            for(int i = 0; i < list.size() - 1; i++){
                length += list.get(i);
                map.put(length, map.getOrDefault(length, 0) + 1);
                count = Math.max(count, map.get(length));
            }
        }
        return wall.size() - count;
    }
	
	public int leastCross(int[][] input) {
		int counter = Integer.MAX_VALUE;
		
		int min = Integer.MAX_VALUE;
		int minCount = 0;
		int[] sum = new int[input.length];
		List<Integer> minRows = new ArrayList<>();
		//rowNumber -> minIndex
		Map<Integer, Integer> minIndexes = new HashMap<>();
		
		for(int i=0; i<input.length; i++) {
			sum[i] = input[i][0];
			minIndexes.put(i, 0);
		}

		for(;;) {
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<input.length; i++) {
				if(sum[i] < min) {
					min = sum[i];
					minCount = 1;
					
					minRows.clear();
					minRows.add(i);
				}
				else if(sum[i] == min) {
					minCount++;
					minRows.add(i);
				}
			}
			
			if(minCount == input.length) {
				break;
			}
			
			for (int i = 0; i < minRows.size(); i++) {
				int index = minRows.get(i);
				minIndexes.put(index, (minIndexes.get(index) + 1));
				sum[index] += input[index][minIndexes.get(index)]; 
			}
			
			if(counter > input.length - minCount) {
				counter = input.length - minCount;
			}
		}
		
		return counter;
	}
	
}
