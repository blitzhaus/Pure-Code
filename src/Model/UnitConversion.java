package Model;

import java.util.StringTokenizer;

public class UnitConversion {

	public double unitConversion(String defaultUnit, String preferredUnit) {
		double convertAmount = 1;
		char[] defaultUnitCharArray;
		char[] preferredUnitCharArray;
		defaultUnitCharArray = defaultUnit.toCharArray();
		preferredUnitCharArray = preferredUnit.toCharArray();
		int defaultUnitLength = defaultUnitCharArray.length;
		int preferredUnitLength = preferredUnitCharArray.length;
		int i = 0;
		int maxLength = defaultUnitLength;
		if (defaultUnitLength < preferredUnitLength)
			maxLength = defaultUnitLength;
		
		StringTokenizer defaultTokenizer = new StringTokenizer(defaultUnit,
				"*/");
		StringTokenizer preferredTokenizer = new StringTokenizer(preferredUnit,
				"*/");
		int length1 = 0;
		int length2 = 0;
		char c = '*';
		while (defaultTokenizer.hasMoreTokens()) {
			String defaultToken = defaultTokenizer.nextToken();
			String preferredToken = preferredTokenizer.nextToken();

			System.out.println("default Token=" + defaultToken
					+ " preferred Token=" + preferredToken);
			System.out.println("operation" + c);

			if (defaultToken.equals(preferredToken)) {

				convertAmount = convertAmount * 1;
			} 
			
			
			else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;
			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000 * 0.002204;
				else
					convertAmount = convertAmount * 0.001 * 453.592;
			}

			else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000 * 0.002204;
				else
					convertAmount = convertAmount * 0.000001 * 453.592;

			}

