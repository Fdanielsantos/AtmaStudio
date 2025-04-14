
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
        <title>Cadastrar Cliente</title>
        <style>
            
            .container-fluid{
              justify-content: flex-end;
                display: flex;
                width: 1000px;
                left: 500px;
            }
           
            .centralizar{
             position: absolute;
             top: 50px;
             left: 200px; 
             width: 1270px;
            }
            .pull-right{
             
             color: #fff;

            }
            
            .item-menu{
            }
            .thead-text{
                color: black;
            }
        </style>
    </head>
    <body>
        <%@include file="menu.jsp"%>    
        <div class="centralizar">
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <h1 class="text-center">Cadastrar Cliente</h1>
        <br>
        <div class="container-fluid">
        
            <form action="gerenciar_cliente.do" method="POST">
                <input type="hidden" name="idCliente" value="${c.idCliente}"/>
                <div class="row">
                    <div class="form-group col-sm-5">
                        <label form="nome" class="control-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" required="" value="${c.nome}" maxlength="45"/> 
                        
                    </div>
                    <div class="form-group col-sm-5">
                        <label form="telefone" class="control-label">Telefone</label>
                        <input type="text" class="form-control" id="telefone" name="telefone" required="" value="${c.telefone}" maxlength="45"/> 
                        
                    </div>
                    <div class="form-group col-sm-5">
                        <label form="email" class="control-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${c.email}" maxlength="45"/> 
                        
                    </div>
                <div class="row">
                    <div class="form-group col-sm-8">
                    <button class="btn btn-success">
                        Gravar <i class="glyphicon glyphicon-floppy-disk"></i>
                    </button>
                    <a href="gerenciar_cliente.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                    </div>
                </div>
        </div>     
            </form>  
        </div>
        </div> 
    </body>
</html>