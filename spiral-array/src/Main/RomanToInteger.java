package Main;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
	
	public static Map<Integer,String> arabicToRoman = new HashMap<>(); 
	
	public static Map<Character,Integer> romanToArabic = new HashMap<>(); 
	
	static {
		arabicToRoman.put(1, 	"I");
		arabicToRoman.put(4, 	"IV");
		arabicToRoman.put(5, 	"V");
		arabicToRoman.put(9, 	"IX");
		arabicToRoman.put(10, 	"X");
		arabicToRoman.put(40, 	"XL");
		arabicToRoman.put(50, 	"L");
		arabicToRoman.put(90, 	"XC");
		arabicToRoman.put(100, 	"C");
		arabicToRoman.put(400, 	"CD");
		arabicToRoman.put(500, 	"D");
		arabicToRoman.put(900, 	"CM");
		arabicToRoman.put(1000, "M");
		
		romanToArabic.put('I', 1);
		romanToArabic.put('V', 5);
		romanToArabic.put('X', 10);
		romanToArabic.put('L', 50);
		romanToArabic.put('C', 100);
		romanToArabic.put('D', 500);
		romanToArabic.put('M', 1000);
	}
	
	public static void main(String[] args) {
		//int n = 3549;
		//System.out.println(new RomanToInteger().intToRoman(n));
		
		String roman = "MMMCMXCV";
		System.out.println(new RomanToInteger().romanToInt(roman));
	}
	
	public int romanToInt(String roman) {
		int integer = 0;
		
		for(int i=0; i<roman.length()-1; i++) {
			if(romanToArabic.get(roman.charAt(i))>=romanToArabic.get(roman.charAt(i+1))) {
				integer += romanToArabic.get(roman.charAt(i));
			} else {
				integer -= romanToArabic.get(roman.charAt(i));
			}
		}
		integer += romanToArabic.get(roman.charAt(roman.length()-1));
		
		return integer;
	}
	
	public String intToRoman(int n) {
		int n4 = (n/1000)*1000;
		int n3 = (n-n4)/100*100;
		int n2 = (n-n4-n3)/10*10;
		int n1 = n-n4-n3-n2;
		
		int[] numberArray = new int[] {n1,n2,n3,n4};
		
		String romanNumber = "";
		String romanDigit = "";
		for (int j = 1000, k = 3; j >= 1; j /= 10, k--) {
			
			if(numberArray[k] != 0) {
				for (int i = 1; i < 10; i++) {
					romanDigit += arabicToRoman.get(1 * j);
					if (i * j == (5 - 1) * j) {
						romanDigit = arabicToRoman.get(4 * j);
					}
					if (i * j == 5 * j) {
						romanDigit = arabicToRoman.get(5 * j);
					}
					if (i * j == (10 - 1) * j) {
						romanDigit = arabicToRoman.get(9 * j);
					}
					if (numberArray[k] == i * j) {
						break;
					}
				}
			}
			
			romanNumber += romanDigit;
			romanDigit = "";
		}
		
		return romanNumber;
	}
}
