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
public class Book {
    private String title;
    private String author;
    private String genre;
    private ArrayList<Review> reviews;
    
    public Book(String title, String author, String genre) {
        DBAbstraction db = new DBAbstraction();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.reviews = db.renderReviews(this);
    }    
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public ArrayList<Review> getReviews() {
        return reviews;  
    }
    
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    
    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }
    
    public void setGenre(String newGenre) {
        genre = newGenre;
    }
    
    public void addReview(Review newReview) {
        reviews.add(newReview);
    }
}
