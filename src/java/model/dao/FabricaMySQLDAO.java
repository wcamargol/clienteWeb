package model.dao;
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
    
    public static AlarmeMySQLDAO getAlarmeMySQLDAO(){
        return new AlarmeMySQLDAO();
    }
    
    public static AmbienteMySQLDAO getAmbienteMySQLDAO(){
        return new AmbienteMySQLDAO();
    }
    
    public static EquipamentoMySQLDAO getEquipamentoMySQLDAO(){
        return new EquipamentoMySQLDAO();
    }
    
    public static EventoMySQLDAO getEventoDAO(){
        return new EventoMySQLDAO();
    }
    
    public static MoradorMySQLDAO getMoradorMySQLDAO(){
        return new MoradorMySQLDAO();
    }
    
    public static OperacaoMySQLDAO getOperacaoMySQLDAO(){
        return new OperacaoMySQLDAO();
    }
}