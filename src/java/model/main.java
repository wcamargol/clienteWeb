package model;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import model.beans.OperacaoBean;
import model.dao.FabricaSessoes;
import model.dao.OperacaoMySQLDAO;

public class main {
    public static void main(String args[]) throws NoSuchAlgorithmException{
        OperacaoBean operacaoBean = new OperacaoBean();

        OperacaoMySQLDAO operacaoMySQLDAO = new OperacaoMySQLDAO();
        List lis = operacaoMySQLDAO.listaOperacaoBean();
        Iterator<List> i = lis.iterator();
        while (i.hasNext()){
            OperacaoBean operacao = (OperacaoBean) i.next();
            String nome = operacao.getId().getMorador().getNome();
            nome = nome.substring(0, nome.indexOf(" ") + 1);
            System.out.println(nome);
        }
    }       
}
