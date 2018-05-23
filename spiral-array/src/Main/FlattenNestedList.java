package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlattenNestedList {

	public static void main(String[] args) {
		List<NestedInteger> list = new ArrayList<>();

//		List<NestedInteger> ni1 = new ArrayList<NestedInteger>();
//		ni1.add(new NestedInteger(1));
//		ni1.add(new NestedInteger(1));
//		list.add(new NestedInteger(ni1));
//		
//		list.add(new NestedInteger(2));
//		
//		List<NestedInteger> ni3 = new ArrayList<NestedInteger>();
//		ni3.add(new NestedInteger(1));
//		ni3.add(new NestedInteger(1));
//		list.add(new NestedInteger(ni3));
		
		List<NestedInteger> list1 = new ArrayList<NestedInteger>();
		List<NestedInteger> list2 = new ArrayList<NestedInteger>();
		List<NestedInteger> list3 = new ArrayList<NestedInteger>();
		List<NestedInteger> list4 = new ArrayList<NestedInteger>();
		list1.add(new NestedInteger(1));
		list1.add(new NestedInteger(list2));
		list2.add(new NestedInteger(4));
		list2.add(new NestedInteger(list3));
		list2.add(new NestedInteger(list4));
		list3.add(new NestedInteger(6));
		list4.add(new NestedInteger(7));
		list4.add(new NestedInteger(8));
		list4.add(new NestedInteger(new ArrayList<>()));
		
		List<Integer> output = new ArrayList<>();
		
		NestedIterator i = new NestedIterator(list1);
		while (i.hasNext()) {
			output.add(i.next());
		}
		
		System.out.println(output);
	}
	
	public static class NestedIterator implements Iterator<Integer> {

		private NestedInteger nestedInteger;
		
	    public NestedIterator(List<NestedInteger> nestedList) {
	        this.nestedInteger = new NestedInteger(nestedList);
	    }

	    @Override
	    public Integer next() {
	    	Integer i = null;
	    	for(NestedInteger ni: nestedInteger.getList()) {
	    		i = next(nestedInteger, ni);
	    		if(i != null) {
	    			break;
	    		}
	    	}
	        return i;
	    }

	    private Integer next(NestedInteger parent, NestedInteger ni) {
	    	if(ni.isInteger()) {
	    		//parent of NestedInteger must be a list
	    		parent.getList().remove(ni);
	    		return ni.getInteger();
	    	} 
	    	else if(ni.getList().size() != 0){
	    		for(NestedInteger niE: ni.getList()) {
	    			Integer i = next(ni, niE);
	    			if(i != null) {
	    				return i;
	    			}
	    		}
	    	}
	    	return null;
	    }
	    
	    @Override
	    public boolean hasNext() {
	    	for(NestedInteger i: nestedInteger.getList()) {
	    		if(hasNext(i)) {
	    			return true;
	    		}
	    	}
	    	
	    	return false;
	    }
	    
	    private boolean hasNext(NestedInteger i) {
	    	if(i.isInteger()) {
    			return true;
    		} else {
    			for(NestedInteger ni: i.getList()) {    				
    				if(hasNext(ni)) {
    					return true;
    				}
    			}
    		}
	    	return false;
	    }
	    
	}
	
	public static class NestedInteger {

		private Integer i;
		private List<NestedInteger> list;
		private boolean isI;
		
		public NestedInteger(Integer i) {
			this.i = i;
			this.isI = true;
		}
		
		public NestedInteger(List<NestedInteger> list) {
			this.list = list;
			this.isI = false;
		}
		
		public boolean isInteger() {
			return isI;
		}

		public Integer getInteger() {
			return i;
		}

		public List<NestedInteger> getList() {
			return list;
		}

	}
	
}
