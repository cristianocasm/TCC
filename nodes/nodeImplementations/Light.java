package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;

public class Light extends Device {
	
	private static double lumVal;
	
	public Light(){
		this.setColor(Color.GREEN);
		this.lumVal = 80.0;
	}
	
	public static void setLumVal(double lum){
		Light.lumVal = lum;
		// TODO alterar o valor de lum nos sensores
	}
}
