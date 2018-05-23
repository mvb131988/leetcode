package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecodeWays {

	private static Map<Integer, Character> map = new HashMap<>();
	
	static {
		map.put(1, 'A');
		map.put(2, 'B');
		map.put(3, 'C');
		map.put(4, 'D');
		map.put(5, 'E');
		map.put(6, 'F');
		map.put(7, 'G');
		map.put(8, 'H');
		map.put(9, 'I');
		map.put(10, 'J');
		map.put(11, 'K');
		map.put(12, 'L');
		map.put(13, 'M');
		map.put(14, 'N');
		map.put(15, 'O');
		map.put(16, 'P');
		map.put(17, 'Q');
		map.put(18, 'R');
		map.put(19, 'S');
		map.put(20, 'T');
		map.put(21, 'U');
		map.put(22, 'V');
		map.put(23, 'W');
		map.put(24, 'X');
		map.put(25, 'Y');
		map.put(26, 'Z');
	}
	
	public static void main(String[] args) {
		String input = "121314";
		
		List<String> decoded = new DecodeWays().decode(input);
		
		for(String s: decoded) {
			System.out.println(s);
		}
	}
	
	private List<String> decode(String input) {
		//null protection
		
		List<String> list = new ArrayList<>();
		//base case 1
		if(input.length() == 1) {
			String decoded = "" + map.get(Character.getNumericValue(input.charAt(0)));
			list.add(decoded);
		}
		
		//base case 2
		if(input.length() == 2) {
			List<String> zero = decode(new String("" + input.charAt(0)));
			List<String> one = decode(new String("" + input.charAt(1)));
			
			list.add(zero.get(0) + one.get(0));
			
			if(Integer.parseInt(input) < 27) {
				list.add("" + map.get(Integer.parseInt(input)));
			}
		}
		
		//general case
		if(input.length() > 2) {
			//case 1: consider last character
			char c = input.charAt(input.length()-1);
			List<String> decoded = decode(minusOne(input));
			List<String> newDecoded = new ArrayList<>();
			for(String d: decoded) {
				newDecoded.add(d + map.get(Character.getNumericValue(c)));
			}
			
			//case 2: consider two last characters
			String s = "" + input.charAt(input.length()-2) + input.charAt(input.length()-1);
			if(Integer.parseInt(s) < 27) {
				decoded = decode(minusTwo(input));
				for(String d: decoded) {
					newDecoded.add(d + map.get(Integer.parseInt(s)));
				}
			}
			
			list = newDecoded;
		}
		
		return list;
	}

	private String minusOne(String s) {
		char[] cArr = s.toCharArray();
		String out = "";
		for(int i=0; i<cArr.length - 1; i++) {
			out += cArr[i];
		}
		return out;
	}
	
	private String minusTwo(String s) {
		char[] cArr = s.toCharArray();
		String out = "";
		for(int i=0; i<cArr.length - 2; i++) {
			out += cArr[i];
		}
		return out;
	}
	
}
