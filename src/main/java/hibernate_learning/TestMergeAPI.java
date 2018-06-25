package hibernate_learning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate_learning.models.Vehicle;

public class TestMergeAPI {

	public static void main(String... strings) {
		SessionFactory sessionFactoryBuilder = HIbernateApplication.sessionFactoryBuilder();

		// new TestMergeAPI().testWithoutTransaction(sessionFactoryBuilder);
//		 new TestMergeAPI().testWithFlush(sessionFactoryBuilder);
		// new TestMergeAPI().testWithTXTransaction(sessionFactoryBuilder);
//		 new TestMergeAPI().testExceptionWithTransientEntity(sessionFactoryBuilder);
		 new TestMergeAPI().testNoExceptionWithcacheLoaded(sessionFactoryBuilder);
//		 new TestMergeAPI().testLoadsFromDBWhileMerging(sessionFactoryBuilder);

	}

	private void testWithoutTransaction(SessionFactory sessionFactoryBuilder) {
		Session openSession = sessionFactoryBuilder.openSession();
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		vehicle.setId(1);
		System.out.println("before save " + vehicle.getId());
		openSession.merge(vehicle);
		System.out.println("before close session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println("with new session hitting DB to find vehicle " + openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testWithFlush(SessionFactory sessionFactoryBuilder) {
		Vehicle vehicle = insertAnObject(sessionFactoryBuilder);
		Session openSession = sessionFactoryBuilder.openSession();
		vehicle.setVehicleName("Car");
		System.out.println("before merge " + vehicle.getId());
		openSession.merge(vehicle);
		System.out.println("after merge tx " + vehicle.getId());
		openSession.flush();
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
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

	private void testWithTXTransaction(SessionFactory sessionFactoryBuilder) {
		Vehicle vehicle = insertAnObject(sessionFactoryBuilder);
		Session openSession = sessionFactoryBuilder.openSession();

		Transaction transaction = openSession.getTransaction();
		transaction.begin();
		vehicle.setVehicleName("lamborgini");
		System.out.println("before save tx " + vehicle.getId());
		openSession.merge(vehicle);
		transaction.commit();
		System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
		openSession.close();
		System.out.println("after close session " + vehicle.getId());
		openSession = sessionFactoryBuilder.openSession();
		System.out.println(openSession.get(Vehicle.class, 1));
		openSession.close();
		sessionFactoryBuilder.close();
	}

	private void testExceptionWithTransientEntity(SessionFactory sessionFactoryBuilder) {
		Session openSession = null;
		try {
			Vehicle vehicle = insertAnObject(sessionFactoryBuilder);
			openSession = sessionFactoryBuilder.openSession();

			Transaction transaction = openSession.getTransaction();
			transaction.begin();
			vehicle.setId(null);
			vehicle.setVehicleName("lamborgini");
			System.out.println("before save tx " + vehicle.getId());
			openSession.merge(vehicle);
			transaction.commit();
			System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
			openSession.close();
			System.out.println("after close session " + vehicle.getId());
			openSession = sessionFactoryBuilder.openSession();
			System.out.println(openSession.get(Vehicle.class, 1).getVehicleName());
			System.out.println(openSession.get(Vehicle.class, 2).getVehicleName());
		} catch (Exception e) {
			e.printStackTrace();
			openSession.close();
			sessionFactoryBuilder.close();
		}
		finally{
			close(openSession,sessionFactoryBuilder);
		}
	}
	
	private void testNoExceptionWithcacheLoaded(SessionFactory sessionFactoryBuilder) {
		Session openSession = null;
		try {
			Vehicle vehicle = insertAnObject(sessionFactoryBuilder);
			openSession = sessionFactoryBuilder.openSession();
			Transaction transaction = openSession.getTransaction();
			transaction.begin();
			vehicle.setId(1);
			vehicle.setVehicleName("lamborgini");
			System.out.println("before open session " + openSession.get(Vehicle.class, 1).getVehicleName());
			System.out.println("before save tx " + vehicle.getId());
			openSession.merge(vehicle);
			transaction.commit();
			openSession.close();
			System.out.println("after close session " + vehicle.getId());
			openSession = sessionFactoryBuilder.openSession();
			System.out.println(openSession.get(Vehicle.class, 1).getVehicleName());
		} catch (Exception e) {
			e.printStackTrace();
			openSession.close();
			sessionFactoryBuilder.close();
		}
		finally{
			close(openSession,sessionFactoryBuilder);
		}
	}
	
	private void testLoadsFromDBWhileMerging(SessionFactory sessionFactoryBuilder) {
		Session openSession = null;
		try {
			Vehicle vehicle = insertAnObject(sessionFactoryBuilder);
			openSession = sessionFactoryBuilder.openSession();
			Transaction transaction = openSession.getTransaction();
			transaction.begin();
			vehicle.setId(1);
			vehicle.setVehicleName("lamborgini");
			System.out.println("before merge tx " + vehicle.getId());
			openSession.merge(vehicle);
			System.out.println("after merge tx " + vehicle.getId());
			transaction.commit();
			openSession.close();
			System.out.println("after close session " + vehicle.getVehicleName());
			openSession = sessionFactoryBuilder.openSession();
			System.out.println(openSession.get(Vehicle.class, 1));
		} catch (Exception e) {
			e.printStackTrace();
			openSession.close();
			sessionFactoryBuilder.close();
		}finally{
			close(openSession,sessionFactoryBuilder);
		}
	}
	private void close(Session openSession,SessionFactory sessionFactoryBuilder){
		if(openSession!=null)
		openSession.close();
		if(sessionFactoryBuilder!=null)
		sessionFactoryBuilder.close();
	}
}
