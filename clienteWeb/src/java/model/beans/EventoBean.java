package model.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EventoBean  implements Serializable {

    @EmbeddedId
    private EventoIdBean id;
    @Temporal(TemporalType.DATE)
    private Date diaEvento;
    @Temporal(TemporalType.TIME)
    private Date horaEvento;

    public EventoIdBean getId() {
        return id;
    }

    public void setId(EventoIdBean id) {
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