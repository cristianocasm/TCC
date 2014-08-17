package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;

public class AirConditioner extends Device {
	
	private static double actualTemperatureVal = 25.0;
	private static double oldTemperatureVal = AirConditioner.actualTemperatureVal;
	private static boolean turnedOn = false;
	
	public AirConditioner(){
		this.setColor(Color.orange);
	}
	
	public static double getTemperature(){
		return AirConditioner.actualTemperatureVal;
	}
	
	public static void setTemperature(double newTemperature){
		AirConditioner.oldTemperatureVal = AirConditioner.actualTemperatureVal;
		AirConditioner.actualTemperatureVal = newTemperature;
	}
	
	private void changeSensorsTemperature(){
		if(AirConditioner.actualTemperatureVal != AirConditioner.oldTemperatureVal){
			// TODO
		}
	}
	
	public static void turnOn(){
		AirConditioner.turnedOn = true;
	}
	
	public static void turnOff(){
		AirConditioner.turnedOn = false;
	}

}
