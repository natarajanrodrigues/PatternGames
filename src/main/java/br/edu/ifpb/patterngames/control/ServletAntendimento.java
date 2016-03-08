/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.control;

import br.edu.ifpb.patterngames.entity.Cliente;
import br.edu.ifpb.patterngames.model.ClienteBo;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natarajan
 */
@WebServlet(name = "ServletAntendimento", urlPatterns = {"/Atendimento"})
public class ServletAntendimento extends HttpServlet {

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

        String cpf = request.getParameter("cpf");
        request.setAttribute("cpf", cpf);

        Map<String, String> verificaCPF = new ClienteBo().verificaCpf(cpf);
        
        Map<String, String> resultado = new HashMap<>();
        
        
        if (verificaCPF.get("verificacao").equals("ok")) {
            request.setAttribute("cpf", cpf);
            resultado.put("cpf", "verificado");
            Cliente c = new ClienteBo().buscarPorCPF(cpf);
            // se o cliente já está cadastrado
            if (c != null) {
//                request.setAttribute("cliente", c);
//                request.getRequestDispatcher("home");
                request.getSession().setAttribute("cliente", c);
            } else {
                resultado.put("operacao", "clienteNãoExiste");
            }
        } else {
            resultado.put("cpf", "errado");
            for (String i : verificaCPF.keySet()){
                resultado.put("erro" + i, verificaCPF.get(i));
            }
        }

        String json = new Gson().toJson(resultado);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

//        
//        if (c == null) {
//            request.setAttribute("operacao", "cadastrar");
//            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//            rd.forward(request, response);
//        } else {
//            response.getWriter().print("OK");
//        }
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
