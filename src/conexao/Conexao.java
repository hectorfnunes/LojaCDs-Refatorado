package conexao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    private static String url;
    private static String user;
    private static String pass;

    static {
        try (InputStream input = Conexao.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            url  = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            pass = prop.getProperty("db.pass");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar db.properties: " + e.getMessage());
        }
    }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}