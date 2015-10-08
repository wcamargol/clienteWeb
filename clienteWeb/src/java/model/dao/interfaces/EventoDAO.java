package model.dao.interfaces;

import model.beans.EventoBean;
import model.beans.EventoIdBean;

public interface EventoDAO {
    public EventoBean recuperar(EventoIdBean id);
    public boolean atualizar(EventoBean evento);
    public boolean salvar(EventoBean evento);
    public boolean apagar(EventoBean evento);
}
