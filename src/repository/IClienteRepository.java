package repository;

import modelo.Cliente;
import java.sql.SQLException;
import java.util.List;

public interface IClienteRepository {
    void inserir(Cliente cliente) throws SQLException;
    void atualizar(Cliente cliente) throws SQLException;
    void deletar(int id) throws SQLException;
    Cliente buscarPorId(int id) throws SQLException;
    List<Cliente> listar() throws SQLException;
    List<Cliente> listarPorNome(String nome) throws SQLException;
}