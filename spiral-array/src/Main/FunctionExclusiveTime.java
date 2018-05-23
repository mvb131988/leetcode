package Main;

import java.util.ArrayList;
import java.util.List;

public class FunctionExclusiveTime {
	
	public static int START = 1;
	public static int END = 0;
	
	public static void main(String[] args) {
		int n = 6;
		
		List<String> logs = new ArrayList<>();
		logs.add("0:start:0");
		logs.add("1:start:2");
		logs.add("1:end:3");
		logs.add("2:start:4");
		logs.add("2:end:5");
		logs.add("0:end:9");
		logs.add("3:start:10");
		logs.add("4:start:11");
		logs.add("5:start:13");
		logs.add("5:end:15");
		logs.add("4:end:19");
		logs.add("3:end:20");
		
		logs = new ArrayList<>();
		logs.add("0:start:0");
		logs.add("1:start:2");
		logs.add("1:end:5");
		logs.add("0:end:6");
		
		new FunctionExclusiveTime().exclusiveTime(n, logs);
	}
	
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] time = new int[n];
		
		String[] prevLog = logs.get(0).split(":");
		int prevTime = Integer.parseInt(prevLog[2]);
		
		String[] nowLog = null;
		for(int i=1; i < logs.size(); i++) {
			nowLog = logs.get(i).split(":");
			
			if(nowLog[1].equals("start")) {
				int startTime = prevTime;
				int endTime = Integer.parseInt(nowLog[2]);
				time[Integer.parseInt(prevLog[0])] += endTime - startTime;
				prevTime = endTime;
			}
			else {
				int startTime = prevTime;
				int endTime = Integer.parseInt(nowLog[2]);
				time[Integer.parseInt(nowLog[0])] += endTime - startTime + 1;
				prevTime = endTime + 1;
			}
			
			prevLog = nowLog;
		}
		
        return time;
    }
	
	public int[] getFunctionState(String s) {
		int[] state = new int[3];
		char[] chs = s.toCharArray();
		
		// find id
		int i = 0;
		while(chs[i] != ':') {
			i++;
		}
		char[] id = new char[i];
		for(int ii=0; ii<i; ii++) {
			id[ii] = chs[ii];
		}
		state[0] = Integer.parseInt(new String(id)); 
		
		// find start or end
		int j = ++i;
		while(chs[j] != ':') {
			j++;
		}
		char[] sOrE = new char[j-i];
		for(int iii=0,ii=i; ii<j; ii++,iii++) {
			sOrE[iii] = chs[ii];
		}
		state[1] = new String(sOrE).equals("end") ? END : START;
		
		//find time
		i = j;
		j++;
		char[] time = new char[chs.length - j];
		for(int iii=0; j < chs.length; j++,iii++) {
			time[iii] = chs[j];
		}
		state[2] = Integer.parseInt(new String(time)); 
		
		return state;
	}
	
}
