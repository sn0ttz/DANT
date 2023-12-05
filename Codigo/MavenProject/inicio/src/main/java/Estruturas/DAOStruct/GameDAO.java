package Estruturas.DAOStruct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import Estruturas.Objetos.Game;

public class GameDAO extends DAO {

    public GameDAO() {
        super();
    }

    public void insertGame(int appid, String name, String json) {
        String sql = "INSERT INTO games (appid, nome,json) VALUES (?, ?,?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, appid);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, json);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir jogo", e);
        }
    }

    public void removegameByID(int appid) {
        String sql = "DELETE FROM games WHERE appid = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, appid);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar jogo", e);
        }
    }

    public Game getGameByID(int appid) {
        Game game = null;
        if (appid >= 0) {

            String sql = "SELECT * FROM games WHERE appid = ?";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

                preparedStatement.setInt(1, appid);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        game = new Game();
                        game.setAppid(resultSet.getInt("appid"));
                        game.setNome(resultSet.getString("nome"));

                        game.setJson(resultSet.getString("json"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao buscar o jogo por appid", e);
            }

        }
        return game;
    }

    public LinkedList<Game> GetAllGames() throws Exception {
        LinkedList<Game> games = new LinkedList<Game>();

        String sql = "SELECT * FROM games where appid > 0 ORDER BY appid;";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            games.add(new Game(resultSet.getInt("appid"), resultSet.getString("nome"), resultSet.getString("json")));
        }

        return games;

    }

    public void InsertJson(int appid, String json) {
        String sql = "UPDATE games SET json = ? WHERE appid = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            // checar se game existe por appid
            preparedStatement.setString(1, json);
            preparedStatement.setInt(2, appid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir json", e);
        }
    }

    public LinkedList<Game> getGamesByGenero(String genero) throws Exception {
        LinkedList<Game> games = new LinkedList<Game>();

        String sql = "SELECT * FROM games where json like '%" + genero + "%' ORDER BY appid;";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            games.add(new Game(resultSet.getInt("appid"), resultSet.getString("nome"), resultSet.getString("json")));

        }
        return games;
    }

    public LinkedList<Game> getGamesBy(String genero, boolean free, String nome) {
        LinkedList<Game> games = new LinkedList<Game>();
        String sql = "SELECT * FROM GAMES WHERE 1=1 ";

        if (genero != null || genero != "") {
            sql += "AND json like '%" + genero + "%' ";
        }
        if (free) {
            sql += "AND NOT  json like '%price%'";
        }
        if (nome != null || nome != "") {
            sql += "AND nome like '%" + nome + "%' ";
        }

        sql += "ORDER BY appid;";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                games.add(
                        new Game(resultSet.getInt("appid"), resultSet.getString("nome"), resultSet.getString("json")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o jogo por appid", e);
        }
        return games;
    }

    // fazer função para pesquisar por nome, preço, genero;

}
