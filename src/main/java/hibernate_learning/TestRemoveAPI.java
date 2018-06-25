package hibernate_learning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.models.Vehicle;

public class TestRemoveAPI {

	public TestRemoveAPI() {

	}

	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();

//		new TestRemoveAPI().testWithoutTransaction(sessionFactoryBuilder);
//		new TestRemoveAPI().testWithFlush(sessionFactoryBuilder);
		new TestRemoveAPI().testWithTXTransaction(sessionFactoryBuilder);
		
	}

	private Vehicle insertAnObject(SessionFactory sessionFactoryBuilder) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		Session openSession = sessionFactoryBuilder.openSession();
		System.out.println("before save " + vehicle.getId());
		openSession.save(vehicle);
		openSession.flush();
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		return vehicle;
	}
	private void testWithoutTransaction(SessionFactory sessionFactoryBuilder) {
		
		Vehicle vehicle = insertAnObject(sessionFactoryBuilder);
		Session openSession = sessionFactoryBuilder.openSession();
		vehicle.setVehicleName("Bike");
		System.out.println("before delete " + vehicle.getId());
		openSession.delete(vehicle);
//		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testWithFlush(SessionFactory sessionFactoryBuilder) {
		Vehicle vehicle = insertAnObject(sessionFactoryBuilder);
		Session openSession = sessionFactoryBuilder.openSession();
		vehicle.setVehicleName("Bike");
		System.out.println("before delete " + vehicle.getId());
		openSession.delete(vehicle);
//		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.flush();
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
		openSession.delete(vehicle);
		transaction.commit();
//		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}
}
