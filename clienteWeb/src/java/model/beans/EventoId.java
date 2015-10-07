package model.beans;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EventoId  implements java.io.Serializable {
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigoEquipamento")
    private Equipamento equipamento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigoAlarme")
    private Alarme alarme;
    
    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Alarme getAlarme() {
        return alarme;
    }

    public void setAlarme(Alarme alarme) {
        this.alarme = alarme;
    }
}