package model.dao.interfaces;

import model.beans.MoradorBean;

public interface MoradorDAO {
    public MoradorBean recuperar(String login);
    public boolean atualizar(MoradorBean morador);
    public boolean salvar(MoradorBean morador);
    public boolean apagar(MoradorBean morador);    
}
