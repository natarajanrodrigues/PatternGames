/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.persistencia;

import br.edu.ifpb.patterngames.entity.Cliente;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class ClienteBdDao extends GenericBdDao<Cliente, String> {

    @Override
    public boolean salvar(Cliente objeto) {
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "INSERT INTO Cliente(cpf, nome, email) VALUES(?, ?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, objeto.getCpf());
            ps.setString(2, objeto.getNome());
            ps.setString(3, objeto.getEmail());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }

        return false;
    }

    @Override
    public boolean apagar(Cliente objeto) {
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "INSERT INTO Cliente(cpf, nome, email) VALUES(?, ?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, objeto.getCpf());
            ps.setString(2, objeto.getNome());
            ps.setString(3, objeto.getEmail());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            return false;
        }

        return false;
    }

    @Override
    public boolean alterar(Cliente objeto) {
        try {
            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "DELETE FROM Cliente WHERE cpf = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, objeto.getCpf());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            return false;
        }

        return false;
    }

    @Override
    public Cliente buscar(String key) {
        Cliente usuario = null;
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "SELECT * FROM Cliente WHERE cpf = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, key);

            ResultSet rs = ps.executeQuery();
            rs.next();

            usuario = preencherCliente(rs);

            return usuario;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();

            return null;
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "SELECT * FROM usuario";

            PreparedStatement ps = getConnection().prepareStatement(sql.toString());

            ResultSet rs = ps.executeQuery();
            List<Cliente> clientes = new ArrayList<>();

            while (rs.next()) {
                Cliente usuario = preencherCliente(rs);

                clientes.add(usuario);
            }

            return clientes;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();

            return null;
        }
    }

    private Cliente preencherCliente(ResultSet rs) {
        Cliente cliente;
        try {

            cliente = new Cliente();
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCpf(rs.getString("cpf"));

        } catch (SQLException ex) {
            ex.printStackTrace();
            cliente = null;
        }

        return cliente;
    }

}
