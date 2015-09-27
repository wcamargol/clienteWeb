/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.net.DatagramSocket;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Mensagem;

/**
 *
 * @author lubuntu
 */
public class ClienteWebServlet extends HttpServlet {

    private DatagramSocket socket;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Cliente cliente = new Cliente();
        Mensagem mensagem = new Mensagem();
        String comando =null,
            retorno = null;
        if(cliente.inicializar()){
            comando = request.getParameter("comando");
            if (comando != null){
                mensagem.setMsg(comando);
                cliente.enviar(mensagem);
                retorno = cliente.buscarRetorno();
                request.setAttribute("eqto", retorno.subSequence(0, 4));
                request.setAttribute("estado", retorno.substring(7));
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request,response);
    }
}
