package model;

import java.util.Date;
import model.beans.AmbienteBean;
import model.beans.AtuadorBean;
import model.beans.MoradorBean;
import model.beans.OperacaoBean;
import model.beans.OperacaoIdBean;
import model.dao.AmbienteMySQLDAO;
import model.dao.AtuadorMySQLDAO;
import model.dao.OperacaoMySQLDAO;

public class TrataComando {
    AtuadorMySQLDAO atuadorMySQLDAO;
    private AtuadorBean atuadorBean;
    
    public void executa(String comando, MoradorBean moradorBean){
        this.atuadorMySQLDAO = new AtuadorMySQLDAO();
        this.atuadorBean = atuadorMySQLDAO.getAtuadorBean(comando.substring(4, 6));
        String retorno = null, operacao = null;
        operacao = comando.substring(6,7);
        Cliente cliente = new Cliente(comando.substring(6));
        try {
            retorno = (String)cliente.call();
            if (operacao.equals("L")){
                operacao = "D";
            }else if (!operacao.equals("P")){
               operacao = "L";
            }
            this.atuadorBean.setComando(operacao);
            this.atuadorMySQLDAO.updateAtuadorBean(this.atuadorBean);
            if (this.atuadorBean.getRequerLogin()){
                salvaOperacao(operacao, comando, moradorBean);
            }
        } catch (Exception ex){ 
            ex.printStackTrace();
        }
    }
    
    private void salvaOperacao(String operacao, String comando, MoradorBean moradorBean){        
        AmbienteMySQLDAO ambienteMySQLDAO = new AmbienteMySQLDAO();
        AmbienteBean ambienteBean = ambienteMySQLDAO.getAmbienteBean(comando.substring(0, 4));
        OperacaoIdBean operacaoIdBean = new OperacaoIdBean();
        operacaoIdBean.setAtuador(this.atuadorBean);
        operacaoIdBean.setMorador(moradorBean);                
        OperacaoBean operacaoBean = new OperacaoBean();
        operacaoBean.setId(operacaoIdBean);
        operacaoBean.setDataOperacao(new Date());
        operacaoBean.setHoraOperacao(new Date());                
        String descricaoOperacao = ambienteBean.getDescricaoAmbiente()
            + " " + this.atuadorBean.getDescricaoAtuador();
        if (operacao.equals("L")){
            descricaoOperacao += " DESLIGADO";                    
        }else if (operacao.equals("D")){
            descricaoOperacao += " LIGADO";
        }else if (operacao.equals("P")){
            descricaoOperacao += " ACIONADO";
        }else{
            descricaoOperacao = "Comando não executado";
        }
        operacaoBean.setDescricaoOperacao(descricaoOperacao);                
        OperacaoMySQLDAO operacaoMySQLDAO = new OperacaoMySQLDAO();
        operacaoMySQLDAO.saveOperacaoBean(operacaoBean); 
    }
}
