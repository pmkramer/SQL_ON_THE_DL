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
    public static Home home;
    private Page search;
    private Page account;
    private Page create;
    private Page pages[] = new Page[4];
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
        create = new CreateRecipe();
        pages[0] = home;
        pages[1] = search;
        pages[2] = create;
        pages[3] = account;
        //Page[] pages = {home, search, create, account};
        Menu.setUpPages(pages);
        for (Page p : pages) {
            p.getMenu().setUpNavigation();
        }
        stage.setScene(pages[Home.ID].getScene());
        stage.show();
    }
    
                      
}
