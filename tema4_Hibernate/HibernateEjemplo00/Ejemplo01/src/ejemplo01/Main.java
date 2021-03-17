package ejemplo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Main {


    public static void main(String[] args) {
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        
        Profesor profesor=new Profesor(101, "Juan", "Perez", "García");
        
        Session session=sessionFactory.openSession();
        
        session.beginTransaction();
        session.save(profesor);
        session.getTransaction().commit();
        
        
        Profesor profesor2=(Profesor)session.get(Profesor.class,101);
        System.out.println(profesor2.getId());
        System.out.println(profesor2.getNombre());
        System.out.println(profesor2.getApe1());
        System.out.println(profesor2.getApe2());        
        
        profesor2.setNombre("Emilio");
        
        session.beginTransaction();
        session.update(profesor2);
        session.getTransaction().commit();        


        
        session.close();
        sessionFactory.close();
    }
}
