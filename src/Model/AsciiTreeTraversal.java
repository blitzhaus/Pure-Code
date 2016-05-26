package Model;
import java.util.ArrayList;
import java.util.Stack;

public class AsciiTreeTraversal {

	public static AsciiTreeTraversal ASCIITREETRAV = null;
	public static AsciiTreeTraversal createAsciiTreeTrav(){
		if(ASCIITREETRAV == null){
			ASCIITREETRAV = new AsciiTreeTraversal();
			
		}
		return ASCIITREETRAV;
	}
	
	AsciiNode root;

	ArrayList<AsciiArrObj> inorderArray = new ArrayList<AsciiArrObj>();
	ArrayList<AsciiArrObj> preorderArray = new ArrayList<AsciiArrObj>();
	ArrayList<AsciiArrObj> postorderArray = new ArrayList<AsciiArrObj>();

	public void parseExpression(String exp) {
		inorderArray.clear();
		preorderArray.clear();
		postorderArray.clear();
		//System.gc();
		root = parse(exp);
	}

	// This is the recursive method that does the parsing.
	public  AsciiNode parse(String exp) {
		AsciiNode node = new AsciiNode();

		// expr.charAt(0) is a left paren, find the matching right paren.
		int m = findMatchingRightParen(exp, 1);
		String leftExpr = exp.substring(1, m + 1);

		// Bottom-out condition:
		if (m == exp.length() - 1) {
			// It's at the other end => this is a leaf.
			String operandStr = exp.substring(1, exp.length() - 1);
			node.isLeaf = true;
			node.value = getValue(operandStr);
			return node;
		}

		// Find the left paren to match the rightmost right paren.
		int n = findMatchingLeftParen(exp, exp.length() - 2);
		String rightExpr = exp.substring(n, exp.length() - 1);

		// Recursively parse the left and right substrings.
		node.left = parse(leftExpr);
		node.right = parse(rightExpr);
		node.op = exp.charAt(m + 1);

		return node;
	}

