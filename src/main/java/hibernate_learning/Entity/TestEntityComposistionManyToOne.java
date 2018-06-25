package hibernate_learning.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.HIbernateApplication;
import hibernate_learning.models.DepartmentManyToOne;
import hibernate_learning.models.DepartmentOneToMany;
import hibernate_learning.models.EmployeeManyToOne;

public class TestEntityComposistionManyToOne {
	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();
		new TestEntityComposistionManyToOne().testDepedentEntityPersistence(sessionFactoryBuilder);
	}

	private void testDepedentEntityPersistence(SessionFactory sessionFactory) {
		Session openSession = sessionFactory.openSession();
		Transaction tx = openSession.beginTransaction();
		tx.begin();
		DepartmentManyToOne department = new DepartmentManyToOne();
		department.setName("chemistry");
		
		EmployeeManyToOne emp = new EmployeeManyToOne();
		emp.setName("emp1");
		EmployeeManyToOne emp1 = new EmployeeManyToOne();
		emp1.setName("emp2");
		EmployeeManyToOne emp2 = new EmployeeManyToOne();
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
		DepartmentManyToOne vehi = openSession1.get(DepartmentManyToOne.class, 1);
		System.out.println(vehi.getName());
		System.out.println(vehi.getEmployee().size());
		openSession1.close();
		sessionFactory.close();
		
	}
}
