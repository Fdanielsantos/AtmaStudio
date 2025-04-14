/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author flavio daniel
 */
public class ServicoDAO extends DataBaseDAO {
    
    public ServicoDAO()throws Exception{}
    
    public ArrayList<Servico> getLista() throws Exception{
        
        ArrayList<Servico> lista = new ArrayList<Servico>();
        String SQL = "SELECT * FROM servico";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()){
            Servico s = new Servico();
            s.setIdServico(rs.getInt("idServico"));
            s.setNome(rs.getString("nome"));
            s.setDescricao(rs.getString("descricao"));
            s.setPreco(rs.getDouble("preco"));
            s.setDuracao(rs.getString("duracao"));
            s.setStatus(rs.getInt("status"));
            lista.add(s);
        }
        this.desconectar();
        return lista;    
    }
    
    public boolean gravar (Servico s){
    
        try{
          String sql;
          this.conectar();
          if(s.getIdServico()==0){
              sql = "INSERT INTO servico (nome,descricao,preco,duracao,status) VALUES (?,?,?,?,?)";
          }else{
              sql = "UPDATE servico SET nome=?, descricao=?, preco=?, duracao=?, status=? WHERE idServico=?";
          }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, s.getNome());
            pstm.setString(2, s.getDescricao());
            pstm.setDouble(3, s.getPreco());
            pstm.setString(4, s.getDuracao());
            pstm.setInt(5, s.getStatus());
            if(s.getIdServico()>0){
                pstm.setInt(6,s.getIdServico());
            }
            pstm.execute();
            this.desconectar();
            return true;            
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Servico getCarregaPorID(int idServico) throws Exception{
        Servico s = new Servico();
        String sql = "SELECT * FROM servico WHERE idServico=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idServico);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            s.setIdServico(idServico);
            s.setNome(rs.getString("nome"));
            s.setDescricao(rs.getString("descricao"));
            s.setPreco(rs.getDouble("preco"));
            s.setDuracao(rs.getString("duracao"));
            s.setStatus(rs.getInt("status"));
        }
        this.desconectar();
        return s;
    
    }
    
    public boolean desativar(Servico s){
        
        try{
            this.conectar();
            String SQL = "UPDATE servico SET status=2 WHERE idServico=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, s.getIdServico());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    public boolean ativar(Servico s){
        
        try{
            this.conectar();
            String SQL = "UPDATE servico SET status=1 WHERE idServico=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, s.getIdServico());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
     public boolean deletar(Servico s){
        
        try{
            this.conectar();
            String SQL = "DELETE FROM servico WHERE idServico=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, s.getIdServico());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
}
