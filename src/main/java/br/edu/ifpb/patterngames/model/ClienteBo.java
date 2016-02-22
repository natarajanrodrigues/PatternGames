/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.model;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.edu.ifpb.patterngames.entity.Cliente;
import br.edu.ifpb.patterngames.persistencia.ClienteBdDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
public class ClienteBo {

//    private ClienteBdDao clienteBdDao;
    public boolean adicionarCliente(Cliente c) {

        return new ClienteBdDao().salvar(c);
    }

    public Cliente buscarPorCPF(String cpf) {
        return new ClienteBdDao().buscar(cpf);
    }

    public Cliente buscar(String nome, String cpf, String email) {
        Cliente c = new ClienteBdDao().buscar(cpf);

        if (c == null) {
            c = new Cliente(nome, cpf, email);
            adicionarCliente(c);
        }

        return c;
    }

    public List<ValidationMessage> verificarCliente(Cliente cliente) {

        CPFValidator validator = new CPFValidator();

        if (cliente == null) {
            return null;
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            return null;
        }

        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            return null;
        }

        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            return null;
        } else {
//            try {
//                validator.assertValid(cliente.getCpf());
//            } catch (InvalidStateException e) { 
//                System.out.println(e.getInvalidMessages());
//            }
//            List<ValidationMessage> validationMessages = validator.invalidMessagesFor(cliente.getCpf());
//            if (!validationMessages.isEmpty()) 
//                return false;

            return validator.invalidMessagesFor(cliente.getCpf());
        }

    }

    public Map<String, String> verificaCpf(String cpf) {
        Map<String, String> resultado = new HashMap<>();

        CPFValidator validator = new CPFValidator();
        List<ValidationMessage> validaCPF = validator.invalidMessagesFor(cpf);

        if (validaCPF.size() == 0) {
            resultado.put("verificacao", "ok");
//            if (buscarPorCPF(cpf) != null){
//                resultado.put("cliente", "existente");
//            }

        } else {
            resultado.put("verificacao", "erro");
            for (ValidationMessage i : validaCPF)
                resultado.put("erroCPF", i.getMessage());
        }

        return resultado;
    }

}
