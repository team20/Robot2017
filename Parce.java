
public class Parce {
	
	static String Angle;
	static String Distance;
	static double Angle1;
	static double Distance1;
public static void main(String[] args){
	String str = "*008*Angle*68.79*Distance*4.89*";
	//int starct = str.length() - str.replace("*","").length();
	//String[] Data = new String [starct];
	String[] Data = str.split("\\*");
	Angle = Data[3];
	Distance = Data[5];
	double Angle1 = Double.parseDouble(Angle);
	double Distance1 = Double.parseDouble(Distance);
	System.out.println( "Angle "+ Angle1 +" Distance " + Distance1 +" ft");
}
	public String getAngle(){
		return Angle;
	}
	public String getDistance(){
		return Distance;		
	}
}