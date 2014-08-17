package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;
import java.util.Random;

import projects.tcc.nodes.messages.NetworkMessage;
import projects.tcc.nodes.timers.NetworkMessageTimer;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;

public class Coordinator extends Node {
	
	public Coordinator(){
		this.setColor(Color.BLUE);
	}
	
	private void scheduleMessageSending(Integer typeMsg, Double value){
		NetworkMessageTimer timer = new NetworkMessageTimer(new NetworkMessage(typeMsg, value));
		timer.startRelative(1, this);
	}
	
	private int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	@NodePopupMethod(menuText = "Get Temperature")
	public void getTemperature() {
		this.scheduleMessageSending(NetworkMessage.GET_TEMPERATURE, null);
	}
	
	@NodePopupMethod(menuText = "Change Temperature")
	public void setTemperature() {
		this.scheduleMessageSending(NetworkMessage.SET_TEMPERATURE, new Double(randInt(15, 25)));
	}
	
	@NodePopupMethod(menuText = "Get Lightness")
	public void getLightness() {
		this.scheduleMessageSending(NetworkMessage.GET_LIGHTNESS, null);
	}

	@NodePopupMethod(menuText = "Change Lightness")
	public void setLightness() {
		this.scheduleMessageSending(NetworkMessage.SET_LIGHTNESS, new Double(randInt(0,100)));
	}
	
	@NodePopupMethod(menuText = "Turn Air Conditioner ON")
	public void airConditionerON() {
		this.scheduleMessageSending(NetworkMessage.AIR_CONDITIONER_ON, null);
	}
	
	@NodePopupMethod(menuText = "Turn Air Conditioner OFF")
	public void airConditionerOFF() {
		this.scheduleMessageSending(NetworkMessage.AIR_CONDITIONER_OFF, null);
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