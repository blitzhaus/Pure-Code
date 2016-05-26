package Model;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class AsciiFunctionEval {
	
	public static AsciiFunctionEval ASCIIFUNCEVAL = null;
	public static AsciiFunctionEval createAsciiFuncEval(){
		if(ASCIIFUNCEVAL == null){
			ASCIIFUNCEVAL = new AsciiFunctionEval();
		}
		return ASCIIFUNCEVAL;
	}
	
	String toEval = "";
	private ArrayList<String> func = new ArrayList<String>();
	private String[] funct = { "sin", "cos", "tan", "exp", "loge",
			"log10", "sqrt", "atan", "max", "min", "const", "seco", "prim" };
	private final String REGEX_NUMERIC = "(-?[0-9]*([0-9]*|[\\.]?[0-9]+))";

	public double main(String s) {
		
		func.clear();
		for (int i = 0; i < funct.length; i++) {
			func.add(funct[i]);
		}
		
		String result = helper(s);

		Double val = AsciiTreeTraversal.createAsciiTreeTrav().main1(result);

		return val;
	}

	public String helper(String str) {

		int len = str.length();
		char c;
		String temp = "";
		int r;
		int flag = 0;
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if (c == '(') {
				temp += "(";
				r = AsciiTreeTraversal.findMatchingRightParen(str, i);
				temp += helper(str.substring(i + 1, r));
				temp += ")";
				i = r;

			} else if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')
					|| (c == '.')) {
				String p = "";
				while (((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')
						|| (c == '.') || (c == ',') || (c == '_'))
						&& i < len) {
					p += Character.toString(c);
					i++;
					if (i < len) {
						c = str.charAt(i);
					}
					flag = 1;
				}
				if (func.contains(p)) {
					int ri = AsciiTreeTraversal.findMatchingRightParen(str, i);
					String result = str.substring(i + 1, ri);
					i = ri + 1;
					if (p.equals("max") || p.equals("min")) {
						String[] array = result.split(",");
						array[1] = AsciiParenthesise.createAsciiParenthesiseInst().main(array[1]);
						double val = Math.max(
								AsciiTreeTraversal.createAsciiTreeTrav().main1(helper(array[0])),
								AsciiTreeTraversal.createAsciiTreeTrav().main1(helper(array[1])));
						temp += Double.toString(val);
					} else if (p.equals("const") || p.equals("seco")
							|| p.equals("prim")) {
						String newResult = p + "("
								+ (int) AsciiTreeTraversal.createAsciiTreeTrav().main1(helper(result))
								+ ")";
						if (AsciiParameterEstimator.hashTable.containsKey(newResult)) {
							temp += AsciiParameterEstimator.hashTable.get(newResult);
						} else {
						}
					} else {
						double val = AsciiTreeTraversal.createAsciiTreeTrav().main1(helper(result));
						String arg = null;
						if (p.equals("loge")) {
							arg = Double.toString(Math.log(val));
						} else if (p.equals("log10")) {
							arg = Double.toString(Math.log10(val));
						} else if (p.equals("exp")) {
							arg = Double.toString(Math.exp(val));
						} else if (p.equals("tan")) {
							arg = Double.toString(Math.tan(val));
						} else if (p.equals("sqrt")) {
							arg = Double.toString(Math.sqrt(val));
						}
						if (temp.length() > 0
								&& temp.charAt(temp.length() - 1) == '-'
								&& arg.charAt(0) == '-') {
							temp = temp.substring(0, temp.length() - 1)
									+ arg.substring(1);
						} else {
							temp += arg;
						}
					}
				} else if (AsciiParameterEstimator.hashTable.containsKey(p)) {
					if (AsciiParameterEstimator.hashTable.get(p).startsWith("-")) {
						temp = temp + "(" + AsciiParameterEstimator.hashTable.get(p) + ")";
					} else
						temp += AsciiParameterEstimator.hashTable.get(p);
				} else if (p.matches(REGEX_NUMERIC)) {
					temp += p;
				} else {
				}
			} else if (c == '+' || c == '-' || c == '*' || c == '/') {
				temp += c;
			}
			if (flag == 1) {
				flag = 0;
				i--;
			}
		}
		return temp;
	}
}