/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.persistencia;

import br.edu.ifpb.patterngames.entity.Cliente;
import br.edu.ifpb.patterngames.entity.Jogo;
import br.edu.ifpb.patterngames.entity.state.JogoAlugado;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class JogoBdDao extends GenericBdDao<Jogo, String> {

    @Override
    public boolean salvar(Jogo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean apagar(Jogo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(Jogo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean alugar(Jogo objeto) {
        return setarStatus(objeto, false);
    }

    public boolean devolver(Jogo objeto) {
        return setarStatus(objeto, true);
    }

    private boolean setarStatus(Jogo jogo, boolean isDisponivel) {

        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "UPDATE Jogo SET isDisponivel = ? WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setBoolean(1, isDisponivel);
            ps.setInt(2, jogo.getId());

            if (ps.executeUpdate() > 0) {
                desconectar();
                return true;
            }

        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            desconectar();
            return false;
        }
        desconectar();
        return false;

    }

    @Override
    public Jogo buscar(String key) {
        Jogo jogo = null;
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "SELECT * FROM Jogo WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            int idJogo = Integer.parseInt(key);
            ps.setInt(1, idJogo);

            ResultSet rs = ps.executeQuery();
            rs.next();

            jogo = preencherJogo(rs);
            desconectar();
            return jogo;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            desconectar();
            return null;
        }
    }

    @Override
    public List<Jogo> listarTodos() {
        try {
            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }
            String sql = "SELECT * FROM Jogo";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Jogo> jogos = new ArrayList<>();

            while (rs.next()) {
                Jogo jogo = preencherJogo(rs);
                jogos.add(jogo);
            }
            desconectar();
            return jogos;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            desconectar();
            return null;
        }
    }

    public boolean adicionarObservador(Integer idJogo, String cpfCliente) {
        try {
            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }
            String sql = "INSERT INTO Observacoes(idJogo, cpfCliente) VALUES (?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setInt(1, idJogo);
            ps.setString(2, cpfCliente);

            if (ps.executeUpdate() > 0) {
                desconectar();
                return true;
            }

        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        desconectar();
        return false;
    }

    public boolean removerObservador(Integer idJogo, String cpfCliente) {
        try {
            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }
            String sql = "DELETE FROM Observacoes WHERE idJogo = ? AND cpfCliente = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setInt(1, idJogo);
            ps.setString(2, cpfCliente);

            if (ps.executeUpdate() > 0) {
                desconectar();
                return true;
            }

        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        desconectar();
        return false;

    }

    public List<Cliente> buscarObservadores(Integer idJogo) {
        try {
            List<Cliente> observadores = new LinkedList<>();
            ClienteBdDao clienteBdDao = new ClienteBdDao();

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }
            String sql = "SELECT * FROM Observacoes WHERE idJogo = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, idJogo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                observadores.add(clienteBdDao.buscar(rs.getString("cpfCliente")));
            }
            desconectar();
            return observadores;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Jogo> buscarJogosObservados(String cpfCliente) {
        try {
            List<Jogo> jogosDoCliente = new LinkedList<>();
            ClienteBdDao clienteBdDao = new ClienteBdDao();

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }
            String sql = "SELECT * FROM Observacoes WHERE cpfCliente = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, cpfCliente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idJogo = rs.getInt("idJogo");
                jogosDoCliente.add(buscar(new Integer(idJogo).toString()));
            }
            desconectar();
            return jogosDoCliente;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Jogo preencherJogo(ResultSet rs) {
        Jogo jogo;
        try {

            jogo = new Jogo();
            jogo.setId(rs.getInt("id"));
            jogo.setNome(rs.getString("nome"));
            jogo.setGenero(rs.getString("genero"));
            if (!rs.getBoolean("isDisponivel")) {
                jogo.setEstado(new JogoAlugado());
            }
            List<Cliente> observadores = buscarObservadores(jogo.getId());
            for (Cliente c : observadores) {
                jogo.addObserver(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            jogo = null;
        }

        return jogo;
    }

}
