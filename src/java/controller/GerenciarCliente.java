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
import model.Cliente;
import model.ClienteDAO;

/**
 *
 * @author flavio daniel
 */
public class GerenciarCliente extends HttpServlet {

    

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
        String idCliente = request.getParameter("idCliente");
        
        
        Cliente c = new Cliente();
        
        try{
            ClienteDAO cDAO = new ClienteDAO();
            
                if(acao.equals("listar")){
                    if(GerenciarLogin.verificarPermissao(request, response)){ 
                        ArrayList<Cliente> listaClientes = cDAO.getLista();
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_cliente.jsp");
                        request.setAttribute("listaCliente", listaClientes);
                        disp.forward(request, response);
                
                    }else{
                            mensagem = "Acesso Negado";
                        }
                }
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    c = cDAO.getCarregaPorID(Integer.parseInt(idCliente));
                    if(c.getIdCliente()>0){
                         RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_cliente.jsp");
                         request.setAttribute("c", c);
                         disp.forward(request, response);
                    }
                }else{
                    mensagem = "Acesso Negado";
                }
            
            }

            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    c.setIdCliente(Integer.parseInt(idCliente));
                    if(cDAO.deletar(c)){
                        mensagem = "Cliente excluído com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir o cliente";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                } 
            }
            
        
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de clientes";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_cliente.do?acao=listar';");
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
        
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        
        Cliente c = new Cliente();
        
        try {
            ClienteDAO cDAO = new ClienteDAO();
            if(!idCliente.isEmpty()){
                c.setIdCliente(Integer.parseInt(idCliente));
            }
            if(nome.equals("")||(nome.isEmpty())||telefone.equals("")||(telefone.isEmpty())
                    ||email.equals("")||(email.isEmpty())){
                mensagem = "Preencha os campos obrigatórios!";
                
            }else{
                c.setNome(nome);
                c.setTelefone(telefone);
                c.setEmail(email);
                if(cDAO.gravar(c)){
                    mensagem = "Gravado com sucesso!";
                }else{
                    mensagem = "Erro ao gravar o cliente no banco de dados";
                }
            }
   
        } catch (Exception ex) {
            out.print(ex);
            mensagem = "Houve uma falha na comunição com o banco de dados.  ";

        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_cliente.do?acao=listar';");
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
