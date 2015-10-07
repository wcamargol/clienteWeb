package model.dao;

import java.util.List;
import model.beans.Operacao;
import model.beans.OperacaoId;
import model.dao.interfaces.OperacaoDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OperacaoMySQLDAO implements OperacaoDAO {
    private Session session;
    
    public Operacao recuperar(OperacaoId id) {
        Operacao evento = null;
        if(id != null){
            session = FabricaMySQLDAO.getSession();
            Query q = session.createQuery("select a from Operacao a where a.id.morador.login = ? "
                + "and a.id.equipamento.codigoEquipamento = ? ");
            q.setString(0, id.getMorador().getLogin());
            q.setString(1, id.getEquipamento().getCodigoEquipamento());
            List l = q.list();
            if (!l.isEmpty()){
               evento = (Operacao)l.get(0);
            }
        }
        return evento;
    }
    
    public boolean atualizar(Operacao evento) {
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

    
    public boolean salvar(Operacao evento) {
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
    
    public boolean apagar(Operacao evento) {
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