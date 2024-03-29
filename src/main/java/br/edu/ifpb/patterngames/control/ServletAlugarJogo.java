/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.control;

import br.edu.ifpb.patterngames.entity.Cliente;
import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import br.edu.ifpb.patterngames.model.LocacaoBo;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
@WebServlet(name = "ServletAlugarJogo", urlPatterns = {"/ServletAlugarJogo"})
public class ServletAlugarJogo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        if (cliente != null) {
            String cpfCliente = cliente.getCpf();
            String idJogo = (String) request.getParameter("idJogo");

            request.getSession().setAttribute("idJogo", idJogo);

            Map<String, String> resultadoLocacao = new HashMap<>();

            boolean alugar = false;
            try {
                alugar = new LocacaoBo().realizarLocacao(idJogo, cpfCliente);

                if (alugar) {
                    resultadoLocacao.put("alugou", "ok");
                }

            } catch (JogoAlugadoException ex) {
                Logger.getLogger(ServletAlugarJogo.class.getName()).log(Level.SEVERE, null, ex);
                resultadoLocacao.put("alugou", "fail");
                resultadoLocacao.put("erro", ex.getMessage());
            }

            String json = new Gson().toJson(resultadoLocacao);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            response.sendRedirect("index");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
