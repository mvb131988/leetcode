package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RegularExpressionMatching {

	public static void main(String[] args) {
		
//		String input   = "aeqcccd";
//		String pattern = "*.cd.";
//		
//		System.out.println(new RegularExpressionMatching().isMatch(input, pattern));
//	
//		System.out.println(new RegularExpressionMatching().isMatch("aa","a")); //false
//		System.out.println(new RegularExpressionMatching().isMatch("aa","aa")); //true
//		System.out.println(new RegularExpressionMatching().isMatch("aaa","aa")); //false
//		System.out.println(new RegularExpressionMatching().isMatch("aa", "a*")); //true
//		System.out.println(new RegularExpressionMatching().isMatch("aa", ".*")); //true
//		System.out.println(new RegularExpressionMatching().isMatch("ab", ".*")); //true
//		System.out.println(new RegularExpressionMatching().isMatch("cab", "c*a*b")); //true
//		
//		System.out.println(new RegularExpressionMatching().isMatch("abcabcabc", "*.abc"));
		
		
		String input   = "aeqcccd";
		String pattern = "*.cd.";
		
		System.out.println(new RegularExpressionMatching().isMatch0(input, pattern));
	
		System.out.println(new RegularExpressionMatching().isMatch0("aa","a")); //false
		System.out.println(new RegularExpressionMatching().isMatch0("aa","aa")); //true
		System.out.println(new RegularExpressionMatching().isMatch0("aaa","aa")); //false
		System.out.println(new RegularExpressionMatching().isMatch0("aa", "a*")); //true
		System.out.println(new RegularExpressionMatching().isMatch0("aa", ".*")); //true
		System.out.println(new RegularExpressionMatching().isMatch0("ab", ".*")); //true
		System.out.println(new RegularExpressionMatching().isMatch0("cab", "c*a*b")); //true
		
		System.out.println(new RegularExpressionMatching().isMatch0("abcabcabc", "*.abc"));
	}
	
	public boolean isMatch0(String input, String pattern) {
		int i=0,j=0;
		Context c = isMatch0Internally(input, i, pattern, j, new Context());
		return c.result;
	}
	
	public Context isMatch0Internally(String input, int iI, String pattern, int iP, Context context) {
		if(iP == pattern.length()) {
			if(iI == input.length()) {
				context.result = true;
				return context;
			} else {
				context.result = false;
				return context;
			}
		}
		
		if(iI+1 == input.length()) {
			char c = input.charAt(iI);
			
			if(iP+1 != pattern.length() && '*' != pattern.charAt(iP)) {
				context.result = false;
				return context;
			} else if(iP+1 != pattern.length() && '*' == pattern.charAt(iP)) {
				Context c1 = context.clone();
				isMatch0Internally(input, iI, pattern, iP+1,c1);
			} else {
				if(c == pattern.charAt(iP) || '*' == pattern.charAt(iP) || '.' == pattern.charAt(iP)) {
					context.tokens.put(iP, iI);
					context.result = true;
					return context;
				}
				context.result = false;
				return context;
			}
		}
		
		if(pattern.charAt(iP) != '*' && pattern.charAt(iP) != '.' && pattern.charAt(iP) == input.charAt(iI)) {
			Context c1 = context.clone();
			c1.tokens.put(iP, iI);
			return isMatch0Internally(input, iI+1, pattern, iP+1, c1);
		}
		
		if(pattern.charAt(iP) == '.') {
			Context c1 = context.clone();
			c1.tokens.put(iP, iI);
			return isMatch0Internally(input, iI+1, pattern, iP+1, c1);
		}
		
		if(pattern.charAt(iP) == '*') {
			Context c1 = context.clone();
			c1.result = false;
			for(int i = iI, j=0; i<input.length(); i++, j++) {
				Context c2 = isMatch0Internally(input, iI+j, pattern, iP+1, context.clone());
				if(c2.result == true) {
					return c2;
				}
			}
			return c1;
		}
		
		Context c1 = context.clone();
		c1.result = false;
		return c1;
	}
	
	static class Context {
		boolean result;
		Map<Integer, Integer> tokens = new HashMap<>();
		
		public Context clone() {
			Context c = new Context();
			
			Map<Integer, Integer> temp = new HashMap<>();
			for(Entry<Integer, Integer> e: tokens.entrySet()) {
				temp.put(e.getKey(), e.getValue());
			}
			
			c.result = this.result;
			c.tokens = temp;
			
			return c;
		}
	}
	
	public boolean isMatch(String input, String pattern) {		
		//Set iP1, iP2, iP3
		int iP1 = 0;
		int iP2 = 0;
		int iP3 = 0;
		
		int iI = 0;
		
		boolean match = false;
		
		while(iP3< input.length()) { 
			int countAll = 0;
			int countOne = 0;
			
			//[iP1; iP2)
			while(iP2 < pattern.length()) {
				char c = pattern.charAt(iP2);
				if(c != '*' && c != '.') {
					break;
				}
				iP2++;
				
				if(c == '*') {
					countAll++;
				}
				
				if(c == '.') {
					countOne++;
				}
			}
			
			iP3 = iP2;
			while(iP3 < pattern.length()) {
				char c = pattern.charAt(iP3);
				if(c == '*' || c == '.') {
					break;
				}
				iP3++;
			}
			
			//if P2==P3 check wildcard matching to the string from iI till the end
			if(iP3 == iP2) {
				int iSubstrLength = input.length() - iI;
				if ((iSubstrLength > 0 && countOne > iSubstrLength) || 
					(iSubstrLength > 0 && countOne < iSubstrLength && countAll == 0)) {
					return false;
				}
				return true;
			}
			
			while(iI < input.length()) {
				if(input.charAt(iI) == pattern.charAt(iP2)) {
					break;
				}
				iI++;
			}
			
			match = isMatchInternally(input, iI, pattern, iP2, iP3);
			while(iI < input.length()-1 && !match && input.charAt(iI) == input.charAt(iI+1)) {
				iI++;
				match = isMatchInternally(input, iI, pattern, iP2, iP3);
			}
			
			iI += (iP3-iP2);
			iP2=iP3;
		}
		
		return match;
    }
	
	//iP3 not included
	private boolean isMatchInternally(String input, int iI2, String pattern, int iP2, int iP3) {
		boolean match = true;
		
		for(int i = iP2, j=iI2; i<iP3; i++, j++){
			if (iI2 == input.length() || input.charAt(j) != pattern.charAt(i)) {
				match = false;
				break;
			}
		}
		
		return match;
	}
	
}
