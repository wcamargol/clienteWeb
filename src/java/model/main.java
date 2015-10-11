package model;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import model.beans.EquipamentoBean;
import model.beans.MoradorBean;
import model.beans.OperacaoBean;
import model.beans.OperacaoIdBean;
import model.dao.EquipamentoMySQLDAO;
import model.dao.FabricaMySQLDAO;
import model.dao.MoradorMySQLDAO;
import model.dao.OperacaoMySQLDAO;

public class main {
    public static void main(String args[]) throws NoSuchAlgorithmException{
       EquipamentoMySQLDAO equipamento = FabricaMySQLDAO.getEquipamentoMySQLDAO();
       EquipamentoBean equipamentoBean = equipamento.getEquipamentoBean("01");
       
       MoradorMySQLDAO morador = FabricaMySQLDAO.getMoradorMySQLDAO();
       MoradorBean moradorBean = morador.getMoradorBean("walter12");
       OperacaoIdBean operacaoIdBean = new OperacaoIdBean();
                operacaoIdBean.setEquipamento(equipamentoBean);
                operacaoIdBean.setMorador(moradorBean);
                
                OperacaoBean operacaoBean = new OperacaoBean();
                operacaoBean.setId(operacaoIdBean);
                operacaoBean.setDataOperacao(new Date());
                operacaoBean.setHoraOperacao(new Date());
                operacaoBean.setDescricaoOperacao("Ligado");
                
                OperacaoMySQLDAO operacaoMySQLDAO = FabricaMySQLDAO.getOperacaoMySQLDAO();
                operacaoMySQLDAO.saveOperacaoBean(operacaoBean);
    }       
}
