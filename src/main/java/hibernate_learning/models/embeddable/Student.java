package hibernate_learning.models.embeddable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Integer rollNo;
	String name;
	
//	@Embedded
//	Address address;
//	
//	@Embedded
//	@AttributeOverrides({
//		@AttributeOverride(name="street",column=@Column(name="new_street")),
//		@AttributeOverride(name="city",column=@Column(name="new_city")),
//		@AttributeOverride(name="state",column=@Column(name="new_state")),
//		@AttributeOverride(name="pincode",column=@Column(name="new_pincode"))
//	})
//	Address newAddress;
	
	
	@ElementCollection
	@JoinTable(name="Stud_Address",
	joinColumns=@JoinColumn(name="RollNo"))
	private List<Address> list = new ArrayList<Address>();
	
	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
//	public Address getAddress() {
//		return address;
//	}
//	
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//	
//	public Address getNewAddress() {
//		return newAddress;
//	}
//	
//	public void setNewAddress(Address newAddress) {
//		this.newAddress = newAddress;
//	}
	
	public List<Address> getList() {
		return list;
	}
	
	public void setList(List<Address> list) {
		this.list = list;
	}
	
}
