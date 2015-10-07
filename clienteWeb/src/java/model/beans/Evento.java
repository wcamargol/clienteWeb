package model.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento  implements Serializable {

    @EmbeddedId
    private EventoId id;
    @Temporal(TemporalType.DATE)
    private Date diaEvento;
    @Temporal(TemporalType.TIME)
    private Date horaEvento;

    public EventoId getId() {
        return id;
    }

    public void setId(EventoId id) {
        this.id = id;
    }

    public Date getDiaEvento() {
        return diaEvento;
    }

    public void setDiaEvento(Date diaEvento) {
        this.diaEvento = diaEvento;
    }

    public Date getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Date horaEvento) {
        this.horaEvento = horaEvento;
    }
}