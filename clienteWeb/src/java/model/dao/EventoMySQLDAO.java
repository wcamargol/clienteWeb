package model.dao;

import java.util.List;
import model.beans.Evento;
import model.beans.EventoId;
import model.dao.interfaces.EventoDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EventoMySQLDAO implements EventoDAO {
    
    private Session session;
    
    public Evento recuperar(EventoId id) {
        Evento evento = null;
        if(id != null){
            session = FabricaMySQLDAO.getSession();
            Query q = session.createQuery("select a from Evento a where a.id.alarme.codigoAlarme = ? "
                + "and a.id.equipamento.codigoEquipamento = ? ");
            q.setString(0, id.getAlarme().getCodigoAlarme());
            q.setString(1, id.getEquipamento().getCodigoEquipamento());
            List l = q.list();
            if (!l.isEmpty()){
               evento = (Evento)l.get(0);
            }
        }
        return evento;
    }
    
    public boolean atualizar(Evento evento) {
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

    
    public boolean salvar(Evento evento) {
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
    
    public boolean apagar(Evento evento) {
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