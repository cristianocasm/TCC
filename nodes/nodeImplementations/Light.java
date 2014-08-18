package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;

import projects.tcc.nodes.messages.NetworkMessage;
import sinalgo.nodes.edges.Edge;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class Light extends Device {
	
	private static double lumVal = 1600;
	
	public Light(){
		this.setColor(Color.GREEN);
	}
	
	public void setLumVal(double lum){
		for(Edge ed : this.outgoingConnections){
			((RFDLightnessSensorNode) ed.endNode).lumVal = (((RFDLightnessSensorNode) ed.endNode).getLumVal() / Light.lumVal)*lum;
		}
	}
	
	public static double getLum(){
		return Light.lumVal;
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message message = inbox.next();			
			if (message instanceof NetworkMessage) {				
				switch(((NetworkMessage) message).typeMsg){
//					case 0: // GET_TEMPERATURE
//						break;
//					case 1: // SET_TEMPERATURE
//						break;
//					case 2: // GET_LIGHTNESS
//						this.getLightnessFromRFDs();
//						break;
					case 3: // SET_LIGHTNESS
						this.setLumVal(((NetworkMessage) message).value);
						break;
//					case 4: // AIR_CONDITIONER_ON
//						break;
//					case 5: // AIR_CONDITIONER_OFF
//						break;
//					case 6: // TAKE_TEMPERATURE
//						break;
				}
			}
		}
	}
}
