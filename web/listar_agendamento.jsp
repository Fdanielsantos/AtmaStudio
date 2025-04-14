
<%@page import="model.Usuario"%>
<%@page import="controller.GerenciarLogin"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="estilo/menu.css">
        <title>Agendamento</title>
        <script type="text/javascript">
            function formatarData(data) {
                const dataObj = new Date(data);
                const dia = String(dataObj.getDate()).padStart(2, '0');
                const mes = String(dataObj.getMonth() + 1).padStart(2, '0');
                const ano = dataObj.getFullYear();
                return dia + '/' + mes + '/' + ano;
            }

            
            function confirmarConcluir(id, data, nome) {
                const dataFormatada = formatarData(data);
                if (confirm('Deseja realmente concluir o agendamento do cliente '+nome+ ' no dia ' + dataFormatada + '?')) {
                    location.href = 'gerenciar_agendamento.do?acao=concluir&idAgendamento=' + id;
                }
            }
            function confirmarExclusao(id, data, nome) {
                const dataFormatada = formatarData(data);
                if (confirm('Deseja realmente excluir o agendamento do cliente '+nome+ ' no dia ' + dataFormatada + '?')) {
                    location.href = 'gerenciar_agendamento.do?acao=deletar&idAgendamento=' + id;
                }
            }
</script>
                        <style>
            .container-fluid{
              color: black;  
              margin-left: 0px;
              width: 1300px;
              padding-left: 500%;
              
            }
           .centralizar{
             position: absolute;
             top: 50px;
             left: 300px; 
             width: 1270px;
            }
            .thead-text{
                color: black;
            }
            #listarAgendamento th,
            #listarAgendamento td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            #listarAgendamento th {
                background-color: #c8e6c9;
            }

            #listarAgendamento tr:hover {
                background-color: #f8fafb;
            }
          
         
            
           .dataTables_filter input {
        border-radius: 3px; /* Ajuste o valor conforme necessário */
        padding: 3px; /* Ajuste o valor conforme necessário */
        box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1); /* Adiciona uma sombra suave */
    }

 @media screen and (max-width: 1760px){
           .container-fluid{
              color: black;  
               display: flex;
              justify-content: center;
                 
               }
        
}   
         
    
    </style>
    </head>
    <body>
     
        <%@include file="menu.jsp"%>
        
    <div class="container-fluid">
        <div class="centralizar">
        <h1 class="text-center">Lista de Agendamentos</h1>
        <br>   
        <a href="form_agendamento.jsp" class="btn btn-info">
           <i class="glyphicon glyphicon-plus"></i> Novo Cadastro 
        </a>
        <a href="index.jsp" class="btn btn-warning">
            Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
        </a>
       
        <a href="gerenciar_horario.do?acao=listar" class="btn btn-info">
           Genrenciar Horários 
        </a>
        
        <p>
        <table class="table table-hover table-striped table-bordered display" id="listarAgendamento">
            
            <thead class="thead-text">
            <tr>
                <th>ID</th>
                <th>Serviço</th>
                <th>Data </th>
                <th>Horário</th>
                <th>Valor</th>
                <th>Cliente</th>
                <th>Funcionario</th>
                <th>Status</th>
                <th>Opções</th>
            </tr>
            </thead>
            
        <tbody class="thead-text">
            <c:forEach var="a" items="${listaAgendamento}">  
        <tr>
            <td>${a.idAgendamento}</td>
            <td>${a.servico.nome}</td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.data}"/></td>
            <td><fmt:formatDate pattern="HH:mm" value="${a.horario.horario}"/></td>
            <td>R$ ${a.valor}</td>
            <td>${a.cliente.nome}</td>
            <td>${a.usuario.nome}</td>
            <td>
                 <c:if test="${a.status==1}">
                    Ativo
                </c:if>
                <c:if test="${a.status==2}">
                    Concluído
                </c:if>    
            </td>
            <td>
                <a class="btn btn-info" href="gerenciar_agendamento.do?acao=alterar&idAgendamento=${a.idAgendamento}">
                  Alterar  <i class="glyphicon glyphicon-edit"></i>
                </a>
                   <c:if test="${a.status==1}">
                <button class="btn btn-success" onclick="confirmarConcluir(${a.idAgendamento},'${a.data}','${a.cliente.nome}')">
                   Concluir <i class="glyphicon glyphicon-ok"></i>
                </button>  
                   </c:if>
                  <c:if test="${a.status==2}">
                <button class="btn btn-danger" onclick="confirmarExclusao(${a.idAgendamento},'${a.data}','${a.cliente.nome}')">
                   Excluir <i class="glyphicon glyphicon-trash"></i>
                </button>
                  </c:if>
            </td>
        </tr>
        </c:forEach>
        </tbody>
            </table>
            </div>    
            <script type="text/javascript" src="datatables/jquery.js"></script>
            <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script> 
            <script type="text/javascript"> 
                $(document).ready(function(){
                    $("#listarAgendamento").dataTable({
                        "bJQueryUI" : true,
                        "oLanguage":{
                            "sProcessing":"Processando...",
                            "sLengthMenu": "Mostrar _MENU_ registros",
                            "sZeroRecords": "Não foram encontrados resultados",
                            "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros", 
                            "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                            "sInfoFiltered": "",
                            "sInfoPostFix": "",
                            "sSearch": "Pesquisar",
                            "sUrl":"",
                        "oPaginate":{
                            "sFirst":"Primeiro",
                            "sPrevious":"Anterior",
                            "sNext":"Próximo",
                            "sLast":"Ultimo"
                        }
                   }
             })
         })
        </script>            
        </div>
        
                   <script src="menu.js"></script>

    </body>
</html>


