package model.dao;

import java.util.List;
import model.beans.MoradorBean;
import model.dao.interfaces.MoradorDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MoradorMySQLDAO implements MoradorDAO {
    private Session session;
    
    public MoradorBean recuperar(String login){
        MoradorBean morador = null;
        session = FabricaMySQLDAO.getSession();
        Query q = session.createQuery("select m from MoradorBean m where m.login = '"+login+"'");
        List l = q.list();
        if (!l.isEmpty()){
           morador = (MoradorBean)l.get(0);
        }        
        return morador;
    }
    
    public boolean atualizar(MoradorBean morador){
        boolean sucesso = false;
        if(morador != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.update(morador);
                tx.commit();
                sucesso = true;                
            }catch(HibernateException ex){
                ex.printStackTrace();
                tx.rollback();
            }finally{
                session.close();
            }
        }
        return sucesso;    
    }
    public boolean salvar(MoradorBean morador){
        boolean sucesso = false;
        if(morador != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(morador);
                tx.commit();
                sucesso = true;                
            }catch(HibernateException ex){
                ex.printStackTrace();
                tx.rollback();
            }finally{
                session.close();
            }
        }
        return sucesso;            
    }
    public boolean apagar(MoradorBean morador){
        boolean sucesso = false;
        if(morador != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.delete(morador);
                tx.commit();
                sucesso = true;                
            }catch(HibernateException ex){
                ex.printStackTrace();
                tx.rollback();
            }finally{
                session.close();
            }
        }
        return sucesso;
    }    
}