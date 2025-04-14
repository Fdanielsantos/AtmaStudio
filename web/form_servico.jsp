

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Cadastrar Serviço</title>
        <style>
      
            .container-fluid{
             justify-content: flex-end;
                display: flex;
                width: 900px;
            }
            .centralizar{
             position: absolute;
             top: 50px;
             left: 350px; 
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
         <h1 class="text-center">Cadastrar Serviço</h1><br>
        <div class="container-fluid">
           

            <form action="gerenciar_servico.do" method="POST">
                <input type="hidden" name="idServico" value="${s.idServico}"/>
                <div class="row">
                    <div class="form-group col-sm-5">
                        <label for="nome" class="control-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" required="" value="${s.nome}" maxlength="45"/>                        
                    </div>
                    <div class="form-group col-sm-5">
                        <label form="descricao" class="control-label">Descrição</label>
                        <input type="text" class="form-control" id="descricao" name="descricao" value="${s.descricao}" maxlength="45"/>       
                    </div>
                    <div class="form-group col-sm-5">
                        <label form="preco" class="control-label">Preço</label>
                        <input type="text" class="form-control" id="preco" name="preco" required="" value="<fmt:formatNumber pattern="#,##0.00" value="${s.preco}"/>" maxlength="45"/>       
                    </div>
                    <div class="form-group col-sm-5">
                        <label form="duracao" class="control-label">Duração</label>
                        <input type="text" class="form-control" id="duracao" name="duracao" required="" value="${s.duracao}" maxlength="45"/>       
                    </div>  
                    <div class="form-group col-sm-5">
                        <label for="status" class="control-label">Status</label>
                        <select class="form-control" name="status">
                            <option value="1" 
                                    <c:if test="${s.status==1}"> 
                                        selected="" 
                                    </c:if>
                            >Ativo</option>
                            <option value="2" 
                                    <c:if test="${s.status==2}"> 
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
                    <a href="gerenciar_servico.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>
    </body>
</html>

