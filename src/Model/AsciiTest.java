package Model;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsciiTest {
	public static void main(String[] args) {
		String str;
		Scanner in = new Scanner(System.in);
		str = in.nextLine();
		str = removeSpaces(str);
		/*System.out.println("STR : " + str);
		System.out.println(isEquals(str, "COMMENTENDS"));*/
	}

	private static boolean isEquals(String input, String match) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(match, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(input);
		if (m.matches()) {
			return true;
		}
		return false;
	}
	
	private static String removeSpaces(String input) {
		// TODO Auto-generated method stub
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == ' ') {
				input = input.replace(" ", "");
			}
		}
		return input;
	}
}
