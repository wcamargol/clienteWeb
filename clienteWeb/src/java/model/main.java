package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.beans.Alarme;
import model.beans.Equipamento;
import model.beans.Evento;
import model.beans.EventoId;
import model.dao.FabricaMySQLDAO;
import model.dao.interfaces.AlarmeDAO;
import model.dao.interfaces.EquipamentoDAO;
import model.dao.interfaces.EventoDAO;

public class main {
    public static void main(String args[]){
        AlarmeDAO alarmeDAO = FabricaMySQLDAO.getAlarmeDAO();
        EquipamentoDAO equipamentoDAO = FabricaMySQLDAO.getEquipamentoDAO();
        EventoDAO eventoDAO = FabricaMySQLDAO.getEventoDAO();
        Alarme alarme = new Alarme();
        alarme.setCodigoAlarme("chma");
        alarme.setDescricaoAlarme("alarme de incendio");
        alarmeDAO.salvar(alarme);
        Equipamento equipamento = new Equipamento();
        equipamento.setCodigoEquipamento("SL01");
        equipamento.setEstado(1);
        equipamentoDAO.salvar(equipamento);
        EventoId id = new EventoId();
        id.setAlarme(alarme);
        id.setEquipamento(equipamento);
        Evento evento = new Evento();
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
