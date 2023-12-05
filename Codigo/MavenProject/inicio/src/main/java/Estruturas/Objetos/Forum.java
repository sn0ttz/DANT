package Estruturas.Objetos;

public class Forum {
    int GameID;
    String Nome;
    int id;

    //construtor para carregar forum
    public Forum(int GameID, String Nome, int id) { // lembre que o id do forum é serial, não precisa inseri-lo no bd
        this.GameID = GameID;
        this.Nome = Nome;
        this.id = id;
    }
    
    //construtor para lançar forum
    public Forum(int GameID, String Nome) { // lembre que o id do forum é serial, não precisa inseri-lo no bd
        this.GameID = GameID;
        this.Nome = Nome;
    }

    // getter methods
    public int getGameID() {
        return GameID;
    }

    public String getNome() {
        return Nome;
    }

    public int getId() {
        return id;
    }

    //setters
    public void setGameID(int GameID) {
        this.GameID = GameID;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setId(int id) {
        this.id = id;
    }


}
