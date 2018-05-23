package Main;

public class LeastDisruptiveSubrange {

	public static void main(String[] args) {
		
		int[] a1 = {1, 2, 3, 4, 5};
		int[] a2 = {3, 5, 3};
		
		Context c = new LeastDisruptiveSubrange().compute(a1, a2);
		
		System.out.println(c.disruption);
		for(int i: c.a1SubRange) {
			System.out.print(i + " ");
		}
	}
	
	public Context compute(int[] a1, int[] a2) {
		
		int a1Pos = 0;
		int a1MinPos = 0;
		int disruption = 0;
		int minDisruption = Integer.MAX_VALUE;
		
		for (int j = 0; j < a1.length - a2.length + 1; j++) {
			for (int i = 0; i < a2.length; i++) {
				disruption += Math.abs(a1[a1Pos + i] - a2[i]);
			}
			
			if (minDisruption > disruption) {
				minDisruption = disruption;
				a1MinPos = a1Pos;
			}
			
			a1Pos++;
			disruption = 0;
		}
		
		int[] a1Subrange = new int[a2.length];
		for(int i=0; i<a2.length; i++) {
			a1Subrange[i] = a1[a1MinPos+i];
		}
		
		return new Context(minDisruption, a1Subrange);
	}
	
	public class Context {
		int disruption;
		int[] a1SubRange;
		
		public Context(int disruption, int[] a1SubRange) {
			this.disruption = disruption;
			this.a1SubRange = a1SubRange;
		}
		
	}
	
}
