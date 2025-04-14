
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
        <title>Cadastrar Usuario</title>
        <style>
            
            .container-fluid{
             justify-content: flex-end;
                display: flex;
                width: 900px;
                
            }
            .centralizar{
                left: 200px;
            }
           .pull-right{
             
             color: #fff;

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
        <h1 class="text-center">Cadastrar Usuário</h1><br>
        <div class="container-fluid">
            <div class="centralizar">
           
            
            
            
            <form action="gerenciar_usuario.do" method="POST">
                <input type="hidden" name="idUsuario" value="${u.idUsuario}"/>
                <div class="row">
                    <div class="form-group col-sm-5">
                        <label form="nome" class="control-label">Nome:</label>
                        <input type="text" class="form-control" id="nome" name="nome" required="" value="${u.nome}" maxlength="45"/>                         
                    </div>
                    <div class="form-group col-sm-5">
                        <label for="login" class="control-label">Login</label>
                        <input type="text" class="form-control" id="login" name="login" required="" value="${u.login}" maxlength="45"/> 
                        
                    </div>
                    <div class="form-group col-sm-5">
                        <label for="senha" class="control-label">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" value="${u.senha}" required="" maxlength="45"/> 
                        
                    </div>
                    <div class="form-group col-sm-5">
                        <label for="status" class="control-label">Status</label>
                        <select class="form-control" name="status">
                            <option value="1" 
                                    <c:if test="${usuario.status==1}"> 
                                        selected="" 
                                    </c:if>
                            >Ativo</option>
                            <option value="2" 
                                    <c:if test="${usuario.status==2}"> 
                                        selected="" 
                                    </c:if>
                            >Inativo</option>
                        </select>  
                </div>
                    <div class="form-group col-sm-5">
                        <label for="perfil" class="control-label">Perfil</label>
                        <select class="form-control" name="idPerfil" required="">
                            <option value="$">Selecione o Perfil</option>
                            <jsp:useBean class="model.PerfilDAO" id="perfil"/>
                            <c:forEach var="p" items="${perfil.lista}">
                                <c:if test="${p.status != 2}">
                                <option value="${p.idPerfil}"
                                        <c:if test="${p.idPerfil==u.perfil.idPerfil}" >selected=""</c:if>  
                                >   ${p.nome}</option> 
                                </c:if>
                            </c:forEach>
                        </select>  
                </div>        
                <div class="row">
                    <div class="form-group col-sm-8">
                    <button class="btn btn-success">
                        Gravar <i class="glyphicon glyphicon-floppy-disk"></i>
                    </button>
                    <a href="gerenciar_usuario.do?acao=listar" class="btn btn-info">
                        Voltar <i class="glyphicon glyphicon-circle-arrow-left"></i>
                    </a>
                    </div>
                </div>            
        </div>
        </form>                           
        </div>
        </div>
                             <script src="menu.js"></script>
    </body>
</html>

