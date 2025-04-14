/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agendamento;
import model.AgendamentoDAO;
import model.Cliente;
import model.Horario;
import model.Usuario;
import model.Servico;



/**
 *
 * @author flavio daniel
 */
public class GerenciarAgendamento extends HttpServlet {

    
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
        String idAgendamento = request.getParameter("idAgendamento");
        Agendamento a = new Agendamento();
        
        
        try{
            AgendamentoDAO aDAO = new AgendamentoDAO();            
                if(acao.equals("listar")){
                    if(GerenciarLogin.verificarPermissao(request, response)){ 
                        ArrayList<Agendamento> listaAgendamentos = aDAO.getLista();
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_agendamento.jsp");
                        request.setAttribute("listaAgendamento", listaAgendamentos);
                        disp.forward(request, response);
                    
                    }else{
                            mensagem = "Acesso Negado";
                        }
                }
                
            if(acao.equals("alterar")){
                 if(GerenciarLogin.verificarPermissao(request, response)){ 
                    a = aDAO.getCarregaPorID(Integer.parseInt(idAgendamento));
                    if(a.getIdAgendamento()>0){
                         RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_agendamento.jsp");
                         request.setAttribute("a", a);
                         disp.forward(request, response);
                    }  
                }else{
                    mensagem = "Acesso Negado";
                }
            }
            if(acao.equals("cancelar")){
                 if(GerenciarLogin.verificarPermissao(request, response)){ 
                    a.setIdAgendamento(Integer.parseInt(idAgendamento));
                    if(aDAO.cancelar(a)){
                        mensagem = "Agendamento cancelado com sucesso!";
                    }else{
                        mensagem = "Erro ao cancelar o agendamento";
                    }               
            
                 }else{
                    mensagem = "Acesso Negado";
                }
            }
            
             if(acao.equals("concluir")){
                 if(GerenciarLogin.verificarPermissao(request, response)){ 
                    a.setIdAgendamento(Integer.parseInt(idAgendamento));
                    if(aDAO.concluir(a)){
                        mensagem = "Agendamento concluído com sucesso!";
                    }else{
                        mensagem = "Erro ao concluir o agendamento";
                    }               
            
                 }else{
                    mensagem = "Acesso Negado";
                }
             }
                 
             if(acao.equals("deletar")){
                 if(GerenciarLogin.verificarPermissao(request, response)){ 
                    a.setIdAgendamento(Integer.parseInt(idAgendamento));
                    if(aDAO.deletar(a)){
                        mensagem = "Agendamento excluído com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir o agendamento";
                    }               
            
                 }else{
                    mensagem = "Acesso Negado";
                }
             }

        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de agendamentos";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_agendamento.do?acao=listar';");
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
        String idAgendamento = request.getParameter("idAgendamento");
        String idServico = request.getParameter("idServico");
        String data = request.getParameter("data");
        String idHorario = request.getParameter("idHorario");
        String valor = request.getParameter("valor");
        String idCliente = request.getParameter("idCliente");
        String idUsuario = request.getParameter("idUsuario");
        String status = request.getParameter("status");
        
        String mensagem = "";
        
        try{
            
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Agendamento a = new Agendamento();
        if(!idAgendamento.isEmpty()){
            a.setIdAgendamento(Integer.parseInt(idAgendamento));
        }
        if(idServico.equals("")||data.equals("")||idHorario.equals("")||valor.equals("")||idCliente.equals("")||idUsuario.equals("")|| status.equals("")){
            mensagem = "Preencha os campos obrigatórios!";
        }else{
            Servico s = new Servico();
            s.setIdServico(Integer.parseInt(idServico));
            a.setServico(s);
            if(!data.isEmpty()){
                a.setData(df.parse(data));
            }
            Horario h = new Horario();
            h.setIdHorario(Integer.parseInt(idHorario));
            a.setHorario(h);        
            double novovalor=0;
                if(!valor.isEmpty()){
                    novovalor = Double.parseDouble(valor.replace(".", "").replace(",", "."));
                }
            a.setValor(novovalor);         
            Cliente c = new Cliente();
            c.setIdCliente(Integer.parseInt(idCliente));
            a.setCliente(c);
            Usuario u = new Usuario();
            u.setIdUsuario(Integer.parseInt(idUsuario));
            a.setUsuario(u);
            a.setStatus(Integer.parseInt(status));
                AgendamentoDAO aDAO = new AgendamentoDAO();
                
                    if(idAgendamento.isEmpty()){
                        if(!aDAO.VerificarDisponibilidade(Integer.parseInt(idUsuario), data, h.getIdHorario())){
                            if(aDAO.gravar(a)){
                                 mensagem = "Gravado com sucesso!";
                             }else{
                                 mensagem = "Erro ao gravar o agendamento no banco de dados";
                             } 
                            }else{
                    mensagem="Agendamento indisponível.";
                }
                     }else{
                        int Qtd=aDAO.VerificarQtd(Integer.parseInt(idUsuario), a, h.getIdHorario());
                        mensagem ="Qtd="+Qtd;
                         if(Qtd==0){
                             if(aDAO.alterar(a)){
                                 mensagem = "Alterado com sucesso!";
                            }else{
                                mensagem = "Erro ao gravar o agendamento no banco de dados.";
                            }
                             
                        }else{
                             Agendamento age = new Agendamento();
                             age=aDAO.VerificarAgendamento(Integer.parseInt(idUsuario), a, h.getIdHorario());
                             if(Qtd==1 && age.getIdAgendamento()==a.getIdAgendamento()){
                                 if(aDAO.alterar(a)){
                                    mensagem = "Alterado com sucesso!";
                                }else{
                                    mensagem = "Erro ao gravar o agendamento no banco de dados.";
                                }
                             }else{
                                 mensagem="O horário já esta sendo utilizado!";
                             }
                             
                         }
                     }                   
        }
 
            }catch(Exception e){
            out.print(e);
            mensagem = "Houve uma falha na comunicação com o banco de dados";
        }
            out.println("<script type='text/javascript'>");
            out.println("alert('"+mensagem+"');");
            out.println("location.href='gerenciar_agendamento.do?acao=listar';");
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
