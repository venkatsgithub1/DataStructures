package learning.ds.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BracketNumbers {

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			int testCases = Integer.parseInt(br.readLine());
			while (testCases-- > 0) {
				String input = br.readLine();
				Stack<Integer> stack = new Stack<>();
				char chArr[] = input.toCharArray();
				int counter = 1;
				String output = "";
				for (char ch : chArr) {
					if (ch == '(') {
						stack.push(counter);
						output += counter + " ";
						counter++;
					} else if (ch == ')') {
						output += stack.pop() + " ";
					}
				}

				System.out.println(output.trim());
			}
		}
	}

}
