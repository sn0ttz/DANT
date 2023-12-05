package Service;

import Estruturas.Tools.Converter;
import spark.Request;
import spark.Response;
import Estruturas.Objetos.User;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;

import Estruturas.DAOStruct.UserDAO;

public class UserService {
    UserDAO user = new UserDAO();
    Converter conv = new Converter();

    public Boolean registro(Request req, Response res) {
        String nome = req.queryParams("nome");
        String email = req.queryParams("email");
        String senha = req.queryParams("senha");
        String dataNasc = req.queryParams("nasc");

        senha = conv.CriptografarMd5(senha);

        try {
            user.inserirUsuario(nome, senha, email, dataNasc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int auth(Request req, Response res) throws Exception {
        String email = req.queryParams("email");
        String senha = req.queryParams("senha");

        senha = Converter.CriptografarMd5(senha);
        int id = user.authentication(email, senha);

        if (id == -1) {
            return -1;
            // retorna 0 se o usuario não for encontrado no BD
        } else {
            return id;
            // retorna o ID do usuario caso o mesmo for encontrado
        }
    }

    public Boolean update(Request req, Response res) throws Exception {
        String nome = req.queryParams("nome");
        String email = req.queryParams("email");
        String senha = req.queryParams("senha");
        String dataNasc = req.queryParams("nasc");
        int id = Integer.parseInt(req.queryParams("id"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(dataNasc);
        java.sql.Date dataNascDate = new java.sql.Date(parsedDate.getTime());

        senha = conv.CriptografarMd5(senha);

        if (id == -1) {
            return false;
        }

        try {
            user.updateUserName(id, nome);
            user.updateUserEmail(id, email);
            user.updateUserPassword(id, senha);
            user.updateUserDateOfBirth(id, dataNascDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String convertUserToJson(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);

    }

    public String getUserById(Request req, Response res) {
        int id = Integer.parseInt(req.queryParams("id"));
        try {
            return (convertUserToJson(user.GetUserByID(id)));
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean deleteUserById(Request req, Response res) {
        int id = Integer.parseInt(req.queryParams("id"));
        try {
            user.deleteUserByID(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean subscribe (Request req, Response res){
        int id = Integer.parseInt(req.queryParams("id"));
        boolean resp = user.updateSubscription(id, true);
        return resp;
    }

}// fazer service para deletar usuario
