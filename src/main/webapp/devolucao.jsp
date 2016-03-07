<%-- 
    Document   : devolucao
    Created on : 09/03/2016, 03:27:25
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
        <title>Pattern Games - Devolução</title>

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

            <%
                List<Locacao> locsCliente = new LocacaoBo().buscarPorCliente(((Cliente)session.getAttribute("cliente")).getCpf());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DecimalFormat df = new DecimalFormat("#,###,##0.00");
                JogoBo jogoBo = new JogoBo();

                pageContext.setAttribute("locacoesCliente", locsCliente);
                pageContext.setAttribute("dtformat", dtf);
                pageContext.setAttribute("decformat", df);
                pageContext.setAttribute("myJogoBo", jogoBo);


            %>


            <div class="" id="tab_opcoes">

                <div role="tabpanel" class="tab-pane" id="devolucao">

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
        $(document).ready(function () {
            $("#liDevolucao").addClass("active");
        });

    </script>
</body>
</html>
