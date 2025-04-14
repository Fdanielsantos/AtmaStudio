
<%@page import ="model.PerfilDAO"%>
<%@page import ="java.util.ArrayList"%>
<%@page import ="model.Perfil"%>
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
        <title>Perfil</title>
        <script type="text/javascript">
            function confirmarExclusao(id,nome){
                if(confirm('Deseja realmente excluir o perfil '+nome+'?')){
                    location.href='gerenciar_perfil.do?acao=deletar&idPerfil='+id;
                }
            }
            function confirmarDesativar(id,nome){
                if(confirm('Deseja realmente desativar o perfil '+nome+'?')){
                    location.href='gerenciar_perfil.do?acao=desativar&idPerfil='+id;
                }
            }
            function confirmarInclusao(id,nome){
                if(confirm('Deseja ativar o perfil '+nome+'?')){
                    location.href='gerenciar_perfil.do?acao=ativar&idPerfil='+id;
                }
            }
        </script>
                <style>
                
              .container-fluid{
                
              width: 1350px;
            }
                .centralizar{
             position: absolute;
             top: 50px;
             left: 300px; 
             width: 1270px;
            }
           .pull-right{
             
             color: #fff;

            }
          
            #listarPerfil{
                color: black;
            }
            body{
                background-color: #f8fbfb;
            }
            #listarPerfil th,
            #listarPerfil td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            #listarPerfil th {
                background-color: #c8e6c9;
            }

            #listarPerfil tr:hover {
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

        <h1 class="text-center">Lista de Perfis</h1>
        <br>  
        <a href="form_perfil.jsp" class="btn btn-info">
           <i class="glyphicon glyphicon-plus"></i> Novo Cadastro 
        </a>
        <a href="index.jsp" class="btn btn-warning">
            Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
        </a>
        <p>
        <table class="table table-hover table-striped table-bordered display" id="listarPerfil">
            
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Status</th>
                <th>Opções</th>
            </tr>
            </thead>
            
        <tbody>
        <c:forEach var="p" items="${listaPerfil}">  
        <tr>
            <td>${p.idPerfil}</td>
            <td>${p.nome}</td>
            <td>
                <c:if test="${p.status==1}">
                    Ativo
                </c:if>
                <c:if test="${p.status==2}">
                    Inativo
                </c:if>    
            </td>
            <td>
                <a class="btn btn-info" href="gerenciar_perfil.do?acao=alterar&idPerfil=${p.idPerfil}">
                  Alterar  <i class="glyphicon glyphicon-edit"></i>
                </a>
                <c:if test="${p.status==2}">              
                <button class="btn btn-success" onclick="confirmarInclusao(${p.idPerfil},'${p.nome}')">
                   Ativar <i class="glyphicon glyphicon-ok"></i>
                </button>
                  </c:if>                
                    <c:if test="${p.status==1}">
                <button class="btn btn-danger" onclick="confirmarDesativar(${p.idPerfil},'${p.nome}')">
                   Desativar <i class="	glyphicon glyphicon-remove"></i>
                </button>
                    </c:if>
                <a class="btn btn-default" href="gerenciar_menu_perfil.do?acao=gerenciar&idPerfil=${p.idPerfil}">
                    <i class="glyphicon">Acessos</i>
                </a>
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
                    $("#listarPerfil").dataTable({
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
