/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tagreader.flatfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rahul
 */
public class TagFlatFileProcesser {
   ArrayList<String> _tempTagFiledata = new ArrayList<>();
   ArrayList<String> _uniqueTagData = new ArrayList<>();
   BufferedReader br = null;
   String sCurrentLine;
   
    public ArrayList getTagFileData() {
    
    ArrayList<String> tagFiledata = new ArrayList<>();
    String[] _tempdata = new String[10000];
    
    try{
     br = new BufferedReader(new FileReader("C:\\HexData\\log_hex (4).txt"));
        int i = 0;
        while ((sCurrentLine = br.readLine()) != null) {
            i = i + 1; 
            _tempdata[i] = sCurrentLine;
        }
      } catch(Exception e){
      System.out.println("getTagFileData Exception "+e);
      }
    
   for(int j = 0; j <= _tempdata.length-1;j++){

     String pattern ="32 12 (\\d+\\w*) 0E 30 00"; 
     Pattern r = Pattern.compile(pattern);
     Matcher m;
     
    if(_tempdata[j] != null){
         m = r.matcher(_tempdata[j]);
         if(m.find())
         {
            tagFiledata.add(_tempdata[j]); 
         }
     }
 }
return tagFiledata;
}
   
 public ArrayList getAllUnoqueTagId(){
     
        ArrayList<String> _tempFiledata;
       _tempFiledata = new ArrayList<>();
       _tempFiledata = getTagFileData();
       //System.out.println("SIZE"+_tempFiledata.size());
        ArrayList<String> _UniqueIdList = new ArrayList<>();
       int count = 0; 
       for(int j = 0; j <= _tempFiledata.size()-1;j++){

        String pattern ="32 12 (\\d+\\w*) 0E 30 00"; 
        Pattern r = Pattern.compile(pattern);
         Matcher m;
        if(_tempFiledata.get(j) != null){
         m = r.matcher(_tempFiledata.get(j).toString());
         if(m.find())
         {          
          String _temp = _tempFiledata.get(j).toString();
          for (String retval: _temp.split("32 12 (\\d+\\w*) 0E 30 00")){

           if(retval.indexOf(":")!=-1 || retval.length()< 37 ){
            } else {
               // System.out.println("Retval Length"+retval.length());
                retval = retval.substring(0,36);
                  _UniqueIdList.add(retval);  
              }  
         }
         
     }
       
 }     
}
  Set<String> uniqueTag = new HashSet<>(_UniqueIdList);
      // System.out.println("Unique gas count: " + uniqueTag.size());
       for (Iterator<String> it = uniqueTag.iterator(); it.hasNext();) {
           String item = it.next();
           System.out.println(item);
       }
       ArrayList<String> list = new ArrayList<>(uniqueTag);
       return list; 
      
}
}
