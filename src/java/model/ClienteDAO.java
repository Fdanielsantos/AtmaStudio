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
public class ClienteDAO extends DataBaseDAO{
    
    public ClienteDAO()throws Exception{}
    
    public ArrayList<Cliente> getLista() throws Exception{
        
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String SQL = "SELECT * FROM cliente";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()){
            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("idCliente"));
            c.setNome(rs.getString("nome"));
            c.setTelefone(rs.getString("telefone"));
            c.setEmail(rs.getString("email"));
            lista.add(c);
        }
        this.desconectar();
        return lista;
    
    }
    public boolean gravar (Cliente c){
    
        try{
          String sql;
          this.conectar();
          if(c.getIdCliente()==0){
              sql = "INSERT INTO cliente (nome,telefone,email) VALUES (?,?,?)";
          }else{
              sql = "UPDATE cliente SET nome=?, telefone=?, email=? WHERE idCliente=?";
          }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, c.getNome());
            pstm.setString(2, c.getTelefone());
            pstm.setString(3, c.getEmail());
            if(c.getIdCliente()>0){
                pstm.setInt(4, c.getIdCliente());
            }
            pstm.execute();
            this.desconectar();
            return true;            
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    
    }
    
    public Cliente getCarregaPorID(int idCliente) throws Exception{
        Cliente c = new Cliente();
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idCliente);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            c.setIdCliente(idCliente);
            c.setNome(rs.getString("nome"));
            c.setTelefone(rs.getString("telefone"));
            c.setEmail(rs.getString("email"));
        }
        this.desconectar();
        return c;
    
    }
    
    public boolean deletar(Cliente c){
        
        try{
            this.conectar();
            String SQL = "DELETE FROM cliente WHERE idCliente=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, c.getIdCliente());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
}
