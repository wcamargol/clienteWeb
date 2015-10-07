/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.beans.FabricaConexoes;
import model.dao.interfaces.AlarmeDAO;
import model.dao.interfaces.EquipamentoDAO;

/**
 *
 * @author lubuntu
 */
public class main {
    public static void main(String args[]){
        AlarmeDAO alarmeDAO = FabricaConexoes.getAlarmeDAO();
        EquipamentoDAO equipamentoDAO = FabricaConexoes.getEquipamentoDAO();
    }
    
}
