<%-- 
    Document   : index
    Created on : 21/02/2016, 04:44:15
    Author     : Natarajan 
--%>

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
            session.setAttribute("cpfCliente", request.getParameter("cpf"));
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

        <span class="col-md-6 col-md-offset-3 container" >    
            <label>Cliente: </label> ${cliente.nome}
        </span>

        <div class="col-md-6 col-md-offset-3" >
            <h2><strong>Locação de jogo</strong></h2>
            <form action="ServletAlugarJogo" id="formJogo" method="post">
                <div class="form-group has-feedback">
                    <label for="jogo" class="control-label">Escolha um jogo</label>
                    <select class="form-control" id="jogo" name="jogo"> 
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
                <div id="alertaErroLocacao" class="alert alert-danger alert-dismissible" role="alert" hidden></div>

            </form>

            <!--            <div class="row container-fluid">
                            <table class="table table-bordered table-hover table-selectable">
                                <thead>
                                    <tr class="alert-info text-center">
                                        <th id="tableHeadId" class="text-center">Id</th>
                                        <th id="tableHeadNome"class="text-center">Nome</th>
                                        <th id="tableHeadGenero" class="text-center">Genero</th>
                                        <th class="text-center">Operações</th>
                                    </tr>
                                </thead>
                                <tbody id="tableGames" class="searchable">
            <%--<c:forEach var="j" items="games">--%>
                <tr>
                    <th class="text-center"> ${j.id}</th>
                    <th class="text-center"> ${j.nome}</th>
                    <th class="text-center"> ${j.genero}</th>
                    <th class="text-center"></th>
                </tr>
            <%--</c:forEach>--%>
        </tbody>
    </table>
</div>-->



        </div>

        <script src="dist/js/jquery-2.1.4.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function processaRequest() {
                event.preventDefault();

                $('#alertaErroLocacao').hide();

                var dados = $("#formJogo").serialize();

                $.post("ServletAlugarJogo", dados, function (responseGson) {                 // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...

                    var resultado = responseGson.alugou;

                    if (resultado === "ok") {
//                        $(location).attr('href', 'home');
                        $('#alertaErroLocacao').show(250).text("Alugado com Sucesso");
                    } else {
                        var value = responseGson.erro;
                        $('#alertaErroLocacao').show(250).text(value);
                    }
                });

            }

            $('#btnAlugar').click(processaRequest);

        </script>
    </body>
</html>
