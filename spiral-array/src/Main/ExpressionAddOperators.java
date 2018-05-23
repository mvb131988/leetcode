package Main;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

	public static void main(String[] args) {
		String num = "123";
		int target = 6;

//		String num = "232";
//		int target = 8;
		
//		String num = "105";
//		int target = 5;

//		String num = "00";
//		int target = 0;
		
//		String num = "222211";
//		int target = 4;
		
//		String num = "3456237490";
//		int target = 9191;
		 
		List<String> res = new ExpressionAddOperators().evaluate(num, target);

		for (String str : res) {
			System.out.println(str);
		}
	}

	public List<String> evaluate(String num, int target) {
		List<String> expr = new ArrayList<>();
		evaluate(num, -1, 0, target, expr);
		return expr;
	}

	public void evaluate(String num, int pos, int actual, int target, List<String> expr) {
		if (pos >= num.length() - 1) {
			if (actual == target) {
				expr.add(num);
			}
			return;
		}
		
		if (pos == -1) {
			for (int i = 0; i < num.length()-1; i++) {
				int additioning = Integer.parseInt(num.substring(0, i + 1));
				evaluate(num, i, additioning, target, expr);
			}
			return;
		}

		for (int j = pos + 2; j <= num.length(); j++) {
			String current = num.substring(pos + 1, j);
			
			//skip zeroes
			if(!(current.charAt(0) == '0' && current.length() > 1)) {
				int iCurrent = Integer.parseInt(current);
				int actual1 = actual + iCurrent;
				int actual2 = actual - iCurrent;
				int actual3 = multiplication(num, pos, actual, iCurrent);
	
				String num1 = num.substring(0, pos + 1) + '+' + num.substring(pos + 1, num.length());
				String num2 = num.substring(0, pos + 1) + '-' + num.substring(pos + 1, num.length());
				String num3 = num.substring(0, pos + 1) + '*' + num.substring(pos + 1, num.length());
	
				evaluate(num1, j, actual1, target, expr);
				evaluate(num2, j, actual2, target, expr);
				evaluate(num3, j, actual3, target, expr);
			}
		}
	}
	
	public int multiplication(String num, int pos, int actual, int factor) {
		int start = pos;
		while(start != -1 && num.charAt(start) != '+' && num.charAt(start) != '-') {
			start--;
		}

		int res = 1;
		String current = "";
		for(int i=start+1; i<=pos; i++) {
			if(num.charAt(i) == '*') {
				res *= Integer.parseInt(current);
			} else {
				current += num.charAt(i);
			}
		}
		res *= Integer.parseInt(current);
		
		if(start == -1) {
			res = actual*factor;
		} else {
			res = num.charAt(start) == '+' ? (actual - res) + res*factor: (actual + res) - res*factor;
		}
		
		return res;
	}

}
