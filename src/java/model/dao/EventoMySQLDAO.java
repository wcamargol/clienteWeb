package model.dao;

import java.util.List;
import model.beans.EventoBean;
import model.beans.EventoIdBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EventoMySQLDAO{
    
    private Session session;
    
    public EventoBean getEventoBean(EventoIdBean id) {
        EventoBean evento = null;
        if(id != null){
            session = FabricaMySQLDAO.getSession();
            Query consulta = session.createQuery("select a from Evento a where a.id.alarme.codigoAlarme = ? "
                + "and a.id.equipamento.codigoEquipamento = ? ");
            consulta.setString(0, id.getAlarme().getCodigoAlarme());
            consulta.setString(1, id.getEquipamento().getCodigoEquipamento());
            List l = consulta.list();
            if (!l.isEmpty()){
               evento = (EventoBean)l.get(0);
            }
        }
        return evento;
    }
    
    public List listEventoBean(){
        session = FabricaMySQLDAO.getSession();
        Query consulta = session.createQuery("from Alarme ");
        List listaEventoBean = consulta.list();
        return listaEventoBean;
    }
    
    public boolean updateEventoBean(EventoBean evento) {
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

    
    public boolean saveEventoBean(EventoBean evento) {
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
    
    public boolean deleteEventoBean(EventoBean evento) {
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