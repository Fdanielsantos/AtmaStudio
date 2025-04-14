

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
        <title>Horarios</title>
        <script type="text/javascript">
            function confirmarExclusao(id,horario){
                if(confirm('Deseja realmente excluir o horario '+horario+'?')){
                    location.href='gerenciar_horario.do?acao=deletar&idHorario='+id;
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
            width: 1000px;
            }
            .thead-text{
                color: black;
            }
            #listarHorario th,
            #listarHorario td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            #listarHorario th {
                background-color: #c8e6c9;
            }

            #listarHorario tr:hover {
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
              justify-content: flex-end;
                 
               }
        
}  
        </style>
    </head>
    <body>
           
        <%@include file="menu.jsp"%>
           
    <div class="container-fluid">
     <div class="centralizar">
        <h1 class="text-center">Lista de Horarios</h1>
            
        <a href="form_horario.jsp" class="btn btn-info">
             <i class="glyphicon glyphicon-plus"></i> Novo Cadastro</a>
        <a href="index.jsp" class="btn btn-warning">
            Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
        </a>
        
        <table class="table table-hover table-striped table-bordered display" id="listarHorario">
            
            <thead class="thead-text">
            <tr>
                <th>ID</th>
                <th>Horario</th>
                <th>Opções</th>
            </tr>
            </thead>
            
        
        <tbody class="thead-text">
            <c:forEach var="h" items="${listaHorario}">  
        <tr>
            <td>${h.idHorario}</td>
            <td><fmt:formatDate pattern="HH:mm" value="${h.horario}"/></td>
            <td>
                <a class="btn btn-info" href="gerenciar_horario.do?acao=alterar&idHorario=${h.idHorario}">
                    Alterar <i class="glyphicon glyphicon-pencil"></i>
                </a>
                    <button class="btn btn-danger" onclick="confirmarExclusao(${h.idHorario},'${h.horario}')">
                    Excluir <i class="glyphicon glyphicon-trash"></i>
                </button>
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
                    $("#listarHorario").dataTable({
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


