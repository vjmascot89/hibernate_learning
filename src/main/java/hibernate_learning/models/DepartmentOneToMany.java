package hibernate_learning.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DepartmentOneToMany {
	@Id
	@GeneratedValue
	private
	Integer id;
	private String name;
	@OneToMany(cascade=CascadeType.ALL)
	private List<EmployeeOneToMany> employee= new ArrayList<EmployeeOneToMany>();
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
	public List<EmployeeOneToMany> getEmployee() {
		return employee;
	}
	public void setEmployee(List<EmployeeOneToMany> employee) {
		this.employee = employee;
	}
}
