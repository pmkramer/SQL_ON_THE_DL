/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;
import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Account implements Page {

    public static final int ID = 3;
    private final VBox root = new VBox();
    private final Scene scene;
    private final Menu menu = new Menu("account");
    private static Account acc = new Account();
    private User user;
    private static String fontType = "Tahoma";
    private String goldBG = "-fx-background-color: #B5A76C;";
    private static String greenBG = "-fx-background-color: #035642;";
    private static String blackBG = "-fx-border-color: black;";
    private VBox subPage;
    private final int PASSWORD_SIZE = 20;
    private final GridPane grid;
       
    private Account() {
        HBox background = Menu.Background();
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
        subPage = Menu.createSub(root);
        background.getChildren().addAll(menu.getRoot(), subPage);

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        final GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);

        final Label userName = new Label("User Name:");
        userName.setTextFill(Color.BLACK);
        userName.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(userName, 0, 24);

        final TextField userTextField = new TextField();
        userTextField.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(userTextField, 1, 24);

        final Label badInput = new Label("");
        badInput.setTextFill(Color.RED);
        grid.add(badInput, 1, 29);

        final Label firstName = new Label("First Name:");
        firstName.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        firstName.setTextFill(Color.BLACK);
        final TextField firstNameTextField = new TextField();
        firstNameTextField.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        final Label lastName = new Label("Last Name:");
        lastName.setTextFill(Color.BLACK);
        lastName.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        final TextField lastNameTextField = new TextField();
        lastNameTextField.setFont(Font.font(fontType, FontWeight.NORMAL, 20));

        final Label pw = new Label("Password:");
        pw.setTextFill(Color.BLACK);
        pw.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(pw, 0, 25);

        final PasswordField pwBox = new PasswordField();
        pwBox.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(pwBox, 1, 25);

        final Label pw2 = new Label("Retype Password:");
        pw2.setTextFill(Color.BLACK);
        pw2.setFont(Font.font(fontType, FontWeight.NORMAL, 20));

        final PasswordField pwBox2 = new PasswordField();
        pwBox2.setFont(Font.font(fontType, FontWeight.NORMAL, 20));

        final BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(50));

        final Button btn = new Button("Sign in");
        btn.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        btn.setStyle(blackBG + goldBG);
        btn.setTextFill(Color.BLACK);
        final HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String username = userTextField.getText();
                String password = pwBox.getText();
                try {
                    Database db = Database.getDatabase();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    user = signIn(username, password);
                    if (user != null) setUpProfile();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (user == null) {
                    badInput.setText("Incorrect Username or Password");
                } else {
                    badInput.setText("");
                }
            }
        });
        final Button back = new Button("Back to Sign In");
        back.setStyle(blackBG + goldBG);
        back.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        back.setTextFill(Color.BLACK);
        final HBox backBtn = new HBox(10);

        final Button newA = new Button("Sign Up");
        newA.setStyle(blackBG + goldBG);
        newA.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        newA.setTextFill(Color.BLACK);
        final HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(newA);
        newA.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                badInput.setText("");

                if (newA.getText().equals("Sign Up")) {
                    userTextField.setText("");
                    pwBox.setText("");
                    firstNameTextField.setText("");
                    lastNameTextField.setText("");
                    pwBox2.setText("");

                    grid.getChildren().remove(hbBtn);
                    grid.add(pwBox2, 1, 26);
                    grid.add(pw2, 0, 26);
                    newA.setText("Create Account");
                    grid.add(firstName, 0, 22);
                    grid.add(firstNameTextField, 1, 22);
                    grid.add(lastName, 0, 23);
                    grid.add(lastNameTextField, 1, 23);
                    grid.add(back, 0, 35);
                } else {
                    String username = userTextField.getText();
                    String password = pwBox.getText();
                    String passwordVerify = pwBox2.getText();
                    String firstN = firstNameTextField.getText();
                    String lastN = lastNameTextField.getText();
                    try {
                        if(createAccount(username, firstN + " " + lastN, password)) {
                            user = new User(username, firstN + " " + lastN);
                            setUpProfile();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        grid.add(hbBtn, 1, 28);
        grid.add(hbBtn2, 1, 35);

        backBtn.setAlignment(Pos.BOTTOM_RIGHT);
        backBtn.getChildren().add(back);
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                badInput.setText("");

                newA.setText("Sign Up");
                pwBox.setText("");
                userTextField.setText("");

                grid.getChildren().remove(pwBox2);
                grid.getChildren().remove(firstName);
                grid.add(hbBtn, 1, 28);
                grid.getChildren().remove(lastName);
                grid.getChildren().remove(pw2);
                grid.getChildren().remove(back);
                grid.getChildren().remove(firstNameTextField);
                grid.getChildren().remove(lastNameTextField);
            }
        });
        subPage.getChildren().add(grid);
    }

    public void setUpProfile() {
        grid.getChildren().clear();
        Label username = new Label("Username: " + user.username);
        Label name = new Label("Name: " + user.name);
        grid.add(username, 1, 24);
        grid.add(name, 1, 25);
        
        
    }
    public static Account getAccount() {
        return acc;
    }
    
    public String getUser() {
        if (user == null) return "user";
        return user.username;
    }

    private boolean createAccount(String username, String name, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // hash the password before storing
        password = bytesToHex(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
        password = password.substring(0, PASSWORD_SIZE);
        // TODO: connect to database and try to insert user
        try {
            // TODO: clean this up
            Database db = Database.getDatabase();
            Connection connection = db.getConnection();
            
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("insert into User values ('%s', '%s', '%s');", username, name, password));
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true; // return True if success and false otherwise

    }

    // FROM: https://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
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
        public String name;
        
        public User(String username, String name) {
            this.username = username;
            this.name = name;
        }

    }

    private User signIn(String uname, String p) throws NoSuchAlgorithmException {
        User u = null;
        String name = null;
        Query query = new Query();
        
        // TODO: connect to database and try to get user
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // hash the password before storing
        p = bytesToHex(digest.digest(p.getBytes(StandardCharsets.UTF_8)));
        p = p.substring(0, PASSWORD_SIZE);
        
        name = query.checkUserCredentials(uname, p);
        if (name != null) {
            u = new User(uname, name);
        }
        return u;
    }
}
