package projects.tcc.nodes.nodeImplementations;

import java.awt.Color;

import projects.tcc.nodes.messages.NetworkMessage;
import sinalgo.nodes.edges.Edge;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class AirConditioner extends Device {
	
	private static double actualTemperatureVal = 25.0;
	private static double oldTemperatureVal = AirConditioner.actualTemperatureVal;
	private static boolean turnedOn = false;
	
	private double temperatureChangingCycles = 0;
	private double rateTemperatureChanging = 0.2;
	private double maxTemperatureChangingCycles = 1/this.rateTemperatureChanging;
	
	public AirConditioner(){
		this.setColor(Color.orange);
	}
	
	public static double getTemperature(){
		return AirConditioner.actualTemperatureVal;
	}
	
	public void setTemperature(double newTemperature){
		AirConditioner.oldTemperatureVal = AirConditioner.actualTemperatureVal;
		AirConditioner.actualTemperatureVal = newTemperature;
	}
	
	private void changeSensorsTemperature(){
		if(AirConditioner.actualTemperatureVal != AirConditioner.oldTemperatureVal){
			Double diff = AirConditioner.actualTemperatureVal - AirConditioner.oldTemperatureVal;
			for(Edge ed : this.outgoingConnections){
				if(ed.endNode instanceof RFDTemperatureSensorNode)
					((RFDTemperatureSensorNode) ed.endNode).temperatureVal += diff*this.rateTemperatureChanging;
			}
			if(++this.temperatureChangingCycles == this.maxTemperatureChangingCycles){
				this.oldTemperatureVal = this.actualTemperatureVal;
			}
		}
	}
	
	@Override
	public void postStep() {
		this.changeSensorsTemperature();
	}
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message message = inbox.next();			
			if (message instanceof NetworkMessage) {				
				switch(((NetworkMessage) message).typeMsg){
					case 0: // GET_TEMPERATURE
						break;
					case 1: // SET_TEMPERATURE
						this.setTemperature(((NetworkMessage) message).value);
						break;
//					case 2: // GET_LIGHTNESS
//						this.getLightnessFromRFDs();
//						break;
//					case 3: // SET_LIGHTNESS
//						Light.setLumVal(((NetworkMessage) message).value);
//						break;
					case 4: // AIR_CONDITIONER_ON
						this.turnedOn = true;
						break;
					case 5: // AIR_CONDITIONER_OFF
						this.turnedOn = false;
						break;
//					case 6: // TAKE_TEMPERATURE
//						break;
				}
			}
		}
	}
	
}
