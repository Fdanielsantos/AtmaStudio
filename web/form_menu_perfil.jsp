

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width initial-scale, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css">
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="estilo/menu.css">
        <script type="text/javascript">
            function confirmarExclusao(idMenu,nome, idPerfil){
                if(confirm('Deseja realmente desvincular o menu '+nome+'?')){
                    location.href='gerenciar_menu_perfil.do?acao=desvincular&idMenu='+idMenu+'&idPerfil='+idPerfil;
                }
            }
        </script>
        <style>
            body{
                overflow-x: hidden;
            }
            .form{
                color: black;
            }
            label[for="perfil"]{
                font-size: 20px;
                border: 1px solid #000;
                border-radius: 10px;
                padding: 10px;
                
            } 
            .row{
                padding-left: 50px;
                
            }
            .form-group.col-sm-3{
                padding-left: 580px;
                
            }
            .form-group.col-sm-8{
                padding-left: 580px;
              
            }
            .form-group.col-sm-3 select {               
                border: 1px solid #ccc;
                border-radius: 5px; 
                padding: 5px; 
                width: 270px; 
                
            }
            .form-group.col-sm-4 label {
                flex: 1;
                margin-right: 25px; 
                margin-left: 120px;
               
            }
            .container-fluid{
                width: 1300px;
                               
            }
                .centralizar{
             position: relative;
             top: 50px;
             left: 280px; 
             width: 1270px;
         
                }
            
            tbody{
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
        <title>Gerenciar Perfil</title>
    </head>
    <body>
        
        <%@include file="menu.jsp"%>
        <div class="centralizar">

        <h1 class="text-center">Gerenciar Perfil</h1>
       
        <form action="gerenciar_menu_perfil.do" method="POST">
                <input type="hidden" name="idPerfil" value="${perfilv.idPerfil}"/>
                <div class="row">
                    <div class="form-group col-sm-4">
                        <label for="perfil" class="control-label">${perfilv.nome}</label>               
                    </div>
                        <input type="hidden" class="form-control" id="status" name="status" required="" value="1" maxlength="45"/>
                </div>
                    
                    <div class="row">
                    <div class="form-group col-sm-3">
                        <label for="menu" class="control-label">Menus</label>
                        <div class="custom-select">
                       <select name="idMenu" required="" id="idMenu" class="form-control">
                           <option value="">Selecione o Menu</option>
                           <c:forEach var="m" items="${perfilv.naoMenus}">
                               <c:if test="${m.status != 2}">
                               <option value="${m.idMenu}">${m.nome}</option>
                               </c:if>
                           </c:forEach>
                       </select>
                         </div>
                    </div>
                        <input type="hidden" class="form-control" id="status" name="status" required="" value="1" maxlength="45"/>
                </div>   
                    
                <div class="row">
                    <div class="form-group col-sm-8">
                    <button class="btn btn-success">
                        Vincular <i class="glyphicon glyphicon-log-in"></i>
                    </button>
                    <a href="gerenciar_perfil.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                    </div>
                </div> 
            </form>
        <div class="container-fluid">
  
            
                    
                    <table class="table table-hover table-striped table-bordered display" id="listarMenu">
            
            <thead>
                 <tbody>
            <tr>
                <th>ID</th>
                <th>Nome Menu</th>
                <th>Link</th>
                <th>Exibir</th>
                <th>Staus</th>
                <th>Desvincular</th>
            </tr>
            </thead>
            
        
       
            <c:forEach var="m" items="${perfilv.menus}">  
        <tr>
            <td>${m.idMenu}</td>
            <td>${m.nome}</td>
            <td>${m.link}</td>
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
                    <button class="btn btn-danger" onclick="confirmarExclusao(${m.idMenu},'${m.nome}',${perfilv.idPerfil})">
                     <i class="glyphicon glyphicon-log-out"></i>
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

    </body>
</html>

