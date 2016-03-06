<%-- 
    Document   : index
    Created on : 21/02/2016, 04:44:15
    Author     : Natarajan 
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <header style="padding-top: 100px">
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

        <div class="col-md-4 col-md-offset-4" >
            <div class="row container-fluid">
                <h2><strong>Atendimento</strong></h2>
                <form action="Atendimento" id="formulario_cliente" method="post">
                    <div class="form-group has-feedback">
                        <label for="cpf" class="control-label">CPF</label>
                        <input type="text" id="cpf" class="form-control" name="cpf" title="Digite um número de CPF no formato 000.000.000-00." onKeyPress="MascaraCPF(formulario_cliente.cpf);" placeholder="000.000.000-00" maxlength="14" required>

                        <p class="help-block hidden"></p>
                    </div>
                </form>

                <btn id="btnEntrar" form="formulario_cliente" type="submit" class="btn btn-primary">Entrar</btn>
                
            </div>
            
            <span class="row container-fluid">
                <div id="alertaCpfErro" class="alert alert-danger alert-dismissible" role="alert" hidden>
                </div>

                <div id="alertaClienteSemCadastro" class="alert alert-info alert-dismissible" role="alert" hidden>
                    <h4>Não há cliente com o cpf informado.</h4>
                    <h5>Deseja cadastra-lo?</h5>
                    
                    <a id="btnCadastrar" href="cadastro.jsp?cpf=" class="btn btn-primary">Cadastrar</a>'
                </div>
            </span>

        </div>

        <script src="dist/js/jquery-2.1.4.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            
            function processaRequest() {
                event.preventDefault();

                $('#alertaCpfErro').hide();
                $('#alertaClienteSemCadastro').hide();

                var dados = $("#formulario_cliente").serialize();

                if ($("#cpf").val() === "") {
                    $("#alertaCpfErro").html("Informe um número de CPF").show(250);   
                } else {
                    $.post("Atendimento", dados, function (responseJson) {
                        var cpf = responseJson.cpf;

                        if (cpf === "verificado"){
                            if (responseJson.operacao === "clienteNãoExiste"){
                                $("#btnCadastrar").attr("href", 'cadastro.jsp?cpf=' + $("#cpf").val());
                                $("#alertaClienteSemCadastro").show(250);
                            } else {
                                $(location).attr('href', 'home?cpf=' + $("#cpf").val());
//                                $(location).attr('href', 'home');
                            }
                        } else {
                            $("#alertaCpfErro").html("O CPF informado <strong>não é válido</strong>").show(250);   
                        }

                    });
                }

                

            }

            $("#btnEntrar").click(processaRequest);
            

            function mascaraInteiro() {
                if (event.keyCode < 48 || event.keyCode > 57) {
                    event.returnValue = false;
                    return false;
                }
                return true;
            }
            function MascaraCPF(cpf) {
                if (mascaraInteiro(cpf) === false) {
                    event.returnValue = false;
                }
                return formataCampo(cpf, '000.000.000-00', event);
            }
            function formataCampo(campo, Mascara, evento) {
                var boleanoMascara;

                var Digitato = evento.keyCode;
                exp = /\-|\.|\/|\(|\)| /g;
                campoSoNumeros = campo.value.toString().replace(exp, "");

                var posicaoCampo = 0;
                var NovoValorCampo = "";
                var TamanhoMascara = campoSoNumeros.length;
                ;

                if (Digitato !== 8) { // backspace 
                    for (i = 0; i <= TamanhoMascara; i++) {
                        boleanoMascara = ((Mascara.charAt(i) === "-") || (Mascara.charAt(i) === ".")
                                || (Mascara.charAt(i) === "/"));
                        boleanoMascara = boleanoMascara || ((Mascara.charAt(i) === "(")
                                || (Mascara.charAt(i) === ")") || (Mascara.charAt(i) === " "));
                        if (boleanoMascara) {
                            NovoValorCampo += Mascara.charAt(i);
                            TamanhoMascara++;
                        } else {
                            NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
                            posicaoCampo++;
                        }
                    }
                    campo.value = NovoValorCampo;
                    return true;
                } else {
                    return true;
                }
            }
        </script>
    </body>
</html>
