/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author krame
 */
public class Preview {
    private final VBox preview = new VBox();
    private final Recipe recipe;
    
    public Preview(String name, String image, String description, Recipe r) throws FileNotFoundException {
        // TODO: stylize preview to make pretty
        // TODO: change constructor to take all fields a recipe should have
        recipe = r;
        Text title = new Text(name);
        ImageView previewImage = new ImageView(new Image("file:src/res/" + image, 500, 500, true, true));
        previewImage.setOnMouseClicked((MouseEvent me) -> {
            Menu.getStage().setScene(recipe.getScene());
        });
        
        Text descr = new Text(description);
        preview.getChildren().addAll(title, previewImage, descr);
        preview.setAlignment(Pos.CENTER);
    }
    
    
    public VBox getView() {
        return preview;
    }
    
    public static ListView<VBox> initPreviews() throws FileNotFoundException {
        // TODO: connect to database to get all recipes instead of the dummy loop
        ListView<VBox> prevs = new ListView();
        prevs.setMinWidth(900);
	prevs.setMaxWidth(800);
	prevs.setMinHeight(800);
        
        Preview prev;
        
        for (int i = 0; i < 100; ++i) {
            for (int j = 1; j <= 2; ++j) {
                prev = new Preview("food", "food" + j + ".png", "delicious food", new Recipe("food" + j + ".png"));
                prevs.getItems().add(prev.preview);
            }
            prevs.getItems().add(new Preview("food", "food3.jpg", "delicious food", new Recipe("food3.jpg")).preview);
            
        }
        return prevs;
    }
    
    
    
}
