
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
        <title>Usuario</title>
        <script type="text/javascript">
            function confirmarDesativar(id,nome){
                if(confirm('Deseja realmente desativar o usuario '+nome+'?')){
                    location.href='gerenciar_usuario.do?acao=desativar&idUsuario='+id;
                }
            }
            function confirmarInclusao(id,nome){
                if(confirm('Deseja realmente ativar o usuario '+nome+'?')){
                    location.href='gerenciar_usuario.do?acao=ativar&idUsuario='+id;
                }
            }
            function confirmarExclusao(id,nome){
                if(confirm('Deseja realmente excluir o usuario '+nome+'?')){
                    location.href='gerenciar_usuario.do?acao=deletar&idUsuario='+id;
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
             width: 1260px;
 
               }
      
            .thead-text{
                color: black;
            }
             #listarUsuario {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    #listarUsuario th,
    #listarUsuario td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    #listarUsuario th {
        background-color: #c8e6c9;
    }

    #listarUsuario tr:hover {
        background-color: #f8fafb;
    }
    .dataTables_filter input {
        border-radius: 3px; /* Ajuste o valor conforme necessário */
        padding: 3px; /* Ajuste o valor conforme necessário */
        box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1); /* Adiciona uma sombra suave */
        
    }
    tbody td a {
    text-align: right;        
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
    
        <h1 class="text-center">Lista de Usuários</h1>
            
        <a href="form_usuario.jsp" class="btn btn-info">
           <i class="glyphicon glyphicon-plus"></i> Novo Cadastro 
        </a>
        <a href="index.jsp" class="btn btn-warning">
            Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
        </a>
        <p>
        <table class=" table table-borderless " id="listarUsuario">
            
            <thead  class="thead-text">
            <tr>
                <th>ID</th>
                <th>Nome </th>
                <th>Login</th>
                <th>Status</th>
                <th>Perfil</th>
                <th>Opções</th>
            </tr>
            </thead>
            
        <tbody  class="thead-text">
            <c:forEach var="u" items="${listaUsuario}">  
        <tr>
            <td>${u.idUsuario}</td>
            <td>${u.nome}</td>
            <td>${u.login}</td>
            <td>
                <c:if test="${u.status==1}">
                    Ativo
                </c:if>
                <c:if test="${u.status==2}">
                    Inativo
                </c:if>    
            </td>
            <td>${u.perfil.nome}</td>
            
            <td>
                <a class="btn btn-info" href="gerenciar_usuario.do?acao=alterar&idUsuario=${u.idUsuario}">
                  Alterar  <i class="glyphicon glyphicon-edit"></i>
                </a>
                <c:if test="${u.status==1}">  
                <button class="btn btn-danger" onclick="confirmarDesativar(${u.idUsuario},'${u.nome}')">                   
                   Desativar <i class="glyphicon glyphicon-remove"></i>                  
                </button>
                </c:if>   
                <c:if test="${u.status==2}">  
                <button class="btn btn-success" onclick="confirmarInclusao(${u.idUsuario},'${u.nome}')">   
                   Ativar <i class="glyphicon glyphicon-ok"></i>
                </button> 
                </c:if>                
            </td>
         </tr>
         </c:forEach>
            </tbody>
            </table>
      </div>
        <div class="teste">
            <script type="text/javascript" src="datatables/jquery.js"></script>
            <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script> 
            <script type="text/javascript"> 
                $(document).ready(function(){
                    $("#listarUsuario").dataTable({
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
        </div>
                        <script src="menu.js"></script>

    </body>
</html>

