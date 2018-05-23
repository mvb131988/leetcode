package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumber {

	private static Map<Integer, List<Character>> map = new HashMap<>();
	
	static {
		List<Character> l2 = new ArrayList<>();
		l2.add('a');
		l2.add('b');
		l2.add('c');
		
		List<Character> l3 = new ArrayList<>();
		l3.add('d');
		l3.add('e');
		l3.add('f');
	
		List<Character> l4 = new ArrayList<>();
		l4.add('g');
		l4.add('h');
		l4.add('i');
		
		List<Character> l5 = new ArrayList<>();
		l5.add('j');
		l5.add('k');
		l5.add('l');
		
		List<Character> l6 = new ArrayList<>();
		l6.add('m');
		l6.add('n');
		l6.add('o');
		
		List<Character> l7 = new ArrayList<>();
		l7.add('p');
		l7.add('q');
		l7.add('r');
		l7.add('s');
		
		List<Character> l8 = new ArrayList<>();
		l8.add('t');
		l8.add('u');
		l8.add('v');
		
		List<Character> l9 = new ArrayList<>();
		l9.add('w');
		l9.add('x');
		l9.add('y');
		l9.add('z');
		
		map.put(2, l2);
		map.put(3, l3);
		map.put(4, l4);
		map.put(5, l5);
		map.put(6, l6);
		map.put(7, l7);
		map.put(8, l8);
		map.put(9, l9);
	}
 	
	public static void main(String[] args) {
		String s = "23";
		List<String> combinations = new LetterCombinationsPhoneNumber().combinations(s);
		
		for(String sout: combinations) {
			System.out.print(sout + " ");
		}
	}
	
	public List<String> combinations(String in) {
		//null protection
		
		List<String> out = null;
		//base case
		if(in.length() == 1) {
			List<Character> chars = map.get(Character.getNumericValue(in.charAt(0)));
			out = new ArrayList<>();
			
			for(char c: chars) {
				out.add("" + c);
			}
		}
		//general case
		else {
			char c = in.charAt(in.length()-1); 
			List<Character> chars = map.get(Character.getNumericValue(c));
			
			List<String> cms = combinations(minusOne(in));
			List<String> newCms = new ArrayList<>();
			
			for(String s: cms) {
				for(char ch: chars) {
					newCms.add(s + ch);
				}
			}
			
			out = newCms;
		}
		
		return out;
	}
	
	private String minusOne(String in) {
		String temp = "";
		char[] orig = in.toCharArray(); 
		
		for(int i=0; i<orig.length-1; i++) {
			temp += orig[i];
		}
		
		return temp;
	}
	
}
