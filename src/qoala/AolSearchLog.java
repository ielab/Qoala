/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qoala;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

/**
 *
 * @author siavashsajadi
 */
public class AolSearchLog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NullPointerException, FileNotFoundException, ParseException, Exception {
        // TODO code application logic here
         Configfile configefile= new Configfile();
//         configefile.setPropValues();
         configefile.getPropValues();
         
         Properties prop= new Properties();
         InputStream input= null;
         input= new FileInputStream("config.properties");
         prop.load(input);
         
         setData set= new setData();
         set.Dataset(prop.getProperty("Address"));
         set.sessionID(Integer.parseInt(prop.getProperty("sessionDuration")));
         set.numberOfQuery();
         set.numberOfClick();
         set.termsCalculator();
         set.calculateSessionDuration();
         set.Print();
         
         
        arff arf= new arff();
        arf.valueSession(set);
        arf.ArffCreator();
        if(prop.getProperty("methods").equalsIgnoreCase("x-means")){
        arf.XMenas();}
        if(prop.getProperty("methods").equalsIgnoreCase("k-means")){
            
        arf.SimpleKmeans(Integer.parseInt(prop.getProperty("numberOfcluster")));
        }
        if(prop.getProperty("methods").equalsIgnoreCase("EM")){
        arf.EMClustering(Integer.parseInt(prop.getProperty("numberOfcluster")));
        
        }
        
        
    }
    
}
