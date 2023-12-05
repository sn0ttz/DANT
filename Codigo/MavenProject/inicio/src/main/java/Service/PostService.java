package Service;

import com.google.gson.Gson;

import Estruturas.DAOStruct.ForumDAO;
import Estruturas.DAOStruct.PostDAO;

import spark.Request;
import spark.Response;

/**
 * PostService
 */
public class PostService {
    PostDAO post = new PostDAO();
    Gson gson = new Gson();
    
    public int newPost (Request req, Response res){
        int resp = 0;
        String postagem = req.queryParams("postagem");
        int forumID = Integer.parseInt(req.queryParams("forumID"));
        int userID = Integer.parseInt(req.queryParams("userID"));
        int categoria = Integer.parseInt(req.queryParams("categoria"));

        resp = post.InserirPostagem(postagem, forumID, userID, categoria);

        return resp;
    }

    public Boolean deletePost(Request req, Response res){
        Boolean resp = false;
        int id = Integer.parseInt(req.queryParams("id"));
        resp = post.DeletePostById(id);
        return resp;
    }
}