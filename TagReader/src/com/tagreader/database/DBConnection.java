/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tagreader.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahul
 */
public class DBConnection {
    
 
    
 public Connection getConnection() {
    Connection connection = null;  
        try {  
            Class.forName("org.sqlite.JDBC");  
            connection = DriverManager.getConnection("jdbc:sqlite:rfid.db");  

        }catch(ClassNotFoundException | SQLException e) { System.out.println(e);}
      
    return connection;
  }
 
 public void createTable() {
     ArrayList uniqueTagList = new ArrayList();
     try {
         Connection connection = getConnection();
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);  // set timeout to 30 sec.
         statement.executeUpdate("drop table if exists rfidTagTable");
         statement.executeUpdate("create table rfidTagTable (slno integer,tagid string, username string, scantime numeric,lap1 numeric, lap2 numeric, lap3 numeric, lap4 numeric, result text)");
         // inserting data
        PreparedStatement prep = connection.prepareStatement("insert into rfidTagTable values(?,?,?,?,?,?,?,?,?);");
        
        
//          prep.setString(2, "John");
//          prep.setString(3, "21");
//          prep.setString(4, "male");
//          prep.setString(5, "77");
//          prep.setString(6, "185");
//          prep.execute();

         
     } catch (SQLException ex) {
         Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
     } 
 }
    
}
