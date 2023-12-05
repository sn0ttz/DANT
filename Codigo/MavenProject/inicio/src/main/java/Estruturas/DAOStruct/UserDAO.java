package Estruturas.DAOStruct;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import Estruturas.Tools.*;
import Estruturas.Objetos.User;

import java.sql.ResultSet;


public class UserDAO extends DAO {
    public UserDAO() {
        super();
    }
    // super() chama o construtor da classe pai, no caso DAO. que retorna a conexao
    // com o banco de dados

    // usuario tem as colunas id,nome,senha,emailfoto,datanasc,datacadastro
    // lembre se que ID de usuario é serial, não precisa inseri-lo;

    public boolean autenticandoEmail(String email){
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean inserirUsuario(String nome, String senha, String email, String dataNascStr) {
       if(autenticandoEmail(email)) { return false;} //validando email
       
        try {
    
            // Converte a data de String para java.util.Date usando SimpleDateFormat
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dataNascStr);
            java.sql.Date dataNasc = new java.sql.Date(parsedDate.getTime());
    
            // SQL para inserir um novo usuário
            String sql = "INSERT INTO usuario (nome, senha, email, datanasc) VALUES (?, ?, ?, ?)";
    
            // Cria um PreparedStatement com a consulta SQL
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
    
            // Define os parâmetros da consulta com os valores passados como argumentos
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3, email);  
            preparedStatement.setDate(4, dataNasc);
    
            // Executa a consulta de inserção
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    


    public boolean deleteUserByID(int id){

        String sql = "DELETE FROM usuario WHERE id = ?";
        try{

              PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public User GetUserByID(int id){
        User usuario = null;

        String sql = "SELECT * FROM usuario WHERE id = ?";
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int idUsuario = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                byte[] foto = resultSet.getBytes("foto");
                java.sql.Date dataNasc = resultSet.getDate("datanasc");
                boolean assinatura = resultSet.getBoolean("assinatura");
                usuario = new User(idUsuario,nome,senha,email,foto,dataNasc,assinatura);

                return usuario;
            }else{
                return null;
            }
          
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public LinkedList<User> getAllUsers() throws Exception{ //não to afim de tratar exceção aqui
        LinkedList<User> usuarios = new LinkedList<User>();
        String sql = "SELECT * FROM usuario ORDER BY id";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int idUsuario = resultSet.getInt("id"); 
            String nome = resultSet.getString("nome");
            String senha = resultSet.getString("senha");
            String email = resultSet.getString("email");
            byte[] foto = resultSet.getBytes("foto");
            java.sql.Date dataNasc = resultSet.getDate("datanasc");
            boolean assinatura = resultSet.getBoolean("assinatura");
            usuarios.add(new User(idUsuario,nome,senha,email,foto,dataNasc,assinatura));
        }


        return usuarios;
    }

    public int authentication(String email, String senha) throws SQLException {
    
        String sql = "SELECT * FROM usuario WHERE email = ? and senha = ?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, senha);
    
        ResultSet resultSet = preparedStatement.executeQuery();
    
        // Verifica se o ResultSet contém alguma linha
        if (resultSet.next()) {
            return resultSet.getInt("id");  // Usuário encontrado
        } else {
            return -1; // Usuário não encontrado
        }
    }
    
    public boolean UpdateUser(User user) {
String sql = "UPDATE usuario SET nome = ?, senha = ?, email = ?, foto = ?, datanasc = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getSenha());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setBytes(4, user.getFoto());
            preparedStatement.setDate(5, user.getDataNasc());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserName(int id, String newUserName) {
        String sql = "UPDATE usuario SET nome = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, newUserName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUserPassword(int id, String newPassword) {
        newPassword = Converter.CriptografarMd5(newPassword);
        String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateUserEmail(int id, String newEmail) {
        String sql = "UPDATE usuario SET email = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPhoto(int id, byte[] newPhoto) {
        String sql = "UPDATE usuario SET foto = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setBytes(1, newPhoto);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateUserDateOfBirth(int id, java.sql.Date newDateOfBirth) {
        String sql = "UPDATE usuario SET datanasc = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, newDateOfBirth);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean updateSubscription(int id, boolean newSubscription) {
        String sql = "UPDATE usuario SET assinatura = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setBoolean(1, newSubscription);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    



}
