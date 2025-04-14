/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author flavio daniel
 */
public class AgendamentoDAO extends DataBaseDAO{
    
    public AgendamentoDAO() throws Exception{}
    
    public ArrayList<Agendamento> getLista() throws Exception{
        
        ArrayList<Agendamento> lista = new ArrayList<Agendamento>();
            String sql = "SELECT a.*, h.horario, s.nome, c.nome, u.nome FROM agendamento a "
                        + "INNER JOIN horario h ON h.idHorario = a.idHorario " 
                        + "INNER JOIN servico s ON s.idServico = a.idServico "
                        + "INNER JOIN cliente c ON c.idCliente = a.idCliente "
                        + "INNER JOIN usuario u ON u.idUsuario = a.idUsuario";

        this.conectar();
        PreparedStatement pstm = conn.prepareCall(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Agendamento a = new Agendamento();
            a.setIdAgendamento(rs.getInt("a.idAgendamento")); 
            Servico s = new Servico();
            s.setIdServico(rs.getInt("a.idServico"));
            s.setNome(rs.getString("s.nome"));
            a.setData(rs.getDate("a.data"));
            Horario h = new Horario();
            h.setIdHorario(rs.getInt("a.idHorario"));
            h.setHorario(rs.getTime("h.horario"));
            a.setStatus(rs.getInt("a.status"));
            a.setValor(rs.getDouble("a.valor"));
            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("a.idCliente"));
            c.setNome(rs.getString("c.nome"));
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("a.idUsuario"));
            u.setNome(rs.getString("u.nome"));
            a.setHorario(h);
            a.setServico(s);
            a.setCliente(c);
            a.setUsuario(u);
            lista.add(a);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar (Agendamento a){
        
        try{
            String sql;
            this.conectar();           
                sql = "INSERT INTO agendamento (idServico,data,idHorario,status,valor,idCliente,idUsuario) "
                        + "VALUES (?,?,?,?,?,?,?)";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, a.getServico().getIdServico());
            pstm.setDate(2, new Date(a.getData().getTime()));
            pstm.setInt(3, a.getHorario().getIdHorario());
            pstm.setInt(4, a.getStatus());
            pstm.setDouble(5, a.getValor());           
            pstm.setInt(6, a.getCliente().getIdCliente());
            pstm.setInt(7, a.getUsuario().getIdUsuario());
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean alterar (Agendamento a){
         try{
            String sql;
            this.conectar(); 
                sql = "UPDATE agendamento SET  idServico=?, data=?, idHorario=?, status=?, valor=?, idCliente=?, idUsuario=? "
                        + "WHERE idAgendamento=? ";
          
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, a.getServico().getIdServico());
            pstm.setDate(2, new Date(a.getData().getTime()));
            pstm.setInt(3, a.getHorario().getIdHorario());
            pstm.setInt(4, a.getStatus());
            pstm.setDouble(5, a.getValor());           
            pstm.setInt(6, a.getCliente().getIdCliente());
            pstm.setInt(7, a.getUsuario().getIdUsuario());
            pstm.setInt(8, a.getIdAgendamento());
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
    public Agendamento getCarregaPorID(int idAgendamento)throws Exception{
        Agendamento a = new Agendamento();
        String sql =  "SELECT a.*, s.nome, h.horario, c.nome, u.nome FROM agendamento a "
                + "INNER JOIN servico s ON s.idServico =  a.idServico "
                + "INNER JOIN horario h ON h.idHorario = a.idHorario "
                + "INNER JOIN cliente c ON c.idCliente = a.idCliente "
                + "INNER JOIN usuario u ON u.idUsuario = a.idUsuario "
                + "WHERE a.idAgendamento=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idAgendamento);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            a.setIdAgendamento(rs.getInt("a.idAgendamento"));
            Servico s = new Servico();
            s.setIdServico(rs.getInt("a.idServico"));
            s.setNome(rs.getString("s.nome"));
            a.setData(rs.getDate("a.data"));
            Horario h = new Horario();
            h.setIdHorario(rs.getInt("a.idHorario"));
            h.setHorario(rs.getTime("h.horario"));
            a.setStatus(rs.getInt("a.status"));
            a.setValor(rs.getDouble("a.valor"));
            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("a.idCliente"));
            c.setNome(rs.getString("c.nome"));
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("a.idUsuario"));
            u.setNome(rs.getString("u.nome"));          
            a.setServico(s);
            a.setHorario(h);
            a.setCliente(c);
            a.setUsuario(u);
        }
        this.desconectar();
        return a;
    }
    public boolean cancelar(Agendamento a){
        
        try{
            this.conectar();
            String SQL = "DELETE FROM agendamento  WHERE idAgendamento=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, a.getIdAgendamento());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    public boolean concluir(Agendamento a){
        
        try{
            this.conectar();
            String SQL = "UPDATE agendamento SET status=2 WHERE idAgendamento=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, a.getIdAgendamento());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    public boolean deletar(Agendamento a){
        
        try{
            this.conectar();
            String SQL = "DELETE FROM agendamento  WHERE idAgendamento=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, a.getIdAgendamento());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    public boolean VerificarDisponibilidade (int idUsuario, String data, int idHorario){
        Agendamento a = new Agendamento();
        String sql = "SELECT a.* FROM agendamento a " 
                + "WHERE a.idUsuario=? AND a.data=? AND a.idHorario=?";
        boolean resposta;
        try{            
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idUsuario);
            pstm.setString(2, data);
            pstm.setInt(3, idHorario);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
               resposta=true;
            }else{
                resposta=false;
            }
            
            this.desconectar();
            return resposta;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    public int VerificarQtd (int idUsuario, Agendamento a, int idHorario){
       
        String sql = "SELECT COUNT(*) as qtd FROM agendamento a " 
                + "WHERE a.idUsuario=? AND a.data=? AND a.idHorario=? ";
        
        try{            
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idUsuario);
            pstm.setDate(2,  new Date(a.getData().getTime()));
            pstm.setInt(3, idHorario);
            System.out.println("Usuario"+ idUsuario);
            System.out.println("data"+ new Date(a.getData().getTime()));
            System.out.println("idHorario"+ idHorario);
            ResultSet rs = pstm.executeQuery();
            int Cont=0;
            if(rs.next()){
               Cont = rs.getInt("qtd");
            }
            
            this.desconectar();
            System.out.println("Qtd"+Cont);
            return Cont;
            
        }catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    public Agendamento VerificarAgendamento (int idUsuario, Agendamento a, int idHorario){
       
        String sql = "SELECT a.* FROM agendamento a " 
                + "WHERE a.idUsuario=? AND a.data=? AND a.idHorario=? ";
        
        Agendamento ag = new Agendamento();
        
        try{            
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idUsuario);
            pstm.setDate(2,  new Date(a.getData().getTime()));
            pstm.setInt(3, idHorario);
            System.out.println("Usuario"+ idUsuario);
            System.out.println("data"+ new Date(a.getData().getTime()));
            System.out.println("idHorario"+ idHorario);
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
               ag.setIdAgendamento(rs.getInt("a.idAgendamento"));
               Usuario u = new Usuario();
               u.setIdUsuario(rs.getInt("a.idUsuario"));
               ag.setUsuario(u);
               ag.setData(rs.getDate("a.data"));
               Horario h = new Horario();
               h.setIdHorario(rs.getInt("a.idHorario"));
               ag.setHorario(h);
            }
            
            this.desconectar();            
            return ag;
            
        }catch(Exception e){
            System.out.println(e);
            return ag;
        }
    }
}
