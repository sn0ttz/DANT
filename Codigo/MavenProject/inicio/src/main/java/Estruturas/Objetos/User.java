package Estruturas.Objetos;
public class User {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private byte[] foto;
    private java.sql.Date dataNasc;
    private java.sql.Date dataCadastro;
    private boolean assinatura;


    User() {

    }
    //construtor para inserir no banco
   public User(String nome, String senha, String email, byte[] foto, java.sql.Date dataNasc) {
    
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.foto = foto;
        this.dataNasc = dataNasc;
    }
    //construtor para pegar do banco
    public  User(int id,String nome, String senha, String email, byte[] foto, java.sql.Date dataNasc, boolean assinatura) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.foto = foto;
        this.dataNasc = dataNasc;
        this.assinatura = assinatura;

    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String Nome) {
        this.nome = Nome;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public void setDataNasc(java.sql.Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    public void setDataCadastro(java.sql.Date dataCadastro) {
    }
    public void setAssinatura(boolean assinatura){
        this.assinatura = assinatura;
    }

    //getters
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
    public String getEmail() {
        return email;
    }
    public byte[] getFoto() {
        return foto;
    }
    public java.sql.Date getDataNasc() {
        return dataNasc;
    }
    public java.sql.Date getDataCadastro() {
        return dataCadastro;
    }
    public boolean getAssinatura(){
        return assinatura;
    }

}
