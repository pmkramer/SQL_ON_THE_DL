/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.sql.SQLException;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class Recipe implements Page{
    private VBox root = new VBox();
    private Menu menu = new Menu("home");
    private Scene scene;
    private ArrayList<String> ingredients = new ArrayList<>();
    private String name = "";
    private String descr = "";
    private String insts = "";
    private String author = "";
    private int cals;
    private int cost;
    private int id;
    private Image image;
    private Preview preview;
    
    
    // CHANGE CONSTRUCTOR TO TAKE ALL NECESSARY FIELDS
    // THAT DEFINE A RECIPE
    public Recipe(String name, String descr, String insts, String img, String author, int cals, int cost, ArrayList<String> ingredients) throws FileNotFoundException {
        this.name = name;
        this.insts = insts;
        this.descr = descr;
        this.author = author;
        this.cals = cals;
        this.cost = cost;
        this.ingredients = ingredients;
        // DO MORE STUFF HERE
        if (img.length() > 4 && !img.substring(0,4).equals("http")) {
            img = "file:src/res/" + img;
        }
        try {
            this.image = new Image(img, 500, 500, true, true);
            System.out.println(img);
        } catch (Exception e) {
            this.image = new Image("file:src/res/food1.png", 500, 500, true, true);
        }
        this.preview = new Preview(name, this.image, descr, this);
        HBox background = Menu.Background();
        background.getChildren().add(menu.getRoot());
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
        menu.setUpNavigation();
        // THIS IS JUST TO HAVE SOMETHING TO SHOW FOR NOW
        // DO NOT KEEP IN FINAL
        VBox preview = new VBox();
        Text title = new Text(name);
        ImageView previewImage = new ImageView(this.image);
        Text descrip = new Text(descr);
        ListView<Text> instructions = new ListView();
        instructions.getItems().add(new Text("Instructions: "));
        String instrs[] = insts.split("\n");
        for (int i = 0; i < instrs.length; ++i) {
            Text t = new Text(instrs[i]);
            //t.setWrapText(true);
            instructions.getItems().add(t);
        }
        ListView<Text> ingreds = new ListView();
        ingreds.getItems().add(new Text("Ingredients: "));
        for (String ingr : ingredients) 
            ingreds.getItems().add(new Text(ingr));
        
        
        preview.getChildren().addAll(title, previewImage, descrip, ingreds, instructions);
        preview.setAlignment(Pos.CENTER);
        background.getChildren().add(preview);
    }
    
    public Preview preview() {
        return preview;
    }
    
    public Recipe(String image) {
        // TODO: get recipe info
        //this.image = image;
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

    public String name() { 
        return name;
    }
    
    public Image image() {
        return image;
    }
    
    public String descr() {
        return descr;
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
    
    public static void insertRecipe(String name, String description, 
            String instructions, int calories, String img, String owner, 
            ArrayList<String> categories, ArrayList<String> ingredients, int cost) {
        try {
            Database db = Database.getDatabase();
            Connection connection = db.getConnection();

            ResultSet rs;
            Statement statement = connection.createStatement();
            
            int rID = -1;
            
            connection.setAutoCommit(false);
            // insert into Recipe values (name, desc, instr, cals, owner);
            statement.executeUpdate(String.format("insert into Recipes (name, description, instructions, calories, image, owner, price) values ('%s', '%s', '%s', %d, '%s', '%s', %d);", 
                                                    name, description, instructions, calories, img, owner, cost));
            connection.commit();
            
            // select rID of new recipe
            rs = statement.executeQuery("select max(R.rID) as rID from Recipes R;");
            if (rs.next()) {
                rID = rs.getInt("rID");
            }
            
            // insert into categories and ingredients only if they didn't exist
            // insert into recipeIngredients and recipeCategories using rID
            for (String category : categories) {
                try {
                    statement.executeUpdate(String.format("insert into Categories values ('%s');", category));
                } catch (SQLException ex) {
                    // ignore this
                }
                connection.commit();
                statement.executeUpdate(String.format("insert into RecipeCategories values (%d, '%s');", rID, category));
            }
            
            for (String ingredient : ingredients) {
                try {
                    statement.executeUpdate(String.format("insert into Ingredients values ('%s');", ingredient));
                } catch (SQLException ex) {
                    // ignore this
                }
                connection.commit();
                statement.executeUpdate(String.format("insert into RecipeIngredients values (%d, '%s');", rID, ingredient));
            }
            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
