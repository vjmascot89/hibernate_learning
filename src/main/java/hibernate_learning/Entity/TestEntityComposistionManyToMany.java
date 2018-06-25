package hibernate_learning.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.HIbernateApplication;
import hibernate_learning.models.DepartmentManyToMany;
import hibernate_learning.models.EmployeeManyToMany;

public class TestEntityComposistionManyToMany {
	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();
		new TestEntityComposistionManyToMany().testDepedentEntityPersistence(sessionFactoryBuilder);
	}

	private void testDepedentEntityPersistence(SessionFactory sessionFactory) {
		Session openSession = sessionFactory.openSession();
		Transaction tx = openSession.beginTransaction();
		tx.begin();
		DepartmentManyToMany department = new DepartmentManyToMany();
		department.setName("chemistry");
		
		EmployeeManyToMany emp = new EmployeeManyToMany();
		emp.setName("emp1");
		EmployeeManyToMany emp1 = new EmployeeManyToMany();
		emp1.setName("emp2");
		EmployeeManyToMany emp2 = new EmployeeManyToMany();
		emp2.setName("emp3");
		department.getEmployee().add(emp);
		department.getEmployee().add(emp1);
		department.getEmployee().add(emp2);
		emp.getDepartment().add(department);
		emp1.getDepartment().add(department);
		emp2.getDepartment().add(department);
		openSession.persist(department);
		tx.commit();
		openSession.close();
		Session openSession1 = sessionFactory.openSession();
		DepartmentManyToMany vehi = openSession1.get(DepartmentManyToMany.class, 1);
		System.out.println(vehi.getName());
		System.out.println(vehi.getEmployee().size());
		openSession1.close();
		sessionFactory.close();
		
	}
}
