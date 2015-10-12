package controller.servlets;

import controller.Cliente;
import java.io.IOException;
import java.net.DatagramSocket;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.AmbienteBean;
import model.beans.EquipamentoBean;
import model.beans.MoradorBean;
import model.beans.OperacaoBean;
import model.beans.OperacaoIdBean;
import model.dao.AmbienteMySQLDAO;
import model.dao.EquipamentoMySQLDAO;
import model.dao.OperacaoMySQLDAO;

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
        if (sessao.isNew()){
            RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
            rd.forward(request,response);
        }else{
            MoradorBean moradorBean = (MoradorBean) sessao.getAttribute("operadorSSHouse");
            String retorno = null, operacao = null,
            comando = request.getParameter("comando");           
            operacao = comando.substring(6,7);    
            if (comando != null){
                Cliente cliente = new Cliente();
                retorno = cliente.enviaComando(comando.substring(6));
                if (retorno.equals(operacao)){
                    salvaOperacao(operacao, comando, moradorBean);
                }          
            }
            RequestDispatcher rd = request.getRequestDispatcher("AtualizaServlet");
            rd.forward(request,response);
        }       
    }
    
    public void salvaOperacao(String operacao, String comando, MoradorBean moradorBean){        
        AmbienteMySQLDAO ambienteMySQLDAO = new AmbienteMySQLDAO();
        AmbienteBean ambienteBean = ambienteMySQLDAO.getAmbienteBean(comando.substring(0, 4));
        EquipamentoMySQLDAO equipamentoMySQLDAO = new EquipamentoMySQLDAO();
        EquipamentoBean equipamentoBean = equipamentoMySQLDAO.getEquipamentoBean(comando.substring(4, 6));
       
        equipamentoBean.setEstado(operacao);
        equipamentoMySQLDAO.updateEquipamentoBean(equipamentoBean);

        OperacaoIdBean operacaoIdBean = new OperacaoIdBean();
        operacaoIdBean.setEquipamento(equipamentoBean);
        operacaoIdBean.setMorador(moradorBean);                
        OperacaoBean operacaoBean = new OperacaoBean();
        operacaoBean.setId(operacaoIdBean);
        operacaoBean.setDataOperacao(new Date());
        operacaoBean.setHoraOperacao(new Date());                
        String descricaoOperacao = equipamentoBean.getDescricaoEquipamento()
            + " " + ambienteBean.getDescricaoAmbiente();
        if (operacao.equals("L")){
            descricaoOperacao += " LIGADO";                    
        }else if (operacao.equals("D")){
            descricaoOperacao += " DESLIGADO";
        } else{
            descricaoOperacao = "Comando não executado";
        }
        operacaoBean.setDescricaoOperacao(descricaoOperacao);                
        OperacaoMySQLDAO operacaoMySQLDAO = new OperacaoMySQLDAO();
        operacaoMySQLDAO.saveOperacaoBean(operacaoBean); 
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