package model.dao;

import java.util.List;
import model.beans.Equipamento;
import model.dao.interfaces.EquipamentoDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EquipamentoMySQLDAO implements EquipamentoDAO {
     private Session session;
    
    public Equipamento recuperar(Equipamento codigoEquipamento){
        Equipamento equipamento = null;
        if(codigoEquipamento != null){
            session = FabricaMySQLDAO.getSession();
            Query q = session.createQuery("select a from Equipamento a where a.codigoEquipamento = ?");
            q.setString(0, codigoEquipamento.getCodigoEquipamento());
            List l = q.list();
            if (!l.isEmpty()){
               equipamento = (Equipamento)l.get(0);
            }
        }
        return equipamento;
    }
    
    public boolean atualizar(Equipamento equipamento){
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
    public boolean salvar(Equipamento equipamento){
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
    public boolean apagar(Equipamento equipamento){
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