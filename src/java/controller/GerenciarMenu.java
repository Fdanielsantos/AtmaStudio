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
import model.Menu;
import model.MenuDAO;


/**
 *
 * @author flavio daniel
 */
public class GerenciarMenu extends HttpServlet {

    

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
        String idMenu = request.getParameter("idMenu");
        String acao = request.getParameter("acao");
        
        Menu m = new Menu();
        
        try{
            MenuDAO mDAO = new MenuDAO();
            if(GerenciarLogin.verificarPermissao(request, response)){ 
            if(acao.equals("listar")){
                ArrayList<Menu> listaMenus = mDAO.getLista();
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_menu.jsp");
                request.setAttribute("listaMenu", listaMenus);
                disp.forward(request, response);
            }
            }else{
                  mensagem = "Acesso Negado!";
              }
            if(acao.equals("alterar")){
              if(GerenciarLogin.verificarPermissao(request, response)){ 
                m = mDAO.getCarregaPorID(Integer.parseInt(idMenu));
                if(m.getIdMenu()>0){
                     RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu.jsp");
                     request.setAttribute("m", m);
                     disp.forward(request, response);
                }else{
                    mensagem = "Menu não encontrado";
                }
              }else{
                  mensagem = "Acesso Negado!";
              }
            }
            if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){ 
                    m.setIdMenu(Integer.parseInt(idMenu));
                    if(mDAO.desativar(m)){
                        mensagem = "Menu desativado com sucesso!";
                    }else{
                        mensagem = "Erro ao desativar o menu";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){ 
                    m.setIdMenu(Integer.parseInt(idMenu));
                    if(mDAO.deletar(m)){
                        mensagem = "Menu deletado com sucesso!";
                    }else{
                        mensagem = "Erro ao deletar o menu";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            
        
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de menus";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_menu.do?acao=listar';");
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
        String mensagem = "";
        
        String idMenu = request.getParameter("idMenu");
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String icone = request.getParameter("icone");
        String exibir = request.getParameter("exibir");
        String status = request.getParameter("status");
        
        Menu m = new Menu();
        
        try {
            MenuDAO mDAO = new MenuDAO();
            if(!idMenu.isEmpty()){
                m.setIdMenu(Integer.parseInt(idMenu));
            }
            if(nome.equals("")||(nome.isEmpty())||link.equals("")||(link.isEmpty())
                    ||exibir.equals("")||(exibir.isEmpty())||status.equals("")||(status.isEmpty())){
                mensagem = "Preencha os campos obrigatórios!";
                
            }else{
                m.setNome(nome);
                m.setLink(link);
                m.setIcone(icone);
                m.setExibir(Integer.parseInt(exibir));
                m.setStatus(Integer.parseInt(status));
                if(mDAO.gravar(m)){
                    mensagem = "Gravado com sucesso!";
                }
            }
   
        } catch (Exception ex) {
            out.print(ex);
            mensagem = "Erro ao gravar o menu no banco de dados";

        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_menu.do?acao=listar';");
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
