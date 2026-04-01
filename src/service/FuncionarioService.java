package service;

import modelo.Funcionario;
import repository.IFuncionarioRepository;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioService {

    private final IFuncionarioRepository repository;

    public FuncionarioService(IFuncionarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Funcionario f) throws SQLException {
        if (f.getNome() == null || f.getNome().isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");
        if (f.getCargo() == null || f.getCargo().isBlank())
            throw new IllegalArgumentException("Cargo é obrigatório.");
        if (f.getSalario() <= 0)
            throw new IllegalArgumentException("Salário deve ser maior que zero.");
        repository.inserir(f);
    }

    public void atualizar(Funcionario f) throws SQLException {
        repository.atualizar(f);
    }

    public void deletar(int id) throws SQLException {
        repository.deletar(id);
    }

    public Funcionario buscarPorId(int id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public List<Funcionario> listar() throws SQLException {
        return repository.listar();
    }

    public List<Funcionario> listarPorFiltro(String filtro) throws SQLException {
        return repository.listarPorFiltro(filtro);
    }
}