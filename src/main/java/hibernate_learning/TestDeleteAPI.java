package hibernate_learning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.models.Vehicle;

public class TestDeleteAPI {

	public TestDeleteAPI() {

	}

	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();

		new TestDeleteAPI().testWithoutTransaction(sessionFactoryBuilder);
	}

	private void testWithoutTransaction(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		
//		Transaction tx = openSession.getTransaction();
//		tx.begin();
		Session openSession1 = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before save " + vehicle.getId());
		openSession.save(vehicle);
//		tx.commit();
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		System.out.println("before open session " + openSession1.get(Vehicle.class, 1).getVehicleName());
		openSession.beginTransaction().commit();
		openSession.close();
		openSession1.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testWithFlush(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before save " + vehicle.getId());
		openSession.save(vehicle);
		openSession.flush();
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}
	private void testWithTXTransaction(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		
		Transaction transaction = openSession.getTransaction();
		transaction.begin();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before save tx " + vehicle.getId());
		openSession.save(vehicle);
		transaction.commit();
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}
}