	static int findMatchingRightParen(String s, int leftPos) {
		// Given the position of a left-paren in String s, find the matching
		// right paren.

		Stack<Character> stack = new Stack<Character>();
		stack.push(s.charAt(leftPos));
		for (int i = leftPos + 1; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				stack.pop();
				if (stack.isEmpty()) {
					// This is the one.
					return i;
				}
			}
		}
		// If we reach here, there's an error.
		//System.out.println("ERROR: findRight: s=" + s + " left=" + leftPos);
		return -1;
	}

	int findMatchingLeftParen(String s, int rightPos) {
		Stack<Character> stack1 = new Stack<Character>();
		stack1.push(s.charAt(rightPos));
		for (int i = rightPos - 1; i >= 0; i--) {
			char ch = s.charAt(i);
			if (ch == ')') {
				stack1.push(ch);
			} else if (ch == '(') {
				stack1.pop();
				if (stack1.isEmpty()) {
					// This is the one.
					return i;
				}
			}
		}
		// If we reach here, there's an error.
		/*System.out.println("ERROR: findRight: s = " + s + " left = " + rightPos);*/
		return -1;
	}

	 double getValue(String s) {
		try {
			double k = Double.valueOf(s);
			return k;
		} catch (NumberFormatException e) {
			return -1.0;
		}
	}

	 double main1(String exp1) {
		String exp = addBraces(exp1);
	//	System.out.println("modified Exp : " + exp);
		// String expMod = removeSpaces(exp);
		// System.out.println("modified Exp : " + expMod);
		String str = changeExp(exp);
		AsciiNode node = new AsciiNode();
		
		for(int i = 0; i < str.length() - 1; i++) {
			if(str.charAt(i) == '(' && str.charAt(i + 1) == '(') {
				int ri = findMatchingRightParen(str, i + 1);
				if (str.charAt(ri + 1) == ')'
						&& ri + 1 == findMatchingRightParen(str, i)) {
					str = str.replace(str.substring(i, ri + 2), str.substring(i + 1, ri + 1));
				}
			}
		}
		//System.out.println("modified Exp : " + str);
		node = parse(str);
		
		return Double.parseDouble(Calculator.createCalinst().evaluate(str));
	}

	/*
	 * Helper function to remove spaces in the expression, if any
	 */
	private String removeSpaces(String exp) {
		int i = 0;
		int len = exp.length();
		String modExp = "";
		while (i < len) {
			if (exp.charAt(i) != ' ') {
				modExp = modExp.concat(exp.substring(i, i + 1));
			}
			i += 1;
		}
		return modExp;
	}

	/*
	 * InOrder traversal
	 */
	private void inorderTraversal(AsciiNode node) {
		if (node != null) {
			if (!node.isLeaf) {
				node.value = compute(node);
				node.getValue = true;
			}
			inorderTraversal(node.left);
			if (node.isLeaf) {
				//System.out.println(node.value);
				return;
			} else {
			//	System.out.println(node.op + " nodeValue : " + node.value);
				AsciiArrObj a = new AsciiArrObj();
				a.op = node.op;
				a.value = node.value;
				inorderArray.add(a);
			}
			if (!node.isLeaf) {
				node.value = compute(node);
				node.getValue = true;
			}
			inorderTraversal(node.right);
		}
	}

	/*
	 * PostOrder Traversal
	 */
	private void postOrderTraversal(AsciiNode node) {
		if (node != null) {
			if (!node.isLeaf) {
				node.value = compute(node);
				node.getValue = true;
			}
			postOrderTraversal(node.left);
			if (!node.isLeaf) {
				node.value = compute(node);
				node.getValue = true;
			}
			postOrderTraversal(node.right);
			if (node.isLeaf) {
		//		System.out.println(node.value);
			} else {
			//	System.out.println(node.op + " nodeValue : " + node.value);
				AsciiArrObj a = new AsciiArrObj();
				a.op = node.op;
				a.value = node.value;
				postorderArray.add(a);
			}
		}
	}

	/*
	 * PreOrder Traversal
	 */
	private void preOrderTraversal(AsciiNode node) {
		if (node != null) {
			if (node.isLeaf) {
				//System.out.println(node.value);
			} else {
				//System.out.println(node.op + " nodeValue : " + node.value);
				AsciiArrObj a = new AsciiArrObj();
				a.op = node.op;
				a.value = node.value;
				preorderArray.add(a);
			}
			if (!node.isLeaf) {
				node.value = compute(node);
				node.getValue = true;
			}
			preOrderTraversal(node.left);
			if (!node.isLeaf) {
				node.value = compute(node);
				node.getValue = true;
			}
			preOrderTraversal(node.right);
		}
	}

	public double computeValue(AsciiNode node) {
		return compute(node);
	}

	/*
	 * Computes the final value to be present at the root
	 */
	public double compute(AsciiNode node) {
		if (node.isLeaf || node.getValue) {
			return node.value;
		}

		// Otherwise do left and right, and add.
		double leftValue = compute(node.left);
		double rightValue = compute(node.right);

		if (node.op == '+') {
			return leftValue + rightValue;
		} else if (node.op == '-') {
			return leftValue - rightValue;
		} else if (node.op == '*') {
			return leftValue * rightValue;
		} else {
			return leftValue / rightValue;
		}
	}

	/*
	 * Adds parentheses surrounding very numerical value
	 */
	public String changeExp(String exp) {
		if (!exp.contains("+") && !exp.contains("-") && !exp.contains("*")
				&& !exp.contains("/")) {
			return exp;
		}
		int len = exp.length();
		String newString = "";

		for (int i = 0; i < len; i++) {
			String s = exp.substring(i, i + 1);
			if (exp.charAt(i) == '-' && exp.charAt(i - 1) == '(') {
				s = exp.substring(i, i + 1);
				newString = newString.concat(s);
				i += 1;
				while (exp.charAt(i) != ')') {
					s = exp.substring(i, i + 1);
					newString = newString.concat(s);
					i += 1;
				}
				i -= 1;
			} else if ((exp.charAt(i) >= '0' && exp.charAt(i) <= '9')) {
				newString = newString.concat("(");
				while ((exp.charAt(i) >= '0' && exp.charAt(i) <= '9')
						|| exp.charAt(i) == '.' || exp.charAt(i) == 'E'
						|| (exp.charAt(i) == '-' && exp.charAt(i - 1) == 'E')) {
					s = exp.substring(i, i + 1);
					newString = newString.concat(s);
					i += 1;
				}
				i -= 1;
				newString = newString.concat(")");
			} else {
				newString = newString.concat(s);
			}
		}
		return newString;
	}

	/*
	 * Prints the arrays in inOrder, preOrder and postOrder way
	 */
	public void printArrays() {
		for (int i = 0; i < inorderArray.size(); i++) {
	/*		System.out.print(inorderArray.get(i).op + " "
					+ inorderArray.get(i).value + " ");*/
		}
		//System.out.println();

		for (int i = 0; i < preorderArray.size(); i++) {
			/*System.out.print(preorderArray.get(i).op + " "
					+ preorderArray.get(i).value + " ");*/
		}
		//System.out.println();

		for (int i = 0; i < postorderArray.size(); i++) {
			/*System.out.print(postorderArray.get(i).op + " "
					+ postorderArray.get(i).value + " ");*/
		}
		//System.out.println();
	}

	/*
	 * Adds parentheses to the expression at the start and at the end, if not
	 * present
	 */
	private String addBraces(String exp) {
		String s = null;
		if (exp.charAt(0) == '('
				&& findMatchingRightParen(exp, 0) == exp.length() - 1) {
			return exp;
		} else {
			s = "(" + exp + ")";
			return s;
		}
	}
}