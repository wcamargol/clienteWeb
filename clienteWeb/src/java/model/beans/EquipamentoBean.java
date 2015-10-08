package model.beans;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class EquipamentoBean  implements Serializable {
    
    @Id
    private String codigoEquipamento;
    private int estado;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="Evento", schema="SSHouse",
        joinColumns=@JoinColumn(name="codigoEquipamento"),
        inverseJoinColumns=@JoinColumn(name="codigoAlarme"))
    private Collection<AlarmeBean> alarme;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="Operacao", schema="SSHouse",
        joinColumns=@JoinColumn(name="codigoEquipamento"),
        inverseJoinColumns=@JoinColumn(name="login"))
    private Collection<MoradorBean> morador;

    public String getCodigoEquipamento() {
        return codigoEquipamento;
    }

    public void setCodigoEquipamento(String codigoEquipamento) {
        this.codigoEquipamento = codigoEquipamento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Collection<AlarmeBean> getAlarme() {
        return alarme;
    }

    public void setAlarme(Collection<AlarmeBean> alarme) {
        this.alarme = alarme;
    }

    public Collection<MoradorBean> getMorador() {
        return morador;
    }

    public void setMorador(Collection<MoradorBean> morador) {
        this.morador = morador;
    }
    
}