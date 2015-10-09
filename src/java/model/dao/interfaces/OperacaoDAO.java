package model.dao.interfaces;

import model.beans.OperacaoBean;
import model.beans.OperacaoIdBean;

public interface OperacaoDAO {
    public OperacaoBean recuperar(OperacaoIdBean id);
    public boolean atualizar(OperacaoBean operacao);
    public boolean salvar(OperacaoBean operacao);
    public boolean apagar(OperacaoBean operacao);
}
