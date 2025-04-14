<%@page import="model.Usuario"%>
<%@page import="controller.GerenciarLogin"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width initial-scale, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
        <link rel="stylesheet" type="css" href="estilo/menu.css">
        <style>
            .pull-right{
                position: absolute;
                top: 15px;
                right: 0;
                color: #fff;
            }
            
            .item-menu a {
                display: flex;
                align-items: center;
            }

            .item-menu i {
                 font-size: 20px;
                padding-left: 19px;
                margin-right: 20px;; /* Ajuste o valor conforme necessário */
            }
            .header{
               position: relative;
                z-index: 1000;
            }
            nav.menu-lateral.expandir {
             width: 230px;
        } 
         ul li.item-menu a{
                margin-bottom: 60px;
            }
        @media screen and (max-width: 1600px){
             .item-menu a {
                display: flex;
                align-items: center;
            }

            .item-menu i {
                font-size: 20px;
                padding-left: 19px;
                margin-right: 20px; /* Ajuste o valor conforme necessário */
            }
            .header{
                position: relative;
                z-index: 1000;
            }
          nav.menu-lateral.expandir {
             width: 230px;
        } 
            ul li.item-menu a{
                margin-bottom: 60px;
            }
            
        </style>
 
    </head>
    <body>
        
        <% 
        Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);
        request.setAttribute("ulogado", ulogado);

    %>
   
    
    <div class="header">
        
        <br><br>
        <a href="index.jsp">
            <span><a href="index.jsp"><img src="img/ASlogo4.png" alt="" class="logo1"></a></span>
        </a>

            <div class="pull-right">Bem-Vindo(a), <c:if test="${ulogado!=null}">${ulogado.nome}</c:if> 
                <a href="gerenciar_login.do" class="btn btn-success"> Sair <i class="glyphicon glyphicon-log-out"></i></a>
            </div> 
         
        <nav class="menu-lateral expandir">
            <div class="btn-expandir">
                <br>
                
            </div> <!--btn-expandir-->

            <div class="item-menu ativo">
          
                    <a href="index.jsp">
            <span><img src="img/ASlogo4.png" alt="" class="logo1"></span>
                    </a>
                
            </div> 

            <ul>
            <div class="text-center ">
            </div>
            <c:if test="${ulogado!=null && ulogado.perfil!=null}">
                <c:forEach var="menu" items="${ulogado.perfil.menus}">
                    <c:if test="${menu.exibir==1}">
                        <li class="item-menu" style="font-size: 9px; height: 8%;"> 
                            <a href="${menu.link}"><img src="img/${menu.icone}"  style="width: 50px; height: 50px; margin-bottom: 2px;"/> ${menu.nome}</a>
                        </li> 
                    </c:if>
                </c:forEach>
            </c:if>
            <li class="item-menu"> 
                <a href="gerenciar_login.do">
                    <i class="glyphicon glyphicon-log-out"></i>Sair</a>
            </li>
            </ul>

        </nav><!--menu lateral-->
    </div>
                  <script src="menu.js"></script>
    </body>
</html>