package model;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import model.dao.EquipamentoMySQLDAO;
import model.dao.FabricaMySQLDAO;

public class main {
    public static void main(String args[]) throws NoSuchAlgorithmException{
       EquipamentoMySQLDAO equipamento = FabricaMySQLDAO.getEquipamentoMySQLDAO();
       List listaEquipamento = equipamento.listEquipamentoBean();
    }       
}
