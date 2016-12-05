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
public class User {
    private boolean admin;
    private String username;
    private String password;
    private ArrayList<Book> favorites;
    
    public User(String username, String password, boolean admin, ArrayList<Book> favorites) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        if (favorites != null) {
            this.favorites = favorites;
        }
    }
    
    public boolean getAdmin() {
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getFavorites() {
        return favorites;
    }
    
    public void setAdmin(Boolean status) {
        this.admin = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFavorites(ArrayList<Book> faveList) {
        this.favorites = faveList;
    }
    
    public void addFavorite(Book favorite) {
        favorites.add(favorite);
    }
    
    public void reviewBook(Book book, Review review) {
        DBAbstraction db = new DBAbstraction();
        db.addReview(review);
    }
    
    public User renderUser() {
        DBAbstraction db = new DBAbstraction();
        User updated = db.renderUser(username);
        this.setAdmin(updated.getAdmin());
        this.setFavorites(updated.getFavorites());
        return this;
    }
    
}
