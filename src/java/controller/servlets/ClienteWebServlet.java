package controller.servlets;

import controller.Cliente;
import java.io.IOException;
import java.net.DatagramSocket;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.EquipamentoBean;
import model.dao.EquipamentoMySQLDAO;
import model.dao.FabricaMySQLDAO;

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
        String retorno = null, operacao = null,
            comando = request.getParameter("comando");           
            operacao = comando.substring(0, 1);
        if (comando != null){            
            Cliente cliente = new Cliente();
            retorno = cliente.enviaComando(comando.substring(2));
            if (retorno.equals(operacao)){
                EquipamentoMySQLDAO equipamentoMySQLDAO = FabricaMySQLDAO.getEquipamentoMySQLDAO();
                EquipamentoBean equipamentoBean = equipamentoMySQLDAO.getEquipamentoBean(comando.substring(0, 2));
                equipamentoBean.setEstado(inverteOperacao(operacao));
                equipamentoMySQLDAO.updateEquipamentoBean(equipamentoBean);
            }
            
        }
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request,response);
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
    
    private String inverteOperacao(String comando){
        if (comando.equals("L")){
            return "D";
        }else if (comando.equals("D")) {
            return "L";
        }else{
            return null;
        }
    }
    
}