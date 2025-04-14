/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author flavio daniel
 */
public class Horario {
    private int idHorario;
    private Date horario;

    public Horario() {
    }

    public Horario(int idHorario, Date horario) {
        this.idHorario = idHorario;
        this.horario = horario;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Horario{" + "horario=" + horario + '}';
    }
    
    
}
