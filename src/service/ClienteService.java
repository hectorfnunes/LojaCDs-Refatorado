package service;

import modelo.Cliente;
import repository.IClienteRepository;
import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private final IClienteRepository repository;

    public ClienteService(IClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Cliente c) throws SQLException {
        if (c.getNome() == null || c.getNome().isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");
        if (c.getCpf() == null || c.getCpf().isBlank())
            throw new IllegalArgumentException("CPF é obrigatório.");
        repository.inserir(c);
    }

    public void atualizar(Cliente c) throws SQLException {
        repository.atualizar(c);
    }

    public void deletar(int id) throws SQLException {
        repository.deletar(id);
    }

    public Cliente buscarPorId(int id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public List<Cliente> listar() throws SQLException {
        return repository.listar();
    }

    public List<Cliente> listarPorNome(String nome) throws SQLException {
        return repository.listarPorNome(nome);
    }
}