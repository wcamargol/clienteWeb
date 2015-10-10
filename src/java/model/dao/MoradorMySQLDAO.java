package model.dao;

import java.util.List;
import model.beans.MoradorBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MoradorMySQLDAO{
    private Session session;
    
    public MoradorBean getMoradorBean(String login){
        MoradorBean morador = null;
        if (login != null){
            try{
                session = FabricaMySQLDAO.getSession();
                Query consulta = session.createQuery("select m from MoradorBean "
                    + "m where m.login = '"+login+"'");
                List l = consulta.list();
                if (!l.isEmpty()){
                   morador = (MoradorBean)l.get(0);
                }
            }catch (HibernateException ex){
                ex.printStackTrace();
            }
        }
        return morador;
    }
    
    public List listMoradorBean(){
        session = FabricaMySQLDAO.getSession();
        Query consulta = null;
        try{            
            consulta = session.createQuery("from MoradorBean");
        }catch (HibernateException ex){
                ex.printStackTrace();
            
        }
        return consulta.list();
    }
    
    public boolean updateMoradorBean(MoradorBean morador){
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
            }
        }
        return sucesso;    
    }
    public boolean saveMoradorBean(MoradorBean morador){
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
            }
        }
        return sucesso;            
    }
    public boolean deleteMoradorBean(MoradorBean morador){
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
            }
        }
        return sucesso;
    }    
}