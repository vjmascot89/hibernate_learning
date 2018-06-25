package hibernate_learning.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class DepartmentManyToOne {
	@Id
	@GeneratedValue
	private
	Integer id;
	private String name;
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL)
	private List<EmployeeManyToOne> employee= new ArrayList<EmployeeManyToOne>();
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
	public List<EmployeeManyToOne> getEmployee() {
		return employee;
	}
	public void setEmployee(List<EmployeeManyToOne> employee) {
		this.employee = employee;
	}
}
