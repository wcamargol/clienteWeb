package model.beans;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Operacao  implements java.io.Serializable {
    
    @EmbeddedId
    private OperacaoId id;
    @Temporal(TemporalType.DATE)
    private Date diaOperacao;
    @Temporal(TemporalType.TIME)
    private Date horaOpercao;

    public OperacaoId getId() {
        return id;
    }

    public void setId(OperacaoId id) {
        this.id = id;
    }

    public Date getDiaOperacao() {
        return diaOperacao;
    }

    public void setDiaOperacao(Date diaOperacao) {
        this.diaOperacao = diaOperacao;
    }

    public Date getHoraOpercao() {
        return horaOpercao;
    }

    public void setHoraOpercao(Date horaOpercao) {
        this.horaOpercao = horaOpercao;
    }    
}