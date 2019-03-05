/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Recipe implements Page{
    private VBox root = new VBox();
    private Menu menu = new Menu("home");
    private Scene scene;
    private ArrayList<String> ingredients = new ArrayList<>();
    private String name = "";
    private String descr = "";
    private String insts = "";
    private String author = "";
    private int id;
    
    // CHANGE CONSTRUCTOR TO TAKE ALL NECESSARY FIELDS
    // THAT DEFINE A RECIPE
    public Recipe(String image) {
        // TODO: get recipe info
        HBox background = Menu.Background();
        background.getChildren().add(menu.getRoot());
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
        menu.setUpNavigation();
        
        
        // THIS IS JUST TO HAVE SOMETHING TO SHOW FOR NOW
        // DO NOT KEEP IN FINAL
        VBox preview = new VBox();
        Text title = new Text(name);
        ImageView previewImage = new ImageView(new Image("file:src/res/" + image, 500, 500, true, true));
        Text descrip = new Text(descr);
        preview.getChildren().addAll(title, previewImage, descrip);
        preview.setAlignment(Pos.CENTER);
        background.getChildren().add(preview);
    }

    
    
    @Override
    public VBox getRoot() {
        return root;
    }
    
    @Override
    public Menu getMenu() {
        return menu;
    }
    
    @Override
    public Scene getScene() {
        return scene;
    }
    
    
    
}
