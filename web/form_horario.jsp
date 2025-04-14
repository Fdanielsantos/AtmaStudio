
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width initial-scale, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="estilo/menu.css">
        <title>Cadastrar Horario</title>
        <style>
            .container-fluid{
                justify-content: center;
                display: flex;
            }
        </style>
    </head>
    <body>
         <%@include file="menu.jsp" %>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
         <h1 class="text-center">Cadastrar Horario</h1><br>
        <div class="container-fluid">

            <form action="gerenciar_horario.do" method="POST">
                <input type="hidden" name="idHorario" value="${h.idHorario}"/>
                <div class="row">
                    <div class="form-group col-sm-12">
                        <label for="horario" class="control-label">Horario</label>
                        <input type="text" class="form-control" id="horario" name="horario" required="" value="<fmt:formatDate pattern="HH:mm" value="${h.horario}"/>" maxlength="45"/>                       
                    </div>    
                </div>
                <div class="row">
                    <button class="btn btn-success">
                        Gravar <i class="glyphicon glyphicon-floppy-disk"></i>
                    </button>
                    <a href="gerenciar_horario.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                </div>
            </form>
        </div>       
    </body>
</html>

