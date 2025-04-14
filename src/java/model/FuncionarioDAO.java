/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author flavio daniel
 */
public class FuncionarioDAO extends DataBaseDAO{
    
    public FuncionarioDAO()throws Exception{}
    
    public ArrayList<Funcionario> getLista() throws Exception{
        
        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
        String sql = "SELECT f.*, u.login FROM funcionario f "
                + "INNER JOIN usuario u ON u.idUsuario = f.idUsuario ";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareCall(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Funcionario f = new Funcionario();
            f.setIdFuncionario(rs.getInt("f.idFuncionario"));
            f.setNome(rs.getString("f.nome"));
            f.setEmail(rs.getString("f.email"));
            f.setTelefone(rs.getString("f.telefone"));
            f.setEndereco(rs.getString("f.endereco"));
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("f.idUsuario"));
            u.setNome(rs.getString("u.login"));
            f.setUsuario(u);
            lista.add(f);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar (Funcionario f){
        
        try{
            String sql;
            this.conectar();
            if(f.getIdFuncionario()==0){
                sql = "INSERT INTO funcionario (nome,email,telefone,endereco,idUsuario) "
                        + "VALUES (?,?,?,?,?)";
            }else{
                sql = "UPDATE funcionario SET nome=?, email=?, telefone=?, endereco=?, idUsuario=? "
                        + "WHERE idFuncionario=?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, f.getNome());
            pstm.setString(2, f.getEmail());
            pstm.setString(3, f.getTelefone());
            pstm.setString(4, f.getEndereco());
            pstm.setInt(5, f.getUsuario().getIdUsuario());
            if(f.getIdFuncionario()>0)
                pstm.setInt(6, f.getIdFuncionario());
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Funcionario getCarregaPorID(int idFuncionario)throws Exception{
        Funcionario f = new Funcionario();
        String sql =  "SELECT f.*, u.login FROM funcionario f "
                + "INNER JOIN usuario u ON u.idUsuario = f.idUsuario "
                + "WHERE f.idFuncionario=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idFuncionario);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            f.setIdFuncionario(rs.getInt("f.idFuncionario"));
            f.setNome(rs.getString("f.nome"));
            f.setEmail(rs.getString("f.email"));
            f.setTelefone(rs.getString("f.telefone"));
            f.setEndereco(rs.getString("f.endereco"));
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("f.idUsuario"));
            u.setLogin(rs.getString("u.login"));
            f.setUsuario(u);
        }
        this.desconectar();
        return f;
    }
    public boolean deletar(Funcionario f){
        
        try{
            this.conectar();
            String SQL = "DELETE FROM funcionario WHERE idFuncionario=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, f.getIdFuncionario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
}
