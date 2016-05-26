package Model;

import java.util.StringTokenizer;

public class DefaultToPreferredUnitConversion {

	public DefaultToPreferredUnitConversion() {

	}

	public double unitConversion(String defaultUnit, String preferredUnit) {
		double convertAmount = 1;
		char[] defaultUnitCharArray;
		char[] preferredUnitCharArray;
		
		defaultUnitCharArray = defaultUnit.toCharArray();
		preferredUnitCharArray = preferredUnit.toCharArray();
		
		int defaultUnitLength = defaultUnitCharArray.length;
		int preferredUnitLength = preferredUnitCharArray.length;
	
		int maxLength = defaultUnitLength;
		if (defaultUnitLength < preferredUnitLength)
			maxLength = defaultUnitLength;
		
		StringTokenizer defaultTokenizer = new StringTokenizer(defaultUnit,
				"*/");
		StringTokenizer preferredTokenizer = new StringTokenizer(preferredUnit,
				"*/");
		int length1 = 0;
	
		String c = "*"; // operator
		UCMComputer ucm = new UCMComputer();

		while (defaultTokenizer.hasMoreTokens()) {
			String defaultToken = defaultTokenizer.nextToken();
			String preferredToken = preferredTokenizer.nextToken();


			if (defaultToken.equals(preferredToken)) {

				convertAmount = convertAmount * 1;
			} else {
				double multiplier = ucm.traceOutOperationValue(defaultToken,
						preferredToken, c);
				convertAmount = convertAmount * multiplier;
			}

			length1 = length1 + defaultToken.length();
			if (length1 < defaultUnitLength)
				c = defaultUnitCharArray[length1] + "";

		}

		return convertAmount;
	}
}
