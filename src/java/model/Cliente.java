package model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Cliente implements Callable{

    private Socket connSocket;
    private String comando;

    public Cliente(String comando){
        try {
            this.connSocket = new Socket("127.0.0.1", 12345);
            this.comando = comando;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public String enviaComando(){
        Scanner entrada = null;
        PrintStream saida = null;
        String respostaServidor = null;
        try {
            entrada = new Scanner(this.connSocket.getInputStream());
            saida = new PrintStream(this.connSocket.getOutputStream());
            saida.println(this.comando);
            saida.flush();
            while (entrada.hasNextLine()) {
                respostaServidor = entrada.nextLine();
            }
            entrada.close();
            saida.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return respostaServidor;
    }

    @Override
    public Object call() throws Exception{
        return enviaComando();
    }
}