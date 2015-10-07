package model.dao.interfaces;

import model.beans.Evento;
import model.beans.EventoId;

public interface EventoDAO {
    public Evento recuperar(EventoId id);
    public boolean atualizar(Evento evento);
    public boolean salvar(Evento evento);
    public boolean apagar(Evento evento);
}
