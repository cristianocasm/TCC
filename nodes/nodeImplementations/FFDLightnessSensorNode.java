package projects.tcc.nodes.nodeImplementations;

public class FFDLightnessSensorNode extends LightnessSensorNode {
	
	public FFDLightnessSensorNode(){
		super();
		this.setDefaultDrawingSizeInPixels(this.defaultDrawingSizeInPixels*2);
	}

}
