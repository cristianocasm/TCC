package projects.tcc.nodes.nodeImplementations;

import projects.tcc.nodes.messages.NetworkMessage;
import sinalgo.nodes.Node;
import sinalgo.nodes.edges.Edge;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.Tools;

public class FFDTemperatureSensorNode extends TemperatureSensorNode {
	
	private Double auxTemperature = 0.0;
	
	public FFDTemperatureSensorNode(){
		super();
		this.setDefaultDrawingSizeInPixels(this.defaultDrawingSizeInPixels*2);
		// TemperatureSensorNode.temperatureSensors.add(this);
	}
	
	private void getTemperatureFromRFDs(){
		for(Edge ed : this.outgoingConnections){
			if(ed.endNode instanceof RFDTemperatureSensorNode){
				this.send(new NetworkMessage(NetworkMessage.GET_TEMPERATURE, null), ed.endNode);
				Tools.appendToOutput("GET: FFD" + " ~> " + ed.endNode.toString() + "\n");
			}
		}
	}
	
	private void sendMsgToAirConditioner(Integer msg, Double value){
		for(Edge ed : this.outgoingConnections){
			if(ed.endNode instanceof AirConditioner){
				this.send(new NetworkMessage(msg, value), ed.endNode);
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
						this.sendMsgToAirConditioner(NetworkMessage.SET_TEMPERATURE, ((NetworkMessage) message).value);
						Tools.appendToOutput("SET: FFD" + " ~> Air Conditioner (" + ((NetworkMessage) message).value + ")\n");
						break;
//					case 2: // GET_LIGHTNESS
//						this.getLightnessFromRFDs();
//						break;
//					case 3: // SET_LIGHTNESS
//						Light.setLumVal(((NetworkMessage) message).value);
//						break;
					case 4: // AIR_CONDITIONER_ON
						this.sendMsgToAirConditioner(NetworkMessage.AIR_CONDITIONER_ON, null);
						Tools.appendToOutput("TURN_ON: FFD ~> Air Conditioner\n");
						break;
					case 5: // AIR_CONDITIONER_OFF
						this.sendMsgToAirConditioner(NetworkMessage.AIR_CONDITIONER_OFF, null);
						Tools.appendToOutput("TURN_OFF: FFD ~> Air Conditioner\n");
						break;
					case 6: // TAKE_TEMPERATURE
						this.auxTemperature += ((NetworkMessage) message).value;
						break;
				}
			}
		}
	}
	
	@Override
	public void postStep() {
		if(this.auxTemperature != 0.0){
			for(Edge ed : this.outgoingConnections){
				if(ed.endNode instanceof Coordinator){
					this.send(new NetworkMessage(NetworkMessage.TAKE_TEMPERATURE, this.auxTemperature/3), ed.endNode);
					Tools.appendToOutput("TAKE: FFD" + " ~> " + ed.endNode.toString() + " (" + String.format("%.2f", this.auxTemperature/3) + ")" + "\n");
					this.auxTemperature = 0.0;
				}
			}
		}
	}
	
}
