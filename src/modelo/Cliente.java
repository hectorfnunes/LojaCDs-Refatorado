package modelo;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente() {}

    public Cliente(String nome, String cpf, String email, String telefone) {
        this.nome     = nome;
        this.cpf      = cpf;
        this.email    = email;
        this.telefone = telefone;
    }

    public int getId()                   { return id; }
    public void setId(int id)            { this.id = id; }
    public String getNome()              { return nome; }
    public void setNome(String n)        { this.nome = n; }
    public String getCpf()               { return cpf; }
    public void setCpf(String c)         { this.cpf = c; }
    public String getEmail()             { return email; }
    public void setEmail(String e)       { this.email = e; }
    public String getTelefone()          { return telefone; }
    public void setTelefone(String t)    { this.telefone = t; }
}