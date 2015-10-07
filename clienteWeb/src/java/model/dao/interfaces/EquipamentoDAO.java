package model.dao.interfaces;

import model.beans.Equipamento;

public interface EquipamentoDAO {
    public Equipamento recuperar(Equipamento codigoEquipamento);
    public boolean atualizar(Equipamento equipamento);
    public boolean salvar(Equipamento equipamento);
    public boolean apagar(Equipamento equipamento);    
}
