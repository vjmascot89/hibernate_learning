package hibernate_learning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeManyToOne {
	@Id
	@GeneratedValue
	private
	Integer id;
	private String name;
	@ManyToOne
	@JoinColumn(name ="Dep_Id")
	private DepartmentManyToOne department;
	
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
	public DepartmentManyToOne getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentManyToOne department) {
		this.department = department;
	}
}
