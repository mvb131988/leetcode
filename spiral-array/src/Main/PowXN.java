package Main;

public class PowXN {

	private static int numberOfInvocations = 0;
	
	public static void main(String[] args) {
		System.out.println(new PowXN().pow(2.00000, 1000) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;
		
		System.out.println(new PowXN().logPow(2.00000, 1000) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;
		
		System.out.println("=========");
		
		System.out.println(new PowXN().pow(2.00000, 10) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;
		
		System.out.println(new PowXN().logPow(2.00000, 10) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;

		System.out.println("=========");
		
		System.out.println(new PowXN().pow(2.10000, 3) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;
		
		System.out.println(new PowXN().logPow(2.10000, 3) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;

		System.out.println("=========");
		
		System.out.println(new PowXN().pow(2.00000, -2) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;
		
		System.out.println(new PowXN().logPow(2.00000, -2) + " inv: " + numberOfInvocations);
		numberOfInvocations = 0;
	}
	
	public double pow(double x, int n) {
		numberOfInvocations++;
		
		if(n==0) {
			return 1;
		}
		
		if(n==1) {
			return x;
		}
		
		return n>0 ? x*pow(x, n-1): 1/x*pow(x, n+1);
	}
	
	public double logPow(double x, int n) {
		numberOfInvocations++;
		
		if(n==0) {
			return 1;
		}
		
		if(n < 0) {
			return 1/logPow(x, -n);
		}
		
		double multiplier = logPow(x, n/2);
		
		if(n % 2 == 0) {
			return multiplier*multiplier;
		} else {
			return x*multiplier*multiplier;
		}
	}
	
}
