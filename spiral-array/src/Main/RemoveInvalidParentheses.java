package Main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class RemoveInvalidParentheses {

	public static int gCounter = 0;
	
	public static void main(String[] args) {
		String input = "(a)())()((";
		//input = "()())()";
		//input = ")(";
		input = "((((((((((";
		
		Set<String> out = new RemoveInvalidParentheses().correct(input);
		System.out.println(out);
		System.out.println(gCounter);
	}
	
	public Set<String> correct(String input) {
		
		Set<String> valid = new HashSet<>(); 
		Set<String> invalid = new HashSet<>();
		
		Queue<String> qValid = new LinkedList<>();
		Queue<String> qInvalid = new LinkedList<>();
		
		Queue<String> qNextLevelInvalid = new LinkedList<>();
		
		invalid.add(input);
		qInvalid.add(input);
		
		while(qValid.size() == 0 || qInvalid.size() > 0) {
			if(qInvalid.size() == 0) {
				qInvalid = qNextLevelInvalid;
				qNextLevelInvalid = new LinkedList<>();
			}
			
			String nextInput = qInvalid.remove();
			invalid.remove(nextInput);
			
			// ----
			for (int i = 0; i < nextInput.length(); i++) {
				
				gCounter++;
				
				if (nextInput.charAt(i) == ')' || nextInput.charAt(i) == '(') {
					String s = substring(nextInput, i);
					boolean b = isValid(s);
					if (b && !valid.contains(s)) {
						valid.add(s);
						qValid.add(s);
					} else if (!b && !invalid.contains(s)) {
						invalid.add(s);
						qNextLevelInvalid.add(s);
					}
				}
			}
			// ----
		}
		
		return valid;
	}
	
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == '(' || s.charAt(i) == ')') {
				if(s.charAt(i) == '(') {
					stack.push('(');
				} else {
					if(stack.size()>0) {
						stack.pop();
					} else {
						return false;
					}
				}
			}
		}
		
		return stack.size() == 0;
	}
	
	public String substring(String s, int index) {
		char[] str = new char[s.length()-1];
		for(int i=0, j=0; i<s.length(); i++){
			if( i != index) {
				str[j] = s.charAt(i);
				j++;
			}
		}
		return new String(str);
	}
	
}
