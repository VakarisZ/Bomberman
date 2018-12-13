/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class DatabaseTesting {

    public static void main(String[] args) {
//        testdb();
        testProxy();
    }

    public static void testdb() {
        Mariadb mdb = new Mariadb();
        mdb.InitDB();
        mdb.testDB();
        mdb.CloseConnection();
    }

    public static void testProxy() {
        MariaDBProxy mdbp = new MariaDBProxy();

        ResultSet rs = mdbp.ExecuteQuery("select * from users");
        try {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "RE " + rs.getString(2) + "  " + rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mariadb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
