package model.dao.interfaces;

import model.beans.AlarmeBean;

public interface AlarmeDAO {
    public AlarmeBean recuperar(String codigoAlarme);
    public boolean atualizar(AlarmeBean alarme);
    public boolean salvar(AlarmeBean alarme);
    public boolean apagar(AlarmeBean alarme);
}
