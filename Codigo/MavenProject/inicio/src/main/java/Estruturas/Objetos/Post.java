package Estruturas.Objetos;

public class Post {
    //post possui id,postagem,forum_id,usuario_id, categoria(1,2,3);

    private int id;
    private String postagem;
    private int forumID;
    private int userID;
    private int categoriaID;
    
    //construtor para carregar posts
    public Post(int id, String postagem, int forumID, int userID, int categoriaID) { // lembre que o id do post é serial, não precisa inseri-lo no bd
        this.id = id;
        this.postagem = postagem;
        this.forumID = forumID;
        this.userID = userID;
        this.categoriaID = categoriaID;
    }
    //construtor para lançar posts
    public Post(String postagem, int forumID, int userID, int categoriaID) { // lembre que o id do post é serial, não precisa inseri-lo no bd
        this.postagem = postagem;
        this.forumID = forumID;
        this.userID = userID;
        this.categoriaID = categoriaID;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setPostagem(String postagem) {
        this.postagem = postagem;
    }
    public void setForumID(int forumID) {
        this.forumID = forumID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setcategoriaID(int categoriaID) {
        if(categoriaID == 1 || categoriaID == 2 || categoriaID == 3){
            this.categoriaID = categoriaID;
        }else{
            System.out.println("categoriaID invalida");
        }
        
    }

    //getter methods
    public int getId() {
        return id;
    }
    public String getPostagem() {
        return postagem;
    }
    public int getForumID() {
        return forumID;
    }
    public int getUserID() {
        return userID;
    }
    public int getcategoriaID() {
        return categoriaID;
    }








}
