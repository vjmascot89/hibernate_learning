package hibernate_learning;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import hibernate_learning.model.inheritance.FourWheeler;
import hibernate_learning.model.inheritance.Generator;
import hibernate_learning.model.inheritance.Machine;
import hibernate_learning.model.inheritance.TwoWheeler;
import hibernate_learning.model.inheritance.WaterJet;
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
// config.addXML("/hibernate_learning/src/main/resources/hibernate.cfg.xml");
// config.addFile(
// "/Users/vijayjaiswal/Documents/workspace/hibernate_learning/src/main/resources/hibernate.cfg.xml");
// config.setProperty("connection.pool_size", "1");
// config.setProperty("transaction.factory_class",
// "org.hibernate.transaction.JDBCTransactionFactory");
import hibernate_learning.models.embeddable.Address;
import hibernate_learning.models.embeddable.Student;

// config.setProperty("cache.provider_class",
// "org.hibernate.cache.NoCacheProvider");
public class HIbernateApplication {

	public static SessionFactory sessionFactoryBuilder() {
		Configuration config = new Configuration();

		config.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
		config.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:xe");
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle9Dialect");
		config.setProperty("hibernate.connection.username", "xtp");
		config.setProperty("hibernate.connection.password", "xtp");

		config.setProperty("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");

		config.setProperty("hibernate.show_sql", "true");

		config.setProperty("hibernate.hbm2ddl.auto", "create");
		config.setProperty("hibernate.format_sql", "true");
		
		config.setProperty("hibernate.c3p0.max_size", "300");
		config.setProperty("hibernate.c3p0.min_size", "100");
		config.setProperty("hibernate.c3p0.timeout", "5000");
		config.setProperty("hibernate.c3p0.max_statements", "2");
		config.setProperty("hibernate.c3p0.idle_test_period", "20");
		config.setProperty("hibernate.c3p0.acquire_increment", "1");
		//add entity to the configuration
		enterAllEntity(config,
				Student.class,
				Address.class,
				Vehicle.class,
				Registration.class,
				VehicleModel.class,
				DepartmentOneToMany.class,
				EmployeeOneToMany.class,
				DepartmentManyToOne.class,
				EmployeeManyToOne.class,
				DepartmentManyToMany.class,
				EmployeeManyToMany.class,
				Generator.class,Machine.class,WaterJet.class,
				hibernate_learning.model.inheritance.Vehicle.class,TwoWheeler.class,FourWheeler.class
				);
		Properties configProperties = config.getProperties();
		StandardServiceRegistryBuilder serviceRegisteryBuilder = new StandardServiceRegistryBuilder();
		ServiceRegistry serviceRegistry = serviceRegisteryBuilder.applySettings(configProperties).build();
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// sessionfactory is resposible to give you multiple connection.
//		testconnection(sessionFactory);
		return sessionFactory;
	}

	private static void testconnection(SessionFactory sessionFactory) {
		ArrayList ls = new ArrayList();
		try {
			for (int i = 0; i < 21; i++) {
				
					ls.add(sessionFactory.getSessionFactoryOptions().getServiceRegistry()
							.getService(ConnectionProvider.class).getConnection());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