			else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;
			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9 * 0.002204;
				else
					convertAmount = convertAmount * 1E-9 * 453.592;
			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18 * 0.002204;
				else
					convertAmount = convertAmount * 1E-18 * 453.592;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15 * 0.002204;
				else
					convertAmount = convertAmount * 1E-15 * 453.592;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12 * 0.002204;
				else
					convertAmount = convertAmount * 1E-12 * 453.592;

			}

			else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001 * 0.002204;
				else
					convertAmount = convertAmount * 1000 * 453.592;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1 * 0.002204;
				else
					convertAmount = convertAmount * 10 * 453.592;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10 * 0.002204;
				else
					convertAmount = convertAmount * 0.1 * 453.592;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100 * 0.002204;
				else
					convertAmount = convertAmount * 0.01 * 453.592;

			} else if ((defaultToken.equals("g"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.002204;
				else
					convertAmount = convertAmount * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;
			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100 * 0.002204;
				else
					convertAmount = convertAmount * 0.01 * 453.592;
			}

			else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000 * 0.002204;
				else
					convertAmount = convertAmount * 0.001 * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000 * 0.002204;
				else
					convertAmount = convertAmount * 0.0001 * 453.592;

			}
			else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100000 ;
				else
					convertAmount = convertAmount * 0.00001;

			}
			else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100000 * 0.002204;
				else
					convertAmount = convertAmount * 0.00001 * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000 * 0.002204;
				else
					convertAmount = convertAmount * 0.000001 * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9 * 0.002204;
				else
					convertAmount = convertAmount * 1E-9 * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12 * 0.002204;
				else
					convertAmount = convertAmount * 1E-12 * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15 * 0.002204;
				else
					convertAmount = convertAmount * 1E-15 * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18 * 0.002204;
				else
					convertAmount = convertAmount * 1E-18 * 453.592;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E21;
				else
					convertAmount = convertAmount * 1E-21;

			} else if ((defaultToken.equals("kg"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E21 * 0.002204;
				else
					convertAmount = convertAmount * 1E-21 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;
			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01 * 0.002204;
				else
					convertAmount = convertAmount * 100 * 453.592;
			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10 * 0.002204;
				else
					convertAmount = convertAmount * 0.1 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100 * 0.002204;
				else
					convertAmount = convertAmount * 0.01 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000 * 0.002204;
				else
					convertAmount = convertAmount * 0.001 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			}

			else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000 * 0.002204;
				else
					convertAmount = convertAmount * 0.0001 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7 * 0.002204;
				else
					convertAmount = convertAmount * 1E-7 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10 * 0.002204;
				else
					convertAmount = convertAmount * 1E-10 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13 * 0.002204;
				else
					convertAmount = convertAmount * 1E-13 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16 * 0.002204;
				else
					convertAmount = convertAmount * 1E-16 * 453.592;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E19;
				else
					convertAmount = convertAmount * 1E-19;

			} else if ((defaultToken.equals("dkg"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E19 * 0.002204;
				else
					convertAmount = convertAmount * 1E-19 * 453.592;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.0001;
				else
					convertAmount = convertAmount * 10000;
			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E5;
				else
					convertAmount = convertAmount * 1E-5;

			}

			else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E8;
				else
					convertAmount = convertAmount * 1E-8;

			}

			else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E11;
				else
					convertAmount = convertAmount * 1E-11;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E14;
				else
					convertAmount = convertAmount * 1E-14;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E17;
				else
					convertAmount = convertAmount * 1E-17;

			}

			else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.0001 * 0.002204;
				else
					convertAmount = convertAmount * 10000 * 453.592;
			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01 * 0.002204;
				else
					convertAmount = convertAmount * 100 * 453.592;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1 * 0.002204;
				else
					convertAmount = convertAmount * 10 * 453.592;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10 * 0.002204;
				else
					convertAmount = convertAmount * 0.1 * 453.592;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100 * 0.002204;
				else
					convertAmount = convertAmount * 0.01 * 453.592;

			}

			else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E5 * 0.002204;
				else
					convertAmount = convertAmount * 1E-5 * 453.592;

			}

			else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E8 * 0.002204;
				else
					convertAmount = convertAmount * 1E-8 * 453.592;

			}

			else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E11 * 0.002204;
				else
					convertAmount = convertAmount * 1E-11 * 453.592;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E14 * 0.002204;
				else
					convertAmount = convertAmount * 1E-14 * 453.592;

			} else if ((defaultToken.equals("dg"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E17 * 0.002204;
				else
					convertAmount = convertAmount * 1E-17 * 453.592;

			}

			else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;
			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E4;
				else
					convertAmount = convertAmount * 1E-4;

			}

			else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5 * 0.002204;
				else
					convertAmount = convertAmount * 1E5 * 453.592;
			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001 * 0.002204;
				else
					convertAmount = convertAmount * 1000 * 453.592;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01 * 0.002204;
				else
					convertAmount = convertAmount * 100 * 453.592;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1 * 0.002204;
				else
					convertAmount = convertAmount * 10 * 453.592;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10 * 0.002204;
				else
					convertAmount = convertAmount * 0.1 * 453.592;

			}

			else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E4 * 0.002204;
				else
					convertAmount = convertAmount * 1E-4 * 453.592;

			}

			else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7 * 0.002204;
				else
					convertAmount = convertAmount * 1E-7 * 453.592;

			}

			else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10 * 0.002204;
				else
					convertAmount = convertAmount * 1E-10 * 453.592;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13 * 0.002204;
				else
					convertAmount = convertAmount * 1E-13 * 453.592;

			} else if ((defaultToken.equals("cg"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16 * 0.002204;
				else
					convertAmount = convertAmount * 1E-16 * 453.592;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;
			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			}

			else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;
			}

			else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 0.002204;
				else
					convertAmount = convertAmount * 1E6 * 453.592;
			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4 * 0.002204;
				else
					convertAmount = convertAmount * 1E4 * 453.592;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001 * 0.002204;
				else
					convertAmount = convertAmount * 1000 * 453.592;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01 * 0.002204;
				else
					convertAmount = convertAmount * 100 * 453.592;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10 * 0.002204;
				else
					convertAmount = convertAmount * 0.1 * 453.592;

			}

			else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3 * 0.002204;
				else
					convertAmount = convertAmount * 1E-3 * 453.592;

			}

			else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6 * 0.002204;
				else
					convertAmount = convertAmount * 1E-6 * 453.592;

			}

			else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9 * 0.002204;
				else
					convertAmount = convertAmount * 1E-9 * 453.592;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12 * 0.002204;
				else
					convertAmount = convertAmount * 1E-12 * 453.592;

			} else if ((defaultToken.equals("mg"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15 * 0.002204;
				else
					convertAmount = convertAmount * 1E-15 * 453.592;
			}

			 else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;
			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			}

			else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 0.002204;
				else
					convertAmount = convertAmount * 1E9 * 453.592;
			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7 * 0.002204;
				else
					convertAmount = convertAmount * 1E7 * 453.592;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 0.002204;
				else
					convertAmount = convertAmount * 1E6 * 453.592;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5 * 0.002204;
				else
					convertAmount = convertAmount * 1E5 * 453.592;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4 * 0.002204;
				else
					convertAmount = convertAmount * 1E4 * 453.592;

			}

			else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 0.002204;
				else
					convertAmount = convertAmount * 1E3 * 453.592;

			}

			else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3 * 0.002204;
				else
					convertAmount = convertAmount * 1E-3 * 453.592;

			}

			else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6 * 0.002204;
				else
					convertAmount = convertAmount * 1E-6 * 453.592;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9 * 0.002204;
				else
					convertAmount = convertAmount * 1E-9 * 453.592;

			} else if ((defaultToken.equals("ug"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12 * 0.002204;
				else
					convertAmount = convertAmount * 1E-12 * 453.592;
				;

			}

			else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;
			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-8;
				else
					convertAmount = convertAmount * 1E8;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			}

			else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 0.002204;
				else
					convertAmount = convertAmount * 1E12 * 453.592;
			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10 * 0.002204;
				else
					convertAmount = convertAmount * 1E10 * 453.592;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 0.002204;
				else
					convertAmount = convertAmount * 1E9 * 453.592;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-8 * 0.002204;
				else
					convertAmount = convertAmount * 1E8 * 453.592;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7 * 0.002204;
				else
					convertAmount = convertAmount * 1E7 * 453.592;

			}

			else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 0.002204;
				else
					convertAmount = convertAmount * 1E6 * 453.592;

			}

			else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 0.002204;
				else
					convertAmount = convertAmount * 1E3 * 453.592;

			}

			else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3 * 0.002204;
				else
					convertAmount = convertAmount * 1E-3 * 453.592;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6 * 0.002204;
				else
					convertAmount = convertAmount * 1E-6 * 453.592;

			} else if ((defaultToken.equals("ng"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9 * 0.002204;
				else
					convertAmount = convertAmount * 1E-9 * 453.592;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;
			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-11;
				else
					convertAmount = convertAmount * 1E11;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			}

			else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15 * 0.002204;
				else
					convertAmount = convertAmount * 1E15 * 453.592;
			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13 * 0.002204;
				else
					convertAmount = convertAmount * 1E13 * 453.592;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 0.002204;
				else
					convertAmount = convertAmount * 1E12 * 453.592;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-11 * 0.002204;
				else
					convertAmount = convertAmount * 1E11 * 453.592;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10 * 0.002204;
				else
					convertAmount = convertAmount * 1E10 * 453.592;

			}

			else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 0.002204;
				else
					convertAmount = convertAmount * 1E9 * 453.592;

			}

			else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 0.002204;
				else
					convertAmount = convertAmount * 1E6 * 453.592;

			}

			else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 0.002204;
				else
					convertAmount = convertAmount * 1E3 * 453.592;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3 * 0.002204;
				else
					convertAmount = convertAmount * 1E-3 * 453.592;

			} else if ((defaultToken.equals("pg"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6 * 0.002204;
				else
					convertAmount = convertAmount * 1E-6 * 453.592;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;
			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-14;
				else
					convertAmount = convertAmount * 1E14;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			}

			else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18 * 0.002204;
				else
					convertAmount = convertAmount * 1E18 * 453.592;
			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16 * 0.002204;
				else
					convertAmount = convertAmount * 1E16 * 453.592;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15 * 0.002204;
				else
					convertAmount = convertAmount * 1E15 * 453.592;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-14 * 0.002204;
				else
					convertAmount = convertAmount * 1E14 * 453.592;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13 * 0.002204;
				else
					convertAmount = convertAmount * 1E13 * 453.592;

			}

			else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 0.002204;
				else
					convertAmount = convertAmount * 1E12 * 453.592;

			}

			else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 0.002204;
				else
					convertAmount = convertAmount * 1E9 * 453.592;

			}

			else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 0.002204;
				else
					convertAmount = convertAmount * 1E6 * 453.592;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 0.002204;
				else
					convertAmount = convertAmount * 1E3 * 453.592;

			} else if ((defaultToken.equals("fg"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3 * 0.002204;
				else
					convertAmount = convertAmount * 1E-3 * 453.592;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-21;
				else
					convertAmount = convertAmount * 1E21;
			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-19;
				else
					convertAmount = convertAmount * 1E19;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-17;
				else
					convertAmount = convertAmount * 17;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			}

			else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			}

			else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-21 * 0.002204;
				else
					convertAmount = convertAmount * 1E21 * 453.592;
			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-19 * 0.002204;
				else
					convertAmount = convertAmount * 1E19 * 453.592;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18 * 0.002204;
				else
					convertAmount = convertAmount * 1E18 * 453.592;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-17 * 0.002204;
				else
					convertAmount = convertAmount * 17 * 453.592;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16 * 0.002204;
				else
					convertAmount = convertAmount * 1E16 * 453.592;

			}

			else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15 * 0.002204;
				else
					convertAmount = convertAmount * 1E15 * 453.592;

			}

			else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 0.002204;
				else
					convertAmount = convertAmount * 1E12 * 453.592;

			}

			else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 0.002204;
				else
					convertAmount = convertAmount * 1E9 * 453.592;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 0.002204;
				else
					convertAmount = convertAmount * 1E6 * 453.592;

			} else if ((defaultToken.equals("ag"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 0.002204;
				else
					convertAmount = convertAmount * 1E3 * 453.592;

			}

			else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000* 453.592;
				else
					convertAmount = convertAmount * 0.001* 0.002204;
			}
			else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000 ;
				else
					convertAmount = convertAmount * 0.001;
			}

			else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000* 453.592 ;
				else
					convertAmount = convertAmount * 0.000001* 0.002204 ;

			}

			else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;
			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9* 453.592;
				else
					convertAmount = convertAmount * 1E-9 * 0.002204 ;
			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18 * 453.592 * 0.002204;
				else
					convertAmount = convertAmount * 1E-18 * 0.002204;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15 * 453.592 ;
				else
					convertAmount = convertAmount * 1E-15* 0.002204;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12  * 453.592;
				else
					convertAmount = convertAmount * 1E-12* 0.002204;

			}

			else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001* 453.592;
				else
					convertAmount = convertAmount * 1000  * 0.002204;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1* 453.592 ;
				else
					convertAmount = convertAmount * 10 * 0.002204;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10* 453.592;
				else
					convertAmount = convertAmount * 0.1  * 0.002204;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100 * 453.592 ;
				else
					convertAmount = convertAmount * 0.01* 0.002204;

			} else if ((defaultToken.equals("lb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 453.592;
				else
					convertAmount = convertAmount * 0.002204;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;
			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100  * 453.592;
				else
					convertAmount = convertAmount * 0.01* 0.002204;
			}

			else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000* 453.592;
				else
					convertAmount = convertAmount * 0.001  * 0.002204;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000* 453.592 ;
				else
					convertAmount = convertAmount * 0.0001 * 0.002204;

			}
			else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100000 ;
				else
					convertAmount = convertAmount * 0.00001;

			}
			else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100000 * 453.592;
				else
					convertAmount = convertAmount * 0.00001* 0.002204 ;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000  * 453.592;
				else
					convertAmount = convertAmount * 0.000001* 0.002204;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9 * 453.592;
				else
					convertAmount = convertAmount * 1E-9* 0.002204 ;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12 * 453.592;
				else
					convertAmount = convertAmount * 1E-12* 0.002204 ;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15 * 453.592 ;
				else
					convertAmount = convertAmount * 1E-15* 0.002204;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18 * 453.592 ;
				else
					convertAmount = convertAmount * 1E-18* 0.002204;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E21;
				else
					convertAmount = convertAmount * 1E-21;

			} else if ((defaultToken.equals("klb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E21  * 453.592;
				else
					convertAmount = convertAmount * 1E-21* 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;
			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01  * 453.592;
				else
					convertAmount = convertAmount * 100* 0.002204;
			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 10 * 453.592;
				else
					convertAmount = convertAmount * 0.1 * 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100  * 453.592;
				else
					convertAmount = convertAmount * 0.01* 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000  * 453.592;
				else
					convertAmount = convertAmount * 0.001* 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			}

			else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000  * 453.592;
				else
					convertAmount = convertAmount * 0.0001* 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7  * 453.592;
				else
					convertAmount = convertAmount * 1E-7* 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10 * 453.592;
				else
					convertAmount = convertAmount * 1E-10 * 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13  * 453.592;
				else
					convertAmount = convertAmount * 1E-13* 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16  * 453.592;
				else
					convertAmount = convertAmount * 1E-16* 0.002204;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E19;
				else
					convertAmount = convertAmount * 1E-19;

			} else if ((defaultToken.equals("dklb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E19  * 453.592;
				else
					convertAmount = convertAmount * 1E-19* 0.002204;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.0001;
				else
					convertAmount = convertAmount * 10000;
			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("dkl"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E5;
				else
					convertAmount = convertAmount * 1E-5;

			}

			else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E8;
				else
					convertAmount = convertAmount * 1E-8;

			}

			else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E11;
				else
					convertAmount = convertAmount * 1E-11;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E14;
				else
					convertAmount = convertAmount * 1E-14;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E17;
				else
					convertAmount = convertAmount * 1E-17;

			}

			else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.0001* 453.592;
				else
					convertAmount = convertAmount * 10000  * 0.002204;
			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01 * 453.592;
				else
					convertAmount = convertAmount * 100 * 0.002204;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1 * 453.592;
				else
					convertAmount = convertAmount * 10 * 0.002204;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10 * 453.592;
				else
					convertAmount = convertAmount * 0.1 * 0.002204;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 100  * 453.592;
				else
					convertAmount = convertAmount * 0.01* 0.002204;

			}

			else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E5  * 453.592;
				else
					convertAmount = convertAmount * 1E-5* 0.002204;

			}

			else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E8  * 453.592;
				else
					convertAmount = convertAmount * 1E-8* 0.002204;

			}

			else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E11  * 453.592;
				else
					convertAmount = convertAmount * 1E-11* 0.002204;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E14  * 453.592;
				else
					convertAmount = convertAmount * 1E-14* 0.002204;

			} else if ((defaultToken.equals("dlb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E17* 453.592;
				else
					convertAmount = convertAmount * 1E-17  * 0.002204;

			}

			else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;
			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E4;
				else
					convertAmount = convertAmount * 1E-4;

			}

			else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5  * 453.592;
				else
					convertAmount = convertAmount * 1E5* 0.002204;
			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001* 453.592;
				else
					convertAmount = convertAmount * 1000  * 0.002204;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01* 453.592;
				else
					convertAmount = convertAmount * 100  * 0.002204;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1 * 453.592;
				else
					convertAmount = convertAmount * 10 * 0.002204;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10  * 453.592;
				else
					convertAmount = convertAmount * 0.1* 0.002204;

			}

			else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E4  * 453.592;
				else
					convertAmount = convertAmount * 1E-4* 0.002204;

			}

			else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7  * 453.592;
				else
					convertAmount = convertAmount * 1E-7* 0.002204;

			}

			else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10  * 453.592;
				else
					convertAmount = convertAmount * 1E-10* 0.002204;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13  * 453.592;
				else
					convertAmount = convertAmount * 1E-13* 0.002204;

			} else if ((defaultToken.equals("clb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16  * 453.592;
				else
					convertAmount = convertAmount * 1E-16* 0.002204;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;
			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			}

			else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;
			}

			else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6* 453.592;
				else
					convertAmount = convertAmount * 1E6  * 0.002204;
			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4 * 453.592;
				else
					convertAmount = convertAmount * 1E4 * 0.002204;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001* 453.592;
				else
					convertAmount = convertAmount * 1000  * 0.002204;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01* 453.592;
				else
					convertAmount = convertAmount * 100  * 0.002204;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 10  * 453.592;
				else
					convertAmount = convertAmount * 0.1* 0.002204;

			}

			else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3  * 453.592;
				else
					convertAmount = convertAmount * 1E-3* 0.002204;

			}

			else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6  * 453.592;
				else
					convertAmount = convertAmount * 1E-6* 0.002204;

			}

			else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9  * 453.592;
				else
					convertAmount = convertAmount * 1E-9* 0.002204;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12 * 453.592;
				else
					convertAmount = convertAmount * 1E-12 * 0.002204;

			} else if ((defaultToken.equals("mlb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15  * 453.592;
				else
					convertAmount = convertAmount * 1E-15* 0.002204;
			}

			
			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;
			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			}

			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 453.592;
				else
					convertAmount = convertAmount * 1E9 * 0.002204;
			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7 * 453.592;
				else
					convertAmount = convertAmount * 1E7 * 0.002204;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 453.592;
				else
					convertAmount = convertAmount * 1E6 * 0.002204;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5 * 453.592;
				else
					convertAmount = convertAmount * 1E5 * 0.002204;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4 * 453.592;
				else
					convertAmount = convertAmount * 1E4 * 0.002204;

			}

			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 453.592;
				else
					convertAmount = convertAmount * 1E3 * 0.002204;

			}

			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3 * 453.592; 
				else
					convertAmount = convertAmount * 1E-3* 0.002204;

			}

			else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6  * 453.592;
				else
					convertAmount = convertAmount * 1E-6* 0.002204;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9  * 453.592;
				else
					convertAmount = convertAmount * 1E-9* 0.002204;

			} else if ((defaultToken.equals("ulb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12  * 453.592;
				else
					convertAmount = convertAmount * 1E-12* 0.002204;
				

			}

			else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;
			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-8;
				else
					convertAmount = convertAmount * 1E8;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			}

			else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 453.592;
				else
					convertAmount = convertAmount * 1E12 * 0.002204;
			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10 * 453.592;
				else
					convertAmount = convertAmount * 1E10 * 0.002204;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 453.592;
				else
					convertAmount = convertAmount * 1E9 * 0.002204;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-8* 453.592;
				else
					convertAmount = convertAmount * 1E8  * 0.002204;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7 * 453.592;
				else
					convertAmount = convertAmount * 1E7 * 0.002204;

			}

			else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6* 453.592;
				else
					convertAmount = convertAmount * 1E6  * 0.002204;

			}

			else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3* 453.592;
				else
					convertAmount = convertAmount * 1E3  * 0.002204;

			}

			else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3  * 453.592;
				else
					convertAmount = convertAmount * 1E-3* 0.002204;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6  * 453.592;
				else
					convertAmount = convertAmount * 1E-6* 0.002204;

			} else if ((defaultToken.equals("nlb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9  * 453.592;
				else
					convertAmount = convertAmount * 1E-9* 0.002204;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;
			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-11;
				else
					convertAmount = convertAmount * 1E11;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			}

			else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15  * 453.592;
				else
					convertAmount = convertAmount * 1E15* 0.002204;
			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13 * 453.592;
				else
					convertAmount = convertAmount * 1E13 * 0.002204;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 453.592;
				else
					convertAmount = convertAmount * 1E12 * 0.002204;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-11 * 453.592;
				else
					convertAmount = convertAmount * 1E11 * 0.002204;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10 * 453.592;
				else
					convertAmount = convertAmount * 1E10 * 0.002204;

			}

			else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 453.592;
				else
					convertAmount = convertAmount * 1E9 * 0.002204;

			}

			else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 453.592;
				else
					convertAmount = convertAmount * 1E6 * 0.002204;

			}

			else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3* 453.592;
				else
					convertAmount = convertAmount * 1E3  * 0.002204;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3  * 453.592;
				else
					convertAmount = convertAmount * 1E-3* 0.002204;

			} else if ((defaultToken.equals("plb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6 * 453.592;
				else
					convertAmount = convertAmount * 1E-6 * 0.002204;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;
			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-14;
				else
					convertAmount = convertAmount * 1E14;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			}

			else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("alb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("kg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18* 453.592;
				else
					convertAmount = convertAmount * 1E18  * 0.002204;
			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16 * 453.592;
				else
					convertAmount = convertAmount * 1E16 * 0.002204;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15 * 453.592;
				else
					convertAmount = convertAmount * 1E15 * 0.002204;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-14 * 453.592;
				else
					convertAmount = convertAmount * 1E14 * 0.002204;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13 * 453.592;
				else
					convertAmount = convertAmount * 1E13 * 0.002204;

			}

			else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 453.592;
				else
					convertAmount = convertAmount * 1E12 * 0.002204;

			}

			else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 453.592;
				else
					convertAmount = convertAmount * 1E9 * 0.002204;

			}

			else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 453.592;
				else
					convertAmount = convertAmount * 1E6 * 0.002204;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 453.592;
				else
					convertAmount = convertAmount * 1E3 * 0.002204;

			} else if ((defaultToken.equals("flb"))
					&& (preferredToken.equals("ag"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3  * 453.592;
				else
					convertAmount = convertAmount * 1E-3* 0.002204;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("klb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-21;
				else
					convertAmount = convertAmount * 1E21;
			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("dklb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-19;
				else
					convertAmount = convertAmount * 1E19;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("lb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("dlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-17;
				else
					convertAmount = convertAmount * 17;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("clb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			}

			else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("mlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			}

			else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("ulb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("nlb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("plb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("flb"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("k"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-21 * 453.592;
				else
					convertAmount = convertAmount * 1E21 * 0.002204;
			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("dkg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-19 * 453.592;
				else
					convertAmount = convertAmount * 1E19 * 0.002204;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("g"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18 * 453.592;
				else
					convertAmount = convertAmount * 1E18 * 0.002204;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("dg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-17 * 453.592;
				else
					convertAmount = convertAmount * 17 * 0.002204;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("cg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16 * 453.592;
				else
					convertAmount = convertAmount * 1E16 * 0.002204;

			}

			else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("mg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15 * 453.592;
				else
					convertAmount = convertAmount * 1E15 * 0.002204;

			}

			else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("ug"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12 * 453.592;
				else
					convertAmount = convertAmount * 1E12 * 0.002204;

			}

			else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("ng"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9 * 453.592;
				else
					convertAmount = convertAmount * 1E9 * 0.002204;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("pg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6 * 453.592;
				else
					convertAmount = convertAmount * 1E6 * 0.002204;

			} else if ((defaultToken.equals("alb"))
					&& (preferredToken.equals("fg"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3 * 453.592;
				else
					convertAmount = convertAmount * 1E3 * 0.002204;

			}

			
			

			else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;
			} else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			}

			else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			}

			else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("mol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;
			} else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			} else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 100000;
				else
					convertAmount = convertAmount * 0.00001;

			} else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			}

			else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("kmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E21;
				else
					convertAmount = convertAmount * 1E-21;

			}

			else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;
			} else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			} else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			}

			else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			}

			else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("dkmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E19;
				else
					convertAmount = convertAmount * 1E-19;

			}

			else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.0001;
				else
					convertAmount = convertAmount * 10000;
			} else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E5;
				else
					convertAmount = convertAmount * 1E-5;

			}

			else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E8;
				else
					convertAmount = convertAmount * 1E-8;

			}

			else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E11;
				else
					convertAmount = convertAmount * 1E-11;

			} else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E14;
				else
					convertAmount = convertAmount * 1E-14;

			} else if ((defaultToken.equals("dmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E17;
				else
					convertAmount = convertAmount * 1E-17;

			}

			else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;
			} else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E4;
				else
					convertAmount = convertAmount * 1E-4;

			}

			else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			} else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("cmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;
			} else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			} else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			}

			else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			} else if ((defaultToken.equals("mmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;
			}

			else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;
			} else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			} else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;

			} else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			}

			else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("umol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;
			} else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			} else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-8;
				else
					convertAmount = convertAmount * 1E8;

			} else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			}

			else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("nmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;
			} else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			} else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			} else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-11;
				else
					convertAmount = convertAmount * 11;

			} else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			}

			else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("pmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;
			} else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			} else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			} else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-14;
				else
					convertAmount = convertAmount * 14;

			} else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			}

			else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("fmol"))
					&& (preferredToken.equals("amol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("kmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-21;
				else
					convertAmount = convertAmount * 1E21;
			} else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("dkmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-19;
				else
					convertAmount = convertAmount * 1E19;

			} else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("mol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;

			} else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("dmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-17;
				else
					convertAmount = convertAmount * 17;

			} else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("cmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			}

			else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("mmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			}

			else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("umol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("nmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("pmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("amol"))
					&& (preferredToken.equals("fmol"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;
			} else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;
			} else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			}

			else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			}

			else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("UI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;
			} else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			} else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 100000;
				else
					convertAmount = convertAmount * 0.00001;

			} else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			}

			else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("kUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E21;
				else
					convertAmount = convertAmount * 1E-21;

			}

			else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;
			} else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			} else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			}

			else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			}

			else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("dkUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E19;
				else
					convertAmount = convertAmount * 1E-19;

			}

			else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.0001;
				else
					convertAmount = convertAmount * 10000;
			} else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E5;
				else
					convertAmount = convertAmount * 1E-5;

			}

			else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E8;
				else
					convertAmount = convertAmount * 1E-8;

			}

			else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E11;
				else
					convertAmount = convertAmount * 1E-11;

			} else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E14;
				else
					convertAmount = convertAmount * 1E-14;

			} else if ((defaultToken.equals("dUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E17;
				else
					convertAmount = convertAmount * 1E-17;

			}

			else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;
			} else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E4;
				else
					convertAmount = convertAmount * 1E-4;

			}

			else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			} else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("cUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;
			} else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			} else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			}

			else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			} else if ((defaultToken.equals("mUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;
			}

			else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;
			} else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			} else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;

			} else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			}

			else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("uUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;
			} else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			} else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-8;
				else
					convertAmount = convertAmount * 1E8;

			} else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			}

			else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("nUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;
			} else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			} else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			} else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-11;
				else
					convertAmount = convertAmount * 11;

			} else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			}

			else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("pUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;
			} else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			} else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			} else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-14;
				else
					convertAmount = convertAmount * 14;

			} else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			}

			else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("fUI"))
					&& (preferredToken.equals("aUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("kUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-21;
				else
					convertAmount = convertAmount * 1E21;
			} else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("dkUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-19;
				else
					convertAmount = convertAmount * 1E19;

			} else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("UI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;

			} else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("dUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-17;
				else
					convertAmount = convertAmount * 17;

			} else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("cUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			}

			else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("mUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			}

			else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("uUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("nUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("pUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("aUI"))
					&& (preferredToken.equals("fUI"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else

			if ((defaultToken.equals("hr") || defaultToken.equals("hour") || defaultToken
					.equals("h"))
					&& (preferredToken.equals("hr")
							|| preferredToken.equals("hour") || preferredToken
							.equals("h"))) {

				convertAmount = convertAmount;

			} else if ((defaultToken.equals("min") || defaultToken.equals("m"))
					&& (preferredToken.equals("min") || preferredToken
							.equals("m"))) {
				convertAmount = convertAmount;
			} else if ((defaultToken.equals("sec") || defaultToken.equals("s") || defaultToken
					.equals("seconds"))
					&& (preferredToken.equals("sec")
							|| preferredToken.equals("s") || preferredToken
							.equals("seconds"))) {
				convertAmount = convertAmount;
			} else if (defaultToken.equals("day")
					&& preferredToken.equals("day")) {
				convertAmount = convertAmount;
			} else if ((defaultToken.equals("hr")
					|| defaultToken.equals("hour") || defaultToken.equals("h"))
					&& (preferredToken.equals("day"))) {

				if (c == '*')
					convertAmount = convertAmount * 0.041666667;
				else
					convertAmount = convertAmount * 24;

			} else if ((defaultToken.equals("hr")
					|| defaultToken.equals("hour") || defaultToken.equals("h"))
					&& (preferredToken.equals("min") || preferredToken
							.equals("m"))) {

				if (c == '*')
					convertAmount = convertAmount * 60;
				else
					convertAmount = convertAmount * 0.016666667;

			} else if ((defaultToken.equals("hr")
					|| defaultToken.equals("hour") || defaultToken.equals("h"))
					&& (preferredToken.equals("sec")
							|| preferredToken.equals("s") || preferredToken
							.equals("seconds"))) {

				if (c == '*')
					convertAmount = convertAmount * 3600;
				else
					convertAmount = convertAmount * 0.000277778;

			} else if ((defaultToken.equals("min") || defaultToken.equals("m"))
					&& (preferredToken.equals("day"))) {

				if (c == '*')
					convertAmount = convertAmount * 0.016666667 * 0.041666667;
				else
					convertAmount = convertAmount * 60 * 24;

			} else if ((defaultToken.equals("min") || defaultToken.equals("m"))
					&& (preferredToken.equals("h")
							|| preferredToken.equals("hour") || preferredToken
							.equals("hr"))) {

				if (c == '*')
					convertAmount = convertAmount * 0.016666667;
				else
					convertAmount = convertAmount * 60;

			} else if ((defaultToken.equals("min") || defaultToken.equals("m"))
					&& (preferredToken.equals("sec")
							|| preferredToken.equals("s") || preferredToken
							.equals("seconds"))) {

				if (c == '*')
					convertAmount = convertAmount * 60;
				else
					convertAmount = convertAmount * 0.016666667;

			} else if ((defaultToken.equals("sec") || defaultToken.equals("s") || defaultToken
					.equals("seconds"))
					&& (preferredToken.equals("day"))) {

				if (c == '*')
					convertAmount = convertAmount * 0.000277778 * 0.041666667;
				else
					convertAmount = convertAmount * 3600 * 24;

			} else if ((defaultToken.equals("sec") || defaultToken.equals("s") || defaultToken
					.equals("seconds"))
					&& (preferredToken.equals("h")
							|| preferredToken.equals("hour") || preferredToken
							.equals("hr"))) {

				if (c == '*')
					convertAmount = convertAmount * 0.000277778;
				else
					convertAmount = convertAmount * 3600;

			} else if ((defaultToken.equals("sec") || defaultToken.equals("s") || defaultToken
					.equals("seconds"))
					&& (preferredToken.equals("min") || preferredToken
							.equals("m"))) {

				if (c == '*')
					convertAmount = convertAmount * 0.016666667;
				else
					convertAmount = convertAmount * 60;

			} else

			if ((defaultToken.equals("day") && (preferredToken.equals("sec")
					|| preferredToken.equals("s") || preferredToken
					.equals("seconds")))) {

				if (c == '*')
					convertAmount = convertAmount * 24 * 3600;
				else
					convertAmount = convertAmount * 0.041666667 * 0.000277778;

			} else if ((defaultToken.equals("day"))
					&& (preferredToken.equals("h")
							|| preferredToken.equals("hour") || preferredToken
							.equals("hr"))) {

				if (c == '*')
					convertAmount = convertAmount * 24;
				else
					convertAmount = convertAmount * 0.041666667;

			} else if ((defaultToken.equals("day"))
					&& (preferredToken.equals("min") || preferredToken
							.equals("m"))) {

				if (c == '*')
					convertAmount = convertAmount * 24 * 60;
				else
					convertAmount = convertAmount * 0.041666667 * 0.016666667;

			} else

			if ((defaultToken.equals("L")) && (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;
			} else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 0.01;
			} else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			}

			else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			}

			else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("L"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;
			} else if ((defaultToken.equals("KL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			} else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 100000;
				else
					convertAmount = convertAmount * 0.00001;

			} else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000000;
				else
					convertAmount = convertAmount * 0.000001;

			}

			else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			}

			else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;

			} else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E18;
				else
					convertAmount = convertAmount * 1E-18;

			} else if ((defaultToken.equals("kL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E21;
				else
					convertAmount = convertAmount * 1E-21;

			}

			else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;
			} else if ((defaultToken.equals("dKL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			} else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1000;
				else
					convertAmount = convertAmount * 0.001;

			} else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 10000;
				else
					convertAmount = convertAmount * 0.0001;

			}

			else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			}

			else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("dkL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E19;
				else
					convertAmount = convertAmount * 1E-19;

			}

			else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.0001;
				else
					convertAmount = convertAmount * 10000;
			} else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			} else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 100;
				else
					convertAmount = convertAmount * 0.01;

			}

			else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E5;
				else
					convertAmount = convertAmount * 1E-5;

			}

			else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E8;
				else
					convertAmount = convertAmount * 1E-8;

			}

			else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E11;
				else
					convertAmount = convertAmount * 1E-11;

			} else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E14;
				else
					convertAmount = convertAmount * 1E-14;

			} else if ((defaultToken.equals("dL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E17;
				else
					convertAmount = convertAmount * 1E-17;

			}

			else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;
			} else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.1;
				else
					convertAmount = convertAmount * 10;

			} else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E4;
				else
					convertAmount = convertAmount * 1E-4;

			}

			else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E7;
				else
					convertAmount = convertAmount * 1E-7;

			}

			else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E10;
				else
					convertAmount = convertAmount * 1E-10;

			} else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E13;
				else
					convertAmount = convertAmount * 1E-13;

			} else if ((defaultToken.equals("cL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E16;
				else
					convertAmount = convertAmount * 1E-16;

			} else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;
			} else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			} else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.001;
				else
					convertAmount = convertAmount * 1000;

			} else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 0.01;
				else
					convertAmount = convertAmount * 100;

			} else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 10;
				else
					convertAmount = convertAmount * 0.1;

			}

			else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			}

			else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			} else if ((defaultToken.equals("mL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E15;
				else
					convertAmount = convertAmount * 1E-15;
			}

			else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;
			} else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			} else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-5;
				else
					convertAmount = convertAmount * 1E5;

			} else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-4;
				else
					convertAmount = convertAmount * 1E4;

			}

			else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			}

			else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("uL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E12;
				else
					convertAmount = convertAmount * 1E-12;

			}

			else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;
			} else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			} else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-8;
				else
					convertAmount = convertAmount * 1E8;

			} else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-7;
				else
					convertAmount = convertAmount * 1E7;

			}

			else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("nL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E9;
				else
					convertAmount = convertAmount * 1E-9;

			} else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;
			} else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			} else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			} else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-11;
				else
					convertAmount = convertAmount * 11;

			} else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-10;
				else
					convertAmount = convertAmount * 1E10;

			}

			else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			}

			else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("pL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E6;
				else
					convertAmount = convertAmount * 1E-6;

			} else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;
			} else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			} else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			} else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-14;
				else
					convertAmount = convertAmount * 14;

			} else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-13;
				else
					convertAmount = convertAmount * 1E13;

			}

			else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			}

			else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			} else if ((defaultToken.equals("fL"))
					&& (preferredToken.equals("aL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E3;
				else
					convertAmount = convertAmount * 1E-3;

			} else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("kL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-21;
				else
					convertAmount = convertAmount * 1E21;
			} else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("dkL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-19;
				else
					convertAmount = convertAmount * 1E19;

			} else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("L"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-18;
				else
					convertAmount = convertAmount * 1E18;

			} else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("dL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-17;
				else
					convertAmount = convertAmount * 17;

			} else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("cL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-16;
				else
					convertAmount = convertAmount * 1E16;

			}

			else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("mL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-15;
				else
					convertAmount = convertAmount * 1E15;

			}

			else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("uL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-12;
				else
					convertAmount = convertAmount * 1E12;

			}

			else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("nL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-9;
				else
					convertAmount = convertAmount * 1E9;

			} else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("pL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-6;
				else
					convertAmount = convertAmount * 1E6;

			} else if ((defaultToken.equals("aL"))
					&& (preferredToken.equals("fL"))) {
				if (c == '*')
					convertAmount = convertAmount * 1E-3;
				else
					convertAmount = convertAmount * 1E3;

			}

			length1 = length1 + defaultToken.length();
			if (length1 < defaultUnitLength)
				c = defaultUnitCharArray[length1];
			System.out.println("convert amount=" + convertAmount);
		}

		return convertAmount;
	}
}

