package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * gerencia conexão com a base de dados. Possui intruções necessarias para
 * conectar ao banco
 *
 * @author devsys-a
 */
public class MySQLConnection {

    //Define String de conexão com o banco
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.16.0.30:3306/frc_bookstore";

    private static final String USER = "fernando";
    private static final String PASS = "21262800";

    /**
     * cria conexão com banco de dados MYSQL
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na Conexao com o BD. Verifique", ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Erro na carga do driver. Verifique", ex);
        }

    }

    /**
     * fecha a conexão com o DB.
     *
     * @param conn Conection a ser fechada.
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * fecha a conexão com o DB.
     *
     * @param conn conexão.
     * @param stmt Statement
     */
    public static void closeConnection(Connection conn, PreparedStatement stmt) {
        closeConnection(conn);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(
                    MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        closeConnection(conn, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
