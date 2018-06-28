package hibernate_learning.model.inheritance;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="VehicleInhe")
@DiscriminatorColumn(name="VehicleType")
@DiscriminatorValue("Vehicle")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Vehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Integer id;
	
	String make;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
	
}
