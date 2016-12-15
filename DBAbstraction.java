/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wombats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author jesse
 */
public class DBAbstraction {
    
    private String dbUser = "test";
    private String dbPassword = "password";
    private String dbUrl = "jdbc:derby://localhost:1527/bookburrow";
    private Connection conn;
    
    public DBAbstraction() {
        try {
            this.conn = getConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.dbUser);
        connectionProps.put("password", this.dbPassword);
        
        conn = DriverManager.getConnection(this.dbUrl + ";create=true", connectionProps);
        return conn;
    }
    
    private ResultSet runQuery(String query, boolean update) {
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = this.conn.createStatement();
            if (update) {
                stmt.executeUpdate(query);
            } else {
                rs = stmt.executeQuery(query);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    private int getBookId(Book book) {
        int id = 1;
        String retrieveBook = "SELECT * from books WHERE title='" + book.getTitle() + 
                "' AND author='" + book.getAuthor() + "' AND genre='" + book.getGenre() + "'";
        System.out.println(retrieveBook);
        ResultSet rs = runQuery(retrieveBook, false);
        try {
            id = rs.getInt("id");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    private int getReviewId(Review review) {
        int id = 1;
        String retrieveReview = "SELECT * from reviews WHERE book_id='" + review.getBookId() + 
                "' AND rating='" + review.getRating() + "' AND review='" + 
                review.getContent() + "'";
        ResultSet rs = runQuery(retrieveReview, false);
        try {
            id = rs.getInt("id");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return id;
    }
    
    public ArrayList<Book> searchBooks(String search) {
        ArrayList<Book> books = new ArrayList<>();
        String allLikeSearch = "SELECT * FROM books WHERE title LIKE '%" + search +
                "%' OR author LIKE '%" + search + "%' OR genre LIKE '%" + search + "%'";
        
        ResultSet rs = runQuery(allLikeSearch, false);
        
        try {  
            while(rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                books.add(new Book(title, author, genre));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    
    public ArrayList<Review> renderReviews(Book book) {
        ArrayList<Review> reviews = new ArrayList<>();
        int bookId = getBookId(book);
        String getReviews = "SELECT * from reviews WHERE book_id=" + bookId;
        ResultSet rs = runQuery(getReviews, false);
        
        try {
            while(rs.next()) {
                int rating = rs.getInt("rating");
                String content = rs.getString("review");
                reviews.add(new Review(rating, content, bookId));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return reviews;
    }
    
    public ArrayList<Book> renderFavorites(String username) {
        ArrayList<Book> favorites = new ArrayList<>();
        String query = "SELECT * FROM favorites WHERE username='" + username
                + "'";
        ResultSet rs = runQuery(query, false);
        
        try {
            while(rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                favorites.add(new Book(title, author, genre));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return favorites;
    }
    
    public User renderUser(String username) {
        User user = null;
        String getUser = "SELECT * FROM users WHERE username='" + username + "' LIMIT 1";
        ResultSet rs = runQuery(getUser, false);
        
        try {
            String password = rs.getString("password");
            boolean admin = rs.getBoolean("admin");
            user = new User(username, password, admin, null);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public void createUser(String username, String password, boolean admin) {
        String query = "INSERT INTO users VALUES('" + username + "','" + password + "'," + admin + ")";
        runQuery(query, true);
    }
    
    public void deleteUser(String username) {
        String query = "DELETE FROM users WHERE username='" + username + "'";
        runQuery(query, true);
    }
    
    public void makeAdmin(String username) {
        String query = "UPDATE users SET admin='1' WHERE username='" + username + "'";
        runQuery(query, true);
    }

    public void createBook(Book book) {
        String query = "INSERT INTO books VALUES(" + book.getTitle() + "," + book.getAuthor() +
                "," + book.getGenre() + ")";
        runQuery(query, true);
    }  
    
    public void deleteBook(Book book) {
        int bookId = getBookId(book);
        String query = "DELETE FROM books WHERE id=" + bookId;
        runQuery(query, true);
    }
    
    public void addReview(Review review) {
        String query = "INSERT INTO reviews VALUES(" + review.getBookId() + "," +
                review.getRating() + "," + review.getContent() + ")";
        runQuery(query, true);
    }
    
    public void deleteReview(Review review) {
        int reviewId = getReviewId(review);
        String query = "DELETE FROM reviews WHERE id=" + reviewId;
        runQuery(query, true);
    }
    
    public void addFavorite(User user, Book book) {
        int bookId = getBookId(book);
        String query = "INSERT INTO favorites VALUES(" + user.getUsername() +
                "," + bookId + ")";
        runQuery(query, true);
    }
    
    public void deleteFavorite(User user, Book book) {
        int bookId = getBookId(book);
        String query = "DELETE FROM favorites WHERE username='" + user.getUsername() + 
                "' AND book_id='" + bookId + "'";
        runQuery(query, true);
    }
    
    public boolean loginValid(String username, String password) {
        String query = "SELECT * FROM users WHERE username='" + username + 
                "' AND password='" + password + "'";
        ResultSet rs = runQuery(query, false);
        boolean valid = true;
        
        try {
            if (!rs.next()) {
                valid = false;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return valid;
    }
    
    public boolean usernameAvailable(String username) {
        String query = "SELECT * FROM users WHERE username='" + username + "'";
        boolean available = false;
        ResultSet rs = runQuery(query, false);
        
        try {
            if (!rs.next()) {
                available = true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return available;
    }
}
