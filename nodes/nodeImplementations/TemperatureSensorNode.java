package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;

public class TemperatureSensorNode extends Node {
	
	private static ArrayList<TemperatureSensorNode> temperatureSensors = new ArrayList<TemperatureSensorNode>();
	private double temperatureVal;
	
	public TemperatureSensorNode(){
		this.setColor(Color.RED);
		this.temperatureVal = AirConditioner.getTemperature() * (new Random().nextDouble()*2 + 0.90); // [0.9, 1.1[
	}
	
	public double temperatureVal(){
		return this.temperatureVal;
	}
	
	public static void addTemperatureSensor(TemperatureSensorNode temperatureSensor){
		TemperatureSensorNode.temperatureSensors.add(temperatureSensor);
	}

	@Override
	public void handleMessages(Inbox inbox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void neighborhoodChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		// TODO Auto-generated method stub

	}

}
