package controller.servlets;

import model.Hash;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.MoradorBean;
import model.dao.AmbienteMySQLDAO;
import model.dao.EquipamentoMySQLDAO;
import model.dao.FabricaMySQLDAO;
import model.dao.MoradorMySQLDAO;

@WebServlet(name = "LoginAtualizaServlet", urlPatterns = {"/LoginAtualizaServlet"})
public class LoginAtualizaServlet extends HttpServlet {

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
        MoradorBean moradorBean = (MoradorBean) sessao.getAttribute("operadorSSHouse");
        
        AmbienteMySQLDAO ambienteMySQLDAO = FabricaMySQLDAO.getAmbienteMySQLDAO();
        List listaAmbientesBean = ambienteMySQLDAO.listAmbienteBean();
        sessao.setAttribute("listaAmbientes", listaAmbientesBean);
        
        EquipamentoMySQLDAO equipamentoMySQLDAO = FabricaMySQLDAO.getEquipamentoMySQLDAO();
        List listaEquipamentosBean = equipamentoMySQLDAO.listEquipamentoBean();
        sessao.setAttribute("listaEquipamentos", listaEquipamentosBean);
        
        if(moradorBean == null){
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            if (sessao.isNew()){
                request.setAttribute("erro", "Sua sessão expirou.");
            }else{
                if (login != null && !login.equals("") && senha != null && !senha.equals("")){
                    MoradorMySQLDAO moradorMySQLDAO = FabricaMySQLDAO.getMoradorMySQLDAO();
                    moradorBean = moradorMySQLDAO.getMoradorBean(login);
                    if (moradorBean != null && moradorBean.getSenha().equals(new Hash().getMD5(senha))){
                        sessao.setAttribute("operadorSSHouse",moradorBean);                        
                        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                        rd.forward(request,response);
                    }else {
                        request.setAttribute("erro", "Login ou senha inválidos.");                    
                    }  
                }else{
                    request.setAttribute("erro", "Todos os campos deve ser preenchidos.");
                }                
            }
            RequestDispatcher rd = request.getRequestDispatcher("homeLogin.jsp");
            rd.forward(request, response);
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request,response);
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
