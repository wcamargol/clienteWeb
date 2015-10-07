/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import model.beans.Evento;

/**
 *
 * @author lubuntu
 */
public interface EventoDAO {
    public Evento recuperar(Evento evento);
    public boolean atualizar(Evento evento);
    public boolean salvar(Evento evento);
    public boolean apagar(Evento evento);
}
