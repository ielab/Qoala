/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qoala;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import weka.datagenerators.Test;


public class Configfile {
	
//	public void setPropValues() throws IOException, NullPointerException {
//            Properties prop= new Properties();
//            OutputStream output=null;
//           try{ 
//            output= new FileOutputStream("config.properties");
//            prop.setProperty("Address","/Users/siavashsajadi/Desktop/finalSample.txt");
//            prop.setProperty("sessionDuration","1800");
//            prop.setProperty("numberOfcluster","2");
//            prop.setProperty("methods","x-means");
//            
//            prop.store(output, null);
//           }
//            catch(IOException io){
//            io.printStackTrace();
//            }
//            finally{
//            if(output!=null){
//              try{
//              output.close();
//              
//              }
//            catch(IOException e){
//            e.printStackTrace();
//            }
//            
//            }
//           
//           }
//           
//           
//           
//           
//            
//        }
        
        
        public void getPropValues() throws IOException, NullPointerException, FileNotFoundException, ParseException, Exception{
        Properties prop= new Properties();
        InputStream input= null;
        try{
        input= new FileInputStream("config.properties");
        
        prop.load(input);
        
        System.out.println(prop.getProperty("Address"));
        System.out.println(prop.getProperty("sessionDuration"));
                
        
        }
        catch(IOException ex){
        ex.printStackTrace();
        
        } finally{
         if(input!=null){
          try{
          input.close();
          
          } catch(IOException e){
          
          e.printStackTrace();
          }
         
         
         }
        
        
        }
        
        
        
        }
}

