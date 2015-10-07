package model.dao.interfaces;

import model.beans.Operacao;
import model.beans.OperacaoId;

public interface OperacaoDAO {
    public Operacao recuperar(OperacaoId id);
    public boolean atualizar(Operacao operacao);
    public boolean salvar(Operacao operacao);
    public boolean apagar(Operacao operacao);
}
