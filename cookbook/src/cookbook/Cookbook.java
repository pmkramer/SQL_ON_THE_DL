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
    private Page home;
    private Page search;
    private Page account;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Database.initDB();
        stage.setTitle("Cookbook");
        Menu.setUpStage(stage);
        home = new Home();
        search = new Search();
        account = Account.getAccount();
        Page[] pages = {home, search, account};
        Menu.setUpPages(pages);
        for (Page p : pages) {
            p.getMenu().setUpNavigation();
        }
        stage.setScene(pages[Home.ID].getScene());
        stage.show();
    }
    
                      
}
