package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
        // 1 : StandardServiceRegistry
        standardServiceRegistry = new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").build();

        // 2. Metadata Source
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);

        // 3. Metadata
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.build();

        // 4. SessionFactory
        sessionFactory =  metadata.buildSessionFactory();
        }
        return sessionFactory;
    }
}
