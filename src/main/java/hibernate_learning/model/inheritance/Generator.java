package hibernate_learning.model.inheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Generator extends Machine{

	@Column
	String handle;
	
	@Column
	String belt;

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getBelt() {
		return belt;
	}

	public void setBelt(String belt) {
		this.belt = belt;
	}

}
