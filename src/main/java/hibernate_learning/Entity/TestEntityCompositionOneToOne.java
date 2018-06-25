package hibernate_learning.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.HIbernateApplication;
import hibernate_learning.models.Registration;
import hibernate_learning.models.VehicleModel;

public class TestEntityCompositionOneToOne {
	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();
		new TestEntityCompositionOneToOne().testDepedentEntityPersistence(sessionFactoryBuilder);
	}

	private void testDepedentEntityPersistence(SessionFactory sessionFactory) {
		Session openSession = sessionFactory.openSession();
		Transaction tx = openSession.beginTransaction();
		tx.begin();
		Registration registration = new Registration();
		registration.setRegistrationName("Vijay Bike H5770");
		
		VehicleModel vehicleModel = new VehicleModel();
		vehicleModel.setVehicleName("Bike");
		registration.setVehicleModel(vehicleModel);
		Registration registration1 = new Registration();
		registration1.setRegistrationName("RUdra Car H5770");
		
		VehicleModel vehicleModel1 = new VehicleModel();
		vehicleModel1.setVehicleName("Car");
		registration1.setVehicleModel(vehicleModel1);
		Registration registration2 = new Registration();
		registration2.setRegistrationName("Isha Bekaar H5770");
		
		VehicleModel vehicleModel2 = new VehicleModel();
		vehicleModel2.setVehicleName("Bike");
		registration2.setVehicleModel(vehicleModel2);
		openSession.persist(registration);
		openSession.persist(registration1);
		openSession.persist(registration2);
		tx.commit();
		openSession.close();
		Session openSession1 = sessionFactory.openSession();
		VehicleModel vehi = openSession1.get(VehicleModel.class, 2);
		System.out.println(vehi);
		System.out.println(vehi.getRegistration());
		openSession1.close();
		sessionFactory.close();
		
	}
	
}
