package Model;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsciiParenthesise {
	
	public static AsciiParenthesise ASCIIPARENTHESISE = null;
	public static AsciiParenthesise createAsciiParenthesiseInst(){
		if(ASCIIPARENTHESISE == null){
			ASCIIPARENTHESISE = new AsciiParenthesise();
		}
		return ASCIIPARENTHESISE;
	}
	private ArrayList<Character> charArray = new ArrayList<Character>();
	private char[] operators = { '/', '*', '+', '-' };
	private ArrayList<String> func = new ArrayList<String>();
	private String[] funct = { "sin", "cos", "tan", "exp", "loge",
			"log10", "sqrt", "atan", "max", "min", "const", "seco", "prim" };
	private String REGEX_FUNC = "([a-z0-9]+[//(]+)";

	public String main(String str) {
		
		//These are the steps I tried to reduse the memory pile up
		func.clear();
		charArray.clear();
		//System.gc();
		
		
		for (int i = 0; i < funct.length; i++) {
			func.add(funct[i]);
		}
		for (int i = 0; i < operators.length; i++) {
			charArray.add(operators[i]);
		}
		// Scanner in = new Scanner(System.in);
		// String str = in.nextLine();
		if (str.charAt(0) == '-'
				&& str.charAt(1) == '('
				&& AsciiTreeTraversal.findMatchingRightParen(str, 1) == str.length() - 1)
			str = "(-1)*" + str.substring(1);

			for (int i = 0; i < operators.length; i++) {
				str = getParen(str, operators[i]);
			}
		for (int i = 0; i < str.length() - 1; i++) {
			if (str.charAt(i) == '(' && str.charAt(i + 1) == '(') {
				int ri = AsciiTreeTraversal.findMatchingRightParen(str, i + 1);
				if (str.charAt(ri + 1) == ')'
						&& ri + 1 == AsciiTreeTraversal.findMatchingRightParen(str,
								i)) {
					str = str.replace(str.substring(i, ri + 2),
							str.substring(i + 1, ri + 1));
				}
			}
		}

		//System.out.println("STR : " + str);
		return str;
	}

	/*
	 * Function to parenthesise the string str
	 */
	public String getParen(String str, char op) {
		String opStr = Character.toString(op);
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == op && str.charAt(i - 1) != '(') {
				String left = getLeft(str, i);
			//	System.out.println("left : " + left);
				String right = getRight(str, i);
				//System.out.println("right : " + right);
				//System.out.println("i : " + i);
				if (right.endsWith("(")
						&& str.charAt(i + right.length() + 1) == '-') {
					continue;
				}
				if (str.charAt(i - 1) == ')' || str.charAt(i + 1) == '(') {
					if (str.charAt(i - 1) == ')' && str.charAt(i + 1) == '(') {
						int matchLeft = AsciiTreeTraversal.createAsciiTreeTrav().findMatchingLeftParen(str, i - 1);
						int matchRight = AsciiTreeTraversal.findMatchingRightParen(str, i + 1);
						int testLeft = matchLeft - 1;
						int testRight = matchRight + 1;
						if (testRight < str.length()
								&& str.charAt(testRight) == ')'
								&& testLeft == AsciiTreeTraversal.createAsciiTreeTrav()
										.findMatchingLeftParen(str, testRight)) {
							continue;
						}
					}
					if (str.charAt(i - 1) == ')') {
						int match = AsciiTreeTraversal.createAsciiTreeTrav().findMatchingLeftParen(str,
								i - 1);
						match -= 1;
						if (match >= 0
								&& str.charAt(match) == '('
								&& AsciiTreeTraversal.findMatchingRightParen(str,
										match) <= i + right.length()) {
							continue;
						}
					} else if (str.charAt(i + 1) == '(') {
						int match = AsciiTreeTraversal.findMatchingRightParen(str,
								i + 1);
						match += 1;
						if (match < str.length()
								&& str.charAt(match) == ')'
								&& AsciiTreeTraversal.createAsciiTreeTrav().findMatchingLeftParen(str,
										match) >= i - left.length()) {
							continue;
						}
					}
					str = str.replace(left + opStr + right, "(" + left + opStr
							+ right + ")");
					i += 2;
				} else if (!(left.startsWith("(") && right.endsWith(")"))) {
					str = str.replace(left + opStr + right, "(" + left + opStr
							+ right + ")");
					i += 2;
				}
				//System.out.println("str /: " + str);
			}
		}
		return str;
	}

	/*
	 * Function to get the operand to the left of the operator at the index idx
	 */
	public String getLeft(String str, int idx) {
		String left = "";
		int j = idx - 1;
		if (str.charAt(j) == ')') {
			int le = AsciiTreeTraversal.createAsciiTreeTrav().findMatchingLeftParen(str, j);
			if (le >= 3 && func.contains(str.substring(le - 3, le))) {
				String s = str.substring(le - 3, j + 1);
			//	System.out.println("s : " + s);
				int t = le - 4;
				while (t >= 0 && str.charAt(t) == '(') {
					s = "(" + s;
					t -= 1;
				}
				//System.out.println("s : " + s);
				return s;
			}
			if (le >= 4 && func.contains(str.substring(le - 4, le))) {
				return str.substring(le - 4, j + 1);
			}
			if (le >= 5 && func.contains(str.substring(le - 5, le))) {
				return str.substring(le - 5, j + 1);
			}
			return str.substring(le, j + 1);
		}
		while (j >= 0 && !charArray.contains(str.charAt(j))) {
			left += Character.toString(str.charAt(j));
			j -= 1;
		}
		left = reverse(left);
		if (left.length() >= 3 && func.contains(left.substring(0, 3))) {
			left = left.replace(left.substring(0, 3), "");
		} else if (left.length() >= 4 && func.contains(left.substring(0, 4))) {
			left = left.replace(left.substring(0, 4), "");
			//System.out.println("left : " + left);
		}
		return left;
	}

	/*
	 * Function to get the right operand of the operator at the index idx
	 */
	public String getRight(String str, int idx) {
		Pattern p = Pattern.compile(REGEX_FUNC);
		int len = str.length();
		String right = "";
		int j = idx + 1;
		if (str.charAt(j) == '(') {
			int ri = AsciiTreeTraversal.findMatchingRightParen(str, j);
			return str.substring(j, ri + 1);
		}
		while (j < len && !charArray.contains(str.charAt(j))) {
			right += Character.toString(str.charAt(j));
			j += 1;
		}
		Matcher m = p.matcher(right);
		if (m.find()) {
			int iterator = 0;
			while (iterator < right.length()) {
				if (right.charAt(iterator) == '('
						&& ((right.charAt(iterator - 1) >= 'a' && right
								.charAt(iterator - 1) <= 'z') || (right
								.charAt(iterator - 1) <= '9' && right
								.charAt(iterator - 1) >= '0'))) {
					break;
				}
				iterator += 1;
			}
		/*	System.out.println("iterator : " + iterator + " idx : " + idx
					+ " j : " + j);
		*/	int rig = AsciiTreeTraversal.findMatchingRightParen(str, iterator + idx
					+ 1);
			//System.out.println("rig : " + rig);
			right += str.substring(j, rig + 1);
			int t = rig + 1;
			while (t < str.length() && str.charAt(t) == ')') {
				right += ")";
				t += 1;
			}
			return right;
		}
		if (right.contains("(")) {
			int firstRight = findFirstRightParen(str, j - 1);
			if (firstRight - 3 >= 0
					&& func.contains(str.substring(firstRight - 3, firstRight))) {
				int matchParen = AsciiTreeTraversal.findMatchingRightParen(str,
						firstRight);
				right += str.substring(j, matchParen + 1);
			}
		}
		return right;
	}

	/*
	 * Function to find the position of first opening bracket from the right in
	 * the string str from the position idx
	 */
	public static int findFirstRightParen(String str, int idx) {
		while (idx >= 0) {
			if (str.charAt(idx) == '(') {
				break;
			}
			idx -= 1;
		}
		return idx;
	}

	/*
	 * function to find the position of last opening bracket from the right in
	 * the string str from the position idx
	 */
	public static int findLastRightParen(String str, int idx) {
		while (idx >= 0) {
			if (str.charAt(idx) == '(') {
				break;
			}
			idx -= 1;
		}
		return idx;
	}

	/*
	 * Function to find the first opening bracket from the left in the string
	 * str from the position idx
	 */
	public static int findFirstLeftParen(String str, int idx) {
		while (idx >= 0) {
			if (str.charAt(idx) == '(') {
				break;
			}
			idx += 1;
		}
		return idx;
	}

	/*
	 * Function to reverse the string str
	 */
	public static String reverse(String str) {
		int len = str.length();
		String req = "";
		for (int i = len - 1; i >= 0; i--) {
			req += Character.toString(str.charAt(i));
		}
		return req;
	}
}