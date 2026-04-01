package modelo;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String cargo;
    private double salario;

    public Funcionario() {}

    public Funcionario(String nome, String cpf, String cargo, double salario) {
        this.nome   = nome;
        this.cpf    = cpf;
        this.cargo  = cargo;
        this.salario = salario;
    }

    public int getId()                   { return id; }
    public void setId(int id)            { this.id = id; }
    public String getNome()              { return nome; }
    public void setNome(String n)        { this.nome = n; }
    public String getCpf()               { return cpf; }
    public void setCpf(String c)         { this.cpf = c; }
    public String getCargo()             { return cargo; }
    public void setCargo(String c)       { this.cargo = c; }
    public double getSalario()           { return salario; }
    public void setSalario(double s)     { this.salario = s; }
}