package hibernate_learning.Entity.table_per_class;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate_learning.HIbernateApplication;
import hibernate_learning.model.inheritance.FourWheeler;
import hibernate_learning.model.inheritance.TwoWheeler;
import hibernate_learning.model.inheritance.Vehicle;

public class SingleTableClassTest {
	public static void main(String... strings) {
		SessionFactory sessionFactory = HIbernateApplication.sessionFactoryBuilder();
		testTablePerClass(sessionFactory);
	}

	private static void testTablePerClass(SessionFactory sessionFactory) {
		Vehicle vehicle = new Vehicle();
		vehicle.setMake("Ship 2018");
		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setHandle("non break handle");
		twoWheeler.setMake("Ion 2018");
		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setSteering("Stereo mounted steering");
		fourWheeler.setMake("Samsung");
		Session session = sessionFactory.openSession();
		session.save(vehicle);
		session.save(twoWheeler);
		session.save(fourWheeler);
		session.flush();
		session.close();
		sessionFactory.close();
			
	}
}
