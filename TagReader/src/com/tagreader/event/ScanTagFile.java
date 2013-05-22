/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tagreader.event;

import com.tagreader.database.DBConnection;

/**
 *
 * @author rahul
 */
public class ScanTagFile {
    
    public void storeUniqueID() {
        DBConnection db = new DBConnection();
        db.createTable();
        System.out.println("Table created !!");    
    }
      
}
