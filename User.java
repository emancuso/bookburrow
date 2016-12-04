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
    private String type;
    private String username;
    private String password;
    private ArrayList<Book> favorites;
    
    public String getType() {
        return type;
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
    
    public void setType(String type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addFavorite(Book favorite) {
        favorites.add(favorite);
    }
    
    public void reviewBook(Book book, Review review) {
        //access the DBAbstraction somehow? Go through process?
    }
    
    
}
