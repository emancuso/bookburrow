/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wombats;

import java.util.ArrayList;

/**
 *
 * @author krejci
 */
public class Process {
    
    private String newusername;
    private String newpassword;
    private String newpassconfirm;
    private String submit;
    private String review;
    private int rating;
    
    public Process() {
        submit = "x";
    }
    
    public boolean checkLogin(String u, String p) {
        DBAbstraction db = new DBAbstraction();
        if (u == null || p == null) return false;
        return db.userCheck(u.toLowerCase(), p);
    }
    
    public void createUser(String u, String p, boolean admin) {
        DBAbstraction db = new DBAbstraction();
        db.createUser(u,p,admin);
    }
    
    public boolean validString(String s, boolean username) {
        if (s == null) return false;
        if (s.length() > 20 || s.length() < 3) return false;
        String yes = "abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-+=";
        char[] string = s.toCharArray();
        for (int i = 0; i < string.length; i++) {
            if (!yes.contains(String.valueOf(string[i]))) return false;
        }
        if (username) {
            DBAbstraction db = new DBAbstraction();
            return db.validUsername(u);
        }
        return true;
    }
    
    public ArrayList<Book> renderFavorites(String username) {
        DBAbstraction db = new DBAbstraction();
        return db.renderFavorites(username);
    }

    /**
     * @return the newusername
     */
    public String getNewusername() {
        return newusername;
    }

    /**
     * @param newusername the newusername to set
     */
    public void setNewusername(String newusername) {
        this.newusername = newusername;
    }

    /**
     * @return the newpassword
     */
    public String getNewpassword() {
        return newpassword;
    }

    /**
     * @param newpassword the newpassword to set
     */
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    /**
     * @return the newpassconfirm
     */
    public String getNewpassconfirm() {
        return newpassconfirm;
    }

    /**
     * @param newpassconfirm the newpassconfirm to set
     */
    public void setNewpassconfirm(String newpassconfirm) {
        this.newpassconfirm = newpassconfirm;
    }

    /**
     * @return the submit
     */
    public String getSubmit() {
        return submit;
    }

    /**
     * @param submit the submit to set
     */
    public void setSubmit(String submit) {
        this.submit = submit;
    }

    /**
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}
