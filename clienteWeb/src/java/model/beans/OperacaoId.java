package model.beans;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OperacaoId  implements java.io.Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigoEquipamento")
    private Equipamento equipamento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login")
    private Morador morador;
    
    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }
}