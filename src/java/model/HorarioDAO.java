/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author flavio daniel
 */
public class HorarioDAO extends DataBaseDAO {
    
    public HorarioDAO() throws Exception{}
        
        public ArrayList<Horario> getLista() throws Exception{
           
            ArrayList<Horario> lista = new ArrayList<Horario>();
            String SQL = "SELECT * FROM horario";
            this.conectar();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while(rs.next()){
                Horario h = new Horario();
                h.setIdHorario(rs.getInt("idHorario"));
                h.setHorario(rs.getTime("horario"));
                lista.add(h);
            }
            this.desconectar();
            return lista;        
    }
     
        public boolean gravar(Horario h){
        
         try{
            String sql;
            this.conectar();
            if(h.getIdHorario() == 0){
                 sql = "INSERT into horario (horario) VALUES(?)";                
            }else{
                 sql = "UPDATE horario set horario=? WHERE idHorario=?";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setTime(1,new Time(h.getHorario().getTime()));
                     
            if(h.getIdHorario()>0) 
                pstm.setInt(2, h.getIdHorario());
            
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
        
        public boolean deletar(Horario h){
        try{
            this.conectar();
            String sql = "DELETE FROM horario WHERE idHorario= ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, h.getIdHorario());
            pstm.execute();        
            this.desconectar();
            return true;
         }catch(Exception e){
             System.out.println(e);
             return false;
        }
        
    }
    
    public Horario getCarregaPorId(int idHorario) throws Exception{
        Horario h = new Horario();
        String sql = "SELECT * FROM horario WHERE idHorario = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idHorario);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            h.setIdHorario(idHorario);
            h.setHorario(rs.getTime("horario"));    
        }
        this.desconectar();
        return h;
    }
}
