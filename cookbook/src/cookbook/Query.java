/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author andy
 */
public class Query {
    
    private static Database db;
    private static Connection connection;
    
    public Query() {
        try {
            this.db = Database.getDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.connection = db.getConnection();
    }
    
    public static ArrayList<Recipe> search(ArrayList<String> filters, String method) throws FileNotFoundException {
        switch (method) {
            case "Ingredients" :
                System.out.println("searching on ingredients");
                return searchRecipeByIngredients(filters);
            case "Categories" :
                System.out.println("searching on categories");
                return searchByCategory(filters);
            case "Recipe Name" : 
                System.out.println("searching on recipe names");
                return searchRecipeByName(filters);
            case "Recipe Cost" : 
                System.out.println("searching on cost");
                return searchByCost(filters);
            case "Calories" : 
                System.out.println("searching on calories");
                return searchByCalories(filters);
            case "Owner UserName" : 
                System.out.println("searching on user name");
                return searchRecipeByOwner(filters);
            default:
                return null;
        }
    }
    
    public static ArrayList<Recipe> searchRecipeByIngredients(ArrayList<String> filters) throws FileNotFoundException {
        ArrayList<Recipe> recipes = null;
        try {
            Statement stmt;
            ResultSet rs;
            
            try {
                db = Database.getDatabase();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection = db.getConnection();
            
            stmt = connection.createStatement();
            String select_string = "SELECT R.rID, R.name, R.description, R.instructions, R.calories, R.image, R.owner, R.price " +
                                   "FROM Recipes R, RecipeIngredients RI, Ingredients I " +
                                   "WHERE R.rID = RI.rID and I.name = RI.ingredient and (I.name =";
            
            String filter;
            for (int i = 0; i < filters.size(); i++) {
                filter = filters.get(i);
                filter = "'" + filter + "'";
                select_string += (filter) += (i == filters.size()-1 ? ")" : " or I.name=");
            }
            
            String conditional = " GROUP BY R.rID, R.name, R.description, R.instructions, R.calories, R.image, R.owner, R.price " +
                                 "HAVING COUNT(*) = %d;";
            
            conditional = String.format(conditional, filters.size());
            
            System.out.println(select_string + conditional);
            rs = stmt.executeQuery(select_string + conditional);
            
            recipes = genRecipeList(rs);
            stmt.close();
        } catch (SQLException ex) {
            
        }
        
        return recipes;
    }
    
    private static ArrayList<Recipe> searchRecipeByOwner(ArrayList<String> filters) throws FileNotFoundException {
        ArrayList<Recipe> recipes = null;
        try {
            Statement stmt;
            ResultSet rs;
            
            try {
                db = Database.getDatabase();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection = db.getConnection();
            
            stmt = connection.createStatement();
            String select_string = "select distinct r.* from Recipes r where r.owner =";
            String filter;
            for (int i = 0; i < filters.size(); i++) {
                filter = filters.get(i);
                filter = "'" + filter + "'";
                select_string += (filter) += (i == filters.size()-1 ? ");" : " or r.owner=");
            }
            rs = stmt.executeQuery(select_string);
            
            recipes = genRecipeList(rs);
        } catch (SQLException ex) {
            
        }
        
        return recipes;
    }
    
    private static ArrayList<Recipe> searchRecipeByName(ArrayList<String> filters) throws FileNotFoundException {
        ArrayList<Recipe> recipes = null;
        try {
            Statement stmt;
            ResultSet rs;
            
            try {
                db = Database.getDatabase();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection = db.getConnection();
            
            stmt = connection.createStatement();
            String select_string = "select distinct r.* from Recipes r where (r.name =";
            String filter;
            for (int i = 0; i < filters.size(); i++) {
                filter = filters.get(i);
                filter = "'" + filter + "'";
                select_string += (filter) += (i == filters.size()-1 ? ");" : " or r.name=");
            }
            System.out.println(select_string);
            rs = stmt.executeQuery(select_string);
            
            recipes = genRecipeList(rs);
        } catch (SQLException ex) {
            
        }
        
        return recipes;
    }
    
    private ArrayList<String> getCategories(int rid) {
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
    
    private static ArrayList<String> getIngredients(int rid) {
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
    
    private ArrayList<Recipe> searchByIngredient(ArrayList<String> ingredients) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        try {
            Statement statement;
            ResultSet rs;
            String query;
            
            // select distinct r.*
            // from Recipes r, RecipeIngredients ri
            // where r.rID = ri.rID and (ri.ingredient
            
            statement = connection.createStatement();
        } catch (SQLException ex) {
            
        }
        return recipes;
    }
    
    private static ArrayList<Recipe> searchByCategory(ArrayList<String> categories) throws FileNotFoundException {
        ArrayList<Recipe> recipes = null;
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
            recipes = genRecipeList(rs);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return recipes;
    }
    
    private static ArrayList<Recipe> searchByCost(ArrayList<String> filters) throws FileNotFoundException {
        ArrayList<Recipe> recipes = null;
        String filter = filters.get(0);
        int cost = Integer.parseInt(filter);
        try {
            ResultSet rs;
            Statement statement = connection.createStatement();
            String selectStatement = "select * from Recipes where %s";
            String condition = "cost <= %d";
            
            condition = String.format(condition, cost);
            selectStatement = String.format(selectStatement, condition);
            
            rs = statement.executeQuery(selectStatement);
            
            recipes = genRecipeList(rs);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return recipes;
    }
    
    private static ArrayList<Recipe> searchByCalories(ArrayList<String> filters) throws FileNotFoundException {
        ArrayList<Recipe> recipes = null;
        String filter = filters.get(0);
        int calories = Integer.parseInt(filter);
        try {
            ResultSet rs;
            Statement statement = connection.createStatement();
            String selectStatement = "select * from Recipes where %s";
            String condition = "calories <= %d";
            
            condition = String.format(condition, calories);
            selectStatement = String.format(selectStatement, condition);
            
            rs = statement.executeQuery(selectStatement);
            
            recipes = genRecipeList(rs);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return recipes;
    }
    
    public static ArrayList<Recipe> getTop10() throws FileNotFoundException {
        ArrayList<Recipe> recipes = null;
        if (db == null) {
            try {
                db = Database.getDatabase();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection = db.getConnection();
        }
        
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from Recipes limit 10;");
            
            recipes = genRecipeList(rs);
            stmt.close();
        } catch (SQLException e) {
            
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
    
    public static ArrayList<Recipe> genRecipeList(ResultSet rs) throws FileNotFoundException {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        int rID = -1;
        String recipeName;
        String author;
        int cals;
        int cost;
        ArrayList<String> ingredients;
        String descriptions;
        String instructions;
        String image;
        try {
            while(rs.next()) {
                rID = rs.getInt("rID");
                recipeName = rs.getString("name");
                author = rs.getString("owner");
                cals = rs.getInt("calories");
                cost = rs.getInt("price");
                descriptions = rs.getString("description");
                instructions = rs.getString("instructions");
                ingredients = getIngredients(rID);
                image = rs.getString("image");
                recipes.add(new Recipe(recipeName, descriptions, instructions, image, author, cals, cost, ingredients));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return recipes;
    }
}
