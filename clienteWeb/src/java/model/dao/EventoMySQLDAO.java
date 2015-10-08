package model.dao;

import java.util.List;
import model.beans.EventoBean;
import model.beans.EventoIdBean;
import model.dao.interfaces.EventoDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EventoMySQLDAO implements EventoDAO {
    
    private Session session;
    
    public EventoBean recuperar(EventoIdBean id) {
        EventoBean evento = null;
        if(id != null){
            session = FabricaMySQLDAO.getSession();
            Query q = session.createQuery("select a from Evento a where a.id.alarme.codigoAlarme = ? "
                + "and a.id.equipamento.codigoEquipamento = ? ");
            q.setString(0, id.getAlarme().getCodigoAlarme());
            q.setString(1, id.getEquipamento().getCodigoEquipamento());
            List l = q.list();
            if (!l.isEmpty()){
               evento = (EventoBean)l.get(0);
            }
        }
        return evento;
    }
    
    public boolean atualizar(EventoBean evento) {
        boolean sucesso = false;
        if(evento != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.update(evento);
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

    
    public boolean salvar(EventoBean evento) {
        boolean sucesso = false;
        if(evento != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(evento);
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
    
    public boolean apagar(EventoBean evento) {
        boolean sucesso = false;
        if(evento != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.delete(evento);
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