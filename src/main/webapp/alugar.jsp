<%-- 
    Document   : index
    Created on : 21/02/2016, 04:44:15
    Author     : Natarajan 
--%>

<%@page import="br.edu.ifpb.patterngames.entity.Cliente"%>
<%@page import="br.edu.ifpb.patterngames.model.JogoBo"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="br.edu.ifpb.patterngames.entity.Locacao"%>
<%@page import="br.edu.ifpb.patterngames.model.LocacaoBo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%
            Cliente cliente = (Cliente) session.getAttribute("cliente");
        %>
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


        <div class="container">

            <div>   
                <label>Cliente: </label> ${cliente.nome}
            </div>

            <ul class="nav nav-tabs" id="navs_opcoes">
                <li class="active"><a href="#" aria-controls="locação" >Locaçao</a></li>
                <li><a href="devolucao.jsp" aria-controls="devolução" >Devolução</a></li>
            </ul>


            <div class="" id="locacao">
                <div class="col-md-6 col-md-offset-3" >
                    <h2><strong>Locação de jogo</strong></h2>
                    <form action="ServletAlugarJogo" id="formJogo" method="post">
                        <div class="form-group has-feedback">
                            <label for="jogo" class="control-label">Escolha um jogo</label>
                            <select class="form-control" id="idJogo" name="idJogo"> 
                                <%
                                    List<Jogo> jogos = new JogoBdDao().listarTodos();
                                    pageContext.setAttribute("games", jogos);
                                    try {

                                        for (Jogo jogo : jogos) {
                                            out.print("<option value=\"" + jogo.getId() + "\">" + jogo.getNome() + "</option>\n");
                                        }
                                    } catch (Exception e) {
                                    }
                                %>
                            </select>
                        </div>
                        <div class="form-group">
                            <!--<btn id="btnAlugar" form="formJogo" type="submit" class="btn btn-primary">Alugar</btn>-->
                            <!--<input id="btnAlugar" type="submit" form="formJogo" class="btn btn-primary" value="Alugar">-->
                            <a id="btnAlugar" form="formJogo" type="submit" class="btn btn-primary">Alugar</a>
                        </div>
                        <div id="bannerJogoJaAlugado" hidden>
                            <div id="alertaErroLocacao" class="alert alert-info alert-dismissible" role="alert"></div>
                            <div class="container-fluid">
                                <label>Deseja ser informado por email quando este jogo for devolvido?</label>
                                <a id="btnObserver" href="ServletAdicionarObservador" type="submit" class="btn btn-primary">Avise-me</a>
                            </div>
                                
                                
                        </div>
                        <div id="alertaLocacaoSucesso" class="alert alert-success alert-dismissible" role="alert" hidden></div>


                    </form>
                </div>
            </div>


        </div>
    </div>

    <script src="dist/js/jquery-2.1.4.min.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function processaRequest() {
            event.preventDefault();

            $('#bannerJogoJaAlugado').hide();
//            $('#alertaErroLocacao').hide();
            $('#alertaLocacaoSucesso').hide();

            var dados = $("#formJogo").serialize();

            $.post("ServletAlugarJogo", dados, function (responseGson) {                 // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...

                var resultado = responseGson.alugou;

                if (resultado === "ok") {
//                        $(location).attr('href', 'home');
                    $('#alertaLocacaoSucesso').show(250).text("Alugado com Sucesso");
                } else {
                    var value = responseGson.erro;
//                    $('#alertaErroLocacao').hide();
                    $('#alertaErroLocacao').text(value);
                    $('#bannerJogoJaAlugado').show(250);
                }

            });

        }

        $('#btnAlugar').click(processaRequest);
        

    </script>
</body>
</html>
