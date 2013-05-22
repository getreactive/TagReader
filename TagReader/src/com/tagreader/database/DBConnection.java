/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tagreader.database;

import com.tagreader.flatfile.TagFlatFileProcesser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahul
 */
public class DBConnection {
    private ResultSet resultSet;
    
 
    
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
     TagFlatFileProcesser tf = new TagFlatFileProcesser();
     try {
         Connection connection = getConnection();
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);  // set timeout to 30 sec.
         statement.executeUpdate("drop table if exists rfidTagTable");
         statement.executeUpdate("create table rfidTagTable (slno integer,tagid string, username string, scantime string,lap1 numeric, lap2 numeric, lap3 numeric, lap4 numeric, result text)");
         // inserting data
        PreparedStatement prep = connection.prepareStatement("insert into rfidTagTable values(?,?,?,?,?,?,?,?,?);");
        uniqueTagList = tf.getAllUnoqueTagId();
        System.out.println(uniqueTagList);
       for (int j = 0; j < uniqueTagList.size(); j++) {
        
           int slNo= j+1;
          
           String tagId = (String) uniqueTagList.get(j);
            prep.setInt(1, slNo);
            prep.setString(2, tagId);
            prep.setString(4, datetime());
            prep.execute();
}
    resultSet = statement.executeQuery("SELECT * FROM rfidTagTable");  
            while (resultSet.next()) {  
                System.out.println("SlNo:"  + resultSet.getInt("slno")+" " 
                        + resultSet.getString("tagid")+" "+ resultSet.getString("scantime"));  
            }           
         
     } catch (SQLException ex) {
         Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
     } 
 }
 
 
 public void updateTable() {
 
 
 
 
 
 }

    private String datetime() {
        
        Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	System.out.println( sdf.format(cal.getTime()) );
        return sdf.format(cal.getTime());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
