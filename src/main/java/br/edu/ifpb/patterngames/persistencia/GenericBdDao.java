/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class GenericBdDao<T, I> implements Dao<T, I> {

    private Connection connection;

    /**
     * <p>
     * Método responsável por realizar conexão com o banco de dados. A conexão
     * busca as propriedades da conexão em um arquivo a ser persistidos nos
     * resources do projeto, no caminho "/banco/banco.properties", onde a "/"
     * (barra) representa a raiz do projeto.
     * </p>
     *
     * @throws FileNotFoundException
     * @throws URISyntaxException
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected void conectar() throws FileNotFoundException, URISyntaxException, IOException, SQLException, ClassNotFoundException {
        String url;
        String user;
        String password;
        String name;

        Properties properties = new Properties();
        properties.load(new FileInputStream(getClass().getResource("/banco/banco.properties").toURI().getPath()));

        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        name = properties.getProperty("name");

        Class.forName(name);
        connection = DriverManager.getConnection(url, user, password);
    }

    /**
     * Método utilizado para fechar uma conexão recém estabelecida.
     */
    protected void desconectar() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que retorna instância de conexão com o banco de dados.
     *
     * @return arquivo do tipo Connection
     * @see Connection
     */
    public Connection getConnection() {
        return connection;
    }


}
