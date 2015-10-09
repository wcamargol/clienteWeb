package model.dao;

import java.util.List;
import model.beans.EquipamentoBean;
import model.dao.interfaces.EquipamentoDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EquipamentoMySQLDAO implements EquipamentoDAO {
     private Session session;
    
    public EquipamentoBean recuperar(String codigoEquipamento){
        EquipamentoBean equipamento = null;
        if(codigoEquipamento != null){
            session = FabricaMySQLDAO.getSession();
            Query q = session.createQuery("select a from Equipamento a where "
                + "a.codigoEquipamento = '" + codigoEquipamento + "'");
            List l = q.list();
            if (!l.isEmpty()){
               equipamento = (EquipamentoBean)l.get(0);
            }
        }
        return equipamento;
    }
    
    public boolean atualizar(EquipamentoBean equipamento){
        boolean sucesso = false;
        if(equipamento != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.update(equipamento);
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
    public boolean salvar(EquipamentoBean equipamento){
        boolean sucesso = false;
        if(equipamento != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(equipamento);
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
    public boolean apagar(EquipamentoBean equipamento){
        boolean sucesso = false;
        if(equipamento != null){
            session = FabricaMySQLDAO.getSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.delete(equipamento);
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