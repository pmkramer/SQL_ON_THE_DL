/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author krame
 */
public class Account implements Page{
    public static final int ID = 2;
    private final VBox root = new VBox();
    private final Scene scene;
    private final Menu menu = new Menu("account");
    private static Account acc = new Account();
    private User user;
    
    
    private Account() {
        HBox background = Menu.Background();
        background.getChildren().add(menu.getRoot());
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
    }
    
    public static Account getAccount() {
        return acc;
    }
    
    private void createAccount(String username, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // hash the password before storing
        password = bytesToHex(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
        // TODO: connect to database and insert user
        
    }
    
    // FROM: https://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    @Override
    public VBox getRoot() {
        return root;
    }
    
    @Override
    public Scene getScene() {
        return scene;
    }
    
    @Override
    public Menu getMenu() {
        return menu;
    }
    
    private class User {
        public String username;
        public String first;
        public String last;
        public ArrayList<String> recipes;
    }
}
