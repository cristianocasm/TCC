package projects.tcc.nodes.nodeImplementations;

import projects.tcc.nodes.messages.NetworkMessage;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.Tools;

public class RFDTemperatureSensorNode extends TemperatureSensorNode {
	
	public RFDTemperatureSensorNode(){
		super();
		// TemperatureSensorNode.temperatureSensors.add(this);
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message message = inbox.next();			
			if (message instanceof NetworkMessage) {
				Node sender = inbox.getSender();
				
				switch(((NetworkMessage) message).typeMsg){
					case 0: // GET_TEMPERATURE
						this.send(new NetworkMessage(NetworkMessage.TAKE_TEMPERATURE, this.temperatureVal), sender);
						Tools.appendToOutput("TAKE: " + this.toString() + " ~> FFD " + " (" + String.format("%.2f", this.temperatureVal) + ")" + "\n");
						break;
//					case 1: // SET_TEMPERATURE
//						break;
//					case 2: // GET_LIGHTNESS
//						break;
//					case 3: // SET_LIGHTNESS
//						break;
//					case 4: // AIR_CONDITIONER_ON
//						break;
//					case 5: // AIR_CONDITIONER_OFF
//						break;
				}
			}
		}
	}
}
