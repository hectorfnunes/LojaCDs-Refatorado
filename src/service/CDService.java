package service;

import modelo.CD;
import repository.ICDRepository;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CDService {

    private final ICDRepository repository;

    public CDService(ICDRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(CD cd) throws SQLException {
        if (cd.getTitulo() == null || cd.getTitulo().isBlank())
            throw new IllegalArgumentException("Título é obrigatório.");
        if (cd.getPreco() == null || cd.getPreco().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        if (cd.getEstoque() < 0)
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        repository.inserir(cd);
    }

    public void atualizar(CD cd) throws SQLException {
        if (cd.getId() <= 0)
            throw new IllegalArgumentException("ID inválido.");
        repository.atualizar(cd);
    }

    public void deletar(int id) throws SQLException {
        repository.deletar(id);
    }

    public CD buscarPorId(int id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public List<CD> listar(String filtro) throws SQLException {
        return repository.listar(filtro == null ? "" : filtro);
    }
}