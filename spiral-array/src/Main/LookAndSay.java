package Main;

public class LookAndSay {

	public static void main(String[] args) {
		
		int n = 10;
		
		String output = new LookAndSay().generate("1",n);
		
		System.out.println(output);
	}
	
	public String generate(String s, int n) {
		
		if(n == 2) {
			return s;
		}
		
		String output = "";
		
		char[] sequence = s.toCharArray();
		if (sequence.length == 1) {
			System.out.print("1, ");
			
			output = "1" + sequence[0];
		} else {
			char c = sequence[0];
			int counter = 1;
			for(int i=1; i<sequence.length; i++) {
				if(sequence[i] != c) {
					output += counter;
					output += c;
					c = sequence[i];
					counter = 1;
				} else {
					counter++;
				}
			}
			output += counter;
			output += c;
		}
		
		System.out.print(output + ", ");
		
		return generate(output, n-1);
	}
	
}
