/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

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
    
    public Account() {
        HBox background = Menu.Background();
        background.getChildren().add(menu.getRoot());
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
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
}
