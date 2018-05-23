package Main;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

	public static void main(String[] args) {
//		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		
		String[] words = {"What","must","be","acknowledgment","shall","be"};
//		
//		String[] words = {"Science","is","what","we","understand","well","enough","to","explain",
//		         		  "to","a","computer.","Art","is","everything","else","we","do"};
		int maxWidth = 16;
		
		List<String> res = new TextJustification().fullJustify(words, maxWidth);
		
		for(String s: res) {
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) != ' ') {
					System.out.print(s.charAt(i));
				} else {
					System.out.print('~');
				}
			}
			System.out.println();
		}
	}
	
	public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> strings = new ArrayList<>();
        
        String s = "";
        int counter = 0;
        int size = 0;
        
        for(int i=0; i<words.length; i++) {
        	if(size + counter + words[i].length() <= maxWidth) {
        		size += words[i].length();
        		counter++;
        	} else {
        		
				int padNumber = counter == 1 ? maxWidth-size : (maxWidth - size) / (counter-1);
				int additionalPadNumber = counter == 1 ? 0 : (maxWidth - size) % (counter-1);
				
				for(int j=i-counter; j<i; j++) {
					s += words[j];
					if(j<i-1) {
						for(int k=0; k<padNumber; k++) { s+= " ";}
						if(additionalPadNumber>0) {
							s+= " ";
							additionalPadNumber--;
						}
					}
				}
				
				// corner case
				if(counter == 1) {
					for (int l = 0; l < maxWidth - size; l++) {
						s += " ";
					}
				}
				
				strings.add(s);
				
				s = "";
				counter = 1;
				size = words[i].length();
        	}
        }
        
        for(int i = words.length - counter; i<words.length; i++) {
        	s += words[i] + " ";
        }
        
        for(int i = size+counter; i<maxWidth; i++) {
        	s += " ";
        }
        
        strings.add(s);
        
        return strings;
    }
	
}
