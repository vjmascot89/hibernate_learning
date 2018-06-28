package hibernate_learning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.models.Vehicle;

public class TestPersistAPI {

	public TestPersistAPI() {

	}

	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();
		
		new TestPersistAPI().testTansactionCommitDoesnotWorkWithoutBeginObject(sessionFactoryBuilder);
		// new TestPersistAPI().testWithFlush(sessionFactoryBuilder);

		// new TestPersistAPI().testSessionPersistenceFollowedBYEvictThrows(sessionFactoryBuilder);
		// new TestPersistAPI().testSessionUpdateFollowedBYEvictThrows(sessionFactoryBuilder);
		// new TestPersistAPI().testSessionMergeFollowedBYEvictThrows(sessionFactoryBuilder);
		// new TestPersistAPI().testSessionSaveOrUpdateFollowedBYEvictThrows(sessionFactoryBuilder);
		// new TestPersistAPI().testSessionSaveFollowedBYEvictThrows(sessionFactoryBuilder);

	}

	private void testTansactionCommitDoesnotWorkWithoutBeginObject(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		//transient entity
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before persist " + vehicle.getId());
		//persistent entity
		openSession.persist(vehicle);
		System.out.println("before commit getting entity \n" + openSession.get(Vehicle.class, 1).getVehicleName());
		//begintransaction and commit
		openSession.beginTransaction().commit();
		
		//if done like this expect an error
		//openSession.getTransaction().commit();
		openSession.close();
		System.out.println("after close session vehicle id assigned" + vehicle.getId());
		
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testWithFlush(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before save " + vehicle.getId());
		openSession.persist(vehicle);
//		openSession.persist(vehicle);
		openSession.flush();
//		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1).getVehicleName());
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
		openSession.persist(vehicle);
		transaction.commit();
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testSessionPersistenceFollowedBYEvictThrows(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before persist " + vehicle.getId());
		openSession.persist(vehicle);
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.evict(vehicle);
		openSession.persist(vehicle);
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testSessionUpdateFollowedBYEvictThrows(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before persist " + vehicle.getId());
		openSession.persist(vehicle);
		System.out.println("before evict session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.evict(vehicle);
		System.out.println("after evict " + openSession.get(Vehicle.class, 1));
		openSession.update(vehicle);
		openSession.flush();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testSessionMergeFollowedBYEvictThrows(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Transaction beginTransaction = openSession.beginTransaction();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before persist " + vehicle.getId());
		openSession.persist(vehicle);
		System.out.println("before evict session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.evict(vehicle);
		System.out.println("after evict " + openSession.get(Vehicle.class, 1));
		vehicle.setId(null);
		openSession.merge(vehicle);
		try {
			beginTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println("1: "+openSession.get(Vehicle.class, 1));
		System.out.println("2: "+openSession.get(Vehicle.class, 2));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testSessionSaveOrUpdateFollowedBYEvictThrows(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before persist " + vehicle.getId());
		openSession.persist(vehicle);
		System.out.println("before evict session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.evict(vehicle);
		System.out.println("after evict " + openSession.get(Vehicle.class, 1));
		openSession.saveOrUpdate(vehicle);
		openSession.flush();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testSessionSaveFollowedBYEvictThrows(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		System.out.println("before persist " + vehicle.getId());
		openSession.persist(vehicle);
		System.out.println("before evict session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.evict(vehicle);
		System.out.println("after evict " + openSession.get(Vehicle.class, 1));
		System.out.println(vehicle.getId());
		openSession.save(vehicle);
		openSession.flush();
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		System.out.println(openSession.get(Vehicle.class, 2));
		System.out.println(openSession.get(Vehicle.class, 3));
		openSession.close();
		sessionFactoryBuilder.close();
	}
}
