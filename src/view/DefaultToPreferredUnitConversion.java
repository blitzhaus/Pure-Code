package view;

import java.util.StringTokenizer;


public class DefaultToPreferredUnitConversion {

	public static double unitConversion(String defaultUnit,String preferredUnit){
		double convertAmount=1;
		char[] defaultUnitCharArray;
		char[] preferredUnitCharArray;
		defaultUnitCharArray=defaultUnit.toCharArray();
		preferredUnitCharArray=preferredUnit.toCharArray();
		int defaultUnitLength=defaultUnitCharArray.length;
		int preferredUnitLength=preferredUnitCharArray.length;
		int i=0;
		int maxLength=defaultUnitLength;
		if(defaultUnitLength<preferredUnitLength)
			maxLength=defaultUnitLength;
		System.out.println("********* in unit conversion********");
		System.out.println("default unit="+defaultUnit+"\t"+"preferred unit="+preferredUnit);
		
		StringTokenizer defaultTokenizer=new StringTokenizer(defaultUnit,"*/");
		StringTokenizer preferredTokenizer=new StringTokenizer(preferredUnit,"*/");
		int length1=0;
		int length2=0;
		char c='*';
		while(defaultTokenizer.hasMoreTokens()){
			String defaultToken=defaultTokenizer.nextToken();
			String preferredToken=preferredTokenizer.nextToken();
			
			System.out.println("default Token="+defaultToken+" preferred Token="+preferredToken);
			System.out.println("operation"+c);
			
			if(defaultToken.equals(preferredToken))
			{
				
					convertAmount=convertAmount*1;
			}
			else
				if((defaultToken.equals("g"))&&(preferredToken.equals("mg")))
				{
					if(c=='*')
						convertAmount=convertAmount*1000;
					else
						convertAmount=convertAmount*0.001;
				}
				else
					if((defaultToken.equals("g"))&& (preferredToken.equals("ug")))
					{
						if(c=='*')
							convertAmount=convertAmount*1000000;
						else
							convertAmount=convertAmount*0.000001;
						
					}
			
			
					else
						if((defaultToken.equals("g"))&&(preferredToken.equals("ng")))
						{
							if(c=='*')
								convertAmount=convertAmount*1E9;
							else
								convertAmount=convertAmount*0.01;
						}
					else
						if((defaultToken.equals("g"))&&(preferredToken.equals("ag")))
						{
							if(c=='*')
								convertAmount=convertAmount*1E18;
							else
								convertAmount=convertAmount*1E-18;
							
							
						}
						else
							if((defaultToken.equals("g"))&&(preferredToken.equals("fg")))
							{
								if(c=='*')
									convertAmount=convertAmount*1E15;
								else
									convertAmount=convertAmount*1E-15;
								
								
							}
							else
								if((defaultToken.equals("g"))&&(preferredToken.equals("pg")))
								{
									if(c=='*')
										convertAmount=convertAmount*1E12;
									else
										convertAmount=convertAmount*1E-12;
									
									
								}
			
								else
									if((defaultToken.equals("g"))&&(preferredToken.equals("kg")))
									{
										if(c=='*')
											convertAmount=convertAmount*0.001;
										else
											convertAmount=convertAmount*1000;
										
										
									}
			
									else
										if((defaultToken.equals("g"))&&(preferredToken.equals("dkg")))
										{
											if(c=='*')
												convertAmount=convertAmount*0.1;
											else
												convertAmount=convertAmount*10;
											
											
										}
			
										else
											if((defaultToken.equals("g"))&&(preferredToken.equals("dg")))
											{
												if(c=='*')
													convertAmount=convertAmount*10;
												else
													convertAmount=convertAmount*0.1;
												
												
											}
											else
												if((defaultToken.equals("g"))&&(preferredToken.equals("cg")))
												{
													if(c=='*')
														convertAmount=convertAmount*100;
													else
														convertAmount=convertAmount*0.01;
													
													
												}
			
												else
													if((defaultToken.equals("kg"))&&(preferredToken.equals("dkg")))
													{
														if(c=='*')
															convertAmount=convertAmount*100;
														else
															convertAmount=convertAmount*0.01;
													}
													else
														if((defaultToken.equals("Kg"))&& (preferredToken.equals("g")))
														{
															if(c=='*')
																convertAmount=convertAmount*1000;
															else
																convertAmount=convertAmount*0.001;
															
														}
														else
															if((defaultToken.equals("kg"))&&(preferredToken.equals("dg")))
															{
																if(c=='*')
																	convertAmount=convertAmount*10000;
																else
																	convertAmount=convertAmount*0.0001;
																
																
															}
															else
																if((defaultToken.equals("kg"))&&(preferredToken.equals("cg")))
																{
																	if(c=='*')
																		convertAmount=convertAmount*100000;
																	else
																		convertAmount=convertAmount*0.00001;
																	
																	
																}
																else
																	if((defaultToken.equals("kg"))&&(preferredToken.equals("mg")))
																	{
																		if(c=='*')
																			convertAmount=convertAmount*1000000;
																		else
																			convertAmount=convertAmount*0.000001;
																		
																		
																	}
												
																	else
																		if((defaultToken.equals("kg"))&&(preferredToken.equals("ug")))
																		{
																			if(c=='*')
																				convertAmount=convertAmount*1E9;
																			else
																				convertAmount=convertAmount*1E-9;
																			
																			
																		}
												
																		else
																			if((defaultToken.equals("kg"))&&(preferredToken.equals("ng")))
																			{
																				if(c=='*')
																					convertAmount=convertAmount*1E12;
																				else
																					convertAmount=convertAmount*1E-12;
																				
																				
																			}
												
																			else
																				if((defaultToken.equals("kg"))&&(preferredToken.equals("pg")))
																				{
																					if(c=='*')
																						convertAmount=convertAmount*1E15;
																					else
																						convertAmount=convertAmount*1E-15;
																					
																					
																				}
																				else
																					if((defaultToken.equals("kg"))&&(preferredToken.equals("fg")))
																					{
																						if(c=='*')
																							convertAmount=convertAmount*1E18;
																						else
																							convertAmount=convertAmount*1E-18;
																						
																						
																					}
																					else
																						if((defaultToken.equals("kg"))&&(preferredToken.equals("ag")))
																						{
																							if(c=='*')
																								convertAmount=convertAmount*1E21;
																							else
																								convertAmount=convertAmount*1E-21;
																							
																							
																						}
						
			
																						else
																							if((defaultToken.equals("dkg"))&&(preferredToken.equals("kg")))
																							{
																								if(c=='*')
																									convertAmount=convertAmount*0.01;
																								else
																									convertAmount=convertAmount*100;
																							}
																							else
																								if((defaultToken.equals("dKg"))&& (preferredToken.equals("g")))
																								{
																									if(c=='*')
																										convertAmount=convertAmount*10;
																									else
																										convertAmount=convertAmount*0.1;
																									
																								}
																								else
																									if((defaultToken.equals("dkg"))&&(preferredToken.equals("dg")))
																									{
																										if(c=='*')
																											convertAmount=convertAmount*100;
																										else
																											convertAmount=convertAmount*0.01;
																										
																										
																									}
																									else
																										if((defaultToken.equals("dkg"))&&(preferredToken.equals("cg")))
																										{
																											if(c=='*')
																												convertAmount=convertAmount*1000;
																											else
																												convertAmount=convertAmount*0.001;
																											
																											
																										}
																										else
																											if((defaultToken.equals("dkg"))&&(preferredToken.equals("mg")))
																											{
																												if(c=='*')
																													convertAmount=convertAmount*10000;
																												else
																													convertAmount=convertAmount*0.0001;
																												
																												
																											}
																						
																											else
																												if((defaultToken.equals("dkg"))&&(preferredToken.equals("ug")))
																												{
																													if(c=='*')
																														convertAmount=convertAmount*1E7;
																													else
																														convertAmount=convertAmount*1E-7;
																													
																													
																												}
																						
																												else
																													if((defaultToken.equals("dkg"))&&(preferredToken.equals("ng")))
																													{
																														if(c=='*')
																															convertAmount=convertAmount*1E10;
																														else
																															convertAmount=convertAmount*1E-10;
																														
																														
																													}
																						
																													else
																														if((defaultToken.equals("dkg"))&&(preferredToken.equals("pg")))
																														{
																															if(c=='*')
																																convertAmount=convertAmount*1E13;
																															else
																																convertAmount=convertAmount*1E-13;
																															
																															
																														}
																														else
																															if((defaultToken.equals("dkg"))&&(preferredToken.equals("fg")))
																															{
																																if(c=='*')
																																	convertAmount=convertAmount*1E16;
																																else
																																	convertAmount=convertAmount*1E-16;
																																
																																
																															}
																															else
																																if((defaultToken.equals("dkg"))&&(preferredToken.equals("ag")))
																																{
																																	if(c=='*')
																																		convertAmount=convertAmount*1E19;
																																	else
																																		convertAmount=convertAmount*1E-19;
																																																																				
																																	
																																	
																																}
			
																																else
																																	if((defaultToken.equals("dg"))&&(preferredToken.equals("kg")))
																																	{
																																		if(c=='*')
																																			convertAmount=convertAmount*0.0001;
																																		else
																																			convertAmount=convertAmount*10000;
																																	}
																																	else
																																		if((defaultToken.equals("dg"))&& (preferredToken.equals("dkg")))
																																		{
																																			if(c=='*')
																																				convertAmount=convertAmount*0.01;
																																			else
																																				convertAmount=convertAmount*100;
																																			
																																		}
																																		else
																																			if((defaultToken.equals("dg"))&&(preferredToken.equals("g")))
																																			{
																																				if(c=='*')
																																					convertAmount=convertAmount*0.1;
																																				else
																																					convertAmount=convertAmount*10;
																																				
																																				
																																			}
																																			else
																																				if((defaultToken.equals("dg"))&&(preferredToken.equals("cg")))
																																				{
																																					if(c=='*')
																																						convertAmount=convertAmount*10;
																																					else
																																						convertAmount=convertAmount*0.1;
																																					
																																					
																																				}
																																				else
																																					if((defaultToken.equals("dg"))&&(preferredToken.equals("mg")))
																																					{
																																						if(c=='*')
																																							convertAmount=convertAmount*100;
																																						else
																																							convertAmount=convertAmount*0.01;
																																						
																																						
																																					}
																																
																																					else
																																						if((defaultToken.equals("dg"))&&(preferredToken.equals("ug")))
																																						{
																																							if(c=='*')
																																								convertAmount=convertAmount*1E5;
																																							else
																																								convertAmount=convertAmount*1E-5;
																																							
																																							
																																						}
																																
																																						else
																																							if((defaultToken.equals("dg"))&&(preferredToken.equals("ng")))
																																							{
																																								if(c=='*')
																																									convertAmount=convertAmount*1E8;
																																								else
																																									convertAmount=convertAmount*1E-8;
																																								
																																								
																																							}
																																
																																							else
																																								if((defaultToken.equals("dg"))&&(preferredToken.equals("pg")))
																																								{
																																									if(c=='*')
																																										convertAmount=convertAmount*1E11;
																																									else
																																										convertAmount=convertAmount*1E-11;
																																									
																																									
																																								}
																																								else
																																									if((defaultToken.equals("dg"))&&(preferredToken.equals("fg")))
																																									{
																																										if(c=='*')
																																											convertAmount=convertAmount*1E14;
																																										else
																																											convertAmount=convertAmount*1E-14;
																																										
																																										
																																									}
																																									else
																																										if((defaultToken.equals("dg"))&&(preferredToken.equals("ag")))
																																										{
																																											if(c=='*')
																																												convertAmount=convertAmount*1E17;
																																											else
																																												convertAmount=convertAmount*1E-17;
																																											
																																										}
																																			
																																											else
																																												if((defaultToken.equals("cg"))&&(preferredToken.equals("kg")))
																																												{
																																													if(c=='*')
																																														convertAmount=convertAmount*1E-5;
																																													else
																																														convertAmount=convertAmount*1E5;
																																												}
																																												else
																																													if((defaultToken.equals("cg"))&& (preferredToken.equals("dkg")))
																																													{
																																														if(c=='*')
																																															convertAmount=convertAmount*0.001;
																																														else
																																															convertAmount=convertAmount*1000;
																																														
																																													}
																																													else
																																														if((defaultToken.equals("cg"))&&(preferredToken.equals("g")))
																																														{
																																															if(c=='*')
																																																convertAmount=convertAmount*0.01;
																																															else
																																																convertAmount=convertAmount*100;
																																															
																																															
																																														}
																																														else
																																															if((defaultToken.equals("cg"))&&(preferredToken.equals("dg")))
																																															{
																																																if(c=='*')
																																																	convertAmount=convertAmount*0.1;
																																																else
																																																	convertAmount=convertAmount*10;
																																																
																																																
																																															}
																																															else
																																																if((defaultToken.equals("cg"))&&(preferredToken.equals("mg")))
																																																{
																																																	if(c=='*')
																																																		convertAmount=convertAmount*10;
																																																	else
																																																		convertAmount=convertAmount*0.1;
																																																	
																																																	
																																																}
																																											
																																																else
																																																	if((defaultToken.equals("cg"))&&(preferredToken.equals("ug")))
																																																	{
																																																		if(c=='*')
																																																			convertAmount=convertAmount*1E4;
																																																		else
																																																			convertAmount=convertAmount*1E-4;
																																																		
																																																		
																																																	}
																																											
																																																	else
																																																		if((defaultToken.equals("cg"))&&(preferredToken.equals("ng")))
																																																		{
																																																			if(c=='*')
																																																				convertAmount=convertAmount*1E7;
																																																			else
																																																				convertAmount=convertAmount*1E-7;
																																																			
																																																			
																																																		}
																																											
																																																		else
																																																			if((defaultToken.equals("cg"))&&(preferredToken.equals("pg")))
																																																			{
																																																				if(c=='*')
																																																					convertAmount=convertAmount*1E10;
																																																				else
																																																					convertAmount=convertAmount*1E-10;
																																																				
																																																				
																																																			}
																																																			else
																																																				if((defaultToken.equals("cg"))&&(preferredToken.equals("fg")))
																																																				{
																																																					if(c=='*')
																																																						convertAmount=convertAmount*1E13;
																																																					else
																																																						convertAmount=convertAmount*1E-13;
																																																					
																																																					
																																																				}
																																																				else
																																																					if((defaultToken.equals("cg"))&&(preferredToken.equals("ag")))
																																																					{
																																																						if(c=='*')
																																																							convertAmount=convertAmount*1E16;
																																																						else
																																																							convertAmount=convertAmount*1E-16;
																																																						
																																																						
																																																					}
																																																					else
																																																						if((defaultToken.equals("mg"))&&(preferredToken.equals("kg")))
																																																						{
																																																							if(c=='*')
																																																								convertAmount=convertAmount*1E-6;
																																																							else
																																																								convertAmount=convertAmount*1E6;
																																																						}
																																																						else
																																																							if((defaultToken.equals("mg"))&& (preferredToken.equals("dkg")))
																																																							{
																																																								if(c=='*')
																																																									convertAmount=convertAmount*1E-4;
																																																								else
																																																									convertAmount=convertAmount*1E4;
																																																								
																																																							}
																																																							else
																																																								if((defaultToken.equals("mg"))&&(preferredToken.equals("g")))
																																																								{
																																																									if(c=='*')
																																																										convertAmount=convertAmount*0.001;
																																																									else
																																																										convertAmount=convertAmount*1000;
																																																									
																																																									
																																																								}
																																																								else
																																																									if((defaultToken.equals("mg"))&&(preferredToken.equals("dg")))
																																																									{
																																																										if(c=='*')
																																																											convertAmount=convertAmount*0.01;
																																																										else
																																																											convertAmount=convertAmount*100;
																																																										
																																																										
																																																									}
																																																									else
																																																										if((defaultToken.equals("mg"))&&(preferredToken.equals("cg")))
																																																										{
																																																											if(c=='*')
																																																												convertAmount=convertAmount*10;
																																																											else
																																																												convertAmount=convertAmount*0.1;
																																																											
																																																											
																																																										}
																																																					
																																																										else
																																																											if((defaultToken.equals("mg"))&&(preferredToken.equals("ug")))
																																																											{
																																																												if(c=='*')
																																																													convertAmount=convertAmount*1E3;
																																																												else
																																																													convertAmount=convertAmount*1E-3;
																																																												
																																																												
																																																											}
																																																					
																																																											else
																																																												if((defaultToken.equals("mg"))&&(preferredToken.equals("ng")))
																																																												{
																																																													if(c=='*')
																																																														convertAmount=convertAmount*1E6;
																																																													else
																																																														convertAmount=convertAmount*1E-6;
																																																													
																																																													
																																																												}
																																																					
																																																												else
																																																													if((defaultToken.equals("mg"))&&(preferredToken.equals("pg")))
																																																													{
																																																														if(c=='*')
																																																															convertAmount=convertAmount*1E9;
																																																														else
																																																															convertAmount=convertAmount*1E-9;
																																																														
																																																														
																																																													}
																																																													else
																																																														if((defaultToken.equals("mg"))&&(preferredToken.equals("fg")))
																																																														{
																																																															if(c=='*')
																																																																convertAmount=convertAmount*1E12;
																																																															else
																																																																convertAmount=convertAmount*1E-12;
																																																															
																																																															
																																																														}
																																																														else
																																																															if((defaultToken.equals("mg"))&&(preferredToken.equals("ag")))
																																																															{
																																																																if(c=='*')
																																																																	convertAmount=convertAmount*1E15;
																																																																else
																																																																	convertAmount=convertAmount*1E-15;
																																																															}
																																																																
																																																																else
																																																																	if((defaultToken.equals("ug"))&&(preferredToken.equals("kg")))
																																																																	{
																																																																		if(c=='*')
																																																																			convertAmount=convertAmount*1E-9;
																																																																		else
																																																																			convertAmount=convertAmount*1E9;
																																																																	}
																																																																	else
																																																																		if((defaultToken.equals("ug"))&& (preferredToken.equals("dkg")))
																																																																		{
																																																																			if(c=='*')
																																																																				convertAmount=convertAmount*1E-7;
																																																																			else
																																																																				convertAmount=convertAmount*1E7;
																																																																			
																																																																		}
																																																																		else
																																																																			if((defaultToken.equals("ug"))&&(preferredToken.equals("g")))
																																																																			{
																																																																				if(c=='*')
																																																																					convertAmount=convertAmount*1E-6;
																																																																				else
																																																																					convertAmount=convertAmount*1E6;
																																																																				
																																																																				
																																																																			}
																																																																			else
																																																																				if((defaultToken.equals("ug"))&&(preferredToken.equals("dg")))
																																																																				{
																																																																					if(c=='*')
																																																																						convertAmount=convertAmount*1E-5;
																																																																					else
																																																																						convertAmount=convertAmount*1E5;
																																																																					
																																																																					
																																																																				}
																																																																				else
																																																																					if((defaultToken.equals("ug"))&&(preferredToken.equals("cg")))
																																																																					{
																																																																						if(c=='*')
																																																																							convertAmount=convertAmount*1E-4;
																																																																						else
																																																																							convertAmount=convertAmount*1E4;
																																																																						
																																																																						
																																																																					}
																																																																
																																																																					else
																																																																						if((defaultToken.equals("ug"))&&(preferredToken.equals("mg")))
																																																																						{
																																																																							if(c=='*')
																																																																								convertAmount=convertAmount*1E-3;
																																																																							else
																																																																								convertAmount=convertAmount*1E3;
																																																																							
																																																																							
																																																																						}
																																																																
																																																																						else
																																																																							if((defaultToken.equals("ug"))&&(preferredToken.equals("ng")))
																																																																							{
																																																																								if(c=='*')
																																																																									convertAmount=convertAmount*1E3;
																																																																								else
																																																																									convertAmount=convertAmount*1E-3;
																																																																								
																																																																								
																																																																							}
																																																																
																																																																							else
																																																																								if((defaultToken.equals("ug"))&&(preferredToken.equals("pg")))
																																																																								{
																																																																									if(c=='*')
																																																																										convertAmount=convertAmount*1E6;
																																																																									else
																																																																										convertAmount=convertAmount*1E-6;
																																																																									
																																																																									
																																																																								}
																																																																								else
																																																																									if((defaultToken.equals("ug"))&&(preferredToken.equals("fg")))
																																																																									{
																																																																										if(c=='*')
																																																																											convertAmount=convertAmount*1E9;
																																																																										else
																																																																											convertAmount=convertAmount*1E-9;
																																																																										
																																																																										
																																																																									}
																																																																									else
																																																																										if((defaultToken.equals("ug"))&&(preferredToken.equals("ag")))
																																																																										{
																																																																											if(c=='*')
																																																																												convertAmount=convertAmount*1E12;
																																																																											else
																																																																												convertAmount=convertAmount*1E-12;
																																																																											
																																																																											
																																																																										}																																																										
											
																																																																										else
																																																																											if((defaultToken.equals("ng"))&&(preferredToken.equals("kg")))
																																																																											{
																																																																												if(c=='*')
																																																																													convertAmount=convertAmount*1E-12;
																																																																												else
																																																																													convertAmount=convertAmount*1E12;
																																																																											}
																																																																											else
																																																																												if((defaultToken.equals("ng"))&& (preferredToken.equals("dkg")))
																																																																												{
																																																																													if(c=='*')
																																																																														convertAmount=convertAmount*1E-10;
																																																																													else
																																																																														convertAmount=convertAmount*1E10;
																																																																													
																																																																												}
																																																																												else
																																																																													if((defaultToken.equals("ng"))&&(preferredToken.equals("g")))
																																																																													{
																																																																														if(c=='*')
																																																																															convertAmount=convertAmount*1E-9;
																																																																														else
																																																																															convertAmount=convertAmount*1E9;
																																																																														
																																																																														
																																																																													}
																																																																													else
																																																																														if((defaultToken.equals("ng"))&&(preferredToken.equals("dg")))
																																																																														{
																																																																															if(c=='*')
																																																																																convertAmount=convertAmount*1E-8;
																																																																															else
																																																																																convertAmount=convertAmount*1E8;
																																																																															
																																																																															
																																																																														}
																																																																														else
																																																																															if((defaultToken.equals("ng"))&&(preferredToken.equals("cg")))
																																																																															{
																																																																																if(c=='*')
																																																																																	convertAmount=convertAmount*1E-7;
																																																																																else
																																																																																	convertAmount=convertAmount*1E7;
																																																																																
																																																																																
																																																																															}
																																																																										
																																																																															else
																																																																																if((defaultToken.equals("ng"))&&(preferredToken.equals("mg")))
																																																																																{
																																																																																	if(c=='*')
																																																																																		convertAmount=convertAmount*1E-6;
																																																																																	else
																																																																																		convertAmount=convertAmount*1E6;
																																																																																	
																																																																																	
																																																																																}
																																																																										
																																																																																else
																																																																																	if((defaultToken.equals("ng"))&&(preferredToken.equals("ug")))
																																																																																	{
																																																																																		if(c=='*')
																																																																																			convertAmount=convertAmount*1E-3;
																																																																																		else
																																																																																			convertAmount=convertAmount*1E3;
																																																																																		
																																																																																		
																																																																																	}
																																																																										
																																																																																	else
																																																																																		if((defaultToken.equals("ng"))&&(preferredToken.equals("pg")))
																																																																																		{
																																																																																			if(c=='*')
																																																																																				convertAmount=convertAmount*1E3;
																																																																																			else
																																																																																				convertAmount=convertAmount*1E-3;
																																																																																			
																																																																																			
																																																																																		}
																																																																																		else
																																																																																			if((defaultToken.equals("ng"))&&(preferredToken.equals("fg")))
																																																																																			{
																																																																																				if(c=='*')
																																																																																					convertAmount=convertAmount*1E6;
																																																																																				else
																																																																																					convertAmount=convertAmount*1E-6;
																																																																																				
																																																																																				
																																																																																			}
																																																																																			else
																																																																																				if((defaultToken.equals("ng"))&&(preferredToken.equals("ag")))
																																																																																				{
																																																																																					if(c=='*')
																																																																																						convertAmount=convertAmount*1E9;
																																																																																					else
																																																																																						convertAmount=convertAmount*1E-9;
																																																																																					
																																																																																					
																																																																																				}																																																										
																																																																																				else
																																																																																					if((defaultToken.equals("pg"))&&(preferredToken.equals("kg")))
																																																																																					{
																																																																																						if(c=='*')
																																																																																							convertAmount=convertAmount*1E-15;
																																																																																						else
																																																																																							convertAmount=convertAmount*1E15;
																																																																																					}
																																																																																					else
																																																																																						if((defaultToken.equals("pg"))&& (preferredToken.equals("dkg")))
																																																																																						{
																																																																																							if(c=='*')
																																																																																								convertAmount=convertAmount*1E-13;
																																																																																							else
																																																																																								convertAmount=convertAmount*1E13;
																																																																																							
																																																																																						}
																																																																																						else
																																																																																							if((defaultToken.equals("pg"))&&(preferredToken.equals("g")))
																																																																																							{
																																																																																								if(c=='*')
																																																																																									convertAmount=convertAmount*1E-12;
																																																																																								else
																																																																																									convertAmount=convertAmount*1E12;
																																																																																								
																																																																																								
																																																																																							}
																																																																																							else
																																																																																								if((defaultToken.equals("pg"))&&(preferredToken.equals("dg")))
																																																																																								{
																																																																																									if(c=='*')
																																																																																										convertAmount=convertAmount*1E-11;
																																																																																									else
																																																																																										convertAmount=convertAmount*11;
																																																																																									
																																																																																									
																																																																																								}
																																																																																								else
																																																																																									if((defaultToken.equals("pg"))&&(preferredToken.equals("cg")))
																																																																																									{
																																																																																										if(c=='*')
																																																																																											convertAmount=convertAmount*1E-10;
																																																																																										else
																																																																																											convertAmount=convertAmount*1E10;
																																																																																										
																																																																																										
																																																																																									}
																																																																																				
																																																																																									else
																																																																																										if((defaultToken.equals("pg"))&&(preferredToken.equals("mg")))
																																																																																										{
																																																																																											if(c=='*')
																																																																																												convertAmount=convertAmount*1E-9;
																																																																																											else
																																																																																												convertAmount=convertAmount*1E9;
																																																																																											
																																																																																											
																																																																																										}
																																																																																				
																																																																																										else
																																																																																											if((defaultToken.equals("pg"))&&(preferredToken.equals("ug")))
																																																																																											{
																																																																																												if(c=='*')
																																																																																													convertAmount=convertAmount*1E-6;
																																																																																												else
																																																																																													convertAmount=convertAmount*1E6;
																																																																																												
																																																																																												
																																																																																											}
																																																																																				
																																																																																											else
																																																																																												if((defaultToken.equals("pg"))&&(preferredToken.equals("ng")))
																																																																																												{
																																																																																													if(c=='*')
																																																																																														convertAmount=convertAmount*1E-3;
																																																																																													else
																																																																																														convertAmount=convertAmount*1E3;
																																																																																													
																																																																																													
																																																																																												}
																																																																																												else
																																																																																													if((defaultToken.equals("pg"))&&(preferredToken.equals("fg")))
																																																																																													{
																																																																																														if(c=='*')
																																																																																															convertAmount=convertAmount*1E3;
																																																																																														else
																																																																																															convertAmount=convertAmount*1E-3;
																																																																																														
																																																																																														
																																																																																													}
																																																																																													else
																																																																																														if((defaultToken.equals("pg"))&&(preferredToken.equals("ag")))
																																																																																														{
																																																																																															if(c=='*')
																																																																																																convertAmount=convertAmount*1E6;
																																																																																															else
																																																																																																convertAmount=convertAmount*1E-6;
																																																																																															
																																																																																															
																																																																																														}																																																										
																																																																																														else
																																																																																															if((defaultToken.equals("fg"))&&(preferredToken.equals("kg")))
																																																																																															{
																																																																																																if(c=='*')
																																																																																																	convertAmount=convertAmount*1E-18;
																																																																																																else
																																																																																																	convertAmount=convertAmount*1E18;
																																																																																															}
																																																																																															else
																																																																																																if((defaultToken.equals("fg"))&& (preferredToken.equals("dkg")))
																																																																																																{
																																																																																																	if(c=='*')
																																																																																																		convertAmount=convertAmount*1E-16;
																																																																																																	else
																																																																																																		convertAmount=convertAmount*1E16;
																																																																																																	
																																																																																																}
																																																																																																else
																																																																																																	if((defaultToken.equals("fg"))&&(preferredToken.equals("g")))
																																																																																																	{
																																																																																																		if(c=='*')
																																																																																																			convertAmount=convertAmount*1E-15;
																																																																																																		else
																																																																																																			convertAmount=convertAmount*1E15;
																																																																																																		
																																																																																																		
																																																																																																	}
																																																																																																	else
																																																																																																		if((defaultToken.equals("fg"))&&(preferredToken.equals("dg")))
																																																																																																		{
																																																																																																			if(c=='*')
																																																																																																				convertAmount=convertAmount*1E-14;
																																																																																																			else
																																																																																																				convertAmount=convertAmount*14;
																																																																																																			
																																																																																																			
																																																																																																		}
																																																																																																		else
																																																																																																			if((defaultToken.equals("fg"))&&(preferredToken.equals("cg")))
																																																																																																			{
																																																																																																				if(c=='*')
																																																																																																					convertAmount=convertAmount*1E-13;
																																																																																																				else
																																																																																																					convertAmount=convertAmount*1E13;
																																																																																																				
																																																																																																				
																																																																																																			}
																																																																																														
																																																																																																			else
																																																																																																				if((defaultToken.equals("fg"))&&(preferredToken.equals("mg")))
																																																																																																				{
																																																																																																					if(c=='*')
																																																																																																						convertAmount=convertAmount*1E-12;
																																																																																																					else
																																																																																																						convertAmount=convertAmount*1E12;
																																																																																																					
																																																																																																					
																																																																																																				}
																																																																																														
																																																																																																				else
																																																																																																					if((defaultToken.equals("fg"))&&(preferredToken.equals("ug")))
																																																																																																					{
																																																																																																						if(c=='*')
																																																																																																							convertAmount=convertAmount*1E-9;
																																																																																																						else
																																																																																																							convertAmount=convertAmount*1E9;
																																																																																																						
																																																																																																						
																																																																																																					}
																																																																																														
																																																																																																					else
																																																																																																						if((defaultToken.equals("fg"))&&(preferredToken.equals("ng")))
																																																																																																						{
																																																																																																							if(c=='*')
																																																																																																								convertAmount=convertAmount*1E-6;
																																																																																																							else
																																																																																																								convertAmount=convertAmount*1E6;
																																																																																																							
																																																																																																							
																																																																																																						}
																																																																																																						else
																																																																																																							if((defaultToken.equals("fg"))&&(preferredToken.equals("pg")))
																																																																																																							{
																																																																																																								if(c=='*')
																																																																																																									convertAmount=convertAmount*1E-3;
																																																																																																								else
																																																																																																									convertAmount=convertAmount*1E3;
																																																																																																								
																																																																																																								
																																																																																																							}
																																																																																																							else
																																																																																																								if((defaultToken.equals("fg"))&&(preferredToken.equals("ag")))
																																																																																																								{
																																																																																																									if(c=='*')
																																																																																																										convertAmount=convertAmount*1E3;
																																																																																																									else
																																																																																																										convertAmount=convertAmount*1E-3;
																																																																																																									
																																																																																																									
																																																																																																								}																																																										
																																																																																																								else
																																																																																																									if((defaultToken.equals("ag"))&&(preferredToken.equals("kg")))
																																																																																																									{
																																																																																																										if(c=='*')
																																																																																																											convertAmount=convertAmount*1E-21;
																																																																																																										else
																																																																																																											convertAmount=convertAmount*1E21;
																																																																																																									}
																																																																																																									else
																																																																																																										if((defaultToken.equals("ag"))&& (preferredToken.equals("dkg")))
																																																																																																										{
																																																																																																											if(c=='*')
																																																																																																												convertAmount=convertAmount*1E-19;
																																																																																																											else
																																																																																																												convertAmount=convertAmount*1E19;
																																																																																																											
																																																																																																										}
																																																																																																										else
																																																																																																											if((defaultToken.equals("ag"))&&(preferredToken.equals("g")))
																																																																																																											{
																																																																																																												if(c=='*')
																																																																																																													convertAmount=convertAmount*1E-18;
																																																																																																												else
																																																																																																													convertAmount=convertAmount*1E18;
																																																																																																												
																																																																																																												
																																																																																																											}
																																																																																																											else
																																																																																																												if((defaultToken.equals("ag"))&&(preferredToken.equals("dg")))
																																																																																																												{
																																																																																																													if(c=='*')
																																																																																																														convertAmount=convertAmount*1E-17;
																																																																																																													else
																																																																																																														convertAmount=convertAmount*17;
																																																																																																													
																																																																																																													
																																																																																																												}
																																																																																																												else
																																																																																																													if((defaultToken.equals("ag"))&&(preferredToken.equals("cg")))
																																																																																																													{
																																																																																																														if(c=='*')
																																																																																																															convertAmount=convertAmount*1E-16;
																																																																																																														else
																																																																																																															convertAmount=convertAmount*1E16;
																																																																																																														
																																																																																																														
																																																																																																													}
																																																																																																								
																																																																																																													else
																																																																																																														if((defaultToken.equals("ag"))&&(preferredToken.equals("mg")))
																																																																																																														{
																																																																																																															if(c=='*')
																																																																																																																convertAmount=convertAmount*1E-15;
																																																																																																															else
																																																																																																																convertAmount=convertAmount*1E15;
																																																																																																															
																																																																																																															
																																																																																																														}
																																																																																																								
																																																																																																														else
																																																																																																															if((defaultToken.equals("ag"))&&(preferredToken.equals("ug")))
																																																																																																															{
																																																																																																																if(c=='*')
																																																																																																																	convertAmount=convertAmount*1E-12;
																																																																																																																else
																																																																																																																	convertAmount=convertAmount*1E12;
																																																																																																																
																																																																																																																
																																																																																																															}
																																																																																																								
																																																																																																															else
																																																																																																																if((defaultToken.equals("ag"))&&(preferredToken.equals("ng")))
																																																																																																																{
																																																																																																																	if(c=='*')
																																																																																																																		convertAmount=convertAmount*1E-9;
																																																																																																																	else
																																																																																																																		convertAmount=convertAmount*1E9;
																																																																																																																	
																																																																																																																	
																																																																																																																}
																																																																																																																else
																																																																																																																	if((defaultToken.equals("ag"))&&(preferredToken.equals("pg")))
																																																																																																																	{
																																																																																																																		if(c=='*')
																																																																																																																			convertAmount=convertAmount*1E-6;
																																																																																																																		else
																																																																																																																			convertAmount=convertAmount*1E6;
																																																																																																																		
																																																																																																																		
																																																																																																																	}
																																																																																																																	else
																																																																																																																		if((defaultToken.equals("ag"))&&(preferredToken.equals("fg")))
																																																																																																																		{
																																																																																																																			if(c=='*')
																																																																																																																				convertAmount=convertAmount*1E-3;
																																																																																																																			else
																																																																																																																				convertAmount=convertAmount*1E3;
																																																																																																																			
																																																																																																																			
																																																																																																																		}																																																										
																																															
			else

			if((defaultToken.equals("hr")||defaultToken.equals("hour")||defaultToken.equals("h")) && (preferredToken.equals("hr")||preferredToken.equals("hour")||preferredToken.equals("h")))
			{
				
					convertAmount=convertAmount;
				
				
			}
			else
				if((defaultToken.equals("min")||defaultToken.equals("m"))&& (preferredToken.equals("min")||preferredToken.equals("m")))
				{
					convertAmount=convertAmount;
				}
				else
					if((defaultToken.equals("sec")||defaultToken.equals("s")||defaultToken.equals("seconds"))&&(preferredToken.equals("sec")||preferredToken.equals("s")||preferredToken.equals("seconds")))
					{
						convertAmount=convertAmount;
					}
					else
						if((defaultToken.equals("hr")||defaultToken.equals("hour")||defaultToken.equals("h")) && (preferredToken.equals("min")||preferredToken.equals("m")))
						{
							
							if(c=='*')
								convertAmount=convertAmount*60;
							else
								convertAmount=convertAmount*0.016666667;
													
						}
						else
							if((defaultToken.equals("hr")||defaultToken.equals("hour")||defaultToken.equals("h")) && (preferredToken.equals("sec")||preferredToken.equals("s")||preferredToken.equals("seconds")))
							{
								
								if(c=='*')
									convertAmount=convertAmount*3600;
								else
									convertAmount=convertAmount*0.000277778;
														
							}
							else
								if((defaultToken.equals("min")||defaultToken.equals("m")) && (preferredToken.equals("h")||preferredToken.equals("hour")||preferredToken.equals("hr")))
								{
									
									if(c=='*')
										convertAmount=convertAmount*0.016666667;
									else
										convertAmount=convertAmount*60;
															
								}
								else
									if((defaultToken.equals("min")||defaultToken.equals("m")) && (preferredToken.equals("sec")||preferredToken.equals("s")||preferredToken.equals("seconds")))
									{
										
										if(c=='*')
											convertAmount=convertAmount*60;
										else
											convertAmount=convertAmount*0.016666667;
																
									}
									else
										if((defaultToken.equals("sec")||defaultToken.equals("s")||defaultToken.equals("seconds")) && (preferredToken.equals("h")||preferredToken.equals("hour")||preferredToken.equals("hr")))
										{
											
											if(c=='*')
												convertAmount=convertAmount*0.000277778;
											else
												convertAmount=convertAmount*3600;
																	
										}
										else
											if((defaultToken.equals("sec")||defaultToken.equals("s")||defaultToken.equals("seconds")) && (preferredToken.equals("min")||preferredToken.equals("m")))
											{
												
												if(c=='*')
													convertAmount=convertAmount*0.016666667;
												else
													convertAmount=convertAmount*60;
																		
											}else
			
												if((defaultToken.equals("L"))&&(preferredToken.equals("mL")))
												{
													if(c=='*')
														convertAmount=convertAmount*1000;
													else
														convertAmount=convertAmount*0.001;
												}
												else
													if((defaultToken.equals("L"))&& (preferredToken.equals("uL")))
													{
														if(c=='*')
															convertAmount=convertAmount*1000000;
														else
															convertAmount=convertAmount*0.000001;
														
													}
											
											
													else
														if((defaultToken.equals("L"))&&(preferredToken.equals("nL")))
														{
															if(c=='*')
																convertAmount=convertAmount*1E9;
															else
																convertAmount=convertAmount*0.01;
														}
													else
														if((defaultToken.equals("L"))&&(preferredToken.equals("aL")))
														{
															if(c=='*')
																convertAmount=convertAmount*1E18;
															else
																convertAmount=convertAmount*1E-18;
															
															
														}
														else
															if((defaultToken.equals("L"))&&(preferredToken.equals("fL")))
															{
																if(c=='*')
																	convertAmount=convertAmount*1E15;
																else
																	convertAmount=convertAmount*1E-15;
																
																
															}
															else
																if((defaultToken.equals("L"))&&(preferredToken.equals("pL")))
																{
																	if(c=='*')
																		convertAmount=convertAmount*1E12;
																	else
																		convertAmount=convertAmount*1E-12;
																	
																	
																}
											
																else
																	if((defaultToken.equals("L"))&&(preferredToken.equals("kL")))
																	{
																		if(c=='*')
																			convertAmount=convertAmount*0.001;
																		else
																			convertAmount=convertAmount*1000;
																		
																		
																	}
											
																	else
																		if((defaultToken.equals("L"))&&(preferredToken.equals("dkL")))
																		{
																			if(c=='*')
																				convertAmount=convertAmount*0.1;
																			else
																				convertAmount=convertAmount*10;
																			
																			
																		}
											
																		else
																			if((defaultToken.equals("L"))&&(preferredToken.equals("dL")))
																			{
																				if(c=='*')
																					convertAmount=convertAmount*10;
																				else
																					convertAmount=convertAmount*0.1;
																				
																				
																			}
																			else
																				if((defaultToken.equals("L"))&&(preferredToken.equals("cL")))
																				{
																					if(c=='*')
																						convertAmount=convertAmount*100;
																					else
																						convertAmount=convertAmount*0.01;
																					
																					
																				}
											
																				else
																					if((defaultToken.equals("kL"))&&(preferredToken.equals("dkL")))
																					{
																						if(c=='*')
																							convertAmount=convertAmount*100;
																						else
																							convertAmount=convertAmount*0.01;
																					}
																					else
																						if((defaultToken.equals("KL"))&& (preferredToken.equals("L")))
																						{
																							if(c=='*')
																								convertAmount=convertAmount*1000;
																							else
																								convertAmount=convertAmount*0.001;
																							
																						}
																						else
																							if((defaultToken.equals("kL"))&&(preferredToken.equals("dL")))
																							{
																								if(c=='*')
																									convertAmount=convertAmount*10000;
																								else
																									convertAmount=convertAmount*0.0001;
																								
																								
																							}
																							else
																								if((defaultToken.equals("kL"))&&(preferredToken.equals("cL")))
																								{
																									if(c=='*')
																										convertAmount=convertAmount*100000;
																									else
																										convertAmount=convertAmount*0.00001;
																									
																									
																								}
																								else
																									if((defaultToken.equals("kL"))&&(preferredToken.equals("mL")))
																									{
																										if(c=='*')
																											convertAmount=convertAmount*1000000;
																										else
																											convertAmount=convertAmount*0.000001;
																										
																										
																									}
																				
																									else
																										if((defaultToken.equals("kL"))&&(preferredToken.equals("uL")))
																										{
																											if(c=='*')
																												convertAmount=convertAmount*1E9;
																											else
																												convertAmount=convertAmount*1E-9;
																											
																											
																										}
																				
																										else
																											if((defaultToken.equals("kL"))&&(preferredToken.equals("nL")))
																											{
																												if(c=='*')
																													convertAmount=convertAmount*1E12;
																												else
																													convertAmount=convertAmount*1E-12;
																												
																												
																											}
																				
																											else
																												if((defaultToken.equals("kL"))&&(preferredToken.equals("pL")))
																												{
																													if(c=='*')
																														convertAmount=convertAmount*1E15;
																													else
																														convertAmount=convertAmount*1E-15;
																													
																													
																												}
																												else
																													if((defaultToken.equals("kL"))&&(preferredToken.equals("fL")))
																													{
																														if(c=='*')
																															convertAmount=convertAmount*1E18;
																														else
																															convertAmount=convertAmount*1E-18;
																														
																														
																													}
																													else
																														if((defaultToken.equals("kL"))&&(preferredToken.equals("aL")))
																														{
																															if(c=='*')
																																convertAmount=convertAmount*1E21;
																															else
																																convertAmount=convertAmount*1E-21;
																															
																															
																														}
														
											
																														else
																															if((defaultToken.equals("dkL"))&&(preferredToken.equals("kL")))
																															{
																																if(c=='*')
																																	convertAmount=convertAmount*0.01;
																																else
																																	convertAmount=convertAmount*100;
																															}
																															else
																																if((defaultToken.equals("dKL"))&& (preferredToken.equals("L")))
																																{
																																	if(c=='*')
																																		convertAmount=convertAmount*10;
																																	else
																																		convertAmount=convertAmount*0.1;
																																	
																																}
																																else
																																	if((defaultToken.equals("dkL"))&&(preferredToken.equals("dL")))
																																	{
																																		if(c=='*')
																																			convertAmount=convertAmount*100;
																																		else
																																			convertAmount=convertAmount*0.01;
																																		
																																		
																																	}
																																	else
																																		if((defaultToken.equals("dkL"))&&(preferredToken.equals("cL")))
																																		{
																																			if(c=='*')
																																				convertAmount=convertAmount*1000;
																																			else
																																				convertAmount=convertAmount*0.001;
																																			
																																			
																																		}
																																		else
																																			if((defaultToken.equals("dkL"))&&(preferredToken.equals("mL")))
																																			{
																																				if(c=='*')
																																					convertAmount=convertAmount*10000;
																																				else
																																					convertAmount=convertAmount*0.0001;
																																				
																																																															
																																				
																																			}
																														
																																			else
																																				if((defaultToken.equals("dkL"))&&(preferredToken.equals("uL")))
																																				{
																																					if(c=='*')
																																						convertAmount=convertAmount*1E7;
																																					else
																																						convertAmount=convertAmount*1E-7;
																																					
																																					
																																				}
																														
																																				else
																																					if((defaultToken.equals("dkL"))&&(preferredToken.equals("nL")))
																																					{
																																						if(c=='*')
																																							convertAmount=convertAmount*1E10;
																																						else
																																							convertAmount=convertAmount*1E-10;
																																						
																																						
																																					}
																														
																																					else
																																						if((defaultToken.equals("dkL"))&&(preferredToken.equals("pL")))
																																						{
																																							if(c=='*')
																																								convertAmount=convertAmount*1E13;
																																							else
																																								convertAmount=convertAmount*1E-13;
																																							
																																							
																																						}
																																						else
																																							if((defaultToken.equals("dkL"))&&(preferredToken.equals("fL")))
																																							{
																																								if(c=='*')
																																									convertAmount=convertAmount*1E16;
																																								else
																																									convertAmount=convertAmount*1E-16;
																																								
																																								
																																							}
																																							else
																																								if((defaultToken.equals("dkL"))&&(preferredToken.equals("aL")))
																																								{
																																									if(c=='*')
																																										convertAmount=convertAmount*1E19;
																																									else
																																										convertAmount=convertAmount*1E-19;
																																																																												
																																									
																																									
																																								}
											
																																								else
																																									if((defaultToken.equals("dL"))&&(preferredToken.equals("kL")))
																																									{
																																										if(c=='*')
																																											convertAmount=convertAmount*0.0001;
																																										else
																																											convertAmount=convertAmount*10000;
																																									}
																																									else
																																										if((defaultToken.equals("dL"))&& (preferredToken.equals("dkL")))
																																										{
																																											if(c=='*')
																																												convertAmount=convertAmount*0.01;
																																											else
																																												convertAmount=convertAmount*100;
																																											
																																										}
																																										else
																																											if((defaultToken.equals("dL"))&&(preferredToken.equals("L")))
																																											{
																																												if(c=='*')
																																													convertAmount=convertAmount*0.1;
																																												else
																																													convertAmount=convertAmount*10;
																																												
																																												
																																											}
																																											else
																																												if((defaultToken.equals("dL"))&&(preferredToken.equals("cL")))
																																												{
																																													if(c=='*')
																																														convertAmount=convertAmount*10;
																																													else
																																														convertAmount=convertAmount*0.1;
																																													
																																													
																																												}
																																												else
																																													if((defaultToken.equals("dL"))&&(preferredToken.equals("mL")))
																																													{
																																														if(c=='*')
																																															convertAmount=convertAmount*100;
																																														else
																																															convertAmount=convertAmount*0.01;
																																														
																																														
																																													}
																																								
																																													else
																																														if((defaultToken.equals("dL"))&&(preferredToken.equals("uL")))
																																														{
																																															if(c=='*')
																																																convertAmount=convertAmount*1E5;
																																															else
																																																convertAmount=convertAmount*1E-5;
																																															
																																															
																																														}
																																								
																																														else
																																															if((defaultToken.equals("dL"))&&(preferredToken.equals("nL")))
																																															{
																																																if(c=='*')
																																																	convertAmount=convertAmount*1E8;
																																																else
																																																	convertAmount=convertAmount*1E-8;
																																																
																																																
																																															}
																																								
																																															else
																																																if((defaultToken.equals("dL"))&&(preferredToken.equals("pL")))
																																																{
																																																	if(c=='*')
																																																		convertAmount=convertAmount*1E11;
																																																	else
																																																		convertAmount=convertAmount*1E-11;
																																																	
																																																	
																																																}
																																																else
																																																	if((defaultToken.equals("dL"))&&(preferredToken.equals("fL")))
																																																	{
																																																		if(c=='*')
																																																			convertAmount=convertAmount*1E14;
																																																		else
																																																			convertAmount=convertAmount*1E-14;
																																																		
																																																		
																																																	}
																																																	else
																																																		if((defaultToken.equals("dL"))&&(preferredToken.equals("aL")))
																																																		{
																																																			if(c=='*')
																																																				convertAmount=convertAmount*1E17;
																																																			else
																																																				convertAmount=convertAmount*1E-17;
																																																			
																																																		}
																																											
																																																			else
																																																				if((defaultToken.equals("cL"))&&(preferredToken.equals("kL")))
																																																				{
																																																					if(c=='*')
																																																						convertAmount=convertAmount*1E-5;
																																																					else
																																																						convertAmount=convertAmount*1E5;
																																																				}
																																																				else
																																																					if((defaultToken.equals("cL"))&& (preferredToken.equals("dkL")))
																																																					{
																																																						if(c=='*')
																																																							convertAmount=convertAmount*0.001;
																																																						else
																																																							convertAmount=convertAmount*1000;
																																																						
																																																					}
																																																					else
																																																						if((defaultToken.equals("cL"))&&(preferredToken.equals("L")))
																																																						{
																																																							if(c=='*')
																																																								convertAmount=convertAmount*0.01;
																																																							else
																																																								convertAmount=convertAmount*100;
																																																							
																																																							
																																																						}
																																																						else
																																																							if((defaultToken.equals("cL"))&&(preferredToken.equals("dL")))
																																																							{
																																																								if(c=='*')
																																																									convertAmount=convertAmount*0.1;
																																																								else
																																																									convertAmount=convertAmount*10;
																																																								
																																																								
																																																							}
																																																							else
																																																								if((defaultToken.equals("cL"))&&(preferredToken.equals("mL")))
																																																								{
																																																									if(c=='*')
																																																										convertAmount=convertAmount*10;
																																																									else
																																																										convertAmount=convertAmount*0.1;
																																																									
																																																									
																																																								}
																																																			
																																																								else
																																																									if((defaultToken.equals("cL"))&&(preferredToken.equals("uL")))
																																																									{
																																																										if(c=='*')
																																																											convertAmount=convertAmount*1E4;
																																																										else
																																																											convertAmount=convertAmount*1E-4;
																																																										
																																																										
																																																									}
																																																			
																																																									else
																																																										if((defaultToken.equals("cL"))&&(preferredToken.equals("nL")))
																																																										{
																																																											if(c=='*')
																																																												convertAmount=convertAmount*1E7;
																																																											else
																																																												convertAmount=convertAmount*1E-7;
																																																											
																																																											
																																																										}
																																																			
																																																										else
																																																											if((defaultToken.equals("cL"))&&(preferredToken.equals("pL")))
																																																											{
																																																												if(c=='*')
																																																													convertAmount=convertAmount*1E10;
																																																												else
																																																													convertAmount=convertAmount*1E-10;
																																																												
																																																												
																																																											}
																																																											else
																																																												if((defaultToken.equals("cL"))&&(preferredToken.equals("fL")))
																																																												{
																																																													if(c=='*')
																																																														convertAmount=convertAmount*1E13;
																																																													else
																																																														convertAmount=convertAmount*1E-13;
																																																													
																																																													
																																																												}
																																																												else
																																																													if((defaultToken.equals("cL"))&&(preferredToken.equals("aL")))
																																																													{
																																																														if(c=='*')
																																																															convertAmount=convertAmount*1E16;
																																																														else
																																																															convertAmount=convertAmount*1E-16;
																																																														
																																																														
																																																													}
																																																													else
																																																														if((defaultToken.equals("mL"))&&(preferredToken.equals("kL")))
																																																														{
																																																															if(c=='*')
																																																																convertAmount=convertAmount*1E-6;
																																																															else
																																																																convertAmount=convertAmount*1E6;
																																																														}
																																																														else
																																																															if((defaultToken.equals("mL"))&& (preferredToken.equals("dkL")))
																																																															{
																																																																if(c=='*')
																																																																	convertAmount=convertAmount*1E-4;
																																																																else
																																																																	convertAmount=convertAmount*1E4;
																																																																
																																																															}
																																																															else
																																																																if((defaultToken.equals("mL"))&&(preferredToken.equals("L")))
																																																																{
																																																																	if(c=='*')
																																																																		convertAmount=convertAmount*0.001;
																																																																	else
																																																																		convertAmount=convertAmount*1000;
																																																																	
																																																																	
																																																																}
																																																																else
																																																																	if((defaultToken.equals("mL"))&&(preferredToken.equals("dL")))
																																																																	{
																																																																		if(c=='*')
																																																																			convertAmount=convertAmount*0.01;
																																																																		else
																																																																			convertAmount=convertAmount*100;
																																																																		
																																																																		
																																																																	}
																																																																	else
																																																																		if((defaultToken.equals("mL"))&&(preferredToken.equals("cL")))
																																																																		{
																																																																			if(c=='*')
																																																																				convertAmount=convertAmount*10;
																																																																			else
																																																																				convertAmount=convertAmount*0.1;
																																																																			
																																																																			
																																																																		}
																																																													
																																																																		else
																																																																			if((defaultToken.equals("mL"))&&(preferredToken.equals("uL")))
																																																																			{
																																																																				if(c=='*')
																																																																					convertAmount=convertAmount*1E3;
																																																																				else
																																																																					convertAmount=convertAmount*1E-3;
																																																																				
																																																																				
																																																																			}
																																																													
																																																																			else
																																																																				if((defaultToken.equals("mL"))&&(preferredToken.equals("nL")))
																																																																				{
																																																																					if(c=='*')
																																																																						convertAmount=convertAmount*1E6;
																																																																					else
																																																																						convertAmount=convertAmount*1E-6;
																																																																					
																																																																					
																																																																				}
																																																													
																																																																				else
																																																																					if((defaultToken.equals("mL"))&&(preferredToken.equals("pL")))
																																																																					{
																																																																						if(c=='*')
																																																																							convertAmount=convertAmount*1E9;
																																																																						else
																																																																							convertAmount=convertAmount*1E-9;
																																																																						
																																																																						
																																																																					}
																																																																					else
																																																																						if((defaultToken.equals("mL"))&&(preferredToken.equals("fL")))
																																																																						{
																																																																							if(c=='*')
																																																																								convertAmount=convertAmount*1E12;
																																																																							else
																																																																								convertAmount=convertAmount*1E-12;
																																																																							
																																																																							
																																																																						}
																																																																						else
																																																																							if((defaultToken.equals("mL"))&&(preferredToken.equals("aL")))
																																																																							{
																																																																								if(c=='*')
																																																																									convertAmount=convertAmount*1E15;
																																																																								else
																																																																									convertAmount=convertAmount*1E-15;
																																																																							}
																																																																								
																																																																								else
																																																																									if((defaultToken.equals("uL"))&&(preferredToken.equals("kL")))
																																																																									{
																																																																										if(c=='*')
																																																																											convertAmount=convertAmount*1E-9;
																																																																										else
																																																																											convertAmount=convertAmount*1E9;
																																																																									}
																																																																									else
																																																																										if((defaultToken.equals("uL"))&& (preferredToken.equals("dkL")))
																																																																										{
																																																																											if(c=='*')
																																																																												convertAmount=convertAmount*1E-7;
																																																																											else
																																																																												convertAmount=convertAmount*1E7;
																																																																											
																																																																										}
																																																																										else
																																																																											if((defaultToken.equals("uL"))&&(preferredToken.equals("L")))
																																																																											{
																																																																												if(c=='*')
																																																																													convertAmount=convertAmount*1E-6;
																																																																												else
																																																																													convertAmount=convertAmount*1E6;
																																																																												
																																																																												
																																																																											}
																																																																											else
																																																																												if((defaultToken.equals("uL"))&&(preferredToken.equals("dL")))
																																																																												{
																																																																													if(c=='*')
																																																																														convertAmount=convertAmount*1E-5;
																																																																													else
																																																																														convertAmount=convertAmount*1E5;
																																																																													
																																																																													
																																																																												}
																																																																												else
																																																																													if((defaultToken.equals("uL"))&&(preferredToken.equals("cL")))
																																																																													{
																																																																														if(c=='*')
																																																																															convertAmount=convertAmount*1E-4;
																																																																														else
																																																																															convertAmount=convertAmount*1E4;
																																																																														
																																																																														
																																																																													}
																																																																								
																																																																													else
																																																																														if((defaultToken.equals("uL"))&&(preferredToken.equals("mL")))
																																																																														{
																																																																															if(c=='*')
																																																																																convertAmount=convertAmount*1E-3;
																																																																															else
																																																																																convertAmount=convertAmount*1E3;
																																																																															
																																																																															
																																																																														}
																																																																								
																																																																														else
																																																																															if((defaultToken.equals("uL"))&&(preferredToken.equals("nL")))
																																																																															{
																																																																																if(c=='*')
																																																																																	convertAmount=convertAmount*1E3;
																																																																																else
																																																																																	convertAmount=convertAmount*1E-3;
																																																																																
																																																																																
																																																																															}
																																																																								
																																																																															else
																																																																																if((defaultToken.equals("uL"))&&(preferredToken.equals("pL")))
																																																																																{
																																																																																	if(c=='*')
																																																																																		convertAmount=convertAmount*1E6;
																																																																																	else
																																																																																		convertAmount=convertAmount*1E-6;
																																																																																	
																																																																																	
																																																																																}
																																																																																else
																																																																																	if((defaultToken.equals("uL"))&&(preferredToken.equals("fL")))
																																																																																	{
																																																																																		if(c=='*')
																																																																																			convertAmount=convertAmount*1E9;
																																																																																		else
																																																																																			convertAmount=convertAmount*1E-9;
																																																																																		
																																																																																		
																																																																																	}
																																																																																	else
																																																																																		if((defaultToken.equals("uL"))&&(preferredToken.equals("aL")))
																																																																																		{
																																																																																			if(c=='*')
																																																																																				convertAmount=convertAmount*1E12;
																																																																																			else
																																																																																				convertAmount=convertAmount*1E-12;
																																																																																			
																																																																																			
																																																																																		}																																																										
																			
																																																																																		else
																																																																																			if((defaultToken.equals("nL"))&&(preferredToken.equals("kL")))
																																																																																			{
																																																																																				if(c=='*')
																																																																																					convertAmount=convertAmount*1E-12;
																																																																																				else
																																																																																					convertAmount=convertAmount*1E12;
																																																																																			}
																																																																																			else
																																																																																				if((defaultToken.equals("nL"))&& (preferredToken.equals("dkL")))
																																																																																				{
																																																																																					if(c=='*')
																																																																																						convertAmount=convertAmount*1E-10;
																																																																																					else
																																																																																						convertAmount=convertAmount*1E10;
																																																																																					
																																																																																				}
																																																																																				else
																																																																																					if((defaultToken.equals("nL"))&&(preferredToken.equals("L")))
																																																																																					{
																																																																																						if(c=='*')
																																																																																							convertAmount=convertAmount*1E-9;
																																																																																						else
																																																																																							convertAmount=convertAmount*1E9;
																																																																																						
																																																																																						
																																																																																					}
																																																																																					else
																																																																																						if((defaultToken.equals("nL"))&&(preferredToken.equals("dL")))
																																																																																						{
																																																																																							if(c=='*')
																																																																																								convertAmount=convertAmount*1E-8;
																																																																																							else
																																																																																								convertAmount=convertAmount*1E8;
																																																																																							
																																																																																							
																																																																																						}
																																																																																						else
																																																																																							if((defaultToken.equals("nL"))&&(preferredToken.equals("cL")))
																																																																																							{
																																																																																								if(c=='*')
																																																																																									convertAmount=convertAmount*1E-7;
																																																																																								else
																																																																																									convertAmount=convertAmount*1E7;
																																																																																								
																																																																																								
																																																																																							}
																																																																																		
																																																																																							else
																																																																																								if((defaultToken.equals("nL"))&&(preferredToken.equals("mL")))
																																																																																								{
																																																																																									if(c=='*')
																																																																																										convertAmount=convertAmount*1E-6;
																																																																																									else
																																																																																										convertAmount=convertAmount*1E6;
																																																																																									
																																																																																									
																																																																																								}
																																																																																		
																																																																																								else
																																																																																									if((defaultToken.equals("nL"))&&(preferredToken.equals("uL")))
																																																																																									{
																																																																																										if(c=='*')
																																																																																											convertAmount=convertAmount*1E-3;
																																																																																										else
																																																																																											convertAmount=convertAmount*1E3;
																																																																																										
																																																																																										
																																																																																									}
																																																																																		
																																																																																									else
																																																																																										if((defaultToken.equals("nL"))&&(preferredToken.equals("pL")))
																																																																																										{
																																																																																											if(c=='*')
																																																																																												convertAmount=convertAmount*1E3;
																																																																																											else
																																																																																												convertAmount=convertAmount*1E-3;
																																																																																											
																																																																																											
																																																																																										}
																																																																																										else
																																																																																											if((defaultToken.equals("nL"))&&(preferredToken.equals("fL")))
																																																																																											{
																																																																																												if(c=='*')
																																																																																													convertAmount=convertAmount*1E6;
																																																																																												else
																																																																																													convertAmount=convertAmount*1E-6;
																																																																																												
																																																																																												
																																																																																											}
																																																																																											else
																																																																																												if((defaultToken.equals("nL"))&&(preferredToken.equals("aL")))
																																																																																												{
																																																																																													if(c=='*')
																																																																																														convertAmount=convertAmount*1E9;
																																																																																													else
																																																																																														convertAmount=convertAmount*1E-9;
																																																																																													
																																																																																													
																																																																																												}																																																										
																																																																																												else
																																																																																													if((defaultToken.equals("pL"))&&(preferredToken.equals("kL")))
																																																																																													{
																																																																																														if(c=='*')
																																																																																															convertAmount=convertAmount*1E-15;
																																																																																														else
																																																																																															convertAmount=convertAmount*1E15;
																																																																																													}
																																																																																													else
																																																																																														if((defaultToken.equals("pL"))&& (preferredToken.equals("dkL")))
																																																																																														{
																																																																																															if(c=='*')
																																																																																																convertAmount=convertAmount*1E-13;
																																																																																															else
																																																																																																convertAmount=convertAmount*1E13;
																																																																																															
																																																																																														}
																																																																																														else
																																																																																															if((defaultToken.equals("pL"))&&(preferredToken.equals("L")))
																																																																																															{
																																																																																																if(c=='*')
																																																																																																	convertAmount=convertAmount*1E-12;
																																																																																																else
																																																																																																	convertAmount=convertAmount*1E12;
																																																																																																
																																																																																																
																																																																																															}
																																																																																															else
																																																																																																if((defaultToken.equals("pL"))&&(preferredToken.equals("dL")))
																																																																																																{
																																																																																																	if(c=='*')
																																																																																																		convertAmount=convertAmount*1E-11;
																																																																																																	else
																																																																																																		convertAmount=convertAmount*11;
																																																																																																	
																																																																																																	
																																																																																																}
																																																																																																else
																																																																																																	if((defaultToken.equals("pL"))&&(preferredToken.equals("cL")))
																																																																																																	{
																																																																																																		if(c=='*')
																																																																																																			convertAmount=convertAmount*1E-10;
																																																																																																		else
																																																																																																			convertAmount=convertAmount*1E10;
																																																																																																		
																																																																																																		
																																																																																																	}
																																																																																												
																																																																																																	else
																																																																																																		if((defaultToken.equals("pL"))&&(preferredToken.equals("mL")))
																																																																																																		{
																																																																																																			if(c=='*')
																																																																																																				convertAmount=convertAmount*1E-9;
																																																																																																			else
																																																																																																				convertAmount=convertAmount*1E9;
																																																																																																			
																																																																																																			
																																																																																																		}
																																																																																												
																																																																																																		else
																																																																																																			if((defaultToken.equals("pL"))&&(preferredToken.equals("uL")))
																																																																																																			{
																																																																																																				if(c=='*')
																																																																																																					convertAmount=convertAmount*1E-6;
																																																																																																				else
																																																																																																					convertAmount=convertAmount*1E6;
																																																																																																				
																																																																																																				
																																																																																																			}
																																																																																												
																																																																																																			else
																																																																																																				if((defaultToken.equals("pL"))&&(preferredToken.equals("nL")))
																																																																																																				{
																																																																																																					if(c=='*')
																																																																																																						convertAmount=convertAmount*1E-3;
																																																																																																					else
																																																																																																						convertAmount=convertAmount*1E3;
																																																																																																					
																																																																																																					
																																																																																																				}
																																																																																																				else
																																																																																																					if((defaultToken.equals("pL"))&&(preferredToken.equals("fL")))
																																																																																																					{
																																																																																																						if(c=='*')
																																																																																																							convertAmount=convertAmount*1E3;
																																																																																																						else
																																																																																																							convertAmount=convertAmount*1E-3;
																																																																																																						
																																																																																																						
																																																																																																					}
																																																																																																					else
																																																																																																						if((defaultToken.equals("pL"))&&(preferredToken.equals("aL")))
																																																																																																						{
																																																																																																							if(c=='*')
																																																																																																								convertAmount=convertAmount*1E6;
																																																																																																							else
																																																																																																								convertAmount=convertAmount*1E-6;
																																																																																																							
																																																																																																							
																																																																																																						}																																																										
																																																																																																						else
																																																																																																							if((defaultToken.equals("fL"))&&(preferredToken.equals("kL")))
																																																																																																							{
																																																																																																								if(c=='*')
																																																																																																									convertAmount=convertAmount*1E-18;
																																																																																																								else
																																																																																																									convertAmount=convertAmount*1E18;
																																																																																																							}
																																																																																																							else
																																																																																																								if((defaultToken.equals("fL"))&& (preferredToken.equals("dkL")))
																																																																																																								{
																																																																																																									if(c=='*')
																																																																																																										convertAmount=convertAmount*1E-16;
																																																																																																									else
																																																																																																										convertAmount=convertAmount*1E16;
																																																																																																									
																																																																																																								}
																																																																																																								else
																																																																																																									if((defaultToken.equals("fL"))&&(preferredToken.equals("L")))
																																																																																																									{
																																																																																																										if(c=='*')
																																																																																																											convertAmount=convertAmount*1E-15;
																																																																																																										else
																																																																																																											convertAmount=convertAmount*1E15;
																																																																																																										
																																																																																																										
																																																																																																									}
																																																																																																									else
																																																																																																										if((defaultToken.equals("fL"))&&(preferredToken.equals("dL")))
																																																																																																										{
																																																																																																											if(c=='*')
																																																																																																												convertAmount=convertAmount*1E-14;
																																																																																																											else
																																																																																																												convertAmount=convertAmount*14;
																																																																																																											
																																																																																																											
																																																																																																										}
																																																																																																										else
																																																																																																											if((defaultToken.equals("fL"))&&(preferredToken.equals("cL")))
																																																																																																											{
																																																																																																												if(c=='*')
																																																																																																													convertAmount=convertAmount*1E-13;
																																																																																																												else
																																																																																																													convertAmount=convertAmount*1E13;
																																																																																																												
																																																																																																												
																																																																																																											}
																																																																																																						
																																																																																																											else
																																																																																																												if((defaultToken.equals("fL"))&&(preferredToken.equals("mL")))
																																																																																																												{
																																																																																																													if(c=='*')
																																																																																																														convertAmount=convertAmount*1E-12;
																																																																																																													else
																																																																																																														convertAmount=convertAmount*1E12;
																																																																																																													
																																																																																																													
																																																																																																												}
																																																																																																						
																																																																																																												else
																																																																																																													if((defaultToken.equals("fL"))&&(preferredToken.equals("uL")))
																																																																																																													{
																																																																																																														if(c=='*')
																																																																																																															convertAmount=convertAmount*1E-9;
																																																																																																														else
																																																																																																															convertAmount=convertAmount*1E9;
																																																																																																														
																																																																																																														
																																																																																																													}
																																																																																																						
																																																																																																													else
																																																																																																														if((defaultToken.equals("fL"))&&(preferredToken.equals("nL")))
																																																																																																														{
																																																																																																															if(c=='*')
																																																																																																																convertAmount=convertAmount*1E-6;
																																																																																																															else
																																																																																																																convertAmount=convertAmount*1E6;
																																																																																																															
																																																																																																															
																																																																																																														}
																																																																																																														else
																																																																																																															if((defaultToken.equals("fL"))&&(preferredToken.equals("pL")))
																																																																																																															{
																																																																																																																if(c=='*')
																																																																																																																	convertAmount=convertAmount*1E-3;
																																																																																																																else
																																																																																																																	convertAmount=convertAmount*1E3;
																																																																																																																
																																																																																																																
																																																																																																															}
																																																																																																															else
																																																																																																																if((defaultToken.equals("fL"))&&(preferredToken.equals("aL")))
																																																																																																																{
																																																																																																																	if(c=='*')
																																																																																																																		convertAmount=convertAmount*1E3;
																																																																																																																	else
																																																																																																																		convertAmount=convertAmount*1E-3;
																																																																																																																	
																																																																																																																	
																																																																																																																}																																																										
																																																																																																																else
																																																																																																																	if((defaultToken.equals("aL"))&&(preferredToken.equals("kL")))
																																																																																																																	{
																																																																																																																		if(c=='*')
																																																																																																																			convertAmount=convertAmount*1E-21;
																																																																																																																		else
																																																																																																																			convertAmount=convertAmount*1E21;
																																																																																																																	}
																																																																																																																	else
																																																																																																																		if((defaultToken.equals("aL"))&& (preferredToken.equals("dkL")))
																																																																																																																		{
																																																																																																																			if(c=='*')
																																																																																																																				convertAmount=convertAmount*1E-19;
																																																																																																																			else
																																																																																																																				convertAmount=convertAmount*1E19;
																																																																																																																			
																																																																																																																		}
																																																																																																																		else
																																																																																																																			if((defaultToken.equals("aL"))&&(preferredToken.equals("L")))
																																																																																																																			{
																																																																																																																				if(c=='*')
																																																																																																																					convertAmount=convertAmount*1E-18;
																																																																																																																				else
																																																																																																																					convertAmount=convertAmount*1E18;
																																																																																																																				
																																																																																																																				
																																																																																																																			}
																																																																																																																			else
																																																																																																																				if((defaultToken.equals("aL"))&&(preferredToken.equals("dL")))
																																																																																																																				{
																																																																																																																					if(c=='*')
																																																																																																																						convertAmount=convertAmount*1E-17;
																																																																																																																					else
																																																																																																																						convertAmount=convertAmount*17;
																																																																																																																					
																																																																																																																					
																																																																																																																				}
																																																																																																																				else
																																																																																																																					if((defaultToken.equals("aL"))&&(preferredToken.equals("cL")))
																																																																																																																					{
																																																																																																																						if(c=='*')
																																																																																																																							convertAmount=convertAmount*1E-16;
																																																																																																																						else
																																																																																																																							convertAmount=convertAmount*1E16;
																																																																																																																						
																																																																																																																						
																																																																																																																					}
																																																																																																																
																																																																																																																					else
																																																																																																																						if((defaultToken.equals("aL"))&&(preferredToken.equals("mL")))
																																																																																																																						{
																																																																																																																							if(c=='*')
																																																																																																																								convertAmount=convertAmount*1E-15;
																																																																																																																							else
																																																																																																																								convertAmount=convertAmount*1E15;
																																																																																																																							
																																																																																																																							
																																																																																																																						}
																																																																																																																
																																																																																																																						else
																																																																																																																							if((defaultToken.equals("aL"))&&(preferredToken.equals("uL")))
																																																																																																																							{
																																																																																																																								if(c=='*')
																																																																																																																									convertAmount=convertAmount*1E-12;
																																																																																																																								else
																																																																																																																									convertAmount=convertAmount*1E12;
																																																																																																																								
																																																																																																																								
																																																																																																																							}
																																																																																																																
																																																																																																																							else
																																																																																																																								if((defaultToken.equals("aL"))&&(preferredToken.equals("nL")))
																																																																																																																								{
																																																																																																																									if(c=='*')
																																																																																																																										convertAmount=convertAmount*1E-9;
																																																																																																																									else
																																																																																																																										convertAmount=convertAmount*1E9;
																																																																																																																									
																																																																																																																									
																																																																																																																								}
																																																																																																																								else
																																																																																																																									if((defaultToken.equals("aL"))&&(preferredToken.equals("pL")))
																																																																																																																									{
																																																																																																																										if(c=='*')
																																																																																																																											convertAmount=convertAmount*1E-6;
																																																																																																																										else
																																																																																																																											convertAmount=convertAmount*1E6;
																																																																																																																										
																																																																																																																										
																																																																																																																									}
																																																																																																																									else
																																																																																																																										if((defaultToken.equals("aL"))&&(preferredToken.equals("fL")))
																																																																																																																										{
																																																																																																																											if(c=='*')
																																																																																																																												convertAmount=convertAmount*1E-3;
																																																																																																																											else
																																																																																																																												convertAmount=convertAmount*1E3;
																																																																																																																											
																																																																																																																											
																																																																																																																										}																																																										
																													
			
			length1=length1+defaultToken.length();
			if(length1<defaultUnitLength)
			c=defaultUnitCharArray[length1];
			System.out.println("convert amount="+convertAmount);
		}
		
		return convertAmount;
	}
}

/*
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
*/
