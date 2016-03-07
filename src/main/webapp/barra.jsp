<%-- 
    Document   : barra
    Created on : 06/03/2016, 22:38:03
    Author     : Natarajan 
--%>

<%@page import="br.edu.ifpb.patterngames.entity.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session == null) {
        request.getRequestDispatcher("index").forward(request, response);
    } else {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            request.getRequestDispatcher("index").forward(request, response);
        }
    }
    
%>

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="home" class="navbar-brand">PatternGames</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li id="liHome"><a href="home" aria-controls="locação" >Locaçao</a></li>
                    <li id="liDevolucao"><a href="devolucao.jsp" aria-controls="devolução" >Devolução</a></li>
                    <li id="liNotificacoes"><a href="ServletAreaNotificacoes" aria-controls="devolução" >Notificações</a></li>
                </ul>
            </div>

        </div>
    </nav>

