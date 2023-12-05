package Estruturas.DAOStruct;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.ResultSet;
import Estruturas.Objetos.SubComent;

public class SubComentDAO extends DAO{
    SubComentDAO(){
        super();
    }

   public void InserirSubcoment(SubComent sc) throws SQLException{
    String sql = "insert into subcoment (conteudo,comentid,user_id) values (?,?,?)";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, sc.getConteudo());
        preparedStatement.setInt(2, sc.getComentId());
        preparedStatement.executeUpdate();
   }


   public Boolean editSubcoment(String newsubcoment, int id){
    String sql = "update subcoment set conteudo = ? where id = ?";
    try{
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, newsubcoment);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        return true;
    }catch(SQLException e){
        e.printStackTrace();
        return false;
    }
   }

   public LinkedList<SubComent> getSubcomentBy(int comentID,int userid,String conteudo) throws SQLException{
    LinkedList<SubComent> subcoments = new LinkedList<SubComent>();
    String sql = "select * from subcoment where 1=1";
    if(comentID > 0){
        sql += " and comentid = " + comentID;
    }
    if(userid > 0){
        sql += " and user_id = "+ userid ;
    }
    if(conteudo != ""){
        sql += " and conteudo like \'%" + conteudo + "%\'";
    }

    PreparedStatement preparedStatement = conexao.prepareStatement(sql);
    
    ResultSet resultSet = preparedStatement.executeQuery();
    while(resultSet.next()){
        int id = resultSet.getInt("id");
        String conteudoSubcoment = resultSet.getString("conteudo");
        int comentid = resultSet.getInt("comentid");
        int user_id = resultSet.getInt("user_id");
        SubComent sc = new SubComent(id,conteudoSubcoment,comentid,user_id);
        subcoments.add(sc);
    }
    return subcoments;
   }

   public void DeleteSubcomentByID(int id) throws SQLException{
    String sql = "delete from subcoment where id = ?";
    PreparedStatement preparedStatement = conexao.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
   }

   public void DeleteSubcomentBy(int UserId, int ComentID) throws SQLException{
    String sql = "delete from subcoment where 1=1";
    if(UserId > 0){
        sql += " and user_id = " + UserId;
    }
    if(ComentID > 0){
        sql += " and comentid = " + ComentID;
    }
    
    PreparedStatement preparedStatement = conexao.prepareStatement(sql);
    
    preparedStatement.executeUpdate();
   }

    
    
}
