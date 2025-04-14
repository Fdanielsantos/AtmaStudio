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
import model.Servico;
import model.ServicoDAO;

/**
 *
 * @author flavio daniel
 */
public class GerenciarServico extends HttpServlet {

    

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
        String idServico = request.getParameter("idServico");
        
        Servico s = new Servico();
        
        try{
            ServicoDAO sDAO = new ServicoDAO();
            if(acao.equals("listar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    ArrayList<Servico> listaServicos = sDAO.getLista();
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_servico.jsp");
                    request.setAttribute("listaServico", listaServicos);
                    disp.forward(request, response);
                    
                    }else{
                    mensagem = "Acesso Negado!";
                    }
            }
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                s = sDAO.getCarregaPorID(Integer.parseInt(idServico));
                if(s.getIdServico()>0){
                     RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_servico.jsp");
                     request.setAttribute("s", s);
                     disp.forward(request, response);
                }
                }else{
                    mensagem = "Acesso Negado!";
            }
            }
            if(acao.equals("desativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                s.setIdServico(Integer.parseInt(idServico));
                if(sDAO.desativar(s)){
                    mensagem = "Serviço desativado com sucesso!";
                }else{
                    mensagem = "Erro ao desativar o serviço";
                } 
                }else{
                    mensagem = "Acesso Negado!";
            }
            }
            if(acao.equals("ativar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                s.setIdServico(Integer.parseInt(idServico));
                if(sDAO.ativar(s)){
                    mensagem = "Serviço ativado com sucesso!";
                }else{
                    mensagem = "Erro ao ativar o serviço";
                } 
                }else{
                    mensagem = "Acesso Negado!";
            }
            }   
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                s.setIdServico(Integer.parseInt(idServico));
                if(sDAO.deletar(s)){
                    mensagem = "Serviço excluído com sucesso!";
                }else{
                    mensagem = "Erro ao exluir o serviço";
                } 
                }else{
                    mensagem = "Acesso Negado!";
            }
            }
            
        
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de serviços";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_servico.do?acao=listar';");
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
        
        String idServico = request.getParameter("idServico");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");
        String duracao = request.getParameter("duracao");
        String status = request.getParameter("status");
        
        Servico s = new Servico();
        
        try {
            ServicoDAO sDAO = new ServicoDAO();
            if(!idServico.isEmpty()){
                s.setIdServico(Integer.parseInt(idServico));
            }
            if(nome.equals("")||(nome.isEmpty())||duracao.equals("")||(duracao.isEmpty())||
                    status.equals("")||(status.isEmpty())){
                mensagem = "Preencha os campos obrigatórios!";
                
            }else{
                s.setNome(nome);
                s.setDescricao(descricao);
                double novopreco=0;
                if(!preco.isEmpty()){
                    novopreco = Double.parseDouble(preco.replace(".", "").replace(",", "."));
                }
                s.setPreco(novopreco);
                s.setDuracao(duracao);
                s.setStatus(Integer.parseInt(status));
     
                if(sDAO.gravar(s)){
                    mensagem = "Gravado com sucesso!";
                }else{
                    mensagem = "Erro ao gravar o serviço no banco de dados";
                }
            }
   
        } catch (Exception e) {
            out.print(e);
            mensagem = "Houve uma falha na comunicação com o banco de dados";

        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_servico.do?acao=listar';");
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
