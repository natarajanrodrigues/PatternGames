<%-- 
    Document   : notificacoes
    Created on : 06/03/2016, 22:19:14
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
        <title>Pattern Games - Notificações</title>

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


            <c:if test="${!empty jogosCliente}">
                <div class="" id="consultarRemoverNotificacoes">
                    <div class="col-md-6 col-md-offset-3" >
                        <h2 class="text-center"><strong>Minhas notificações</strong></h2>

                        <table class="table table-bordered table-hover table-selectable">
                            <thead>
                                <tr class="alert-info text-center">
                                    <th id="tableHeadJogo"class="text-center">idJogo</th>
                                    <th id="tableHeadJogo"class="text-center">Jogo</th>
                                    <th id="tableHeadOperacao" class="text-center">Operações</th>
                                </tr>
                            </thead>


                            <tbody id="locacoesCliente" class="searchable">
                                <c:forEach var="jogo" items="${jogosCliente}">
                                    <tr>
                                        <td class="text-center">${jogo.id}</td>
                                        <td class="text-center">${jogo.nome}</td>
                                        <td class="text-center"> <a title="Remover notificações deste jogo" href="ServletRemoverObservador?idJogo=${jogo.id}" class="btn btn-xs"><span class="glyphicon glyphicon-trash"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>



            <c:if test="${!empty jogos}">
                <div class="container-fluid">
                    <div class="col-md-6 col-md-offset-3" >
                        <h2 class="text-center"><strong>Adicionar Jogos a Notificar</strong></h2>
                        <form action="ServletAdicionarObservador" id="formJogo" method="post">
                            <div class="form-group has-feedback">
                                <label for="jogo" class="control-label">Escolha um jogo</label>
                                <select class="form-control" id="idJogo" name="idJogo"> 
                                    <c:forEach var="j" items="${jogos}">
                                        <option value="${j.id}"> ${j.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <!--<a id="btnObserver" form="formJogo" type="submit" class="btn btn-primary">Adicionar Jogo</a>-->
                                <!--<a id="btnObserver" href="ServletAdicionarObservador" type="submit" class="btn btn-primary">Adicionar</a>-->
                                <input type="submit" form="formJogo" id="btnAdd" class="btn btn-primary" value="Adicionar">
                            </div>
                        </form>
                    </div>
                </div>

            </c:if>


        </div>
    </div>
</div>


</div>
</div>

<script src="dist/js/jquery-2.1.4.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#liNotificacoes").addClass("active");
    });

</script>
</body>
</html>
