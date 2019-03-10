/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook;
import java.sql.*;
/**
 *
 * @author andy
 */
public class Query {
    
    private static final String PREPARED_INSERT = "insert into %s values (%s)";
    public Query() {
        
    }
    
    public void insert(String table, String values) {
        ResultSet rs;
        Statement stmt;
        String query = String.format(PREPARED_INSERT, table, values);
        
        // might need to use built-in prepared statements
        // also may need to do stuff with auto-commits
        try {
            Database db = Database.getDatabase();
            Connection connect = db.getConnection();
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
        
    }
    
    public void select() {
        
    }
}
