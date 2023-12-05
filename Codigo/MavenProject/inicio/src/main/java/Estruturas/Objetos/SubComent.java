package Estruturas.Objetos;

public class SubComent {
    int id;
    String conteudo;
    int comentId;
    int userID;
    SubComent(){

    }

    public SubComent(int id, String conteudo, int comentId, int userID){
        this.id = id;
        this.conteudo = conteudo;
        this.comentId = comentId;
    }
    //setters

    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }
    public void setComentId(int comentId){
        this.comentId = comentId;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    //getters
    public int getId(){
        return this.id;
    }
    public String getConteudo(){
        return this.conteudo;
    }
    public int getComentId(){
        return this.comentId;
    }
    public int getUserID(){
        return this.userID;
    }
}
