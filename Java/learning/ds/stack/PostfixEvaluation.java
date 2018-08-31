package learning.ds.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PostfixEvaluation {

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			int testCases = Integer.parseInt(br.readLine());
			while (testCases-- > 0) {
				char ch[] = br.readLine().toCharArray();
				Stack<Integer> stack = new Stack<>();
				for (Character num : ch) {
					if (Character.isDigit(num)) {
						stack.push(Integer.parseInt(num + ""));
					} else {
						int val1 = stack.pop();
						int val2 = stack.pop();
						if (num.equals('*')) {
							stack.push(val2 * val1);
						} else if (num.equals('+')) {
							stack.push(val2 + val1);
						} else if (num.equals('-')) {
							stack.push(val2 - val1);
						} else if (num.equals('/')) {
							stack.push(val2 / val1);
						}
					}
				}

				System.out.println(stack.pop());
			}
		}
	}

}
