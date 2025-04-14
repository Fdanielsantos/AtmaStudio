
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width initial-scale, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
        <link rel="stylesheet" type="text/css" href="estilo/menu.css">
        <title>Cadastrar Agendamento</title>
                       <style>
            
           

            .thead-text{
                color: black;
            }
            .container-fluid{
                justify-content: flex-end;
                display: flex;
                width: 900px;
            }

        </style>
    </head>
    <body>

                     
           <%@include file="menu.jsp"%>
                
          <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <h1 class="text-center">Cadastrar Agendamento</h1>
        <br>
        <div class="container-fluid">
            
       
            <form action="gerenciar_agendamento.do" method="POST">
                <input type="hidden" name="idAgendamento" value="${a.idAgendamento}"/>
                <div class="row">
                    <div class="form-group col-sm-5">
                        <label for="servico" class="control-label">Serviço</label>
                        <select class="form-control" name="idServico" required="">
                            <option value="$">Selecione o Serviço</option>
                            <jsp:useBean class="model.ServicoDAO" id="servico"/>
                            <c:forEach var="s" items="${servico.lista}">
                                <c:if test="${s.status != 2}">
                                <option value="${s.idServico}"
                                        <c:if test="${s.idServico==a.servico.idServico}" >selected=""</c:if>  
                                >   ${s.nome}</option> 
                                </c:if>
                            </c:forEach>
                        </select>  
                </div>
                    <div class="form-group col-sm-5">
                        <label form="data" class="control-label">Data</label>
                        <input type="date" class="form-control" id="data" name="data" required=""  value="${a.data}" maxlength="45"/>                         
                    </div>
                    <div class="form-group col-sm-5">
                        <label for="horario" class="control-label">Horário</label>
                        <select class="form-control" name="idHorario" required="">
                            <option value="$">Selecione o Horario</option>
                            <jsp:useBean class="model.HorarioDAO" id="horario"/>
                            <c:forEach var="h" items="${horario.lista}">
                                <option value="${h.idHorario}"
                                        <c:if test="${h.idHorario==a.horario.idHorario}" >selected=""</c:if>  
                                >   ${h.horario}</option> 
                            </c:forEach>
                        </select>  
                </div>
                   
                    <div class="form-group col-sm-5">
                        <label for="valor" class="control-label">Valor</label>
                        <input type="text" class="form-control" id="valor" name="valor" required="" value="<fmt:formatNumber pattern="#,##0.00" value="${a.valor}"/>" maxlength="45"/> 
                        
                    </div>
                        
                    <div class="form-group col-sm-5">
                        <label for="cliente" class="control-label">Cliente</label>
                        <select class="form-control" name="idCliente" required="">
                            <option value="$">Selecione o Cliente</option>
                            <jsp:useBean class="model.ClienteDAO" id="cliente"/>
                            <c:forEach var="c" items="${cliente.lista}">
                                <option value="${c.idCliente}"
                                        <c:if test="${c.idCliente==a.cliente.idCliente}" >selected=""</c:if>  
                                >   ${c.nome}</option> 
                            </c:forEach>
                        </select>  
                </div>
                            <div class="form-group col-sm-5">
                        <label for="usuario" class="control-label">Funcionário</label>
                        <select class="form-control" name="idUsuario" required="">
                            <option value="$">Selecione o Funcionário</option>
                            <jsp:useBean class="model.UsuarioDAO" id="usuario"/>
                            <c:forEach var="u" items="${usuario.lista}">
                                <c:if test="${u.status != 2}">
                                <option value="${u.idUsuario}"
                                        <c:if test="${u.idUsuario==a.usuario.idUsuario}" >selected=""</c:if>  
                                >   ${u.nome}</option> 
                                </c:if>
                            </c:forEach>
                        </select>  
                </div>        
                     <div class="form-group col-sm-5">
                        <label for="status" class="control-label">Status</label>
                        <select class="form-control" name="status">
                            <option value="1" 
                                    <c:if test="${a.status==1}"> 
                                        selected="" 
                                    </c:if>
                            >Ativo</option>
                            <option value="2" 
                                    <c:if test="${a.status==2}"> 
                                        selected="" 
                                    </c:if>
                            >Concluído</option>
                        </select>  
                    </div>        
                <div class="row">
                    <div class="form-group col-sm-8">
                    <button class="btn btn-success">
                        Gravar <i class="glyphicon glyphicon-floppy-disk"></i>
                    </button>
                    <a href="gerenciar_agendamento.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                    </div>
                </div>
        </div>
       </form>
        </div>
                                               <script src="menu.js"></script>

    </body>
</html>


