package repository;

import modelo.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface IFuncionarioRepository {
    void inserir(Funcionario f) throws SQLException;
    void atualizar(Funcionario f) throws SQLException;
    void deletar(int id) throws SQLException;
    Funcionario buscarPorId(int id) throws SQLException;
    List<Funcionario> listar() throws SQLException;
    List<Funcionario> listarPorFiltro(String filtro) throws SQLException;
    Funcionario buscarPorCpf(String cpf) throws SQLException;
}