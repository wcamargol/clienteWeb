/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.beans.Evento;
import model.beans.FabricaConexoes;
import model.dao.interfaces.EventoDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author lubuntu
 */
public class EventoMySQLDAO implements EventoDAO {
    
    private Session session;
    
    public Evento recuperar(Evento id) {
        Evento evento = null;
        if(id != null){
            session = FabricaConexoes.getInstance();
            Query q = session.createQuery("select a from Evento a where a.id.evento.codigoAlarme = ? "
                + "and a.id.equipamento.codigoEquipamento = ? ");
            q.setString(0, id.getId().getAlarme().getCodigoAlarme());
            q.setString(1, id.getId().getEquipamento().getCodigoEquipamento());
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
            session = FabricaConexoes.getInstance();
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
            session = FabricaConexoes.getInstance();
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
            session = FabricaConexoes.getInstance();
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
