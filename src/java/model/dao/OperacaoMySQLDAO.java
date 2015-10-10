package model.dao;

import java.util.List;
import model.beans.OperacaoBean;
import model.beans.OperacaoIdBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OperacaoMySQLDAO{
    private Session session;
    
    public OperacaoBean getOperacaoBean(OperacaoIdBean id) {
        OperacaoBean evento = null;
        if(id != null){
            try{
                session = FabricaMySQLDAO.getSession();
                Query consulta = session.createQuery("select a from OperacaoBean a "
                    + "where a.id.morador.login = ? "
                    + "and a.id.equipamento.codigoEquipamento = ? ");
                 consulta.setString(0, id.getMorador().getLogin());
                consulta.setString(1, id.getEquipamento().getCodigoEquipamento());
                List l = consulta.list();
                if (!l.isEmpty()){
                   evento = (OperacaoBean)l.get(0);
                }            
            }catch (HibernateException ex){
                ex.printStackTrace();            
            }
        }
        return evento;
    }
    
    public List listaOperacaoBean(){
        session = FabricaMySQLDAO.getSession();
        Query consulta = null;
        try{
            consulta = session.createQuery("from OperacaoBean");
        }catch (HibernateException ex){
                ex.printStackTrace();            
        }
        return consulta.list();
    }
    
    public boolean updateOperacaoBean(OperacaoBean evento) {
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
            }
        }
        return sucesso;            
    }
    
    public boolean saveOperacaoBean(OperacaoBean evento) {
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
            }
        }
        return sucesso;   
    }
    
    public boolean deleteOperacaoBean(OperacaoBean evento) {
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
            }
        }
        return sucesso;
    } 
}