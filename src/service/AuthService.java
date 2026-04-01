package service;

import modelo.Funcionario;
import repository.IFuncionarioRepository;
import sessao.UserSession;
import java.sql.SQLException;

public class AuthService {

    private final IFuncionarioRepository repository;

    public AuthService(IFuncionarioRepository repository) {
        this.repository = repository;
    }

    public boolean login(String cpf, String cargo) throws SQLException {
        Funcionario f = repository.buscarPorCpf(cpf);
        if (f != null && f.getCargo().equalsIgnoreCase(cargo)) {
            UserSession.getInstance().iniciarSessao(f.getId(), f.getCargo());
            return true;
        }
        return false;
    }

    public void logout() {
        UserSession.getInstance().encerrarSessao();
    }
}