

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link href="https://fonts.googleapis.com/css?family=PT+Serif" rel="stylesheet">
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>

	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">

	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">

	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">

	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" type="text/css" href="estilo/login.css">
        <link rel="stylesheet" type="text/css" href="estilo/util.css">
        <link rel="stylesheet" type="text/css" href="estilo/main.css">
        <title>Login</title>
        <style>
            body{
                background-color: #fff;
            }
	button{
	color: #2ecc71;
	background: transparent;
	border:1px solid #2ecc71;
	padding:5px;
	border-radius: 15px ;
	font-size:12px;
	width:190px;
	height: 40px;
	cursor:pointer;
}

	button:hover{
	background-color: #2ecc71;
	color: #fff;
	transition: 0.5s;

  
}
  .wrap-input100 {
 .wrap-input100 {
            position: relative;
            width: 100%;
            overflow: hidden;
            margin-bottom: 15px;
        }

        .transicao {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: none; /* Remover a borda */
            outline: none; /* Remover a borda quando o campo está focado */
        }

        .focus-input100 {
            position: absolute;
            display: block;
            width: 100%;
            height: 100%;
            top: 3px;
            left: 8px;
            pointer-events: none;
            font-size: 16px;
            color: #999;
            transition: 0.2s ease-out;
        }

        .transicao:focus + .focus-input100,
        .transicao:not(:placeholder-shown) + .focus-input100 {
            top: -30px;
            font-size: 12px;
            color: #555;
        }
</style>
    </head>
    <body>
    <div class="container">     
                
                <% 
                    String mensagem = (String)request.getSession().getAttribute("mensagem");
                    if(mensagem !=null){
                        request.getSession().removeAttribute("mensagem");

                %>
                <div class="alert alert-info"><%=mensagem%></div>
                <% 
                    }
                %>

                <form action="gerenciar_login.do" method="POST">
                    <div class="limiter">
                    <div class="container-login100">
                            <div class="wrap-login100">
                                    <form class="login100-form validate-form">	
                                            <img src="img/ASlogo44.png" style="width: 120px;
                                            height: 120px; margin-left: 85px;">
                                            <br><br><br>

                                            <span class="login100-form-title p-b-26">
                                                    Login
                                            </span>
                                            <div class="wrap-input100 validate-input">
                                                    <input class="transicao" type="text" id="login" name="login" placeholder=" ">
                                                    <span class="focus-input100" data-placeholder="Login"></span>
                                            </div>

                                            <div class="wrap-input100 validate-input">
                                                    <span class="btn-show-pass">
                                                            <i class="zmdi zmdi-eye"></i>
                                                    </span>
                                                    <input class="transicao" type="password" id="senha" name="senha" placeholder=" ">
                                                    <span class="focus-input100" data-placeholder="Senha"></span>
                                            </div>

                                            <div class="container-login100-form-btn">
                                                    <div class="wrap-login100-form-btn">
                                                            <button style="margin-left: 55px; ">
                                                                    Login
                                                            </button>
                                                    </div>
                                            </div>


                                    </form>
                            </div>
                    </div>
	</div>
                </form>
        
    </div>

</body>
</html>


