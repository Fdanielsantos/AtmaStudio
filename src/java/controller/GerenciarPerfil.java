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
import model.PerfilDAO;

/**
 *
 * @author flavio daniel
 */
public class GerenciarPerfil extends HttpServlet {

    

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
        String mensagem ="";
        
        String acao = request.getParameter("acao");
        String idPerfil = request.getParameter("idPerfil");
        Perfil p = new Perfil();
        
        try{
            PerfilDAO pDAO = new PerfilDAO();
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Perfil> listaPerfis = pDAO.getLista();
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_perfil.jsp");
                    request.setAttribute("listaPerfil", listaPerfis);
                    disp.forward(request, response);
                    
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    p = pDAO.getCarregaPorID(Integer.parseInt(idPerfil));
                    if(p.getIdPerfil()>0){
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_perfil.jsp");
                    request.setAttribute("p", p);
                    disp.forward(request, response);
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    p.setIdPerfil(Integer.parseInt(idPerfil));
                    if(pDAO.desativar(p)){
                        mensagem = "Perfil desativado com sucesso!";
                    }else{
                        mensagem = "Erro ao desativar o perfil";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    p.setIdPerfil(Integer.parseInt(idPerfil));
                    if(pDAO.ativar(p)){
                        mensagem = "Perfil ativado com sucesso!";
                    }else{
                        mensagem = "Erro ao ativar o perfil";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                } 
            }
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    p.setIdPerfil(Integer.parseInt(idPerfil));
                    if(pDAO.deletar(p)){
                        mensagem = "Perfil excluído com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir o perfil";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
                
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de perfis. Houve uma falha na comunicação com o banco de dados";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_perfil.do?acao=listar';");
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
        String mensagem="";

        String idPerfil = request.getParameter("idPerfil");
        String nome = request.getParameter("nome");
        String status = request.getParameter("status");
        
        
        
        Perfil p = new Perfil();
        try{
            PerfilDAO pDAO = new PerfilDAO();
            if(!idPerfil.isEmpty()){
               p.setIdPerfil(Integer.parseInt(idPerfil)); 
            }
        
            if(nome.equals("")||(nome.isEmpty())||status.equals("")||(status.isEmpty())){
                mensagem = "Preencha os campos obrigatórios!";                
            }else{
                p.setNome(nome);
                p.setStatus(Integer.parseInt(status));
                if(pDAO.gravar(p)){
                    mensagem = "Gravado com sucesso!";
                }else{
                    mensagem = "Falha ao gravar o perfil no banco de dados";
                }
            }
            
        }catch(Exception e){
            out.print(e);
            mensagem = "Houve uma falha na comunicação com o banco de dados";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_perfil.do?acao=listar';");
        out.println("</script>");
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
