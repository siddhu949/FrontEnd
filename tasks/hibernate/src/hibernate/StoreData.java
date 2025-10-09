package hibernate;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class StoreData {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

	    SessionFactory sf = meta.getSessionFactoryBuilder().build();
	    Session session = sf.openSession();
	    Transaction txn = session.beginTransaction();

	    Employee e1= new Employee();
	    //e1.id = 123;
//	    e1.setId(1);
	    e1.setFirstName("sat3");
	    e1.setLastName("nav3");

//	    RegularEmployee regEmp= new RegularEmployee();
//	    regEmp.setId(1);
//	    regEmp.setFirstName("sara");
//	    regEmp.setLastName("d");
//	    regEmp.setSalary(38000.00);
//	    regEmp.setBonus(500.00);
//
//	    ContractEmployee contractEmp= new ContractEmployee();
//	    contractEmp.setId(1);
//	    contractEmp.setFirstName("raju");
//	    contractEmp.setLastName("navi");
//	    contractEmp.setPayPerHour(500.00);
//	    contractEmp.setContractDuration(1.5);

	    Serializable x = session.save(e1);
	    session.persist(e1);
//	    session.save(regEmp);
//	    session.save(contractEmp);
	    txn.commit();

//	    Department dept = new Department();
//	    TypedQuery query = session.getNamedQuery("findEmployeeByName");
//        query.setParameter("name","amit");
//	    dept.getEmpList().get(0);
//
	    sf.close();
	    session.close();
	    System.out.println("Save Success");
	}
}
