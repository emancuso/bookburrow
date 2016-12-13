package wombats;

import java.util.ArrayList;

/**
 * 
 * @authors krejci. riley mancuso
 */
public class Process {

    String username;
    String password;
    String passConfirm;
    String submit;
    String review;
    int rating;
    boolean admin = false;

    public Process() {
        submit = "x";
    }

    public boolean checkLogin() {
        DBAbstraction db = new DBAbstraction();
        if (username == null || password == null) return false;
        return db.loginValid(username.toLowerCase(), password);
    }


    public void createUser() {
        DBAbstraction db = new DBAbstraction();
        db.createUser(username, password, admin);
    }

    public boolean validString(String s, boolean username) {
        if (s == null) {
            return false;
        }
        if (s.length() > 20 || s.length() < 3) {
            return false;
        }
        String yes = "abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_+=";
        char[] string = s.toCharArray();
        for (int i = 0; i < string.length; i++) {
            if (!yes.contains(String.valueOf(string[i]))) {
                return false;
            }
        }
        if (username) {
            DBAbstraction db = new DBAbstraction();
            return db.usernameAvailable(s);
        }
        return true;
    }

    public ArrayList<Book> renderFavorites(String username) {
        DBAbstraction db = new DBAbstraction();
        return db.renderFavorites(username);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param newusername the username to set
     */
    public void setUsername(String newusername) {
        this.username = newusername;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param newpassword the password to set
     */
    public void setPassword(String newpassword) {
        this.password = newpassword;
    }

    /**
     * @return the passConfirm
     */
    public String getPassConfirm() {
        return passConfirm;
    }

    /**
     * @param passConfirm the passConfirm to set
     */
    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
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
