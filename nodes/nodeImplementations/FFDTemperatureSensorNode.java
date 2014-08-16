package projects.tcc.nodes.nodeImplementations;

public class FFDTemperatureSensorNode extends TemperatureSensorNode {
	
	public FFDTemperatureSensorNode(){
		super();
		this.setDefaultDrawingSizeInPixels(this.defaultDrawingSizeInPixels*2);
		TemperatureSensorNode.addTemperatureSensor(this);
	}
	
	private void changeEnvTemperatureVal(double temperature){
		AirConditioner.setTemperature(temperature);
	}
	
}
