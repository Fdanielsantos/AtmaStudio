
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
        <link rel="stylesheet"  href="estilo/menu.css">
        <title>Inicio</title>
        <style>
            .pull-right{
                position: absolute;
                top: 0;
                right: 0;
                color: #fff;
            }
            

       body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden; /* Impede a barra de rolagem no corpo */
        }

        .inicio {
            width: 100vw;
            height: 100vh;
            overflow: hidden;
            margin: 0; /* Garante que a margem da div também é zero */
            padding-left: 15%;
        }

        .inicio img {
            width: 100%;
            height: 100%;
            object-fit: cover;
                }
   

        </style>
    
    </head>
    <body>
          <%@include file="menu.jsp"%>
        


   
            
            <div class="inicio">
                <img src="img/AAA.png" alt="Imagem de Início"/>

    </div>


    <script src="menu.js"></script>
  
    </body>
</html>
