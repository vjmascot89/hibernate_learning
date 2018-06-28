package hibernate_learning.Entity.table_per_class;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate_learning.HIbernateApplication;
import hibernate_learning.model.inheritance.Generator;
import hibernate_learning.model.inheritance.Machine;
import hibernate_learning.model.inheritance.WaterJet;

public class TablePerClassTest {

	public static void main(String... strings) {
		SessionFactory sessionFactory = HIbernateApplication.sessionFactoryBuilder();
		testTablePerClass(sessionFactory);
	}

	private static void testTablePerClass(SessionFactory sessionFactory) {
		Machine machine = new Machine();
		machine.setMake("Unknown 2018");
		machine.setDateOfManufacturing(new Date(123444));
		WaterJet waterJet = new WaterJet();
		waterJet.setDateOfManufacturing(new Date(123455));
		waterJet.setMake("Ion 2018");
		waterJet.setPipes("XTP pipes 5 mm");
		waterJet.setCapacitor("XTP Capacitor with 5mega watt");
		
		Generator generator = new Generator();
		generator.setDateOfManufacturing(new Date(123455));
		generator.setMake("Samsung");
		generator.setBelt("S6 Belt 5 mm");
		generator.setHandle("S8 5meter length");
		Session session = sessionFactory.openSession();
		session.save(machine);
		session.save(waterJet);
		session.save(generator);
		session.flush();
		session.close();
		sessionFactory.close();
			
	}

}
