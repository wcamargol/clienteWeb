/*
 * Código adaptado do material disponibilizado na disciplina
 * DSAW, unidade 7.
 */
package model.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author lubuntu
 */
@Entity
//@Table(name = "Usuario")
public class Usuario implements Serializable {
    @Id
    //@Column(name = "login")
    private String login;
    //@Column(name="senha")
    private String senha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Morador morador;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }
      
}
