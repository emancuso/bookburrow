/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wombats;

import java.util.ArrayList;

/**
 *
 * @author riley
 */
public class Admin extends User {
    
    public Admin(String username, String password, ArrayList<Book> favorites) {
        super(username, password, true, favorites);
    }
    
    DBAbstraction db = new DBAbstraction();
    
    public void deleteBook(Book b) {
        db.deleteBook(b);
    }
   
    public void deleteUser(User u) {
       db.deleteUser(u.getUsername());
    }
    
    public void deleteReview(Review r) {
       db.deleteReview(r);
    }
    
    public void promoteUser(User u) { 
        db.makeAdmin(u.getUsername());
    }   

}
