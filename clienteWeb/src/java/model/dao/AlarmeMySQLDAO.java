package model.dao;

import java.util.List;
import model.beans.Alarme;
import model.dao.interfaces.AlarmeDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlarmeMySQLDAO implements AlarmeDAO {
    private Session session;
    
    public Alarme recuperar(Alarme codigo){
        Alarme alarme = null;
        if(codigo != null){
            session = FabricaMySQLDAO.getSession();
            Query q = session.createQuery("select a from Alarme a where a.codigoAlarme = ?");
            q.setString(0, codigo.getCodigoAlarme());
            List l = q.list();
            if (!l.isEmpty()){
               alarme = (Alarme)l.get(0);
            }
        }
        return alarme;
    }
    
    public boolean atualizar(Alarme alarme){
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
            }finally{
                session.close();
            }
        }
        return sucesso;    
    }
    public boolean salvar(Alarme alarme){
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
            }finally{
                session.close();
            }
        }
        return sucesso;            
    }
    public boolean apagar(Alarme alarme){
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
            }finally{
                session.close();
            }
        }
        return sucesso;
    }
}