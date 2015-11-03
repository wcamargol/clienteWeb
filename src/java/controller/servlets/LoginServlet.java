package controller.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Hash;
import model.beans.MoradorBean;
import model.dao.MoradorMySQLDAO;

@WebServlet(name = "LogServlet", urlPatterns = {"/LogServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessao = request.getSession(true);
        Boolean logado = (Boolean) sessao.getAttribute("logado");
        MoradorBean moradorBean = null;
        
        
        if((logado==null?true:!logado)){
            String login = null;
            String senha = null;
            if (!sessao.isNew()){
                login = request.getParameter("login");
                senha = request.getParameter("senha");
            }
            if (login != null && !login.equals("") && senha != null && !senha.equals("")){
                MoradorMySQLDAO moradorMySQLDAO = new MoradorMySQLDAO();
                moradorBean = moradorMySQLDAO.getMoradorBean(login);
                if (moradorBean != null && moradorBean.getSenha().equals(new Hash().getMD5(senha))){  
                    sessao.setAttribute("morador", moradorBean);
                    sessao.setAttribute("logado",true);
                    sessao.setAttribute("horaLogin",System.currentTimeMillis());
                    RequestDispatcher rd = request.getRequestDispatcher("ClienteWebServlet");
                    rd.forward(request,response);
                }else {
                    request.setAttribute("erro", "Login ou senha inválidos.");  
                }  
            }else if (!sessao.isNew()) {
                request.setAttribute("erro", "Preencha todos os campos");
            }
            RequestDispatcher rd = request.getRequestDispatcher("homeLogin.jsp");
            rd.forward(request, response);            
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
        } catch (NoSuchAlgorithmException ex) {}
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
        } catch (NoSuchAlgorithmException ex) {}
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
