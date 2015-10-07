/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import model.beans.Equipamento;

/**
 *
 * @author lubuntu
 */
public interface EquipamentoDAO {
    public Equipamento recuperar(Equipamento codigoEquipamento);
    public boolean atualizar(Equipamento equipamento);
    public boolean salvar(Equipamento equipamento);
    public boolean apagar(Equipamento equipamento);
    
}
