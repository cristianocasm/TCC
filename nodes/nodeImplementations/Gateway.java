package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;

import projects.leader.nodes.messages.NetworkMessage;
import projects.leader.nodes.timers.NetworkMessageTimer;

import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Node;
import sinalgo.nodes.Node.NodePopupMethod;
import sinalgo.nodes.messages.Inbox;
import sinalgo.runtime.Global;
import sinalgo.tools.Tools;

public class Gateway extends Node {
	
	private final int GET_TEMPERATURE = 0;
	private final int SET_TEMPERATURE = 1;
	private final int GET_LIGHTNESS = 2;
	private final int SET_LIGHTNESS = 3;
	private final int AIR_CONDITIONER_ON = 4;
	private final int AIR_CONDITIONER_OFF = 5;
	
	public Gateway(){
		this.setColor(Color.BLUE);
	}
	
	private void scheduleMessageSending(int typeMsg){
		NetworkMessageTimer timer = new NetworkMessageTimer(new NetworkMessage(typeMsg));
		timer.startRelative(1, this);
	}
	
	@NodePopupMethod(menuText = "Get Temperature")
	public void getTemperature() {
		this.scheduleMessageSending(GET_TEMPERATURE);
	}
	
	@NodePopupMethod(menuText = "Set Temperature = 20")
	public void setTemperature() {
		this.scheduleMessageSending(SET_TEMPERATURE);
	}
	
	@NodePopupMethod(menuText = "Get Lightness")
	public void getLightness() {
		this.scheduleMessageSending(GET_LIGHTNESS);
	}

	@NodePopupMethod(menuText = "Set Lightness = 100")
	public void setLightness() {
		this.scheduleMessageSending(SET_LIGHTNESS);
	}
	
	@NodePopupMethod(menuText = "Turn Air Conditioner ON")
	public void airConditionerON() {
		this.scheduleMessageSending(AIR_CONDITIONER_ON);
	}
	
	@NodePopupMethod(menuText = "Turn Air Conditioner OFF")
	public void airConditionerOFF() {
		this.scheduleMessageSending(AIR_CONDITIONER_OFF);
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
