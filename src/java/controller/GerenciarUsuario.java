/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.Usuario;
import model.UsuarioDAO;

/**
 *
 * @author flavio daniel
 */
public class GerenciarUsuario extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");
        String idUsuario = request.getParameter("idUsuario");
        
        Usuario u = new Usuario();
        
        try{
            UsuarioDAO uDAO = new UsuarioDAO();
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Usuario> listaUsuarios = uDAO.getLista();
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_usuario.jsp");
                    request.setAttribute("listaUsuario", listaUsuarios);
                    disp.forward(request, response);
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    u = uDAO.getCarregaPorID(Integer.parseInt(idUsuario));
                    if(u.getIdUsuario()>0){
                         RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_usuario.jsp");
                         request.setAttribute("u", u);
                         disp.forward(request, response);
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    u.setIdUsuario(Integer.parseInt(idUsuario));
                    if(uDAO.desativar(u)){
                        mensagem = "Usuario desativado com sucesso!";
                    }else{
                        mensagem = "Erro ao desativar o usuario";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    u.setIdUsuario(Integer.parseInt(idUsuario));
                    if(uDAO.ativar(u)){
                        mensagem = "Usuario ativado com sucesso!";
                    }else{
                        mensagem = "Erro ao ativar o usuario";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                } 
            }
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    u.setIdUsuario(Integer.parseInt(idUsuario));
                    if(uDAO.deletar(u)){
                        mensagem = "Usuario exluído com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir o usuario";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
 
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de usuários";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_usuario.do?acao=listar';");
        out.println("</script>");
    
    }

    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idPerfil = request.getParameter("idPerfil");
        
        String mensagem = "";
        
        Usuario u = new Usuario();
        if(!idUsuario.isEmpty()){
            u.setIdUsuario(Integer.parseInt(idUsuario));
        }
        if(nome.equals("")||login.equals("")||senha.equals("")||status.equals("")||idPerfil.equals("")){
            mensagem = "Preencha os campos obrigatórios!";
        }else{
            u.setNome(nome);
            u.setLogin(login);
            u.setSenha(senha);
            u.setStatus(Integer.parseInt(status));
            Perfil p = new Perfil();
            p.setIdPerfil(Integer.parseInt(idPerfil));
            u.setPerfil(p);
            try{
                UsuarioDAO uDAO = new UsuarioDAO();
                if(uDAO.gravar(u)){
                    mensagem = "Gravado com sucesso!";
                }else{
                    mensagem = "Erro ao gravar o usuario no banco de dados";
                }
            }catch(Exception e){
            out.print(e);
            mensagem = "Houve uma falha na comunicação com o banco de dados";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_usuario.do?acao=listar';");
        out.println("</script>");
        }
       

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
