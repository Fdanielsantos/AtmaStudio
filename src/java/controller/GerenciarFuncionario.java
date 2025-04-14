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
import model.Funcionario;
import model.FuncionarioDAO;
import model.Usuario;
/**
 *
 * @author flavio daniel
 */
public class GerenciarFuncionario extends HttpServlet {

    

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
        String idFuncionario = request.getParameter("idFuncionario");
        
        Funcionario f = new Funcionario();
        
        try{
            FuncionarioDAO fDAO = new FuncionarioDAO();
                
                if(acao.equals("listar")){
                    if(GerenciarLogin.verificarPermissao(request, response)){
                        ArrayList<Funcionario> listaFuncionarios = fDAO.getLista();
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_funcionario.jsp");
                        request.setAttribute("listaFuncionario", listaFuncionarios);
                        disp.forward(request, response);
                    
                    }else{
                            mensagem = "Acesso Negado!";
                    }
                }
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    f = fDAO.getCarregaPorID(Integer.parseInt(idFuncionario));
                    if(f.getIdFuncionario()>0){
                         RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_funcionario.jsp");
                         request.setAttribute("f", f);
                         disp.forward(request, response);
                    }
                    }else{
                    mensagem = "Acesso Negado!";
                    }
            }
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    f.setIdFuncionario(Integer.parseInt(idFuncionario));
                    if(fDAO.deletar(f)){
                        mensagem = "Funcionário deletado com sucesso!";
                    }else{
                        mensagem = "Erro ao deletar o funcionário";
                    } 
                    }else{
                    mensagem = "Acesso Negado!";
            }
            }
            

        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de funcionários";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_funcionario.do?acao=listar';");
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
        String idFuncionario = request.getParameter("idFuncionario");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String idUsuario = request.getParameter("idUsuario");
        
        String mensagem = "";
        
        Funcionario f = new Funcionario();
        if(!idFuncionario.isEmpty()){
            f.setIdFuncionario(Integer.parseInt(idFuncionario));
        }
        if(nome.equals("")||email.equals("")||telefone.equals("")||endereco.equals("")||idUsuario.equals("")){
            mensagem = "Preencha os campos obrigatórios!";
        }else{
            f.setNome(nome);
            f.setEmail(email);
            f.setTelefone(telefone);
            f.setEndereco(endereco);
            Usuario u = new Usuario();
            u.setIdUsuario(Integer.parseInt(idUsuario));
            f.setUsuario(u);
            try{
                FuncionarioDAO fDAO = new FuncionarioDAO();
                if(fDAO.gravar(f)){
                    mensagem = "Gravado com sucesso!";
                }else{
                    mensagem = "Erro ao gravar o funcionário no banco de dados";
                }
            }catch(Exception e){
            out.print(e);
            mensagem = "Houve uma falha na comunicação com o banco de dados";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_funcionario.do?acao=listar';");
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
