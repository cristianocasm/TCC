package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;

public class LightnessSensorNode extends Node {
	
	// public static ArrayList<LightnessSensorNode> lumSensors = new ArrayList<LightnessSensorNode>();
	protected double lumVal;

	public LightnessSensorNode(){
		this.setColor(Color.CYAN);
		this.lumVal = Light.getLum() * (new Random().nextDouble()/2.8 + 0.625); // [1000, 1600[
	}
	
	public double getLumVal(){
		return this.lumVal;
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
