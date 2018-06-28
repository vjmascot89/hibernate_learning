package hibernate_learning.model.inheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class WaterJet extends Machine{

	@Column
	String pipes;
	
	@Column
	String capacitor;

	public String getPipes() {
		return pipes;
	}

	public void setPipes(String pipes) {
		this.pipes = pipes;
	}

	public String getCapacitor() {
		return capacitor;
	}

	public void setCapacitor(String capacitor) {
		this.capacitor = capacitor;
	}
	
}
