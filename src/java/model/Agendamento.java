/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author flavio daniel
 */
public class Agendamento {
    private int idAgendamento;
    private Servico servico;
    private Date Data; 
    private Horario horario;
    private int status;
    private double valor;
    private Cliente cliente;
    private Usuario usuario;

    public Agendamento() {
    }

    public Agendamento(int idAgendamento, Servico servico, Date Data, Horario horario, int status, double valor, Cliente cliente,  Usuario usuario) {
        this.idAgendamento = idAgendamento;
        this.servico = servico;
        this.Data = Data;
        this.horario = horario;
        this.status = status;
        this.valor = valor;
        this.cliente = cliente;
        this.usuario = usuario;
    }

   

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Agendamento{" + "Data=" + Data + '}';
    }
    
}
