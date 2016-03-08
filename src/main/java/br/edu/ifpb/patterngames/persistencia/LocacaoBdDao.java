/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.persistencia;

import br.edu.ifpb.patterngames.entity.Locacao;
import br.edu.ifpb.patterngames.entity.strategy.LocacaoComum;
import br.edu.ifpb.patterngames.entity.strategy.LocacaoEspecial;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class LocacaoBdDao extends GenericBdDao<Locacao, String> {

    public Locacao procurarUltimaLocacao(int idJogo) {
        Locacao loc = null;
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "SELECT * FROM Locacao WHERE idJogo = ? AND dataDevolucao IS NULL ORDER BY dataLocacao DESC LIMIT 1";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setInt(1, idJogo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                loc = montarLocacao(rs);
            }
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        desconectar();
        return loc;
    }

    @Override
    public boolean salvar(Locacao objeto) {
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "INSERT INTO Locacao(cpfCliente, idJogo, dataLocacao, tipo) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, objeto.getCpfCliente());
            ps.setInt(2, Integer.parseInt(objeto.getIdJogo()));
            ps.setDate(3, Date.valueOf(objeto.getDataLocacao()));

            if (objeto.getDataLocacao().getDayOfWeek().equals(DayOfWeek.SUNDAY) || objeto.getDataLocacao().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                ps.setInt(4, TipoLocacao.ESPECIAL.id);
            } else {
                ps.setInt(4, TipoLocacao.COMUM.id);
            }

            int resultUpdate = ps.executeUpdate();
            desconectar();
            
            if (resultUpdate > 0) {
                return true;
            }
            
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }

        return false;
    }

    public boolean finalizar(Integer idLocacao) {
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "UPDATE Locacao SET dataDevolucao = ?, valorPago = ? WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);

            Locacao loc = buscarPorId(idLocacao);

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setDouble(2, loc.calcularValor().doubleValue());
            ps.setInt(3, idLocacao);

            int resultUpdate = ps.executeUpdate();
            desconectar();
            
            if (resultUpdate > 0) {
                return true;
            }
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }

        return false;
    }

    @Override
    public boolean apagar(Locacao objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(Locacao objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Locacao buscarPorId(Integer idLocacao) {
        Locacao loc = null;
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "SELECT * FROM Locacao WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, idLocacao);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                loc =  montarLocacao(rs);
            }
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return loc;
    }

    @Override
    public Locacao buscar(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Locacao> buscarPorCliente(String cpfCliente) {
        List<Locacao> locs = new LinkedList<>();
        try {

            if (getConnection() == null || getConnection().isClosed()) {
                conectar();
            }

            String sql = "SELECT * FROM Locacao WHERE cpfCliente = ? AND dataDevolucao IS NULL";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, cpfCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                locs.add(montarLocacao(rs));
            }
            desconectar();
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return locs;
    }

    @Override
    public List<Locacao> listarTodos() {
        return null;
    }

    private Locacao montarLocacao(ResultSet rs) {
        Locacao loc;
        try {

            loc = new Locacao();
            loc.setId(rs.getInt("id"));
            loc.setCpfCliente(rs.getString("cpfCliente"));
            loc.setIdJogo(new Integer(rs.getInt("idJogo")).toString());
            loc.setDataLocacao(rs.getDate("dataLocacao").toLocalDate());

            if (rs.getInt("tipo") == TipoLocacao.COMUM.id) {
                loc.setStrategy(new LocacaoComum());
            } else {
                loc.setStrategy(new LocacaoEspecial());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            loc = null;
        }
        
        return loc;
    }

}
