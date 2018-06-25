package hibernate_learning.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class EmployeeManyToMany {
	@Id
	@GeneratedValue
	private
	Integer id;
	private String name;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinColumn(name ="Dep_Id")
	private List<DepartmentManyToMany> department = new ArrayList<DepartmentManyToMany>();
	
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
	public List<DepartmentManyToMany> getDepartment() {
		return department;
	}
	public void setDepartment(List<DepartmentManyToMany> department) {
		this.department = department;
	}
}
