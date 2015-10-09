package controller.servlets;

import controller.Cliente;
import java.io.IOException;
import java.net.DatagramSocket;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClienteWebServlet extends HttpServlet {

    private DatagramSocket socket;
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
        
        String comando =null,
        retorno = null;
        comando = request.getParameter("comando");
        if (comando != null){            
            Cliente cliente = new Cliente();                
            retorno = cliente.enviaComando(comando);
            System.out.println(retorno);
            request.setAttribute("eqto", comando.subSequence(0, 4));
            request.setAttribute("estado", retorno.substring(7));
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request,response);
    }
}