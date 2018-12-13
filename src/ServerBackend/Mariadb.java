/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

/**
 *
 * @author mati
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mariadb implements Database {

    //  Database credentials
    final String USER = "bomb";
    final String PASS = "root";
    private Connection con;

    public void main(String[] args) {
        InitConnection();
        InitDatabase();
        testDB();
        CloseConnection();

    }//end main

    public void InitDB(){
        InitConnection();
        InitDatabase();
        
    }
    public void InitConnection() {
        try {//You need to have mariadb jdbc driver installed. 
            //https://mariadb.com/kb/en/library/about-mariadb-connector-j/
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Successfully loaded driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bomberman", USER, PASS);
            //here bomberman is database name, root is username and password  
            System.out.println("Successfully connected to database");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from authors");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void CloseConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet ExecuteQuery(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void ExecuteUpdate(String query) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ExecuteUpdate(String[] queries) {
        for (int i = 0; i < queries.length; i++) {
            ExecuteUpdate(queries[i]);
        }
    }

    public void InitDatabase() {
        String create_usertable = loadResource("queries/usertable.sql");
        String drop_table = "DROP TABLE IF EXISTS `users`;";
        ExecuteQuery(drop_table);
        ExecuteUpdate(create_usertable);

    }

    public String loadResource(String filename) {
        FileInputStream f = null;
        try {
            f = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return convertStreamToString(f);

    }

    static String convertStreamToString(java.io.InputStream is) {
        if (is == null) {
            return "";
        }

        java.util.Scanner s = new java.util.Scanner(is);
        s.useDelimiter("\\A");

        String streamString = s.hasNext() ? s.next() : "";

        s.close();

        return streamString;
    }

    public void testDB() {
        String test_data = loadResource("queries/filling_users.sql");
        String[] entries = test_data.split("\n");
        ExecuteUpdate(entries);
        ResultSet rs = ExecuteQuery("select * from users");
        try {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}//end JDBCExample
