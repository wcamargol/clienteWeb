package model.dao.interfaces;

import model.beans.EquipamentoBean;

public interface EquipamentoDAO {
    public EquipamentoBean recuperar(String codigoEquipamento);
    public boolean atualizar(EquipamentoBean equipamento);
    public boolean salvar(EquipamentoBean equipamento);
    public boolean apagar(EquipamentoBean equipamento);    
}