/*
 * double convert[]=new double[3]; if(dose.equals(conc)) { convert[0]=1; } else
 * if((dose.equals("g"))&&(conc.equals("mg"))) { convert[0]=1000; } else
 * if((dose.equals("g"))&& (conc.equals("ug"))) { convert[0]=1000000; } else
 * if((dose.equals("g"))&&(conc.equals("ng"))) { convert[0]=1000000000; } else
 * if((dose.equals("mg"))&&(conc.equals("ug"))) { convert[0]=1000; } else
 * if((dose.equals("mg"))&&(conc.equals("ng"))) { convert[0]=1000; } else
 * if((dose.equals("ng"))&&(conc.equals("mg"))) { convert[0]=0.000001; } else
 * if((dose.equals("ng"))&&(conc.equals("ug"))) { convert[0]=0.001; }
 * 
 * 
 * 
 * 
 * if((time.equals("hr"))||(time.equals("Hr"))||(time.equals("hour"))||(time.equals
 * ("Hour"))||(time.equals("h"))||(time.equals("H"))) { convert[1]=1; } else
 * if((
 * time.equals("min"))||(time.equals("Min"))||(time.equals("m"))||(time.equals
 * ("M"))) { convert[1]=0.0166667; } else
 * if((time.equals("Sec"))||(time.equals("S"))) { convert[1]=0.0002778; }
 */

