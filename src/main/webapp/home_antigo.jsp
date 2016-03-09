<%-- 
    Document   : index
    Created on : 21/02/2016, 04:44:15
    Author     : Natarajan 
--%>

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


        <div class="container">

            <div>   
                <label>Cliente: </label> ${cliente.nome}
            </div>

            <ul class="nav nav-tabs" id="navs_opcoes">
                <li role="presentation" class="active"><a href="#locacao" aria-controls="locação" >Locaçao</a></li>
                <li role="presentation"><a href="#devolucao" aria-controls="devolução" >Devolução</a></li>
            </ul>

            <div class="tab-content" id="tab_opcoes">

                <!--LOCACACAO-->
                <div role="tabpanel" class="tab-pane active" id="locacao">
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
                    </div>
                </div>

                <div role="tabpanel" class="tab-pane" id="devolucao">
                    <%
                        List<Locacao> locsCliente = new LocacaoBo().buscarPorCliente(request.getParameter("cpf"));
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        DecimalFormat df = new DecimalFormat("#,###,##0.00");
                        JogoBo jogoBo = new JogoBo();

                        pageContext.setAttribute("locacoesCliente", locsCliente);
                        pageContext.setAttribute("dtformat", dtf);
                        pageContext.setAttribute("decformat", df);
                        pageContext.setAttribute("myJogoBo", jogoBo);


                    %>
                    <div class="col-md-6 col-md-offset-3" >
                        <h2><strong>Devolução de jogo</strong></h2>

                        <table class="table table-bordered table-hover table-selectable">
                            <thead>
                                <tr class="alert-info text-center">
                                    <th id="tableHeadJogo"class="text-center">idLoc</th>
                                    <th id="tableHeadJogo"class="text-center">Jogo</th>
                                    <th id="tableHeadData" class="text-center">Data locação</th>
                                    <th id="tableHeadValorPagar" class="text-center">Total a pagar (R$)</th>
                                    <th id="tableHeadOperacao" class="text-center">Operações</th>
                                </tr>
                            </thead>


                            <tbody id="locacoesCliente" class="searchable">
                                <c:forEach var="loc" items="${locacoesCliente}">
                                    <tr>
                                        <td class="text-center">${loc.id}</td>
                                        <td class="text-center">${myJogoBo.buscarPorId(loc.idJogo).nome}</td>
                                        <td class="text-center">${loc.dataLocacao.format(dtformat)}</td>
                                        <td class="text-center">${decformat.format(loc.calcularValor())}</td>
                                        <td class="text-center"> <a title="Devolver este jogo" href="ServletFinalizarLocacao?idLocacao=${loc.id}" class="btn btn-xs"><span class="glyphicon glyphicon-edit"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
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

        $('.nav-tabs a[href="#locacao"]').click(function (e) {
            e.preventDefault();
            $('.nav-tabs a[href="#locacao"]').tab('show');
        });

        $('.nav-tabs a[href="#devolucao"]').click(function (e) {
            e.preventDefault();
            $('.nav-tabs a[href="#devolucao"]').tab('show');
        });

    </script>
</body>
</html>
