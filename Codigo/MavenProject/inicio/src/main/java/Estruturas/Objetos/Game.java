package Estruturas.Objetos;
public class Game  {
    public int appid;
    public String nome;
    public String json;

    public Game(){

    }

    public Game(int appid, String Nome,String json){
        this.appid = appid;
        this.nome = Nome;
        this.json = json;
    }   



    //getter methods
    public int getAppid() {
        return appid;
    }
    public String getNome() {
        return nome;
    }
    public String getJson() {
        return json;
    }

    //setters
    public void setAppid(int appid) {
        this.appid = appid;
    }
    public void setNome(String Nome) {
        this.nome = Nome;
    }
    public void setJson(String json) {
        this.json = json;
    }
    

}
