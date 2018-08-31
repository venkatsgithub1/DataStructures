package learning.ds.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ParenthesisChecker {

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			int testCases = Integer.parseInt(br.readLine());
			while (testCases-- > 0) {
				String str = br.readLine();
				char chArr[] = str.toCharArray();
				Stack<Character> stack = new Stack<>();
				boolean balanced = true;
				for (char sym : chArr) {
					if (sym == '(' || sym == '{' || sym == '[') {
						stack.push(sym);
					} else {
						if (stack.isEmpty()) {
							balanced = false;
							break;
						} else if (sym == '}') {
							if (stack.peek() != '{') {
								balanced = false;
								break;
							}
							stack.pop();
						} else if (sym == ')') {
							if (stack.peek() != '(') {
								balanced = false;
								break;
							}
							stack.pop();
						} else if (sym == ']') {
							if (stack.peek() != '[') {
								balanced = false;
								break;
							}
							stack.pop();
						}
					}
				}
				if (!stack.isEmpty() || !balanced) {
					System.out.println("not balanced");
				} else {
					System.out.println("balanced");
				}
			}
		}
	}

}
