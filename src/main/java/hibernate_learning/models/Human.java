package hibernate_learning.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;

@Entity
public class Human {

	private Integer id;
	private String name;
	private Integer age;
	private String description;
	private String gender;
	
	private List<Address> addressList = new ArrayList<Address>();
//	private Address newAddress;
//	private Address address;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Lob
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
//
//	@Embedded
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//
//	@Embedded
//	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "office_street")),
//			@AttributeOverride(name = "city", column = @Column(name = "office_city")),
//			@AttributeOverride(name = "state", column = @Column(name = "office_state")),
//			@AttributeOverride(name = "pincode", column = @Column(name = "office_pincode")) })
//	public Address getNewAddress() {
//		return newAddress;
//	}
//
//	public void setNewAddress(Address newAddress) {
//		this.newAddress = newAddress;
//	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
    
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="USER_ADDRESS",
	joinColumns=@JoinColumn(name="USER_ID"))
//	@GenericGenerator(name="hi-lo",strategy="hilo")
//	@CollectionId(columns = { @Column(name="Address_Id") }, generator = "hi-lo", type = @Type(type="long"))
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

}
