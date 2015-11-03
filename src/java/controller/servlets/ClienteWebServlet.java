package controller.servlets;

import model.TrataComando;
import java.io.IOException;
import java.net.DatagramSocket;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.AtuadorBean;
import model.beans.MoradorBean;
import model.dao.AtuadorMySQLDAO;

public class ClienteWebServlet extends HttpServlet {

    private DatagramSocket socket;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.security.NoSuchAlgorithmException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessao = request.getSession(true);    
        String comando = request.getParameter("comando");
        if (comando != null){
            sessao.setAttribute("comando",comando);
        }
        comando = (String) sessao.getAttribute("comando");
        AtuadorMySQLDAO atuadorMySQLDAO = new AtuadorMySQLDAO();
        AtuadorBean atuadorBean = atuadorMySQLDAO.getAtuadorBean(comando.substring(4, 6)); 
        MoradorBean moradorBean = (MoradorBean) sessao.getAttribute("morador");
        Boolean logado = (Boolean) sessao.getAttribute("logado");        
        
        if (logado != null && logado){ 
            if ((System.currentTimeMillis() - (long) sessao.getAttribute("horaLogin"))/1000 > 60) {
                logado = !logado;
            }            
        }
        
        if (atuadorBean.getRequerLogin() && (logado==null?true:!logado)){                
            RequestDispatcher rd = request.getRequestDispatcher("homeLogin.jsp");
            rd.forward(request,response);
        }else{
            new TrataComando().executa(comando, moradorBean);
            RequestDispatcher rd = request.getRequestDispatcher("AtualizaServlet");
            rd.forward(request,response);
        }    
    }
    
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }        
    }
}