package org.usfirst.frc.team20.robot;

public class Parse {
	public static double[] parseString(String str){
		//*008*FirstTurnAngle*13.00*FirstDistance*10.00*SecondTurnAngle*10.00*SecondDistance*2.00*
		String[] Data = str.split("\\*");
		double angle1 = Double.parseDouble(Data[3]);
		double distance1 = Double.parseDouble(Data[5]);
		double angle2 = Double.parseDouble(Data[7]);
		double distance2 = Double.parseDouble(Data[9]);
		return new double[] {angle1, distance1, angle2, distance2};
	}
	
}