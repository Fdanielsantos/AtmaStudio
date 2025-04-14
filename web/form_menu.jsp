
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
        <title>Cadastrar Menu</title>
        <style>
            
            .container-fluid{
              justify-content: flex-end;
                display: flex;
                width: 900px;
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
                
          <br>
        <br>
        <br>
        <br>
        <br>
        <br>
         <h1 class="text-center">Cadastrar Menu</h1><br>
        <div class="container-fluid">

            <form action="gerenciar_menu.do" method="POST">
                <input type="hidden" name="idMenu" value="${m.idMenu}"/>
                <div class="row">
                    <div class="form-group col-sm-5">
                        <label for="nome" class="control-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" required="" value="${m.nome}" maxlength="45"/> 
                        
                    </div>
                    <div class="form-group col-sm-5">
                        <label form="link" class="control-label">Link</label>
                        <input type="text" class="form-control" id="link" name="link" required="" value="${m.link}" maxlength="45"/> 
                        
                    </div>
                    <div class="form-group col-sm-5">
                        <label form="icone" class="control-label">Icone</label>
                        <input type="text" class="form-control" id="icone" name="icone" value="${m.icone}" maxlength="45"/> 
                        
                    </div>
                    
                    <div class="form-group col-sm-5">
                        <label form="exibir" class="control-label">Exibir</label>
                        <select class="form-control" name="exibir">
                            <option value="1" 
                                    <c:if test="${m.exibir==1}"> 
                                        selected="" 
                                    </c:if>
                            >Sim</option>
                            <option value="2" 
                                    <c:if test="${m.exibir==2}"> 
                                        selected="" 
                                    </c:if>
                            >Não</option>
                        </select>  
                    </div>
                    <div class="form-group col-sm-5">
                        <label for="status" class="control-label">Status</label>
                        <select class="form-control" name="status">
                            <option value="1" 
                                    <c:if test="${m.status==1}"> 
                                        selected="" 
                                    </c:if>
                            >Ativo</option>
                            <option value="2" 
                                    <c:if test="${m.status==2}"> 
                                        selected="" 
                                    </c:if>
                            >Inativo</option>
                        </select>  
                    </div>
                </div>
                <div class="row">
                    <button class="btn btn-success">
                        Gravar <i class="glyphicon glyphicon-floppy-disk"></i>
                    </button>
                    <a href="gerenciar_menu.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                </div>
            </form>
        </div>       
    </body>
</html>
