package Service;

import spark.Request;
import spark.Response;
import Estruturas.DAOStruct.ForumDAO;
import Estruturas.DAOStruct.PostDAO;
import Estruturas.Objetos.Forum;

import com.google.gson.Gson;

public class ForumService {
    ForumDAO forum = new ForumDAO();
    PostDAO post = new PostDAO();
    Gson gson = new Gson();

    public String getForumById(Request req, Response res) throws Exception {
        int id = Integer.parseInt(req.queryParams("id"));
        Forum forumVar = forum.getForumByGameID(id);
        System.out.println(forumVar.getId());
        return gson.toJson(post.GetPostsBy(forumVar.getId(), 0, 0));
    }

    public Boolean newForum(Request req, Response res) {
        Boolean resp = false;
        int gameId = Integer.parseInt(req.queryParams("gameID"));
        String nome = req.queryParams("gameName");

        resp = forum.insertForum(gameId, nome);
        return resp;
    }

    public Boolean deleteForum(Request req, Response res) {
        Boolean resp = false;
        int id = Integer.parseInt(req.queryParams("id"));
        resp = forum.removeForumByID(id);
        return resp;
    }
}
