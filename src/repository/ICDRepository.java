package repository;

import modelo.CD;
import java.sql.SQLException;
import java.util.List;

public interface ICDRepository {
    void inserir(CD cd) throws SQLException;
    void atualizar(CD cd) throws SQLException;
    void deletar(int id) throws SQLException;
    CD buscarPorId(int id) throws SQLException;
    List<CD> listar(String filtro) throws SQLException;
}