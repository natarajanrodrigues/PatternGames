<%-- 
    Document   : index
    Created on : 21/02/2016, 01:03:50
    Author     : Natarajan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title>Pattern Games</title>

        <!-- Latest compiled and minified CSS -->
        <!--<link rel="stylesheet" href="./dist/css/bootstrap.min.css">-->

        <!-- Optional theme -->
        <link rel="stylesheet" href="dist/css/bootstrap-theme.min.css" type="text/css">
        <link rel="stylesheet" href="dist/css/bootstrap.min.css" type="text/css"/>

    </head>
    <body>
        <header>
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="./" class="navbar-brand">PatternGames</a>
                    </div>

                </div>
            </nav>
        </header>

        <div class="container col-md-6 col-md-offset-3" style="padding-top: 100px">
            <form action="ServletCadastrarCliente" id="formulario_cliente" method="post">
                <h2><strong>Cadastrar Cliente</strong></h2>
                <!--onKeyPress="MascaraCPF(formulario_cliente.cpf);"--> 
                <div class="form-group has-feedback">
                    <label for="cpf" class="control-label">CPF</label>
                    <input type="text" id="cpf" class="form-control" name="cpf" title="Digite um número de CPF no formato 000.000.000-00." onKeyPress="MascaraCPF(formulario_cliente.cpf);" placeholder="000.000.000-00" required value=${param.cpf}>

                    <p class="help-block hidden"></p>
                </div>
                
                <div class="form-group has-feedback">
                    <label for="nome" class="control-label">Nome </label>
                    <input type="text" id="nome" class="form-control" name="nome" placeholder="Digite seu nome" pattern="[A-Za-zÀ-ú0-9 ]+" title="Nome não pode conter caracteres especiais (% - $ _ # @, por exemplo)." required>
                    <p class="help-block hidden"></p>
                </div>
                
                <div class="form-group has-feedback">
                    <label for="email" class="control-label">Email</label>
                    <input type="email" id="email" class="form-control" name="email" 
                           placeholder="Informe seu email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Digite um email válido" required>
                    <p class="help-block hidden"></p>
                </div>
                <input class="btn btn-primary" type="submit" value="Cadastrar">
            </form>
            <!--<btn href="" id="btnEntrar" form="formulario_cliente" type='submit' class="btn btn-primary">Entrar</btn>-->

        </div>

        <footer>
            <div class="container">
                <hr>
            </div> <!-- end container -->
        </footer>

        <script src="dist/js/jquery-2.1.4.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
        <script src="dist/js/validarCPF.js"></script>
        
    </body>
</html>
