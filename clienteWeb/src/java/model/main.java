package model;

import controller.Hash;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.beans.AlarmeBean;
import model.beans.EquipamentoBean;
import model.beans.EventoBean;
import model.beans.EventoIdBean;
import model.beans.MoradorBean;
import model.dao.FabricaMySQLDAO;
import model.dao.interfaces.AlarmeDAO;
import model.dao.interfaces.EquipamentoDAO;
import model.dao.interfaces.EventoDAO;
import model.dao.interfaces.MoradorDAO;

public class main {
    public static void main(String args[]) throws NoSuchAlgorithmException{
        
        DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        formatadorData.setLenient(false); 
        MoradorDAO moradorDAO = FabricaMySQLDAO.getMoradorDAO();
        
        
        MoradorBean moradorBean = new MoradorBean();        
        moradorBean.setLogin("walter12");
        moradorBean.setNome("Walter Luiz de Camargo");
        try {
            Date dataNascimento;
            dataNascimento = formatadorData.parse("12/05/1978");            
            moradorBean.setDataNascimento(dataNascimento);
            moradorBean.setSenha("121578");
        } catch (NoSuchAlgorithmException | ParseException ex) {
            ex.printStackTrace();
        }
        //moradorDAO.salvar(moradorBean);
        MoradorBean m = moradorDAO.recuperar("walter12");
        
               
        System.out.println(m.getLogin());
        System.out.println(formatadorData.format(m.getDataNascimento()));
        System.out.println(m.getNome());
        if (new Hash().getMD5("121578").equals(m.getSenha())){
            System.out.println("senha confere");
        }
        
        
        
        /*AlarmeDAO alarmeDAO = FabricaMySQLDAO.getAlarmeDAO();
        EquipamentoDAO equipamentoDAO = FabricaMySQLDAO.getEquipamentoDAO();
        EventoDAO eventoDAO = FabricaMySQLDAO.getEventoDAO();
        AlarmeBean alarme = new AlarmeBean();
        alarme.setCodigoAlarme("chma");
        alarme.setDescricaoAlarme("alarme de incendio");
        alarmeDAO.salvar(alarme);
        EquipamentoBean equipamento = new EquipamentoBean();
        equipamento.setCodigoEquipamento("SL01");
        equipamento.setEstado(1);
        equipamentoDAO.salvar(equipamento);
        EventoIdBean id = new EventoIdBean();
        id.setAlarme(alarme);
        id.setEquipamento(equipamento);
        EventoBean evento = new EventoBean();
        evento.setId(id);

        DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        formatadorData.setLenient(false);
        formatadorHora.setLenient(false);
        Date horaAtual = new Date();
        Date dataAtual = new Date();
        formatadorData.format(dataAtual);
        formatadorHora.format(horaAtual);

            evento.setDiaEvento(dataAtual);
            evento.setHoraEvento(horaAtual);
            eventoDAO.salvar(evento);*/
        
    }
}
