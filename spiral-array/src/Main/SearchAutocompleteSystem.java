package Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAutocompleteSystem {

	public static void main(String[] args) {
		String[] sentences = new String[] { "i love you", "island", "ironman", "i love leetcode"};
		Integer[] times = new Integer[] { 5, 3, 2, 2};
		
		Searcher s = new Searcher(sentences, times);
		
		List<String> output = s.input('i');
		output = s.input(' ');
		output = s.input('a');
		output = s.input('#');
		
		output = s.input('i');
		output = s.input(' ');
		output = s.input('a');
		output = s.input('#');
		
		output = s.input('a');
		output = s.input('b');
		output = s.input('c');
		output = s.input('#');
		
		output = s.input('a');
		output = s.input('#');
		
		TrieSearcher trieSearcher = new TrieSearcher(sentences, times);
		trieSearcher.add(sentences);
		
		output = trieSearcher.input('i');
		output = trieSearcher.input(' ');
		output = trieSearcher.input('a');
		output = trieSearcher.input('#');
		
		output = trieSearcher.input('i');
		output = trieSearcher.input(' ');
		output = trieSearcher.input('a');
		output = trieSearcher.input('#');
	}

	private static class TrieNode {
		
		char value;
		List<TrieNode> children;
		
		TrieNode() {
			children = new ArrayList<>();
		}
		
		TrieNode(char value) {
			children = new ArrayList<>();
			this.value = value;
		}
		
	}
	
	private static class TrieSearcher {
		
		private TrieNode root;
		private String input = "";
		
		private Map<String, Integer> timeMap = new HashMap<>();
		
		public TrieSearcher(String[] sentences, Integer[] times) {
			for(int i=0; i<sentences.length; i++) {
				timeMap.put(sentences[i], times[i]);
			}
		}
		
		public List<String> input(char c) {
			if(c == '#') {
				addInternally(input);
				
				if(timeMap.containsKey(input)){
					int time = timeMap.get(input).intValue();
					timeMap.put(input, time++);
				} else {
					timeMap.put(input, 1);
				}
				
				input = "";
				return null;
			}
			
			input += c;
			return find(input);
		}
		
		private void collectAll(TrieNode current, List<String> result, String prefix, String suffix) {
			if(current.children.size() == 0) {
				result.add(prefix+suffix);
				return;
			}  
			
			for(int i=0; i<current.children.size(); i++) {
				collectAll(current.children.get(i), result, prefix, suffix+current.children.get(i).value);
			}
		}
		
		private List<String> find(String prefix) {
			TrieNode current = root;
			List<String> candidates = new ArrayList<>();
			
			int match = 0;
			for(int i = 0; i<prefix.length(); i++) {
				int before = match;
				for(int j = 0; j<current.children.size(); j++) {
					if(prefix.charAt(i) == current.children.get(j).value) {
						current = current.children.get(j);
						match++;
					}
				}
				
				if(before == match) {
					break;
				}
			}
			
			if(match==prefix.length()) {
				// use current as starting point
				collectAll(current, candidates, prefix, "");
			}
			
			//SORTING, TRANSFORMATION
			List<TimeValueHolder> outputTimes = new ArrayList<>();
			for(int i=0; i<candidates.size(); i++) {
				outputTimes.add(new TimeValueHolder(candidates.get(i), timeMap.get(candidates.get(i))));
			}
			Collections.sort(outputTimes, (e1,e2) -> {
				if(e1.times < e2.times) {
					return 1;
				}
				if(e1.times > e2.times) {
					return -1;
				}
				return 0;
			});
			
			List<String> result = new ArrayList<>();
			for(int i=0; i<outputTimes.size(); i++) {
				if(i==3) {break;}
				result.add(outputTimes.get(i).value);
			}
			
			return result;
		}
		
		public void add(String[] sentences) {
			for(String s: sentences) {
				addInternally(s);
			}
		}
		
		private void addInternally(String sentence) {
			if(root == null) {
				root = new TrieNode();
			}
			
			TrieNode current = root;
			for(int i=0; i<sentence.length(); i++) {
				boolean exist = false;
				
				for(int j=0; j<current.children.size(); j++) {
					if(current.children.get(j).value == sentence.charAt(i)) {
						current = current.children.get(j);
						exist = true;
					}
				}
				
				if(!exist) {
					TrieNode newTrieNode = new TrieNode(sentence.charAt(i));
					current.children.add(newTrieNode);
					current = newTrieNode;
				}
			}
			
		}
		
	}
	
	private static class TimeValueHolder {
		
		String value;
		int    times;
		
		TimeValueHolder(String value, int times) {
			this.value = value;
			this.times = times;
		}
		
	}
	
	private static class Searcher {

		private String input = "";
		private String[] sentences;
		private Integer[] times;
		
		private List<Integer> list;
		
		public Searcher(String[] sentences, Integer[] times) {
			this.sentences = sentences;
			this.times = times;
		}
		
		private List<String> input(char c) {
			// O(n)
			if(c == '#') {
				boolean insert = true;
				for(int i=0; i<sentences.length; i++) {
					if(input.equals(sentences[i])) {
						times[i] ++;
						insert = false;
						break;
					}
				}
				
				if(insert) {
					sentences = increaseSize(String.class, sentences);
					sentences[sentences.length-1] = input;
					times = increaseSize(Integer.class, times);
					times[sentences.length-1] = 1;
				}
				
				input = "";
				list = null;
				return null;
			}

			input += c;
			
			//sort by times O(n^2)
			if(list == null) {
				list = new ArrayList<>();
				for(int i=0; i<sentences.length; i++) {
					if(c == sentences[i].charAt(0)) {
						if(list.size() == 0) {
							list.add(i);
						} else {
							int insertPos = list.size();
							for(int j=0; j<list.size(); j++) {	
								if(times[j] <= times[i]) {
									insertPos = j;
									break;
								}
							}
							list.add(insertPos, i);
						}
					}
				}
			}
			
			//O(n)
			List<Integer> temp = new ArrayList<>();
			for(Integer i: list) {
				if(sentences[i].length() >= input.length() && c == sentences[i].charAt(input.length()-1)) {
					temp.add(i);
				}
			}
			list = temp;
			
			//O(n)
			List<String> r = new ArrayList<>();
			int counter = 3;
			for(int i=0; i<list.size(); i++) {
				r.add(sentences[list.get(i)]);
				counter--;
				
				if(counter == 0) {
					break;
				}
			}
			
			return r;
		}

		@SuppressWarnings("unchecked")
		public <T> T[] increaseSize(Class<T> c, T[] sentences) {
			T[] temp = (T[]) Array.newInstance(c, sentences.length+1);
			for(int i=0; i<sentences.length; i++) {
				temp[i] = sentences[i];
			}
			return temp;
		}
	}

}
