package model;

/*
 * REDES DE COMPUTADORES
 * PRAT-4
 * Walter Luiz de Camargo - 434094
 * Itapevi
 * 
 * Programa Cliente de Chat
 * Solicita conexao a um Chat do programa Servidor, envia e recebe mensagens
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Cliente{

    private DatagramSocket socket; 
    private Mensagem mensagem;
    private String stringMsg;
    private Serializador sD;

    //Construtor Cliente
    public boolean inicializar(){
        this.sD = new Serializador();
        this.stringMsg = null;
        this.mensagem = new Mensagem();
        try {
            //conecta-se ao servidor
            this.socket = new DatagramSocket();
            return true;
        }//fim do try
        catch (SocketException e) {
            System.out.println("Problemas na conexão com o servidor");
            e.printStackTrace();
            return false;
        }//fim do catch
    } //fim do construtor Cliente
    
    public void enviar(Mensagem msg){
        byte[] data = new byte[1024];
        try {
            //serializa o objeto mensagem
            data = this.sD.serialize(msg);		
            DatagramPacket sendPacket = new DatagramPacket( data,
                    data.length, InetAddress.getLocalHost(), 5000 );
            //envia a mensagem efetivamente
            socket.send(sendPacket);			
        }//fim do try
        catch (UnknownHostException e) {
            System.out.println("Problemas durante o processamento da requisição.");
            e.printStackTrace();
        }//fim do catch
        catch (IOException e) {
            System.out.println("Problemas durante o processamento da requisição.");
            e.printStackTrace();
        }//fim do catch	
    }//fim do metodo enviarMsg
	
    //Espera por mensagens vindas do servidor		
    public String buscarRetorno(){
        String msgCliente = null;
        do{
            // recebe a mensagem e exibe o conteudo
            try{
                byte data[] = new byte[1024]; // configura o pacote
                DatagramPacket receivePacket = new DatagramPacket(
                                data, data.length );
                    //recebe a mensegem do servidor
                    socket.receive(receivePacket); // espera o pacote
                    mensagem = (Mensagem)this.sD.deserialize(receivePacket.getData());
                    return mensagem.getMsg();
            }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
            }catch (IOException ioe ){
                ioe.printStackTrace();
                return null;
            }//fim do catch
        }while (msgCliente == null);// fim do while        
    } // fim do metodo esperaMsg
}//fim da classe Cliente