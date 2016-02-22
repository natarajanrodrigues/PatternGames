<%-- 
    Document   : index
    Created on : 21/02/2016, 04:44:15
    Author     : Natarajan 
--%>

<%@page import="br.edu.ifpb.patterngames.entity.Jogo"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.patterngames.persistencia.JogoBdDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title>Pattern Games - Home</title>

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

        <div class="col-md-6 col-md-offset-3" >
            <div class="row container-fluid">
                <div class="form-group has-feedback">
                    <label for="jogo" class="control-label">Escolha um jogo</label>
                    <select class="form-control" id="jogo" name="jogo"> 
                        <%
                            List<Jogo> jogos = new JogoBdDao().listarTodos();
                            try {

                                for (Jogo jogo : jogos) {
                                    out.print("<option value=\"" + jogo.getId()+ "\">" + jogo.getNome() + "</option>\n");
                                }
                            } catch (Exception e) {
                            }
                        %>
                    </select>

                </div>

            </div>
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

                        if (cpf === "verificado") {
                            if (responseJson.operacao === "clienteNãoExiste") {
                                $("#alertaClienteSemCadastro").show(250);
                            } else {
                                //$("#btnEntrar").attr('href', 'home');
                                //                        $(location).attr('href', 'editarPerfil');
                                $(location).attr('href', 'home?cpf=' + $("#cpf").val());
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
