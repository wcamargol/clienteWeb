package model;

/*
 * Classe que serializa um objeto para ser enviado transportado entre servidor
    e cliente
 */


import java.io.Serializable;


public class Mensagem implements Serializable {
	
    private static final long serialVersionUID = 1L;    
    private String codigo;
    private String mensagem;
    
    public Mensagem(){
        codigo = null;
        mensagem = null;
    }
    
    public Mensagem(String cod, String msg){
        codigo = cod;
        mensagem = msg;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setMsg(String str){
            mensagem = str;
    }

    public String getMsg(){
            return mensagem;
    }
}
