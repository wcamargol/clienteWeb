package controller.servlets;

import controller.Hash;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.MoradorBean;
import model.dao.FabricaMySQLDAO;
import model.dao.interfaces.MoradorDAO;

/**
 *
 * @author lubuntu
 */
@WebServlet(name = "ControleAcessoServlet", urlPatterns = {"/ControleAcessoServlet"})
public class ControleAcessoServlet extends HttpServlet {

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
        sessao.setMaxInactiveInterval(120);
        MoradorBean moradorBean = (MoradorBean) sessao.getAttribute("loginSSHouse");
        
        if(moradorBean == null){
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            if(login != null && !login.equals("") && senha != null && !senha.equals("")){
                MoradorDAO moradorDAO = FabricaMySQLDAO.getMoradorDAO();
                moradorBean = moradorDAO.recuperar(login);
                if (moradorBean.getSenha().equals(new Hash().getMD5(senha))){
                    sessao.setAttribute("loginSSHouse",moradorBean);                
                }
            }
        }
        
    }

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
