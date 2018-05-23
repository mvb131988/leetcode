package Main;

import java.util.Stack;

//Longest common subsequence
public class Palindrom {

	public static int invokedTimes = 0;
	
	public static void main(String[] args) {
		String s = "racecar";
		String s1 = "garrabacegecaisrhere";
		String s2 = "garrabacecvlaisrhe";
		
		Context[][] visitMap = new Context[s2.length()][s2.length()];
		for(int i=0; i<visitMap.length; i++) {
			for(int j=0; j<visitMap.length; j++) {
				visitMap[i][j] = null;
			}
		}
		
		Context c = new Palindrom().lcs(s2.toCharArray(), 
							new StringBuilder(s2).reverse().toString().toCharArray(), 
							s2.length()-1, 
							s2.length()-1,
							visitMap);
		
		//construct string from char array;
		
		// compare with k
		// if(s.length - news.length >= k) found
		
		for(int i=0; i<visitMap.length; i++) {
			for(int j=0; j<visitMap.length; j++) {
				if(visitMap[i][j] != null) {
					System.out.print("[" + i + "," + j + "]");
				} else {
					System.out.print("[" + i + "," + j + "] null");
				}
			}
			System.out.println();
		}
		
		System.out.println(invokedTimes);
	}
	
	public Context lcs(char[] s1, char[] s2, int is1, int is2, Context[][] visitMap) {
		if(is1 > -1 && is2 >-1) {
			if(visitMap[is1][is2] == null) {
				invokedTimes++;
			} 
			else {
				return visitMap[is1][is2];
			}
		}
		
		if(is1 == -1 || is2 == -1) {
			return new Context();
		}
			
		if(s1[is1] == s2[is2]) {
			Context c = new Context().copy(lcs(s1,s2,is1-1, is2-1, visitMap));			
			c.length++;
			c.stack.push(s1[is1]);
			
			visitMap[is1][is2] = c;
			
			return c;
		} else {
			Context c1 = new Context().copy(lcs(s1,s2,is1-1, is2, visitMap));
			Context c2 = new Context().copy(lcs(s1,s2,is1, is2-1, visitMap));
			
			if(c1.length > c2.length) {
				visitMap[is1][is2] = c1;
				return c1;
			}
			else {
				visitMap[is1][is2] = c2;
				return c2;
			}
		}
		
	}
	
	class Context {
		
		int length = 0;
		Stack<Character> stack = new Stack<>();
		
		Context copy(Context c) {
			Context cnew = new Context();
			cnew.length = c.length;
			cnew.stack = new Stack<>();
			
			Stack<Character> temp = new Stack<>();
			while(c.stack.size() > 0) {
				temp.push(c.stack.pop());
			}
			
			while(temp.size() > 0) {
				char item = temp.pop();
				c.stack.push(item);
				cnew.stack.push(item);
			}
			
			return cnew;
		}
		
	}
	
}
