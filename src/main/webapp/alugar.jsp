<%-- 
    Document   : index
    Created on : 21/02/2016, 04:44:15
    Author     : Natarajan 
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
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
        <header style="padding-top: 100px">
            <%@ include file="barra.jsp"%>
        </header>

        <div class="container">

           <div>   
                <label>Cliente: </label> ${cliente.nome} 
            </div>
            <a href="ServletFinalizarAtendimento" class="btn-link"> Escolher outro cliente</a>


            <div class="" id="locacao">
                <div class="col-md-6 col-md-offset-3" >
                    <h2 class="text-center"><strong>Locação de jogo</strong></h2>
                    <form action="ServletAlugarJogo" id="formJogo" method="post">
                        <div class="form-group has-feedback">
                            <label for="jogo" class="control-label">Escolha um jogo</label>
                            <select class="form-control" id="idJogo" name="idJogo" autofocus="true"> 
                                <%                                    
                                    List<Jogo> jogos = new JogoBdDao().listarTodos();
                                    Collections.sort(jogos, Jogo.Comparators.NOME);
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

        $(document).ready(function () {
            $("#liHome").addClass("active");
        });



    </script>
</body>
</html>
