package projects.tcc.nodes.nodeImplementations;

public class FFDTemperatureSensorNode extends TemperatureSensorNode {
	
	public FFDTemperatureSensorNode(){
		super();
		this.setDefaultDrawingSizeInPixels(this.defaultDrawingSizeInPixels*2);
	}
	
}
