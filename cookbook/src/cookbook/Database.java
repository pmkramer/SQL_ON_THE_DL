/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cookbook;

import java.sql.*;
public class Database  {
    private static Database db = null;
    private final Connection connect;
    
    private Database() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(
            "jdbc:mysql://ambari-head.csc.calpoly.edu:3306/sqldl?user=sqldl&password=cookbook");
        } catch (Exception e){
            e.printStackTrace();
            throw new SQLException();
        }
    }
    
    public static void initDB() throws SQLException {
        if (db == null) db = new Database();
    }
    
    public static Database getDatabase() throws SQLException {
        if (db == null) {
            db = new Database();
        }
        return db;
    }
    
    public Connection getConnection() {
        return connect;
    }
    
    
    
}
