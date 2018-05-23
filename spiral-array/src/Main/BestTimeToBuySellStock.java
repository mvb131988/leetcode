package Main;

public class BestTimeToBuySellStock {

	public static void main(String[] args) {
		int[] in = {7, 6, 5, 4, 3, 2, 1};
		
		String s;
		
		System.out.println(new BestTimeToBuySellStock().maxProfit(in));
	}
	
	public int maxProfit(int[] in) {
		int maxProfit = 0;
		int minBuyingPoint = Integer.MAX_VALUE;
		
		for(int i=0; i<in.length; i++) {
			if(in[i]<minBuyingPoint) {
				minBuyingPoint = in[i];
			} else if(in[i]-minBuyingPoint > maxProfit){
				maxProfit = in[i] - minBuyingPoint;
			}
		}
		
		return maxProfit;
	}
	
}
