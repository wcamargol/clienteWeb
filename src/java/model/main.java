package model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import model.beans.AmbienteBean;
import model.beans.EquipamentoBean;
import model.beans.OperacaoBean;
import model.dao.AmbienteMySQLDAO;
import model.dao.EquipamentoMySQLDAO;
import model.dao.FabricaSessoes;
import model.dao.OperacaoMySQLDAO;

public class main {
    public static void main(String args[]) throws NoSuchAlgorithmException{
        EquipamentoMySQLDAO equipamentoMySQLDAO = new EquipamentoMySQLDAO();
        EquipamentoBean e = equipamentoMySQLDAO.getEquipamentoBean("01");
    }       
}
