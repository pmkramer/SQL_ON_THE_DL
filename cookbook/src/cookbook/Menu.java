/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

/**
 *
 * @author krame
 */
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public final class Menu {
    private VBox root = new VBox();
    private String currentPage = "";
    
    private static Stage stage;
    private static Page[] pages;
    
    
    
    public static void setUpStage(Stage s) {
        stage = s;
    }
    
    public static void setUpPages(Page[] p) {
        pages = p;
    }
    
    public Menu(String cp) {
        root.setId("menu");
	root.setMinWidth(300);
	root.setMinHeight(800); 
        
        Button home = new Button("Cookbook Recipes");
        home.setId("home");
        home.setMinWidth(300);
        home.setMinHeight(200);
        
        Button search = new Button("Search");
        search.setId("search");
        search.setMinWidth(300);
        search.setMinHeight(200);
        
        Button create = new Button("Create Recipe");
        create.setId("create");
        create.setMinWidth(300);
        create.setMinHeight(200);
        
        Button account = new Button("Account");
        account.setId("account");
        account.setMinWidth(300);
        account.setMinHeight(200);
        
        root.getChildren().addAll(home, search, create, account);
        setCurrentPage(cp);
    }
    
    public VBox getRoot() {
        return root;
    }
    
    public void setUpSingleConnection(final Stage stage, final Page[] pages, final Button b, final int pageID)
    {
	b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
		/*if(b.getId().equals(ACC))
                    Account.loadResults();*/
		stage.setScene(pages[pageID].getScene());
            }
	});
    }
    
    public void setUpNavigation() {
        for (Node n : root.getChildren()) {
            if (n.getId().equals("home")) {
                setUpSingleConnection(stage, pages, (Button)n, Home.ID);
            } else if (n.getId().equals("search")) {
                setUpSingleConnection(stage, pages, (Button)n, Search.ID);
            } else if (n.getId().equals("account")) {
                setUpSingleConnection(stage, pages, (Button)n, Account.ID);
            } else {
                setUpSingleConnection(stage, pages, (Button)n, CreateRecipe.ID);
            }
        }
    }
    
    public void setCurrentPage(String page) {
        for (Node n : root.getChildren()) {
            if (n.getId().equals(page)) {
                ((Button)n).setTextFill(Color.WHITE);
            } else  {
                ((Button)n).setTextFill(Color.BLACK);
            }
        }
        currentPage = page;
    }
    
    public static VBox createSub(VBox root) {
	VBox subPage = new VBox();
	subPage.setMinWidth(900);
	subPage.setMaxWidth(800);
	subPage.setMinHeight(800);
	subPage.setStyle("-fx-background-color: #035642;");
	subPage.setStyle(root.getStyle()+"-fx-border-color: black;" + "-fx-border-width: 5");
	subPage.getAlignment();
	subPage.setAlignment(Pos.TOP_CENTER);
	return subPage;
    }
    
    public static HBox Background() {
	HBox root = new HBox();
	root.setId("background");
	root.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #6d6d6d);");
        return root;
    }
    
    public static Stage getStage() {
        return stage;
    }
}
