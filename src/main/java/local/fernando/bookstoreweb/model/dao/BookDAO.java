package local.fernando.bookstoreweb.model.dao;

import local.fernando.bookstoreweb.model.bean.Book;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author devsys-a
 */
public class BookDAO {

    private static final String SQL_INSERT = "INSERT INTO book ("
            + "Autor,Titulo,Preco) "
            + "VALUE (?, ?, ? )";

    private static final String SQL_SELECT_ALL = "SELECT * FROM book";
    private static final String SQL_SELECT_ID = "SELECT * FROM book"
            + "WHERE id - ?";

    private static final String SQL_UPDATE = "UPDATE book SET Autor = ?,"
            + "Titulo = ?, Preco = ? "
            + "WHERE ID = ?";

    private static final String SQL_DELETE = "DELETE FROM book WHERE id = ?";


/**
 * Insere um usuario na base de dados Book
 *
 * @param b
 */
public void create(Book b) {
    Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, b.getAutor());
            stmt.setString(2, b.getTitulo());
            stmt.setDouble(3, b.getPreco());
           
        
            //Execute a query
            int auxRetorno = stmt.executeUpdate();
        
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO,null,
                    "iNCLUSÃO: " + auxRetorno);
                    
            }catch (SQLException sQLException ) {
                Logger.getLogger(BookDAO.class  
                .getName()).log(Level.SEVERE, null,
                sQLException);
            } finally {
                //Encerra a conexão com o banco e o  statement.
                MySQLConnection.closeConnection(conn , stmt);
        
     
            }
}

      
      /**
       * Retorna todos os registro de tabela produto
       * @return
       */
       public List<Book> getResults() {
           Connection conn = MySQLConnection.getConnection();
           PreparedStatement stmt = null;
           ResultSet rs = null;
           Book b = null;
           List<Book> listaBooks = null;
           
           try {
               // Prepara a string de SELECT e executa a query.
               stmt = conn.prepareStatement(SQL_SELECT_ALL);
               rs = stmt.executeQuery();
               
               //Carrega os dados do Results rs, converte em Book e 
               //adiciona na lista de retorno.
               listaBooks = new ArrayList<>();
               
               while(rs.next()) {
                   b = new Book();
                   b.setId(rs.getInt("id"));
                   b.setAutor(rs.getString("Autor"));
                   b.setTitulo(rs.getString("Titulo"));
                   b.setPreco(rs.getDouble("Preco"));
                   listaBooks.add(b);
               }
               
            } catch (SQLException ex) {
                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listaBooks;
       }
       
       /**
        * Retorna um Book da tabela Book.
        * @param id IDentificação do Book
        * @return
        */
       public Book getResultById(int id) {
           Connection conn = MySQLConnection.getConnection();
           PreparedStatement stmt = null;
           ResultSet rs = null;
           Book b = null;
           
           try {
               stmt = conn.prepareStatement(SQL_SELECT_ID);
               stmt.setInt(1, id);
               rs = stmt.executeQuery();
               
               if (rs.next())  {
                   b = new Book();
                   b.setId(rs.getInt("id"));
                   b.setAutor(rs.getString("Autor"));
                   b.setTitulo(rs.getString("Titulo"));
                   b.setPreco(rs.getDouble("Preco"));
                   
                  
               }
           }catch (SQLException sQLException) {
               Logger.getLogger(Book.class.getName()).log(Level.SEVERE,null,sQLException);
           }finally {
               // Encerra a conexão com o banco e o statement.
               MySQLConnection.closeConnection(conn, stmt, rs);
           }
           return b;
       }

       /**
        * atualiza um registro na tabela Book.
        * @param b Book a ser atualizado.
        */
       public void update(Book b) {
           Connection conn = MySQLConnection.getConnection();
           PreparedStatement stmt = null;
           
           try {
               stmt = conn.prepareStatement(SQL_UPDATE);
               stmt.setInt(4 , b.getId());
               stmt.setString(2 , b.getAutor());
               stmt.setString(1 , b.getTitulo());
               stmt.setDouble(3 , b.getPreco());
              
               
               //Executa a query
               int auxRetorno = stmt.executeUpdate();
               
               Logger.getLogger(BookDAO.class.getName()).log(Level.INFO,null,
                       "Update :" + auxRetorno);
               
           }catch (SQLException sQLException) {
               Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE,null,
                       sQLException);
           }finally {
               //Encerra a conexâo com banco e o  statement.
               MySQLConnection.closeConnection(conn, stmt);
           }
       }

       /**
        * Exclui um produto com base do id fornecido.
        * @param id IDentificação do produto.
        */
       public void delete (int id ) {
           Connection conn = MySQLConnection.getConnection();
           PreparedStatement stmt = null;
           
           try {
               stmt = conn.prepareStatement(SQL_DELETE);
               stmt.setInt(1 , id);
               
               //Executa a query
               int auxRetorno = stmt.executeUpdate();
               
               Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null,
                       "Delete: " + auxRetorno);
               
            } catch (SQLException sQLException) {
                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE,null, sQLException);
                
            }finally {
               //Encerra a conexão com o banco e o atatement.
               MySQLConnection.closeConnection(conn , stmt);
           }
       }
}
             
