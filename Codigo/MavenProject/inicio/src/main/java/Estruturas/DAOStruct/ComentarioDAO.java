package Estruturas.DAOStruct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Estruturas.Objetos.Comentario;

public class ComentarioDAO extends DAO {
    public ComentarioDAO() {
        super();
    }

    public boolean InserirComentario(String comentario,java.sql.Date data, int postID, int userID) throws Exception {
        String sql = "INSERT INTO comentario (coment,datapostagem,postid,userid) VALUES (,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, comentario);
            preparedStatement.setInt(2, postID);
            preparedStatement.setInt(3, userID);
            preparedStatement.setDate(4, data);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean UpdateComentario(int id, String newcomet)throws Exception{
        try{ 
           String sql = "UPDATE comentarios SET coment = ? WHERE id = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, newcomet);
        ps.executeUpdate();
        return true; 
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteComentarioById(int id) // id do comentario
    {
        String sql = "DELETE FROM comentarios WHERE id = ?";
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

    public Comentario GetComentarioByID(int id) throws Exception {
        Comentario comentario = null;
        String sql = "SELECT * FROM comentarios WHERE id = ?";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int idComentario = resultSet.getInt("id");
            String coment = resultSet.getString("coment");
            int postid = resultSet.getInt("postid");
            int userid = resultSet.getInt("userid");
            java.sql.Date datapostagem = resultSet.getDate("datapostagem");
            comentario = new Comentario(idComentario, coment, postid, userid, datapostagem);
        }
        return comentario;
    }

    // mande null para Date se não quiser filtrar por data, mande 0 para int se não
    // quiser filtrar por int, mande "" se não quiser filtrar por string
    public ArrayList<Comentario> getComentarioBy(int postID, int userID, java.sql.Date datapostagem) throws Exception {
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        String sql = "SELECT * FROM comentarios WHERE postid 1=1";

        if (postID > 0) {
            sql += " AND postid = " + postID ;
        }
        if (userID > 0) {
            sql += " AND userid = " + userID  ;

        }
        if (datapostagem != null) {
            sql += " AND datapostagem = " + datapostagem ;
        }
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int idComentario = resultSet.getInt("id");
            String coment = resultSet.getString("coment");
            int postid = resultSet.getInt("postid");
            int userid = resultSet.getInt("userid");
            java.sql.Date Data = resultSet.getDate("datapostagem");
            comentarios.add(new Comentario(idComentario, coment, postid, userid, Data));
        }
        return comentarios;
    }

}
