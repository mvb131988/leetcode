package Main;

import java.util.HashMap;
import java.util.Map;

public class IntegerToEnglishWords {

	private static Map<Integer, String> map;
	
	static {
		map = new HashMap<>();
		
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
		map.put(6, "six");
		map.put(7, "seven");
		map.put(8, "eight");
		map.put(9, "nine");
		map.put(10, "ten");
		map.put(11, "eleven");
		map.put(12, "twelve");
		map.put(13, "thirteen");
		map.put(14, "forteen");
		map.put(15, "fifteen");
		map.put(16, "sixteen");
		map.put(17, "seventeen");
		map.put(18, "eightteen");
		map.put(19, "nineteen");
		map.put(20, "twenty");
		map.put(30, "thirty");
		map.put(40, "forty");
		map.put(50, "fifty");
		map.put(60, "sixty");
		map.put(70, "seventy");
		map.put(80, "eightty");
		map.put(90, "ninety");
	}
	
	public static void main(String[] args) {
//		int n = 513;
		int n = 2146234513;
		
		System.out.println(new IntegerToEnglishWords().parse(n));
	}
	
	public String parse(int n) {
		String out = "";
		
		int fourthTriplet = n / 1000000000;
		n = n % 1000000000;
		int thirdTriplet = n / 1000000;
		n = n % 1000000;
		int secondTriplet = n / 1000;
		int firstTriplet = n % 1000;
		
		out += (parseTriplet(fourthTriplet).equals("") ? "" : parseTriplet(fourthTriplet)) + 
			   (parseTriplet(fourthTriplet).equals("") ? "" : " billion ") +
			   (parseTriplet(thirdTriplet).equals("") ? "" : parseTriplet(thirdTriplet)) + 
			   (parseTriplet(thirdTriplet).equals("") ? "" : " million ") +
			   (parseTriplet(secondTriplet).equals("") ? "" : parseTriplet(secondTriplet)) + 
			   (parseTriplet(secondTriplet).equals("") ? "" : " thousand ") + 
			    parseTriplet(firstTriplet);
		
		return out;
	}
	
	public String parseTriplet(int n) {
		String out = "";
		
		if(n == 0) {
			return out;
		}
		
		int thirdPos = n/100;
		if(thirdPos > 0) {
			out += map.get(thirdPos) + " " + "hundred ";
		}
		n -= thirdPos*100;
		
		if(n<20) {
			out += map.get(n);
		} else {
			int secondPos = (n/10)*10;
			int firstPos = n%10;
			out += map.get(secondPos) + " " + map.get(firstPos);
		}
		
		return out;
	}
	
}
