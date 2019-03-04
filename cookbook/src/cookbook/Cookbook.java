/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author krame
 */
public class Cookbook extends Application {
    private final Page home;
    private final Page search;
    private final Page account;
    
    public static void main(String[] args) {
        launch(args);
    }

    public Cookbook() throws FileNotFoundException {
        
        this.home = new Home();
        this.search = new Search();
        this.account = new Account();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Cookbook");
        Page[] pages = {home, search, account};
        for (Page p : pages) {
            p.getMenu().setUpNavigation(stage, pages);
        }
        
        stage.setScene(pages[Home.ID].getScene());
        stage.show();
    }
    
                      
}
