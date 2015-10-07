package model.dao.interfaces;

import model.beans.Morador;

public interface MoradorDAO {
    public Morador recuperar(Morador morador);
    public boolean atualizar(Morador morador);
    public boolean salvar(Morador morador);
    public boolean apagar(Morador morador);    
}
