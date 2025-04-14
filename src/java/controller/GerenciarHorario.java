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
import model.Horario;
import model.HorarioDAO;
/**
 *
 * @author flavio daniel
 */
public class GerenciarHorario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        String idHorario = request.getParameter("idHorario");
        
        Horario h = new Horario();
        
        try{
            HorarioDAO hDAO = new HorarioDAO();            
                if(acao.equals("listar")){
                    if(GerenciarLogin.verificarPermissao(request, response)){
                        ArrayList<Horario> listaHorarios = hDAO.getLista();
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_horario.jsp");
                        request.setAttribute("listaHorario", listaHorarios);
                        disp.forward(request, response);
                    
                    }else{
                    mensagem = "Acesso Negado!";
                    }   
                }
                
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    h = hDAO.getCarregaPorId(Integer.parseInt(idHorario));
                    if(h.getIdHorario()>0){
                         RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_horario.jsp");
                         request.setAttribute("h", h);
                         disp.forward(request, response);
                    }
                    
                }else{
                    mensagem = "Acesso Negado!";
                }  
               
            }
             if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    h.setIdHorario(Integer.parseInt(idHorario));
                    if(hDAO.deletar(h)){
                        mensagem = "Horário excluído com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir o horário";
                    }               
                }else{
                    mensagem = "Acesso Negado!";
                }
                 
             }
             
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar a lista de horários";
        }
        
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_horario.do?acao=listar';");
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
        String idHorario = request.getParameter("idHorario");
        String horario = request.getParameter("horario");
        
        Horario h = new Horario();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            HorarioDAO hDAO = new HorarioDAO();
            if(!idHorario.isEmpty()){
                h.setIdHorario(Integer.parseInt(idHorario));
            }
            h.setHorario(sdf.parse(horario));
            
            if(horario.equals("")||(horario.isEmpty())){
                mensagem = "Preencha os campos obrigatórios!";
                
                }else{
                    if(hDAO.gravar(h)){
                        mensagem = "Gravado com sucesso";
                    }else{
                        mensagem = "Erro ao gravar o horário no banco de dados";
                    }
                }
       
   
        } catch (Exception ex) {
            out.print(ex);
            mensagem = "Houve uma falha na comunicação com o banco de dados. Erro ao executar a lista de horários";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='gerenciar_horario.do?acao=listar';");
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
