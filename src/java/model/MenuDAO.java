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
public class MenuDAO extends DataBaseDAO{
    
    public MenuDAO() throws Exception{}
    
    public ArrayList<Menu> getLista() throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String SQL = "SELECT * FROM menu";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("idMenu"));
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setIcone(rs.getString("icone"));
            m.setExibir(rs.getInt("exibir"));
            m.setStatus(rs.getInt("status"));
            lista.add(m);
        }
        this.desconectar();
        return lista;
    
    }
    public boolean gravar (Menu m){
    
        try{
          String sql;
          this.conectar();
          if(m.getIdMenu()==0){
              sql = "INSERT INTO menu (nome,link,icone,exibir,status) VALUES (?,?,?,?,?)";
          }else{
              sql = "UPDATE menu SET nome=?, link=?, icone=?, exibir=?, status=? WHERE idMenu=?";
          }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, m.getNome());
            pstm.setString(2, m.getLink());
            pstm.setString(3, m.getIcone());
            pstm.setInt(4, m.getExibir());
            pstm.setInt(5, m.getStatus());
            if(m.getIdMenu()>0){
                pstm.setInt(6,m.getIdMenu());
            }
            pstm.execute();
            this.desconectar();
            return true;            
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    
    }
    
    public Menu getCarregaPorID(int idMenu) throws Exception{
        Menu m = new Menu();
        String sql = "SELECT * FROM menu WHERE idMenu=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idMenu);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            m.setIdMenu(idMenu);
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setIcone(rs.getString("icone"));
            m.setExibir(rs.getInt("exibir"));
            m.setStatus(rs.getInt("status"));
        }
        this.desconectar();
        return m;
    
    }
    
    public boolean desativar(Menu m){
        
        try{
            this.conectar();
            String SQL = "UPDATE menu SET status=2 WHERE idMenu=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, m.getIdMenu());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    public boolean deletar(Menu m){
        
        try{
            this.conectar();
            String SQL = "DELETE FROM menu WHERE idMenu=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, m.getIdMenu());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
}
