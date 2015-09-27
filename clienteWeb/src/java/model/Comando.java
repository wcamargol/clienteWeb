/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lubuntu
 */
public class Comando {
   private String estado;

   
   public String getOperacao(String feedback){
       if (feedback.substring(7).equals("Desligado")){
           return "Ligar";
       }else return "Desligar";       
   }
   
   public String getCodigo(String feedback){
       if (feedback.substring(0,3).equals("Desligado")){
           return "Ligar";
       }else return "Desligar"; 
   }
}
