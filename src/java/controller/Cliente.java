package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable{

    private Socket connSocket;

    public Cliente(){
        try {
            this.connSocket = new Socket("127.0.0.1", 12345);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public String enviaComando(String str){
        Scanner entrada = null;
        PrintStream saida = null;
        String respostaServidor = null;
        try {
            entrada = new Scanner(this.connSocket.getInputStream());
            saida = new PrintStream(this.connSocket.getOutputStream());
            saida.println(str);
            saida.flush();
            while (entrada.hasNextLine()) {
                respostaServidor = entrada.nextLine();
            } //fim do while
            entrada.close();
            saida.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(respostaServidor);
        return respostaServidor;
    } //fim do metodo esperaMsg

    @Override
    public void run() {
        while(true){
            System.out.println(enviaComando("?"));            
        }
    }
}