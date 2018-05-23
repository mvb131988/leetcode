package Main;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "DCBC";
		
		String window = new MinimumWindowSubstring().minWindow(s, t);
		System.out.println(window);
	}
	
	public String minWindow(String input, String pattern) {
		Map<Character, Integer> patternCounter = new HashMap<>();
		
		for(int i=0; i<pattern.length(); i++) {
			if(patternCounter.containsKey(pattern.charAt(i))) {
				patternCounter.put(pattern.charAt(i), patternCounter.get(pattern.charAt(i)) + 1);
			} else {
				patternCounter.put(pattern.charAt(i), 1);
			}
		}
		
		Map<Character, Integer> inputCounter = new HashMap<>();
		
		int matchLength = 0;
		int matchStartIndex = 0;
		
		String minStr = new String(input);
		int strLenght = input.length()+1;
		
		for(int i=0; i<input.length(); i++) {
			if(inputCounter.containsKey(input.charAt(i))) {
				inputCounter.put(input.charAt(i), inputCounter.get(input.charAt(i)) + 1);
			} else {
				inputCounter.put(input.charAt(i), 1);
			}
			
			if (patternCounter.containsKey(input.charAt(i)) &&
				patternCounter.get(input.charAt(i)) >= inputCounter.get(input.charAt(i))) {
				matchLength++;
			}
			
			if(matchLength == pattern.length()) {
				while(!patternCounter.containsKey(input.charAt(matchStartIndex)) || 
					   patternCounter.get(input.charAt(matchStartIndex))<inputCounter.get(input.charAt(matchStartIndex))) 
				{
					inputCounter.put(input.charAt(matchStartIndex), inputCounter.get(input.charAt(matchStartIndex))-1);
					matchStartIndex++;
				}
				
				if(strLenght > i-matchStartIndex+1) {
					strLenght = i-matchStartIndex+1;
					minStr = input.substring(matchStartIndex, i+1);
				}
			}
		}
		
		return strLenght == input.length() + 1 ? "" : minStr;
	}
	
}
