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
	
//	public static final int GET_TEMPERATURE = 0;
//	public static final int SET_TEMPERATURE = 1;
//	public static final int GET_LIGHTNESS = 2;
//	public static final int SET_LIGHTNESS = 3;
//	public static final int AIR_CONDITIONER_ON = 4;
//	public static final int AIR_CONDITIONER_OFF = 5;
//	public static final int TAKE_TEMPERATURE = 6;

	private void sendGetMsg(Class<?> klass){
		if(this.node instanceof Coordinator){
			for(Edge ed : this.node.outgoingConnections){
				if(ed.endNode.getClass().equals(klass)){ // Temperature or Lightness FFD
					this.node.send(this.message, ed.endNode);
					Tools.appendToOutput("GET: " + this.node.toString() + " ~> FFD\n");
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
				Tools.appendToOutput("SET: " + this.node.toString() + " ~> FFD\n");
			}
		}
	}
	
	@Override
	public void fire() {
		switch(this.message.typeMsg){
			case 0:
				this.sendGetMsg(FFDTemperatureSensorNode.class);
				break;
			case 1:
				this.sendSetMsg(FFDTemperatureSensorNode.class);
				break;
			case 2:
				this.sendGetMsg(FFDLightnessSensorNode.class);
				break;
			case 3:
				this.sendSetMsg(FFDLightnessSensorNode.class);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
		}
	}

}
