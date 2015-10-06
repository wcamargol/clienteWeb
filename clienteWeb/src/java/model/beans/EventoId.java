package model.beans;
// Generated 06/10/2015 13:16:55 by Hibernate Tools 4.3.1

import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;




/**
 * EventoId generated by hbm2java
 */
@Embeddable
public class EventoId  implements java.io.Serializable {
    
    @ManyToOne
    private Equipamento equipamento;
    @ManyToOne
    private Alarme alarme;
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.getEquipamento());
        hash = 47 * hash + Objects.hashCode(this.getAlarme());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventoId other = (EventoId) obj;
        if (!Objects.equals(this.equipamento, other.equipamento)) {
            return false;
        }
        if (!Objects.equals(this.alarme, other.alarme)) {
            return false;
        }
        return true;
    }

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


