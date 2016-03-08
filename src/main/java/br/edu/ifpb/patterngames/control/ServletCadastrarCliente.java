/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.control;

import br.com.caelum.stella.ValidationMessage;
import br.edu.ifpb.patterngames.entity.Cliente;
import br.edu.ifpb.patterngames.model.ClienteBo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Natarajan
 */
@WebServlet(name = "ServletCadastrarCliente", urlPatterns = {"/ServletCadastrarCliente"})
public class ServletCadastrarCliente extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        
        Cliente c = new Cliente(nome, cpf, email);
        
        ClienteBo clienteBo = new ClienteBo();
        
        List<ValidationMessage> validacao = clienteBo.verificarCliente(c);
        if (validacao.isEmpty()){
            boolean cadastrou = clienteBo.adicionarCliente(c);
            if (cadastrou){
                HttpSession sessao = request.getSession();
                sessao.setAttribute("cliente", c);
//                request.getRequestDispatcher("home.jsp?cpf=" + cpf).forward(request, response);
                response.sendRedirect("home");
            } else {
                //out.print("<p>não cadastrou</p>");
                response.sendRedirect("erroCadastro.html");
            }
        } else {
            
            for (ValidationMessage i : validacao){
                out.print("<p>" + i.getMessage() + "</p>");
            }
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
