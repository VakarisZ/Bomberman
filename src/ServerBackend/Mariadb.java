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
import java.sql.*;

public class Mariadb {

    //  Database credentials
    static final String USER = "bomb";
    static final String PASS = "root";

    public static void main(String[] args) {
        try {//You need to have mariadb jdbc driver installed. 
            //https://mariadb.com/kb/en/library/about-mariadb-connector-j/
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Successfully loaded driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bomberman", USER, PASS);
//here bomberman is database name, root is username and password  
            System.out.println("Successfully connected to database");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from authors");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }//end main
}//end JDBCExample
