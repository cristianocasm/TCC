package projects.tcc.nodes.timers;

import projects.tcc.nodes.messages.NetworkMessage;
import projects.tcc.nodes.nodeImplementations.Coordinator;
import projects.tcc.nodes.nodeImplementations.FFDLightnessSensorNode;
import projects.tcc.nodes.nodeImplementations.FFDTemperatureSensorNode;
import sinalgo.nodes.edges.Edge;
import sinalgo.nodes.timers.Timer;
import sinalgo.tools.Tools;

public class NetworkMessageTimer extends Timer {
	private NetworkMessage message = null;

	public NetworkMessageTimer(NetworkMessage message) {
		this.message = message;
	}

	private void sendGetMsg(Class<?> klass){
		if(this.node instanceof Coordinator){
			for(Edge ed : this.node.outgoingConnections){
				if(ed.endNode.getClass().equals(klass)){ // Temperature or Lightness FFD
					this.node.send(this.message, ed.endNode);
					Tools.appendToOutput("GET: Coordinator ~> FFD\n");
				}
			}
		}else if(this.node.getClass().equals(klass)){
			this.node.broadcast(this.message);
			Tools.appendToOutput("GET: " + this.node.toString() + " ~> broadcast\n");
		}
	}
	
	/**
	 * Sends a message from Coordinator to a FFD
	 * @param klass
	 */
	private void sendSetMsg(Class<?> klass){
		for(Edge ed : this.node.outgoingConnections){
			if(ed.endNode.getClass().equals(klass)){
				this.node.send(this.message, ed.endNode);
				Tools.appendToOutput("SET: " + this.node.toString() + " ~> FFD (" + this.message.value + ")\n");
			}
		}
	}
	
	private void sendTurnOnAirConditioner(){
		for(Edge ed : this.node.outgoingConnections){
			if(ed.endNode instanceof FFDTemperatureSensorNode){
				this.node.send(this.message, ed.endNode);
				Tools.appendToOutput("TURN_ON: " + this.node.toString() + " ~> FFD\n");
			}
		}
	}
	
	private void sendTurnOffAirConditioner(){
		for(Edge ed : this.node.outgoingConnections){
			if(ed.endNode instanceof FFDTemperatureSensorNode){
				this.node.send(this.message, ed.endNode);
				Tools.appendToOutput("TURN_OFF: " + this.node.toString() + " ~> FFD\n");
			}
		}
	}
	
	@Override
	public void fire() {
		switch(this.message.typeMsg){
			case 0: // GET_TEMPERATURE
				this.sendGetMsg(FFDTemperatureSensorNode.class);
				break;
			case 1: // SET_TEMPERATURE
				this.sendSetMsg(FFDTemperatureSensorNode.class);
				break;
			case 2: // GET_LIGHTNESS
				this.sendGetMsg(FFDLightnessSensorNode.class);
				break;
			case 3: // SET_LIGHTNESS
				this.sendSetMsg(FFDLightnessSensorNode.class);
				break;
			case 4: // AIR_CONDITIONER_ON
				this.sendTurnOnAirConditioner();
				break;
			case 5: // AIR_CONDITIONER_OFF
				this.sendTurnOffAirConditioner();
				break;
			case 6: // TAKE_TEMPERATURE
				break;
		}
	}

}
