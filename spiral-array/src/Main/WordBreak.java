package Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class WordBreak {

	public static void main(String[] args) {
		
		String s = "leetcode";
		
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");
		
//		String s = "samsungandmangok";
//		
//		List<String> wordDict = new ArrayList<>();
//		wordDict.add("mobile");
//		wordDict.add("samsung");
//		wordDict.add("sam");
//		wordDict.add("sung");
//		wordDict.add("man");
//		wordDict.add("mango");
//		wordDict.add("icecream");
//		wordDict.add("and");
//		wordDict.add("go");
//		wordDict.add("i");
//		wordDict.add("like");
//		wordDict.add("ice");
//		wordDict.add("cream");
		
//		String s = "dabecabde";
//		
//		List<String> wordDict = new ArrayList<>();
//		wordDict.add("a");
//		wordDict.add("ab");
//		wordDict.add("abc");
//		wordDict.add("abcd");
//		wordDict.add("d");
//		wordDict.add("c");
//		wordDict.add("cd");
//		wordDict.add("b");
//		wordDict.add("bc");
//		wordDict.add("bcd");
//		wordDict.add("abe");
//		wordDict.add("eca");
//		wordDict.add("abecabde");
		
		Stack<String> path = new Stack<>();
		CallCounter callNumber = new CallCounter();
		
		System.out.println(new WordBreak().wordBreak(s, wordDict, path, callNumber));
		System.out.println(path.size());
	}

	public boolean wordBreak(String s, List<String> wordDict, Stack<String> path, CallCounter callNumber) {
		Set<String> dictionary = getDictionary(wordDict);
		
		int[] visitMap = new int[s.length()];
		
		return wordBreakInternally(s, dictionary, path, callNumber, visitMap, 0);
    }
	
	public boolean wordBreakInternally(String s, Set<String> dictionary, Stack<String> path, CallCounter callNumber, int[] visitMap, int baseAdr) {
		
		if(s.isEmpty()) {
			return true;
		}
		
		for (int i = 0; i < s.length(); i++) {
			String currentSubstring = s.substring(0, i+1);
			if (dictionary.contains(currentSubstring) ) {
				
				if((baseAdr + currentSubstring.length() < visitMap.length && visitMap[baseAdr + currentSubstring.length()] != -1) ||
				    baseAdr + currentSubstring.length() == visitMap.length) {
				
				
					if(i == s.length()) {
						path.push(currentSubstring);
						return true;
					}
					
					path.push(currentSubstring);
					callNumber.counter++;
					boolean res = wordBreakInternally(s.substring(i+1, s.length()), dictionary, path, callNumber, visitMap, (i+1) + baseAdr);
	
					if (res) {
						return true;
					}
					else {
						
						path.pop();
					}
				}
			}
		}

		// not partitioned from this point
		visitMap[baseAdr] = -1;
		return false;
	}
	
	public Set<String> getDictionary(List<String> wordDict) {
		Set<String> dictionary = new HashSet<>();
		for(String word: wordDict) {
        	dictionary.add(word);
        }
		return dictionary;
	}
	
    static class CallCounter {
    	int counter = 0;
    }
	
}
