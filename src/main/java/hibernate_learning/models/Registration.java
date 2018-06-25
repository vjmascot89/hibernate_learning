package hibernate_learning.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Registration {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Integer id;
	String registrationName;
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private VehicleModel vehicleModel;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistrationName() {
		return registrationName;
	}

	public void setRegistrationName(String registrationName) {
		this.registrationName = registrationName;
	}

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+"   "+registrationName +"   "+vehicleModel;
	}

}
