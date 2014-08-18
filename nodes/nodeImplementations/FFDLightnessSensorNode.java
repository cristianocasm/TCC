package projects.tcc.nodes.nodeImplementations;

import projects.tcc.nodes.messages.NetworkMessage;
import sinalgo.nodes.edges.Edge;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.Tools;

public class FFDLightnessSensorNode extends LightnessSensorNode {
	
	private double auxLightness = 0.0;
	
	public FFDLightnessSensorNode(){
		super();
		this.setDefaultDrawingSizeInPixels(this.defaultDrawingSizeInPixels*2);
	}
	
	private void getLightnessFromRFDs(){
		for(Edge ed : this.outgoingConnections){
			if(ed.endNode instanceof RFDLightnessSensorNode){
				this.send(new NetworkMessage(NetworkMessage.GET_LIGHTNESS, null), ed.endNode);
				Tools.appendToOutput("GET: FFD" + " ~> " + ed.endNode.toString() + "\n");
			}
		}
	}
	
	private void sendSetLightness(double value){
		for(Edge ed : this.outgoingConnections){
			if(ed.endNode instanceof Light){
				this.send(new NetworkMessage(NetworkMessage.SET_LIGHTNESS, value), ed.endNode);
			}
		}
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message message = inbox.next();			
			if (message instanceof NetworkMessage) {
				
				switch(((NetworkMessage) message).typeMsg){
//					case 0: // GET_TEMPERATURE
//						this.getTemperatureFromRFDs();
//						break;
//					case 1: // SET_TEMPERATURE
//						AirConditioner.setTemperature(((NetworkMessage) message).value);
//						break;
					case 2: // GET_LIGHTNESS
						this.getLightnessFromRFDs();
						break;
					case 3: // SET_LIGHTNESS
						this.sendSetLightness(((NetworkMessage) message).value);
						Tools.appendToOutput("SET: FFD" + " ~> Light (" + ((NetworkMessage) message).value + ")\n");
						break;
//					case 4: // AIR_CONDITIONER_ON
//						AirConditioner.turnOn();
//						break;
//					case 5: // AIR_CONDITIONER_OFF
//						AirConditioner.turnOff();
//						break;
					case 6: // TAKE_TEMPERATURE
						this.auxLightness += ((NetworkMessage) message).value;
						break;
				}
			}
		}
	}
	
	@Override
	public void postStep() {
		if(this.auxLightness != 0.0){
			for(Edge ed : this.outgoingConnections){
				if(ed.endNode instanceof Coordinator){
					this.send(new NetworkMessage(NetworkMessage.TAKE_TEMPERATURE, this.auxLightness/3), ed.endNode);
					Tools.appendToOutput("TAKE: FFD" + " ~> " + ed.endNode.toString() + " (" + String.format("%.2f", this.auxLightness/3) + ")" + "\n");
					this.auxLightness = 0.0;
				}
			}
		}
	}

}
