
<%@page import="model.MenuDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Menu"%>
<%@page import ="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Menu</title>
        <script type="text/javascript">
            function confirmarDesativar(id,nome){
                if(confirm('Deseja realmente desativar o menu '+nome+'?')){
                    location.href='gerenciar_menu.do?acao=desativar&idMenu='+id;
                }
            }

            function confirmarExclusao(id,nome){
                if(confirm('Deseja realmente excluir o menu '+nome+'?')){
                    location.href='gerenciar_menu.do?acao=deletar&idMenu='+id;
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
            #listarMenu th,
            #listarMenu td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            #listarMenu th {
                background-color: #c8e6c9;
            }

            #listarMenu tr:hover {
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
            
        <h1 class="text-center">Lista de Menus</h1>
            
        <a href="form_menu.jsp" class="btn btn-info">
             <i class="glyphicon glyphicon-plus"></i> Novo Cadastro</a>
        <a href="index.jsp" class="btn btn-warning">
            Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
        </a>
        
        <table class="table table-hover table-striped table-bordered display" id="listarMenu" >
            
            <thead class="thead-text">
            <tr>
                <th>ID</th>
                <th>Nome Menu</th>
                <th>Link</th>
                <th>Icone</th>
                <th>Exibir</th>
                <th>Staus</th>
                <th>Opções</th>
            </tr>
            </thead>
            
        
        <tbody class="thead-text">
            <c:forEach var="m" items="${listaMenu}">  
        <tr>
            <td>${m.idMenu}</td>
            <td>${m.nome}</td>
            <td>${m.link}</td>
            <td>${m.icone}</td>
            <td>
                <c:if test="${m.exibir==1}">
                    Sim
                </c:if>
                <c:if test="${m.exibir==2}">
                    Não
                </c:if>    
            </td>
            <td>
                <c:if test="${m.status==1}">
                    Ativo
                </c:if>
                <c:if test="${m.status==2}">
                    Inativo
                </c:if>    
            </td>
            <td>
                <a class="btn btn-info" href="gerenciar_menu.do?acao=alterar&idMenu=${m.idMenu}">
                    Alterar <i class="glyphicon glyphicon-pencil"></i>
                </a>
                    <button class="btn btn-danger" onclick="confirmarDesativar(${m.idMenu},'${m.nome}')">
                    Desativar <i class="glyphicon glyphicon-remove"></i>
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
                    $("#listarMenu").dataTable({
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

