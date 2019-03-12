/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author krame
 */
public class CreateRecipe implements Page{
    public static final int ID = 2;
    private final VBox root = new VBox();
    private final Scene scene;
    private final Menu menu = new Menu("create");
    private final VBox subPage;
    private static String fontType = "Tahoma";
    private String goldBG = "-fx-background-color: #B5A76C;";
    private static String greenBG = "-fx-background-color: #035642;";
    private static String blackBG = "-fx-border-color: black;";
    
    public CreateRecipe()  {
        HBox background = Menu.Background();
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
        subPage = Menu.createSub(root);
        background.getChildren().addAll(menu.getRoot(), subPage);
        
        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        
        final Label name = new Label("Recipe Name:");
        name.setTextFill(Color.BLACK);
        name.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(name, 0, 24);

        final TextField recipeName = new TextField();
        recipeName.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(recipeName, 1, 24);
        
        final Label descr = new Label("Description:");
        descr.setTextFill(Color.BLACK);
        descr.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(descr, 0, 25);

        final TextField recipeDescr = new TextField();
        recipeDescr.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(recipeDescr, 1, 25);
        
        final Label cals = new Label("Calories:");
        cals.setTextFill(Color.BLACK);
        cals.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(cals, 0, 26);

        final TextField recipeCals = new TextField();
        recipeCals.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(recipeCals, 1, 26);
        
        final Label cost = new Label("Estimated Cost($):");
        cost.setTextFill(Color.BLACK);
        cost.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(cost, 0, 27);

        final TextField recipeCost = new TextField();
        recipeCost.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(recipeCost, 1, 27);
        
        final Label image = new Label("Image:");
        image.setTextFill(Color.BLACK);
        image.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(image, 0, 28);

        final TextField recipeImage = new TextField();
        recipeImage.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(recipeImage, 1, 28);
        
        final Label instrs = new Label("Instructions:");
        instrs.setTextFill(Color.BLACK);
        instrs.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(instrs, 0, 29);

        final ListView<TextField> recipeInstrs = new ListView();
        final TextField instructions = new TextField();
        instructions.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        recipeInstrs.getItems().add(instructions);
        grid.add(recipeInstrs, 1, 29);
        
        final Button addInstr = new Button("Add Instruction");
        addInstr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TextField instruction = new TextField();
                instruction.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
                recipeInstrs.getItems().add(instruction);
            }
        });
        grid.add(addInstr, 2, 29);
        
        final Label ingrs = new Label("Ingredients:");
        ingrs.setTextFill(Color.BLACK);
        ingrs.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(ingrs, 0, 30);

        final ListView<TextField> recipeIngrs = new ListView();
        final TextField ingredients = new TextField();
        ingredients.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        recipeIngrs.getItems().add(ingredients);
        grid.add(recipeIngrs, 1, 30);
        
        final Button addIngr = new Button("Add Ingredient");
        addIngr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TextField ingredient = new TextField();
                ingredient.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
                recipeIngrs.getItems().add(ingredient);
            }
        });
        grid.add(addIngr, 2, 30);
        
        final Label categs = new Label("Categories:");
        categs.setTextFill(Color.BLACK);
        categs.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        grid.add(categs, 0, 31);

        final ListView<TextField> recipeCategs = new ListView();
        final TextField categories = new TextField();
        categories.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
        recipeCategs.getItems().add(categories);
        grid.add(recipeCategs, 1, 31);
        
        final Button addCateg = new Button("Add Category");
        addCateg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TextField category = new TextField();
                category.setFont(Font.font(fontType, FontWeight.NORMAL, 20));
                recipeCategs.getItems().add(category);
            }
        });
        grid.add(addCateg, 2, 31);
        
        final Button createRecipe = new Button("Create Recipe");
        createRecipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                String name = recipeName.getText();
                if (name == null || name == "") {
                    recipeName.setText("Name field must be set");
                    return;
                }
                String descr = recipeDescr.getText();
                if (descr == null || name == "") {
                    
                }
                Integer cals;
                try {
                    cals = Integer.parseInt(recipeCals.getText());
                } catch (Exception e) {
                    recipeCals.setText("Calories should be a whole number");
                }
                Integer cost;
                try {
                    cost = Integer.parseInt(recipeCost.getText());
                } catch (Exception e) {
                    recipeCost.setText("Cost should be a whole number");
                }
                String instrs = "";
                for (TextField t : recipeInstrs.getItems()) {
                    instrs += t.getText().replace("\n", "") + "\n";
                }
                ArrayList<String> ingredients = new ArrayList();
                for (TextField t : recipeIngrs.getItems()) {
                    ingredients.add(t.getText());
                }
                ArrayList<String> categories = new ArrayList();
                for (TextField t : recipeCategs.getItems()) {
                    categories.add(t.getText());
                }
            }
            
            // TODO: pass values to db query
        });
        grid.add(createRecipe, 1, 32);
        subPage.getChildren().add(grid);
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
