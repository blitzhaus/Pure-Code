package view;
public class Unit_Conversion {

	public double[] UnitConversion(String dose,String time,String conc)
	{
		double convert[]=new double[3];
		if(dose.equals(conc))
		{
			convert[0]=1;
		}
		else
			if((dose.equals("g"))&&(conc.equals("mg")))
			{
				convert[0]=1000;
			}
			else
				if((dose.equals("g"))&& (conc.equals("ug")))
				{
					convert[0]=1000000;
				}
				else
					if((dose.equals("g"))&&(conc.equals("ng")))
					{
						convert[0]=1000000000;
					}
					else
						if((dose.equals("mg"))&&(conc.equals("g")))
						{
							convert[0]=0.001;
						}else
							
						if((dose.equals("mg"))&&(conc.equals("ug")))
						{
							convert[0]=1000;
						}
						else 
							if((dose.equals("mg"))&&(conc.equals("ng")))
							{
								convert[0]=1000000;
							}
							else
								if((dose.equals("ng"))&&(conc.equals("mg")))
								{
									convert[0]=0.000001;
								}
								else
									if((dose.equals("ng"))&&(conc.equals("ug")))
									{
										convert[0]=0.001;
									}
									else
										if((dose.equals("ng"))&&(conc.equals("g")))
										{
											convert[0]=0.000000001;
										}
		
		if((time.equals("hr"))||(time.equals("Hr"))||(time.equals("hour"))||(time.equals("Hour"))||(time.equals("h"))||(time.equals("H")))
		{
			convert[1]=1;
		}
		else
			if((time.equals("min"))||(time.equals("Min"))||(time.equals("m"))||(time.equals("M")))
			{
				convert[1]=0.0166667;
			}
			else
				if((time.equals("Sec"))||(time.equals("S")))
				{
					convert[1]=0.0002778;
				}
		
		/*
		if((conc.equals("mg/L"))||(conc.equals("Mg/L"))||(conc.equals("mg/l"))||(conc.equals("Mg/l"))||(conc.equals("mg/litre"))||(conc.equals("Mg/litre"))||(conc.equals("mg/Litre"))||(conc.equals("Mg/Litre")))
		{
			convert[2]=1;
		}
		else
			if((conc.equals("mg/ml"))||(conc.equals("Mg/ml")))double convert[]=new double[3];
		if(dose.equals(conc))
		{
			convert[0]=1;
		}
		else
			if((dose.equals("g"))&&(conc.equals("mg")))
			{
				convert[0]=1000;
			}
			else
				if((dose.equals("g"))&& (conc.equals("ug")))
				{
					convert[0]=1000000;
				}
				else
					if((dose.equals("g"))&&(conc.equals("ng")))
					{
						convert[0]=1000000000;
					}
					else
						if((dose.equals("mg"))&&(conc.equals("ug")))
						{
							convert[0]=1000;
						}
						else 
							if((dose.equals("mg"))&&(conc.equals("ng")))
							{
								convert[0]=1000;
							}
							else
								if((dose.equals("ng"))&&(conc.equals("mg")))
								{
									convert[0]=0.000001;
								}
								else
									if((dose.equals("ng"))&&(conc.equals("ug")))
									{
										convert[0]=0.001;
									}
		
		if((time.equals("hr"))||(time.equals("Hr"))||(time.equals("hour"))||(time.equals("Hour"))||(time.equals("h"))||(time.equals("H")))
		{
			convert[1]=1;
		}
		else
			if((time.equals("min"))||(time.equals("Min"))||(time.equals("m"))||(time.equals("M")))
			{
				convert[1]=0.0166667;
			}
			else
				if((time.equals("Sec"))||(time.equals("S")))
				{
					convert[1]=0.0002778;
				}
		
		/*
		if((conc.equals("mg/L"))||(conc.equals("Mg/L"))||(conc.equals("mg/l"))||(conc.equals("Mg/l"))||(conc.equals("mg/litre"))||(conc.equals("Mg/litre"))||(conc.equals("mg/Litre"))||(conc.equals("Mg/Litre")))
		{
			convert[2]=1;
		}
		else
			if((conc.equals("mg/ml"))||(conc.equals("Mg/ml")))
				convert[2]=0.001;
			else
				if((conc.equals("g/L"))||(conc.equals("G/L")))
					convert[2]=1000;
				else
					if((conc.equals("g/ml"))||(conc.equals("G/ml")))
						convert[2]=1000000;
					else
						if((conc.equals("ug/L"))||(conc.equals("Ug/L"))||(conc.equals("ug/l"))||(conc.equals("Ug/l"))||(conc.equals("ug/litre"))||(conc.equals("Ug/litre"))||(conc.equals("ug/Litre"))||(conc.equals("Ug/Litre")))
						{
							convert[2]=0.001;
						}
				convert[2]=0.001;
			else
				if((conc.equals("g/L"))||(conc.equals("G/L")))
					convert[2]=1000;
				else
					if((conc.equals("g/ml"))||(conc.equals("G/ml")))
						convert[2]=1000000;
					else
						if((conc.equals("ug/L"))||(conc.equals("Ug/L"))||(conc.equals("ug/l"))||(conc.equals("Ug/l"))||(conc.equals("ug/litre"))||(conc.equals("Ug/litre"))||(conc.equals("ug/Litre"))||(conc.equals("Ug/Litre")))
						{
							convert[2]=0.001;
						}*/
		convert[2]=1;
		return convert; 
		
	}
	

}