package Estruturas.DAOStruct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Estruturas.Objetos.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PostDAO extends DAO {

    public PostDAO() {
        super();
    }

    // as colunas em post se chamam id,postagem,forumID,UserID,categoria

   public int InserirPostagem(String postagem, int forumID, int userID, int categoria) {
    if (categoria < 1 || categoria > 3) {
        return 0;
    }

    String sql = "INSERT INTO posts (postagem, forum_id, user_id, categoria_id) VALUES (?, ?, ?, ?)";
    try {
        // Informe ao PreparedStatement que você deseja obter as chaves geradas
        PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, postagem);
        preparedStatement.setInt(2, forumID);
        preparedStatement.setInt(3, userID);
        preparedStatement.setInt(4, categoria);
        preparedStatement.executeUpdate();

        // Obtenha as chaves geradas
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int novoID = generatedKeys.getInt(1);
            return novoID;
        } else {
            throw new SQLException("Falha ao obter o ID gerado.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}

    public boolean DeletePostById(int id) // id do post
    {
        String sql = "DELETE FROM posts WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Post getPostByID(int id) {
        Post post = null;
        String sql = "SELECT * FROM posts WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idPost = resultSet.getInt("id");
                String postagem = resultSet.getString("postagem");
                int forumID = resultSet.getInt("forum_id");
                int UserID = resultSet.getInt("user_Id");
                int categoria = resultSet.getInt("categoria_id");
                post = new Post(idPost, postagem, forumID, UserID, categoria);
                return post;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<Post> GetPostsBy(int forumIdP, int userIDP, int categoriaP) {
        LinkedList<Post> posts = new LinkedList<Post>();

        // Defina a consulta SQL base
        String sql = "SELECT * FROM posts WHERE 1=1"; // 1=1 é uma condição verdadeira, então não afeta a consulta
                                                      // possibilita usar ands e ors
        // Construa a consulta dinamicamente com base nos parâmetros fornecidos
        if (forumIdP > 0) {
            sql += " AND forum_id = " + forumIdP;
        }

        if (userIDP > 0) {
            sql += " AND user_id = " + userIDP;
        }

        if (categoriaP > 0) {
            sql += " AND categoria_id = " + categoriaP;
        }

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idPost = resultSet.getInt("id");
                String postagem = resultSet.getString("postagem");
                int forumID = resultSet.getInt("forum_id");
                int UserID = resultSet.getInt("user_id");
                int categoria = resultSet.getInt("categoria_id");
                Post post = new Post(idPost, postagem, forumID, UserID, categoria);
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return posts;
    }

    public LinkedList<Post> DeletePostsBy(int forumIdP, int userIDP, int categoriaP) {
        LinkedList<Post> posts = new LinkedList<Post>();

        // Defina a consulta SQL base
        String sql = "DELETE FROM posts WHERE 1=1"; // 1=1 é uma condição verdadeira, então não afeta a consulta
                                                    // possibilita usar ands e ors


        // Construa a consulta dinamicamente com base nos parâmetros fornecidos
        if (forumIdP > 0) {
            sql += " AND forum_Id = " + forumIdP;
        }

        if (userIDP > 0) {
            sql += " AND user_id = " + userIDP;
        }

        if (categoriaP > 0) {
            sql += " AND categoria_id = " + categoriaP;
        }

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return posts;
    }

}
