package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente{

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
        String feedback = null;
        try {
            entrada = new Scanner(this.connSocket.getInputStream());
            saida = new PrintStream(this.connSocket.getOutputStream());
            saida.println(str);
            saida.flush();
            while (entrada.hasNextLine()) {
                feedback = entrada.nextLine();
            } //fim do while
            entrada.close();
            saida.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(feedback);
        return feedback;
    } //fim do metodo esperaMsg
}