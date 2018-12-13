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
public interface Database {


    public ResultSet ExecuteQuery(String query);
    public void ExecuteUpdate(String query);
    public void ExecuteUpdate(String[] queries);
    
    
}
