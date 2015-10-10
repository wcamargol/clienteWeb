package model.dao;

import java.util.List;
import model.beans.EquipamentoBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EquipamentoMySQLDAO{
    private Session session;
    
    EquipamentoMySQLDAO (){
        session = FabricaMySQLDAO.getSession();
    }
    
    public EquipamentoBean getEquipamentoBean(String codigoEquipamento){
        EquipamentoBean equipamento = null;
        if(codigoEquipamento != null){
            try{
                Query consulta = session.createQuery("select a from EquipamentoBean a where "
                    + "a.codigoEquipamento = '" + codigoEquipamento + "'");
                List l = consulta.list();
                if (!l.isEmpty()){
                   equipamento = (EquipamentoBean)l.get(0);
                }
            }catch (HibernateException ex){
                ex.printStackTrace();
            }
        }
        return equipamento;
    }
    
    public List listEquipamentoBean(){
        session = FabricaMySQLDAO.getSession();
        Query consulta = null;
        try{
            consulta = session.createQuery("from EquipamentoBean");
        }catch (HibernateException ex){
            ex.printStackTrace();            
        }
        return consulta.list();
    }
    
    public boolean updateEquipamentoBean(EquipamentoBean equipamento){
        boolean sucesso = false;
        if(equipamento != null){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.update(equipamento);
                tx.commit();
                sucesso = true;                
            }catch(HibernateException ex){
                ex.printStackTrace();
                tx.rollback();
            }
        }
        return sucesso;    
    }
    public boolean saveEquipamentoBean(EquipamentoBean equipamento){
        boolean sucesso = false;
        if(equipamento != null){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(equipamento);
                tx.commit();
                sucesso = true;                
            }catch(HibernateException ex){
                ex.printStackTrace();
                tx.rollback();
            }
        }
        return sucesso;            
    }
    public boolean deleteEquipamentoBean(EquipamentoBean equipamento){
        boolean sucesso = false;
        if(equipamento != null){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.delete(equipamento);
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