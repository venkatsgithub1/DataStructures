package learning.ds.stack;

import java.util.Stack;

public class InfixToPostfix {
	
	static int precedence(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }

	public static void main(String[] args) {
		String str="a+b*(c^d-e)^(f+g*h)-i";
		String result = "";
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<str.length();i++) {
			Character ch = str.charAt(i);
			if(Character.isLetterOrDigit(ch)) {
				result += ch;
			} else if(ch == '(') {
				stack.push(ch);
			} else if(ch==')') {
				while(!stack.isEmpty() && stack.peek()!='(') {
					result+=stack.pop();
				}
				stack.pop();
			} else {
				while(!stack.isEmpty() && precedence(ch)<=precedence(stack.peek())) {
					result+=stack.pop();
				}
				stack.push(ch);
			}
		}
		
		while(!stack.isEmpty()) {
			result+=stack.pop();
		}
		
		System.out.println(result);
	}
}
