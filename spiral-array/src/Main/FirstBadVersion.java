package Main;

public class FirstBadVersion {

	public static void main(String[] args) {
		
		int n = 1234;
		
		System.out.println(new FirstBadVersion().firstBadVersion(n));
		
	}
	
	public int firstBadVersion(int n) {
		return internallyFirstBadVersion(1, n);
	}
	
	public int internallyFirstBadVersion(int iStart, int iStop) {
		if(iStop - iStart == 0) {
			return iStart;
		}
		
		int middle = (iStop+iStart)/2;
		boolean isBad = isBadVersion(middle);
		if(isBad) {
			return internallyFirstBadVersion(iStart, middle);
		} 
		return internallyFirstBadVersion(middle+1, iStop);
	}
	
	boolean isBadVersion(int i) {
		if (i<=79) {
			return false;
		}
		return true;
	}
	
}
