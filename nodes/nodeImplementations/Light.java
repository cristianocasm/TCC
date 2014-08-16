package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;

public class Light extends Device {
	
	private double lumVal;
	
	public Light(){
		this.setColor(Color.GREEN);
		this.lumVal = 80.0;
	}
	
	public double getLumVal(){
		return this.lumVal;
	}
	
	public void setLumVal(double lum){
		this.lumVal = lum;
		// TODO alterar o valor de lum nos sensores
	}
}
