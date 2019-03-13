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
import java.io.FileNotFoundException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Home implements Page{
    public static final int ID = 0;
    private final VBox root = new VBox();
    private final Scene scene;
    private final VBox subpage;
    private ListView<VBox> previews;
    private final Menu menu = new Menu("home");
    private final HBox background;
    
    public Home() throws FileNotFoundException {
        // TODO:  stylize to make pretty
        subpage = Menu.createSub(root);
        background = Menu.Background();
        background.getChildren().addAll(menu.getRoot(), Preview.initPreviews());
        root.getChildren().add(background);
        scene = new Scene(root, 1200, 800);
    }
    
    public void updatePreviews(ListView<VBox> prevs) {
        prevs.setMinWidth(900);
	prevs.setMaxWidth(800);
	prevs.setMinHeight(800);
        background.getChildren().remove(1);
        background.getChildren().add(prevs);
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
