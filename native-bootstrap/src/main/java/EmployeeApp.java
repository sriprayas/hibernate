import model.Employee;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeApp {

    public static void main(String[] args){
        Employee emp1 = new Employee();
        emp1.setAge(20);
        emp1.setDept("HR");
        emp1.setName("Tom");

        Employee emp2 = new Employee();
        emp2.setAge(40);
        emp2.setDept("Eng");
        emp2.setName("Jerry");

        persist(emp1, emp2);

    }

    private static void persist(Employee emp1, Employee emp2){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(emp1);
            session.persist(emp2);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Employee> empList = session.createQuery("from Employee", Employee.class).list();
            empList.forEach(s -> System.out.println(s.toString()));
        }
    }
}
