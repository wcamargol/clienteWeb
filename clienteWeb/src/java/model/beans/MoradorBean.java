package model.beans;


import controller.Hash;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MoradorBean  implements Serializable {
    
    @Id
    private String login;
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private String senha;
    @ManyToMany
    @JoinTable(name="Operacao", schema="SSHouse",
        joinColumns=@JoinColumn(name="login"),
        inverseJoinColumns=@JoinColumn(name="codigoEquipamento"))
    private Collection<EquipamentoBean> equipamento;
    
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException {
        this.senha = new Hash().getMD5(senha);
    }

    public Collection<EquipamentoBean> getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Collection<EquipamentoBean> equipamento) {
        this.equipamento = equipamento;
    }
    
    
}