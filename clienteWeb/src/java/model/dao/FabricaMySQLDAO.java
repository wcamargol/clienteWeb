package model.dao;

import model.dao.interfaces.AlarmeDAO;
import model.dao.interfaces.EquipamentoDAO;
import model.dao.interfaces.EventoDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class FabricaMySQLDAO {
    private static SessionFactory sessionFactory;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    static {
        try{
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch( HibernateException ex){
            ex.printStackTrace();
        }
    }    

    public static Session getSession(){
        Session session = (Session) threadLocal.get();
        session = sessionFactory.openSession();
        threadLocal.set(session);
        return session;
    }
    
    public static AlarmeDAO getAlarmeDAO(){
        return new AlarmeMySQLDAO();
    }
    
    public static EquipamentoDAO getEquipamentoDAO(){
        return new EquipamentoMySQLDAO();
    }
    
    public static EventoDAO getEventoDAO(){
        return new EventoMySQLDAO();
    }
    
}