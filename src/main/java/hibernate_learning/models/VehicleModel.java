package hibernate_learning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
public class VehicleModel {
	private Registration registration;
	private Integer id;
	private String vehicleName;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	@Fetch(FetchMode.JOIN)
	@OneToOne(mappedBy="vehicleModel")
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+"   "+vehicleName+"   ";
	}
}
