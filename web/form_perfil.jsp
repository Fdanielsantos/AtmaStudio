
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
        <link rel="stylesheet" href="estilo/menu.css">
        <style>
           
            .container-fluid{
              justify-content: center;
                display: flex;
                
            }
           .pull-right{
            
             color: #fff;

            }

            .thead-text{
                color: black;
            }
        </style>
        <title>Cadastrar Perfil</title>
    </head>
    <body>
                 
                     
           <%@include file="menu.jsp"%>
          <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <h1 class="text-center">Cadastrar Perfil</h1>
        <br>
        <div class="container-fluid">
            
            

            <form action="gerenciar_perfil.do" method="POST">
                <input type="hidden" name="idPerfil" value="${p.idPerfil}"/>
                <div class="row">
                    <div class="form-group col-sm-18">
                        <label form="nome" class="control-label">Nome:</label>
                        <input type="text" class="form-control" id="nome" name="nome" required="" value="${p.nome}" maxlength="45"/>                         
                    </div>
                        <input type="hidden" class="form-control" id="status" name="status" required="" value="1" maxlength="45"/>
                </div>        
                <div class="row">
                    <button class="btn btn-success">
                        Gravar <i class="glyphicon glyphicon-floppy-disk"></i>
                    </button>
                    <a href="gerenciar_perfil.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                </div> 
            </form>
        </div>
       
    </body>
</html>

