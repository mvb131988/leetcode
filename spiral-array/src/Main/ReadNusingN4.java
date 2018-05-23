package Main;

public class ReadNusingN4 {

	public static void main(String[] args) {
		
		int[] input = {1,2,3,4,5,6,7,8,9,10};
		int n = 6;
		
		int[] out = new ReadNusingN4().read(input, n);
		
		for(int i: out) {
			System.out.print(i + " ");
		}
	}
	
	public int[] read(int[] input, int n) {
		
		int[] buf = new int[4];
		Reader r = new Reader(input);
		
		int fullReadCount = n/4;
		
		int[] output = new int[n];
		int pos = -1;
		int readBytes = 0;
		do {
			readBytes = r.read4(buf);
			for (int j = 0; j < readBytes; j++) {
				if(pos+1 < output.length) { 
					output[++pos] = buf[j];
				}
			}
		} while (readBytes == 4);
		
//		int l = 0;
//		while((l = r.read4(buf)) > 0) {
//			for(int i=0; i<l; i++) {
//				System.out.print(buf[i] + " ");
//			}
//			System.out.println();
//		}
		
		return output;
	}
	
	class Reader {
		
		private int[] input;
		private int pos;
		
		public Reader(int[] in) {
			input = in;
			pos = -1;
		}
		
		int read4(int[] buf) {
			int readBytes = 0;
			
			readBytes = pos + buf.length <= input.length -1 ? buf.length : input.length - (pos + 1);
			
			for(int i = pos+1, j=0; i<pos+1+readBytes; i++, j++) {
				buf[j] = input[i];
			}
			
			pos += buf.length;
					
			return readBytes;
		}
		
	}
	
}
