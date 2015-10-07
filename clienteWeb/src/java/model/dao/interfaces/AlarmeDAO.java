/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import model.beans.Alarme;

public interface AlarmeDAO {
    public Alarme recuperar(Alarme codigoAlarme);
    public boolean atualizar(Alarme alarme);
    public boolean salvar(Alarme alarme);
    public boolean apagar(Alarme alarme);
}
