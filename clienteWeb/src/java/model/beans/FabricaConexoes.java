/*
 *Código adaptado do material disponibilizado na disciplina DSAW
 */
package model.beans;

import model.dao.AlarmeMySQLDAO;
import model.dao.EquipamentoMySQLDAO;
import model.dao.interfaces.AlarmeDAO;
import model.dao.interfaces.EquipamentoDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author lubuntu
 */
public class FabricaConexoes {
    private static final SessionFactory sessionFactory; //A fábrica de sessões para as conexões dos usuários com o banco
    //Thread utilizada para executar a sessão do usuário concorrentemente a outras threads
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    static{
        try{
            //Configura a fábrica de conexões para obter os dados da conexão a partir do arquivo hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch(Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
    * Esta função cria uma sessão para o usuário e a retorna
    * @return A sessão do usuário que permitirá o acesso ao banco de dados
    */
    public static Session getInstance(){
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
    
}
