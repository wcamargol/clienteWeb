
package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.AmbienteBean;
import model.beans.AtuadorBean;
import model.dao.AmbienteMySQLDAO;
import model.dao.AtuadorMySQLDAO;
import model.dao.EventoMySQLDAO;
import model.dao.OperacaoMySQLDAO;

@WebServlet(name = "AtualizaServlet", urlPatterns = {"/AtualizaServlet"})
public class AtualizaServlet extends HttpServlet {

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
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //HttpSession sessao = request.getSession(true);
                
        //request.setAttribute("listaAmbientes", listaAmbientesBean);
        
        AtuadorMySQLDAO equipamentoMySQLDAO = new AtuadorMySQLDAO();
        List listaAtuadoresBean = equipamentoMySQLDAO.listAtuadorBean();
        request.setAttribute("listaAtuadores", listaAtuadoresBean);
        
        AmbienteMySQLDAO ambienteMySQLDAO = new AmbienteMySQLDAO();
        List listaAB = ambienteMySQLDAO.listAmbienteBean();
        ArrayList listaAmbientesBean = new ArrayList();
        for(Object o: listaAB){
            AmbienteBean a = (AmbienteBean) o;
            for(Object obj : listaAtuadoresBean){
                AtuadorBean e = (AtuadorBean) obj;
                if (a.getDescricaoAmbiente().equals(e.getAmbiente().getDescricaoAmbiente()) 
                    && e.getSensor()==null){
                    if (!listaAmbientesBean.contains(a))
                        listaAmbientesBean.add(o);
                }            
            }
        }
        List lista = listaAmbientesBean;
        request.setAttribute("listaAmbientes", listaAmbientesBean);
        
        
        OperacaoMySQLDAO operacaoMySQLDAO = new OperacaoMySQLDAO();
        List listaOperacoesBean = operacaoMySQLDAO.listaOperacaoBean();
        if (!listaOperacoesBean.isEmpty()){
            request.setAttribute("listaOperacoes", listaOperacoesBean);
        }else{
            request.setAttribute("listaOperacoes", null);
        }
        
        EventoMySQLDAO eventoMySQLDAO = new EventoMySQLDAO();
        List listaEventosBean = eventoMySQLDAO.listEventoBean();
        if (!listaEventosBean.isEmpty()){
            request.setAttribute("listaEventos", listaEventosBean);
        }else{
            request.setAttribute("listaEventos", null);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request,response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
