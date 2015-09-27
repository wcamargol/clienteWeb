/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lubuntu
 */
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import model.Cliente;
import model.PortaSerial;

public class Main {
    
    static InputStream input;
    static OutputStream output;

    public static void main(String[] args) throws Exception{
        Cliente cliente = new Cliente();
        String comando ="SL01 - L2",
            operacao = null,
            dispositivo = null;
        if(cliente.inicializar()){
            //if (comando != null){
                System.out.println(comando.substring(0, 4));
                System.out.println(comando.substring(7));
            //}
        }
    }
                    
}