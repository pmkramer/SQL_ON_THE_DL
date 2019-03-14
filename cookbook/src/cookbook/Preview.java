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
    
    public Preview(String name, Image image, String description, Recipe r) throws FileNotFoundException {
        // TODO: stylize preview to make pretty
        // TODO: change constructor to take all fields a recipe should have
        recipe = r;
        Text title = new Text(name);
        ImageView previewImage = new ImageView(image);
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
        
        for (Recipe rec : Query.getTop10()) {
            prevs.getItems().add(rec.preview().getView());
        }
        return prevs;
    }
    
    
    
}
