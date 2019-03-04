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
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface Page {
    Pane getRoot();
    Scene getScene();
    Menu getMenu();
}
