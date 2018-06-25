package hibernate_learning;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import hibernate_learning.models.Address;
import hibernate_learning.models.DepartmentManyToMany;
import hibernate_learning.models.DepartmentManyToOne;
import hibernate_learning.models.DepartmentOneToMany;
import hibernate_learning.models.EmployeeManyToMany;
import hibernate_learning.models.EmployeeManyToOne;
import hibernate_learning.models.EmployeeOneToMany;
import hibernate_learning.models.Human;
import hibernate_learning.models.HumanOneToOne;
import hibernate_learning.models.Registration;
import hibernate_learning.models.Vehicle;
import hibernate_learning.models.VehicleModel;

public class HIbernateApplication {
	public static void main(String... strings) {
//		SessionFactory sessionFactory = sessionFactoryBuilder();

		// TestSessionFactory testSessionFactory = new
		// TestSessionFactory(sessionFactory) ;

//		embededListCollectionetcTopic(sessionFactory);

//		RelationTestingOnetoOne(sessionFactory);

	}

	public static SessionFactory sessionFactoryBuilder() {
		Configuration config = new Configuration();
		// config.addXML("/hibernate_learning/src/main/resources/hibernate.cfg.xml");
		// config.addFile(
		// "/Users/vijayjaiswal/Documents/workspace/hibernate_learning/src/main/resources/hibernate.cfg.xml");

		config.setProperty("connection.driver_class", "org.postgresql.Driver");
		config.setProperty("connection.url", "jdbc:postgresql://localhost:5432/vijay");
		config.setProperty("connection.username", "postgres");
		config.setProperty("connection.password", "qwerty");

		// config.setProperty("connection.pool_size", "1");
		// config.setProperty("transaction.factory_class",
		// "org.hibernate.transaction.JDBCTransactionFactory");

		// config.setProperty("cache.provider_class",
		// "org.hibernate.cache.NoCacheProvider");
		config.setProperty("show_sql", "true");
		config.setProperty("hbm2ddl.auto", "create");
		config.setProperty("hibernate.c3p0.max_size", "1");
		config.setProperty("hibernate.c3p0.min_size", "0");
		config.setProperty("hibernate.c3p0.timeout", "5000");
		config.setProperty("hibernate.c3p0.max_statements", "2");
		config.setProperty("hibernate.c3p0.idle_test_period", "20");
		config.setProperty("hibernate.c3p0.acquire_increment", "1");
		// enterAllEntity(config, Human.class);
		enterAllEntity(config, Vehicle.class,Registration.class,VehicleModel.class,DepartmentOneToMany.class,EmployeeOneToMany.class,DepartmentManyToOne.class,EmployeeManyToOne.class,
				DepartmentManyToMany.class,EmployeeManyToMany.class);
		config.configure();
		Properties configProperties = config.getProperties();
		StandardServiceRegistryBuilder serviceRegisteryBuilder = new StandardServiceRegistryBuilder();
		ServiceRegistry serviceRegistry = serviceRegisteryBuilder.applySettings(configProperties).build();
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
//		try {
//			for (int i = 0; i < 111; i++) {
//				ls.add(sessionFactory.getSessionFactoryOptions().getServiceRegistry()
//						.getService(ConnectionProvider.class).getConnection());
//			}
//		} catch (Exception e) {
//
//		}
	
		return sessionFactory;
	}

	private static void RelationTestingOnetoOne(SessionFactory sessionFactory) {
		HumanOneToOne humanOneToOne = new HumanOneToOne();
		humanOneToOne.setName("Vijay");
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("GrandI10");
		humanOneToOne.setVehicle(vehicle);
		Session openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		openSession.save(humanOneToOne);
		openSession.save(vehicle);
		openSession.getTransaction().commit();
		openSession.close();

	}

	private static void embededListCollectionetcTopic(SessionFactory sessionFactory) {
		Session openSession = sessionFactory.openSession();
		Transaction beginTransaction = openSession.beginTransaction();
		Human human = new Human();
		human.setName("Vijay");
		human.setDescription("asasa");
		Address homeAddress = new Address();
		homeAddress.setStreet("home");
		Address officeAddress = new Address();
		officeAddress.setStreet("office");
		human.getAddressList().add(homeAddress);
		human.getAddressList().add(officeAddress);
		openSession.save(human);
		beginTransaction.commit();

		Human object = (Human) openSession.get(Human.class, 10);
		System.out.println(object);
		openSession.close();

		openSession = sessionFactory.openSession();
		openSession.beginTransaction();
		Human humObj = (Human) openSession.get(Human.class, 10);
		openSession.close();
		System.out.println(humObj.getName());
		System.out.println(humObj.getAddressList().size());
	}

	private static void enterAllEntity(Configuration configuration, Class... class1) {
		for (Class obj : class1) {
			configuration.addAnnotatedClass(obj);
		}
	}
}
