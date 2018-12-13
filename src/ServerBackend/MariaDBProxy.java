/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBackend;

import java.sql.ResultSet;

/**
 *
 * @author mati
 */
public class MariaDBProxy implements Database {
    private Mariadb mdb = null;
    @Override
    public ResultSet ExecuteQuery(String query) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (mdb == null){
            InitMDB();
        }
        return mdb.ExecuteQuery(query);
    }

    @Override
    public void ExecuteUpdate(String query) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (mdb == null){
                InitMDB();
            }
        mdb.ExecuteUpdate(query);
    }

    @Override
    public void ExecuteUpdate(String[] queries) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (mdb == null){
            InitMDB();
        }
        mdb.ExecuteUpdate(queries);
    }
    private void InitMDB(){
        mdb = new Mariadb();
        mdb.InitConnection();
//        mdb.InitDatabase();
    }
    
}
