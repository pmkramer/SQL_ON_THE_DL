/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Search implements Page{
    public static final int ID = 1;
    private final VBox root = new VBox();
    private final Scene scene;
    private final Menu menu = new Menu("search");
    private final VBox subPage;
    private ListView<VBox> previews;
    
    public Search() throws FileNotFoundException {
        HBox background = Menu.Background();
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
        subPage = Menu.createSub(root);
        background.getChildren().addAll(menu.getRoot(), subPage);
        
        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(1);
        grid.setVgap(12);
        
        ComboBox<String> filterType = new ComboBox();
        filterType.getItems().addAll(
                "Ingredients",
                "Categories",
                "Recipe Name",
                "Recipe Cost",
                "Calories",
                "Owner UserName"
        );
        filterType.setValue("Recipe Name");
        
        grid.add(filterType, 0, 24);
        
        final ListView<TextField> filters = new ListView();
        grid.add(filters, 1, 24);
        
        final TextField filter = new TextField();
        filter.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        filters.getItems().add(filter);
        
        Button search = new Button("Search");
        grid.add(search, 2, 24);
        search.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent e) {
               ArrayList<String> searchFilters = new ArrayList();
               for (TextField t : filters.getItems()) searchFilters.add(t.getText());
               ListView<VBox> prevs = new ListView();
               prevs.setMinWidth(900);
               prevs.setMaxWidth(800);
               prevs.setMinHeight(800);
               try {
                   for (Recipe rec : Query.search(searchFilters, filterType.getValue())) {
                       System.out.println("here");
                       prevs.getItems().add(rec.preview().getView());
                   }
               } catch (Exception ex) {
                   Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
               }
               System.out.println(prevs.getItems().size());
               Cookbook.home.updatePreviews(prevs);
           }
           
        });
        
        Button add = new Button("Add Filter Criteria");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TextField filter = new TextField();
                filter.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                filters.getItems().add(filter);
            }
        });
        grid.add(add, 1, 25);
        
        subPage.getChildren().add(grid);
    }
    
    public void updatePreviews(ListView<VBox> prevs) {
        previews = prevs;
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
