package hibernate_learning.Entity;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.HIbernateApplication;
import hibernate_learning.models.DepartmentOneToMany;
import hibernate_learning.models.EmployeeOneToMany;

public class TestEntityComposistionOneToMany {
	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();
		new TestEntityComposistionOneToMany().testDepedentEntityPersistence(sessionFactoryBuilder);
	}

	private void testDepedentEntityPersistence(SessionFactory sessionFactory) {
		Session openSession = sessionFactory.openSession();
		Transaction tx = openSession.beginTransaction();
		tx.begin();
		DepartmentOneToMany department = new DepartmentOneToMany();
		department.setName("chemistry");
		
		EmployeeOneToMany emp = new EmployeeOneToMany();
		emp.setName("emp1");
		EmployeeOneToMany emp1 = new EmployeeOneToMany();
		emp1.setName("emp2");
		EmployeeOneToMany emp2 = new EmployeeOneToMany();
		emp2.setName("emp3");
		department.getEmployee().add(emp);
		department.getEmployee().add(emp1);
		department.getEmployee().add(emp2);
		emp.setDepartment(department);
		emp1.setDepartment(department);
		emp2.setDepartment(department);
		openSession.persist(department);
		tx.commit();
		openSession.close();
		Session openSession1 = sessionFactory.openSession();
		DepartmentOneToMany vehi = openSession1.get(DepartmentOneToMany.class, 1);
		System.out.println(vehi.getName());
		System.out.println(vehi.getEmployee().size());
		openSession1.close();
		sessionFactory.close();
		
	}
}
