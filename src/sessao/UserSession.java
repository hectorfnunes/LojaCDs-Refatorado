package sessao;

public class UserSession {

    private static UserSession instance;

    private int idFuncionario = -1;
    private String cargo = "";

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void iniciarSessao(int idFuncionario, String cargo) {
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
    }

    public void encerrarSessao() {
        this.idFuncionario = -1;
        this.cargo = "";
    }

    public int getIdFuncionario() { return idFuncionario; }
    public String getCargo()      { return cargo; }

    public boolean isGerente() {
        return "gerente".equalsIgnoreCase(cargo);
    }

    public boolean isSessaoAtiva() {
        return idFuncionario != -1;
    }
}