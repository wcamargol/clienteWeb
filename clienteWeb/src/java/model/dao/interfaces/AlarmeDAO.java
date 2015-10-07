package model.dao.interfaces;

import model.beans.Alarme;

public interface AlarmeDAO {
    public Alarme recuperar(Alarme codigoAlarme);
    public boolean atualizar(Alarme alarme);
    public boolean salvar(Alarme alarme);
    public boolean apagar(Alarme alarme);
}
