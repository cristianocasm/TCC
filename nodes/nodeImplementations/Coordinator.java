package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;
import java.util.Random;

import projects.tcc.nodes.messages.NetworkMessage;
import projects.tcc.nodes.timers.NetworkMessageTimer;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

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
		while (inbox.hasNext()) {
			Message message = inbox.next();			
			if (message instanceof NetworkMessage) {
				Node sender = inbox.getSender();
				
				switch(((NetworkMessage) message).typeMsg){
					case 0: // GET_TEMPERATURE
						break;
					case 1: // SET_TEMPERATURE
						break;
					case 2: // GET_LIGHTNESS
						break;
					case 3: // SET_LIGHTNESS
						break;
					case 4: // AIR_CONDITIONER_ON
						break;
					case 5: // AIR_CONDITIONER_OFF
						break;
					case 6: // TAKE_TEMPERATURE
						break;
				}
			}
		}
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
