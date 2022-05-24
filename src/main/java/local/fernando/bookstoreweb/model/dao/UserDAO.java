package local.fernando.bookstoreweb.model.dao;

import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.fernando.bookstoreweb.model.bean.User;

/**
 *
 * @author devsys-a
 */
public class UserDAO {

    private static final String SQL_INSERT = "INSERT INTO user ("
            + "email,password,fullname) "
            + "VALUE (?, ?, ? )";

    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_ID = "SELECT * FROM user"
            + "WHERE id - ?";

    private static final String SQL_SELECT = "SELECT fullname FROM user "
            + "where email = ? AND password = ?";

    private static final String SQL_UPDATE = "UPDATE user SET email = ?,"
            + "password = ?, fullname = ? "
            + "WHERE ID = ?";

    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?";

    /**
     * Insere um usuario na base de dados User
     *
     * @param u
     */
    public void create(User u) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullName());

            //Execute a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null,
                    "iNCLUSÃO: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null,
                            sQLException);
        } finally {
            //Encerra a conexão com o banco e o  statement.
            MySQLConnection.closeConnection(conn, stmt);

        }
    }

    /**
     * Retorna todos os registro de tabela produto
     *
     * @return
     */
    public List<User> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;
        List<User> listaUsers = null;

        try {
            // Prepara a string de SELECT e executa a query.
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            //Carrega os dados do Results rs, converte em User e 
            //adiciona na lista de retorno.
            listaUsers = new ArrayList<>();

            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                listaUsers.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsers;
    }

    /**
     * Retorna um User da tabela User.
     *
     * @param id IDentificação do User
     * @return
     */
    public User getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));

            }
        } catch (SQLException sQLException) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return u;
    }

    /**
     * atualiza um registro na tabela User.
     *
     * @param u User a ser atualizado.
     */
    public void update(User u) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(4, u.getId());
            stmt.setString(2, u.getEmail());
            stmt.setString(1, u.getPassword());
            stmt.setString(3, u.getFullName());

            //Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null,
                    "Update :" + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            //Encerra a conexâo com banco e o  statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Exclui um produto com base do id fornecido.
     *
     * @param id IDentificação do produto.
     */
    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            //Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null,
                    "Delete: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);

        } finally {
            //Encerra a conexão com o banco e o atatement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public User checkLogin(String email, String password) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;

        try {

            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setFullname(rs.getString("fullname"));
                u.setEmail(email);

            }
        } catch (SQLException aQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, aQLException);

        } finally {

            MySQLConnection.closeConnection(conn, stmt);

        }
        return u;
    }

}
