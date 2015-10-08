package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.beans.AlarmeBean;
import model.beans.EquipamentoBean;
import model.beans.EventoBean;
import model.beans.EventoIdBean;
import model.dao.FabricaMySQLDAO;
import model.dao.interfaces.AlarmeDAO;
import model.dao.interfaces.EquipamentoDAO;
import model.dao.interfaces.EventoDAO;

public class main {
    public static void main(String args[]){
        AlarmeDAO alarmeDAO = FabricaMySQLDAO.getAlarmeDAO();
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
        eventoDAO.salvar(evento);
    }
}
