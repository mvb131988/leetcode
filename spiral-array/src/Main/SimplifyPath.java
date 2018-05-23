package Main;

import java.util.Stack;

public class SimplifyPath {

	public static void main(String[] args) {
		String input = "/a/./b/../../c/";
		
		new SimplifyPath().simplifyPath(input);
	}
	
	public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();	
        
        int left = -1;
        int right = 0;
		
        for(;;){
			left = nextSlash(path, right);
			right = nextSlash(path, left+1);
			
			if(right == -1) {
				break;
			}
			
			String str = next(path, left, right);
			if(str.equals(".") || str.equals("")) {
				//skip
			} else if(str.equals("..")) {
				st.pop();
			} else {
				st.push(str);
			}
		}
		
		return null;
    }
	
	public int nextSlash(String path, int startPos) {
		for(int i=startPos; i<path.length(); i++) {
			if(path.charAt(i) == '/') {
				return i;
			}
		}
		return -1;
	}
	
	public String next(String path, int startPos, int endPos) {
		String s = "";
		
		for (int i = startPos + 1; i < endPos; i++) {
			s += path.charAt(i);
		}
		
		return s;
	}
	
}
