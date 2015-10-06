/*
 * Código adaptado do material disponibilizado na disciplina
 * DSAW, unidade 7.
 */
package model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lubuntu
 */
@Entity
public class Morador implements Serializable {
    @Id
    private String login;    
    private String nome;
    
    @Temporal(TemporalType.DATE)
    private String dataNascimento;
    
    @JoinColumn(name = "login", referencedColumnName = "login", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    } 
    
    public String getStringDataNascimento() {
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormatada.format(dataNascimento);
    }
}
