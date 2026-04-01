package repository;

import conexao.Conexao;
import modelo.Funcionario;
import java.sql.*;
import java.util.*;

public class FuncionarioRepository implements IFuncionarioRepository {

    private Funcionario mapear(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setId(rs.getInt("id"));
        f.setNome(rs.getString("nome"));
        f.setCpf(rs.getString("cpf"));
        f.setCargo(rs.getString("cargo"));
        f.setSalario(rs.getDouble("salario"));
        return f;
    }

    @Override
    public void inserir(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, cargo, salario) VALUES (?,?,?,?)";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNome());
            ps.setString(2, f.getCpf());
            ps.setString(3, f.getCargo());
            ps.setDouble(4, f.getSalario());
            ps.executeUpdate();
        }
    }

    @Override
    public void atualizar(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionarios SET nome=?, cpf=?, cargo=?, salario=? WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNome());
            ps.setString(2, f.getCpf());
            ps.setString(3, f.getCargo());
            ps.setDouble(4, f.getSalario());
            ps.setInt(5, f.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Funcionario> listarPorFiltro(String filtro) throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios WHERE nome LIKE ? OR cargo LIKE ?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ps.setString(2, "%" + filtro + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    @Override
    public Funcionario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE cpf=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }
}