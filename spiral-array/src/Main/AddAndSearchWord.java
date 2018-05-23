package Main;

import java.util.ArrayList;
import java.util.List;

public class AddAndSearchWord {

	public static void main(String[] args) {
		
		WordDictionary wd = new WordDictionary();
		
		wd.addWord("bad");
		wd.addWord("dad");
		wd.addWord("mad");
		wd.addWord("man");
		
		System.out.println(wd.search("pad"));
		System.out.println(wd.search("bad"));
		System.out.println(wd.search(".ad"));
		System.out.println(wd.search("b.."));
		System.out.println(wd.search(".e."));
	}
	
	static class WordDictionary {

		private Node root;
		
	    /** Initialize your data structure here. */
	    public WordDictionary() {
	        
	    }
	    
	    /** Adds a word into the data structure. */
	    public void addWord(String word) {
	    	if(root == null) {
	        	root = new Node();
	        }
	    	
	    	Node current = root;
	    	
	    	for(char c: word.toCharArray()) {
	    		boolean notFound = true;
	    		for(Node child: current.nodes) {
	        		if(child.value == c) {
	        			current = child;
	        			notFound = false;
	        			break;
	        		} 
	        	}
	    		if(notFound) {
	    			current = addCharacter(current, c);
	    		}
	        }
	    }
	    
	    private Node addCharacter(Node parent, char c) {
	    	Node n = new Node();
	    	n.value = c;
	    	n.isTerminal = true;
	    	
	    	parent.isTerminal = false;
	    	parent.nodes.add(n);
	    	
	    	return n;
	    }
	    
	    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	    public boolean search(String word) {
	    	return searchInner(root, word, 0);
	    }
	    
	    public boolean searchInner(Node n, String word, int pos) {
	    	// base case
	    	if(pos == word.length()-1) {
		    	for(Node child: n.nodes) {
		    		if(word.charAt(pos) == '.' && child.isTerminal) {
		    			return true;
		    		}
		    		else if(word.charAt(pos) == child.value && child.isTerminal) {
			    		return true;
			    	}
		    	}
	    	}
	    	//recursive step
	    	else {
	    		if(word.charAt(pos) != '.') {
	    			for(Node child: n.nodes) {
	    				if(child.value == word.charAt(pos)) {
	    					return searchInner(child, word, pos+1);
	    				}
	    			}
	    		} else {
	    			boolean result = false;
	    			for(Node child: n.nodes) {
	    				result = result || searchInner(child, word, pos+1);
	    			}
	    			return result;
	    		}
	    	}
	    	
	    	return false;
	    }
	}
	
	static class Node {
		
		private boolean isTerminal;
		private char value;
		private List<Node> nodes;
		
		Node() {
			nodes = new ArrayList<>();
		}
		
	}
	
}
