/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.persistencia;

import br.edu.ifpb.patterngames.entity.Jogo;
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
public class JogoBdDao extends GenericBdDao<Jogo, String>{

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

    @Override
    public Jogo buscar(String key) {
        Jogo jogo = null;
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "SELECT * FROM Jogo WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, key);

            ResultSet rs = ps.executeQuery();
            rs.next();

            jogo = preencherJogo(rs);

            return jogo;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();

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

            return jogos;
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

        } catch (SQLException ex) {
            ex.printStackTrace();
            jogo = null;
        }

        return jogo;
    }
    
}