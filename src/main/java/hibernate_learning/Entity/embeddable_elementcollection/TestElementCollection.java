package hibernate_learning.Entity.embeddable_elementcollection;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate_learning.HIbernateApplication;
import hibernate_learning.models.embeddable.Address;
import hibernate_learning.models.embeddable.Student;

public class TestElementCollection {
	public static void main(String... strings) {
		SessionFactory sessionFactory = HIbernateApplication.sessionFactoryBuilder();
		testElementCollection(sessionFactory);
	}

	private static void testElementCollection(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		ArrayList<Address> arrayList = new ArrayList<Address>();
		Address address = new Address();
		address.setCity("GHazibad");
		address.setPincode("201301");
		address.setStreet("ahinsa khand");
		address.setState("UP");
		Address newAddress = new Address();
		newAddress.setCity("Lucknow");
		newAddress.setPincode("220006");
		newAddress.setStreet("Kursi Road");
		newAddress.setState("UP");
		arrayList.add(address);
		arrayList.add(newAddress);
		Student student = new Student();
		student.setList(arrayList);
		student.setName("Vijay");
		session.save(student);
		session.beginTransaction().commit();
		session.close();
		session = sessionFactory.openSession();
//		Address add  = session.get(Address.class, 1);
		Student stud  = session.get(Student.class, 1);
//		System.out.println(stud.getName());
		session.close();
		sessionFactory.close();
		
	}
}
