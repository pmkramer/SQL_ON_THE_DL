/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author andy
 */
public class Query {
    
    private Database db;
    private Connection connection;
    
    public Query() {
        try {
            this.db = Database.getDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.connection = db.getConnection();
    }
    
    public void searchRecipeByName(String name) {
        try {
            Statement stmt;
            ResultSet rs;
            
            int rid = -1;
            String description;
            String instructions;
            int calories;
            String image = "";
            int price;
            String owner;
            ArrayList<String> ingredients;
            
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("select * from Recipe where name='%s';", name));
            
            if (rs.next()) {
                rid = rs.getInt("rID");
                description = rs.getString("description");
                instructions = rs.getString("instructions");
                calories = rs.getInt("calories");
                image = rs.getString("image");
                price = rs.getInt("price");
                owner = rs.getString("owner");
            }

            ingredients = getIngredients(rid);
            
            Recipe recipe = new Recipe(image);
        } catch (SQLException ex) {
            
        }
    }
    
    public ArrayList<String> getCategories(int rid) {
        ArrayList<String> categories = new ArrayList<String>();
        
        try {
            Statement stmt;
            ResultSet rs;
            
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("select category from RecipeCategories where rID=%d;", rid));
            
            while(rs.next()) {
                categories.add(rs.getString("category"));
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return categories;
    }
    
    public ArrayList<String> getIngredients(int rid) {
        ArrayList<String> categories = new ArrayList<String>();
        
        try {
            Statement stmt;
            ResultSet rs;
            
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("select ingredient from RecipeIngredients where rID=%d;", rid));
            
            while(rs.next()) {
                categories.add(rs.getString("ingredient"));
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return categories;
    }
    
    public ArrayList<Recipe> serachByCategory(ArrayList<String> categories) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String category;
        
        try {
            Statement stmt;
            ResultSet rs;
            
            stmt = connection.createStatement();
            
            /**
             * select distinct r.* 
             * from Recipes r, RecipeCategories rc
             * where r.rID = rc.rID and rc.category = "mexican"
             * ;
             */
            String select_string = "select distinct r.* from Recipes r, RecipeCategories rc where r.rID = rc.rID and (rc.category =";
            for (int i = 0; i < categories.size(); i++) {
                category = categories.get(i);
                category = "'" + category + "'";
                select_string += (category) += (i == categories.size()-1 ? ");" : " or rc.category=");
            }
            
            rs = stmt.executeQuery(select_string);
            
            while(rs.next()) {
                // this will give 1 recipe each
                recipes.add(new Recipe("test"));
            }
            stmt.executeQuery(select_string);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return recipes;
    }
    
    public String checkUserCredentials(String uname, String p) {
        String name = null;
        try {
            ResultSet rs;
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(String.format("SELECT name from User where username='%s' and password='%s';", uname, p));
            
            if (rs.next()) {
               name = rs.getString("name");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return name;
    }
}
