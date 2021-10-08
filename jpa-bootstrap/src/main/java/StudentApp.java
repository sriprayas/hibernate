import model.Student;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentApp {

    public static void main(String[] args){
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        Student stud1 = new Student();
        stud1.setAge(20);
        stud1.setDept("EC");
        stud1.setName("Prayas");

        em.getTransaction().begin();
        em.persist(stud1);
        em.getTransaction().commit();
        em.close();

        EntityManager em1 = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em1.getTransaction().begin();
        List<Student> studList = em1.createQuery("from Student").getResultList();
        studList.forEach(stud -> System.out.println(stud.toString()));
        em1.getTransaction().commit();
        em1.close();
    }
}
