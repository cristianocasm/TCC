package projects.tcc.nodes.nodeImplementations;

import projects.tcc.nodes.messages.NetworkMessage;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class FFDTemperatureSensorNode extends TemperatureSensorNode {
	
	public FFDTemperatureSensorNode(){
		super();
		this.setDefaultDrawingSizeInPixels(this.defaultDrawingSizeInPixels*2);
		TemperatureSensorNode.temperatureSensors.add(this);
	}
	
	private void getTemperatureFromRFDs(){
		for(TemperatureSensorNode sensor : TemperatureSensorNode.temperatureSensors){
			if(sensor instanceof RFDTemperatureSensorNode){
				this.send(new NetworkMessage(NetworkMessage.GET_TEMPERATURE, null), sensor);
			}
		}
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message message = inbox.next();			
			if (message instanceof NetworkMessage) {
				Node sender = inbox.getSender();
				
				switch(((NetworkMessage) message).typeMsg){
					case 0: // GET_TEMPERATURE
						this.getTemperatureFromRFDs();
						break;
					case 1: // SET_TEMPERATURE
						AirConditioner.setTemperature(((NetworkMessage) message).value);
						break;
//					case 2: // GET_LIGHTNESS
//						this.getLightnessFromRFDs();
//						break;
//					case 3: // SET_LIGHTNESS
//						Light.setLumVal(((NetworkMessage) message).value);
//						break;
					case 4: // AIR_CONDITIONER_ON
						AirConditioner.turnOn();
						break;
					case 5: // AIR_CONDITIONER_OFF
						AirConditioner.turnOff();
						break;
					case 6:
						
						break;
				}
			}
		}
	}
	
}
