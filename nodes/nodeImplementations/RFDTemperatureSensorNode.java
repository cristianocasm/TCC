package projects.tcc.nodes.nodeImplementations;

public class RFDTemperatureSensorNode extends TemperatureSensorNode {
	
	public RFDTemperatureSensorNode(){
		super();
		TemperatureSensorNode.addTemperatureSensor(this);
	}
}
