package model.dao;

import java.util.List;
import model.beans.AlarmeBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlarmeMySQLDAO{
    private Session session;
    
    public AlarmeBean getAlarmeBean(String codigo){        
        AlarmeBean alarme = null;
        if (codigo != null){
            session = FabricaMySQLDAO.getSession();
            try{
            Query consulta = session.createQuery("select a from AlarmeBean a where "
                + "a.codigoAlarme = '"+codigo+"'");
            List l = consulta.list();
            if (!l.isEmpty()){
               alarme = (AlarmeBean)l.get(0);
            }
            }catch (HibernateException ex){
                ex.printStackTrace();
            }
        }
        return alarme;
    }
    
    public List listAlarmeBean(){
        session = FabricaMySQLDAO.getSession();
        Query consulta = null;
        try{            
            consulta = session.createQuery("from AlarmeBean ");
        }catch (HibernateException ex){
                ex.printStackTrace();
            
        }     
        return consulta.list();
    }
    
    public boolean updateAlarmeBean(AlarmeBean alarme){
        boolean sucesso = false;
        if(alarme != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.update(alarme);
                tx.commit();
                sucesso = true;                
            }catch(HibernateException ex){
                ex.printStackTrace();
                tx.rollback();
            }
        }
        return sucesso;    
    }
    public boolean saveAlarmeBean(AlarmeBean alarme){
        boolean sucesso = false;
        if(alarme != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(alarme);
                tx.commit();
                sucesso = true;                
            }catch(HibernateException ex){
                ex.printStackTrace();
                tx.rollback();
            }
        }
        return sucesso;            
    }
    public boolean deleteAlarmeBean(AlarmeBean alarme){
        boolean sucesso = false;
        if(alarme != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.delete(alarme);
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