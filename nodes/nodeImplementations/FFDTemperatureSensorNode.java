package projects.tcc.nodes.nodeImplementations;

import projects.leader.nodes.messages.NetworkMessage;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class FFDTemperatureSensorNode extends TemperatureSensorNode {
	
	public FFDTemperatureSensorNode(){
		super();
		this.setDefaultDrawingSizeInPixels(this.defaultDrawingSizeInPixels*2);
		TemperatureSensorNode.addTemperatureSensor(this);
	}
	
	private void changeEnvTemperatureVal(double temperature){
		AirConditioner.setTemperature(temperature);
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message message = inbox.next();			
			if (message instanceof NetworkMessage) {
				Node sender = inbox.getSender();
				
				switch(((NetworkMessage) message).tipoMsg){
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
				}
			}
		}
	}
	
}
