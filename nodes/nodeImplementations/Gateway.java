package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;

import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Node;
import sinalgo.nodes.Node.NodePopupMethod;
import sinalgo.nodes.messages.Inbox;

public class Gateway extends Node {
	
	public Gateway(){
		this.setColor(Color.BLUE);
	}
	
	@NodePopupMethod(menuText = "Get Temperature")
	public void getTemperature() {
		// TODO
	}
	
	@NodePopupMethod(menuText = "Set Temperature = 20")
	public void setTemperature() {
		// TODO
	}
	
	@NodePopupMethod(menuText = "Get Lightness")
	public void getLightness() {
		// TODO
	}

	@NodePopupMethod(menuText = "Set Lightness = 100")
	public void setLightness() {
		// TODO
	}
	
	@NodePopupMethod(menuText = "Turn Air Conditioner ON")
	public void airConditionerON() {
		// TODO
	}
	
	@NodePopupMethod(menuText = "Turn Air Conditioner OFF")
	public void airConditionerOFF() {
		// TODO
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
