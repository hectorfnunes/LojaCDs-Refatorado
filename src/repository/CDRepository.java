package repository;

import conexao.Conexao;
import modelo.CD;
import java.sql.*;
import java.util.*;

public class CDRepository implements ICDRepository {

    private CD mapear(ResultSet rs) throws SQLException {
        CD cd = new CD();
        cd.setId(rs.getInt("id"));
        cd.setTitulo(rs.getString("titulo"));
        cd.setArtista(rs.getString("artista"));
        cd.setGenero(rs.getString("genero"));
        cd.setAno(rs.getInt("ano"));
        cd.setPreco(rs.getBigDecimal("preco"));
        cd.setEstoque(rs.getInt("estoque"));
        return cd;
    }

    @Override
    public void inserir(CD cd) throws SQLException {
        String sql = "INSERT INTO cds (titulo, artista, genero, ano, preco, estoque) VALUES (?,?,?,?,?,?)";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cd.getTitulo());
            ps.setString(2, cd.getArtista());
            ps.setString(3, cd.getGenero());
            ps.setInt(4, cd.getAno());
            ps.setBigDecimal(5, cd.getPreco());
            ps.setInt(6, cd.getEstoque());
            ps.executeUpdate();
        }
    }

    @Override
    public void atualizar(CD cd) throws SQLException {
        String sql = "UPDATE cds SET titulo=?, artista=?, genero=?, ano=?, preco=?, estoque=? WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cd.getTitulo());
            ps.setString(2, cd.getArtista());
            ps.setString(3, cd.getGenero());
            ps.setInt(4, cd.getAno());
            ps.setBigDecimal(5, cd.getPreco());
            ps.setInt(6, cd.getEstoque());
            ps.setInt(7, cd.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM cds WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public CD buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cds WHERE id=?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<CD> listar(String filtro) throws SQLException {
        List<CD> lista = new ArrayList<>();
        String sql = "SELECT * FROM cds";
        if (!filtro.isEmpty()) sql += " WHERE titulo LIKE ? OR artista LIKE ?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (!filtro.isEmpty()) {
                ps.setString(1, "%" + filtro + "%");
                ps.setString(2, "%" + filtro + "%");
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }
}