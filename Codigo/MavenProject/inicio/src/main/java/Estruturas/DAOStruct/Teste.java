package Estruturas.DAOStruct;
import java.sql.PreparedStatement;
import Estruturas.DAOStruct.GameDAO;
import Estruturas.Objetos.Forum;
import Estruturas.Objetos.Game;
import java.util.LinkedList;

import com.google.gson.Gson;

import Estruturas.Objetos.Post;
import Estruturas.Objetos.User;

import java.sql.ResultSet;
import java.sql.SQLException;
public class Teste {
    public static void delete(){
        DAO dao = new DAO();
        String sql = "DELETE FROM games";
        try (PreparedStatement preparedStatement = dao.conexao.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar jogo", e);
        }
    }
    public static void main(String[] args)throws Exception {
      UserDAO user = new UserDAO();
      System.out.println(convertUserToJson(user.GetUserByID(20)));


       
    }

    private static String convertUserToJson(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
        
    }
}
