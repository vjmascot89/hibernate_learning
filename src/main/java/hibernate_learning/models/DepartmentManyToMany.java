package hibernate_learning.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class DepartmentManyToMany {
	@Id
	@GeneratedValue
	private
	Integer id;
	private String name;
	@Fetch(FetchMode.SELECT)
	@ManyToMany(cascade=CascadeType.ALL)
	private List<EmployeeManyToMany> employee= new ArrayList<EmployeeManyToMany>();
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
	public List<EmployeeManyToMany> getEmployee() {
		return employee;
	}
	public void setEmployee(List<EmployeeManyToMany> employee) {
		this.employee = employee;
	}
}
