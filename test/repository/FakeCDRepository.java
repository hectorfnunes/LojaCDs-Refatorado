package repository;

import modelo.CD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório falso (Fake) de CDs para uso exclusivo nos testes unitários.
 * Não acessa banco de dados — armazena tudo em memória.
 * Isso permite testar o CDService sem precisar de conexão com BD.
 */
public class FakeCDRepository implements ICDRepository {

    // Lista em memória que simula o banco de dados
    private final List<CD> banco = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void inserir(CD cd) throws SQLException {
        cd.setId(proximoId++);
        banco.add(cd);
    }

    @Override
    public void atualizar(CD cd) throws SQLException {
        for (int i = 0; i < banco.size(); i++) {
            if (banco.get(i).getId() == cd.getId()) {
                banco.set(i, cd);
                return;
            }
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        banco.removeIf(cd -> cd.getId() == id);
    }

    @Override
    public CD buscarPorId(int id) throws SQLException {
        for (CD cd : banco) {
            if (cd.getId() == id) return cd;
        }
        return null;
    }

    @Override
    public List<CD> listar(String filtro) throws SQLException {
        if (filtro == null || filtro.isBlank()) return new ArrayList<>(banco);
        List<CD> resultado = new ArrayList<>();
        for (CD cd : banco) {
            if (cd.getTitulo().toLowerCase().contains(filtro.toLowerCase())) {
                resultado.add(cd);
            }
        }
        return resultado;
    }

    /** Retorna quantos CDs foram inseridos (útil para verificar no teste) */
    public int totalCadastrados() {
        return banco.size();
    }
}