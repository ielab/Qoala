/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qoala;



/**
 *
 * @author siavashsajadi
 */

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author n8654093
 */

public class setData {
    
    
    static String array;
    static int i=0;
    static int s=0;
    static int b=0;
    static int j=0;
    static int q=0;
    Configfile config =new Configfile();
   
    
    
    static int y;
    static LinkedList  sessionDurations = new LinkedList();
    static LinkedList  CountNumberOfSession = new LinkedList();

     
    static LinkedList numbQueriesInOneSession = new LinkedList();
    static LinkedList numberOfClick = new LinkedList();
    static LinkedList  <Datapoint> Datapoints = new LinkedList<Datapoint>();
    static LinkedList tempo = new LinkedList();
    static LinkedList calculatedterms = new LinkedList();
    static LinkedList avgCalculatedterms = new LinkedList();
    static double sessionDuration[] =new double[10000];
    static LinkedList sessiondurations= new LinkedList();
    
   
     //creates datapoint object based on how many lines we have// 
    public static void Dataset(String Address) throws FileNotFoundException, ParseException{
        
        BufferedReader br = null;
        BufferedReader breader = null;
        br = new BufferedReader(new FileReader(Address));
        breader=new BufferedReader(new FileReader(Address));
        Scanner scanner= new Scanner(br);
         Scanner scannerreader= new Scanner(breader);
        HashMap<Integer, String> map = new HashMap<>();
              while(scanner.hasNextLine()){
                Scanner scannerLine=new Scanner(scanner.nextLine());
                scannerLine.useDelimiter(" ");

                     y++;
              }
             System.out.println(y);
             String reader[][]= new String[y][5];
           for(int t=0 ;t<y;t++){
             for (int E=0 ;E<5;E++){
             reader[t][E]="-1";

             }    
            }
  
            y=j=q=0;
           while(scannerreader.hasNextLine()){
                Scanner scannerLine1=new Scanner(scannerreader.nextLine());
                scannerLine1.useDelimiter("\t");

                  while(scannerLine1.hasNext()){
                  map.put(i, scannerLine1.next());
                  reader[y][q]=map.get(i);
                 // fill spaces and tabs with -1//
                    if(q==3) {  
                      if(reader[y][q].equalsIgnoreCase("-1")){
                       reader[y][q]="-1";
                        reader[y][q+1]="-1";
                         }
                         }
                    if(q==4) {  
                      if(reader[y][q].equalsIgnoreCase("-1")){
                       reader[y][q-1]="-1";
                       }
                       }
                   i++;
                   q++;
                   }
                      j++;
                      y=j;
                      q=0;
              }
           
           for(int t=0 ;t<y;t++){
             for (int E=0 ;E<5;E++){
                 if(reader[t][E].equalsIgnoreCase("")){
                    reader[t][E]="-1";

             }
              }}
           
                  for(int k=0;k<y;k++){
                   for(int s=0;s<5;s++){
              System.out.print(" "+s+"   is "+reader[k][s]+"     ");}
                   System.out.println();

               }	
                  
                  
                  getdata(y, reader);
           
       
    }
    
    
    //Reads the AOL Search File and populates the respectives attribute by using datapoint objects//
    public static void getdata(int y,String fu[][]) throws ParseException, NullPointerException
    {       
        int kg=y;
    	String Temp;
    	for(int k=0;k<kg;k++){
         Datapoint datapoint= new Datapoint();
            for(int s=0;s<5;s++){
            	if (fu[k][s]==null)
            		break;	
            	Temp=fu[k][s];
                
       if (s==0)
   	{
            datapoint.setUserId(Temp);
            
   	}
   	else if(s==1)
   	{
            datapoint.setQuery(Temp);
   	}
   	else if (s==2)
   	{
            datapoint.setdate(Temp);
   	}
   	else if (s==3)
   	{
            datapoint.setItemRank(Temp);
   	}
   	else if (s==4)
   	{
            datapoint.setClickURL(Temp);
   	}
          
        Datapoints.add(k, datapoint);
       
       }
          
        }
       for(int w=0;w<kg;w++){
       System.out.println("user ID is  "+Datapoints.get(w).getUserId()+"query is "+Datapoints.get(w).getQuery()+"time is"+Datapoints.get(w).getdate()+"clicked is "+Datapoints.get(w).getItemRank()+"websites cliked is "+Datapoints.get(w).getClickURL());
       
       
       }
       
      
    }  
   
    //calculate and assign session number to each query log based on time(estimate 1800sec or 30 minutes)// 
    public void sessionID(double sec) throws ParseException, IndexOutOfBoundsException, IOException, Exception{
     
        int kg=y;
        int sessionNumber=0;
        Datapoint ab= new Datapoint();
        
    for (int Q=0 ;Q<kg-1;Q++){
      
        if(Datapoints.get(Q).getUserId().equalsIgnoreCase(Datapoints.get(Q+1).getUserId())){
//            System.out.print(Datapoints.get(Q).getUserId()+" ELO HELL "+Datapoints.get(Q+1).getUserId());
            boolean session=ab.SessionState(Datapoints.get(Q).GetDate(),Datapoints.get(Q+1).GetDate(),sec);
//           System.out.print(Datapoints.get(Q).GetDate()+" ELO HELL "+Datapoints.get(Q+1).GetDate());
                   
         
      System.out.println(session);
     
       if (session==true){
          
           Datapoints.get(Q).setSession(sessionNumber);
           Datapoints.get(Q+1).setSession(sessionNumber);
          
       }
           
        if (session==false) {
            
           Datapoints.get(Q).setSession(sessionNumber);
           sessionNumber++;
           Datapoints.get(Q+1).setSession(sessionNumber);
           
       }
      
      } else 
               sessionNumber++;
  
     }

    for(int u=0;u<y;u++)
        System.out.println(Datapoints.get(u).getSession());
    
       
    
   // numberOfQuery();
   }
    
    //calculate and assign number of queries in one session//
    public void numberOfQuery() throws IndexOutOfBoundsException, ParseException, IOException, Exception{
     
        int  numberOfQueries=0;
        int X=0;
        for (int p=0;p<y;p++){
         
            if (Datapoints.get(p).getSession()<=X){
                numberOfQueries++;
     
     
     }
            else {
     
                numbQueriesInOneSession.add(numberOfQueries);
                X++;
                numberOfQueries=1;
     
     
     }
    
    }
        for(int k=0 ;k<Datapoints.get(y).getSession();k++){
        
          System.out.println(" number of query is "+numbQueriesInOneSession.get(k)+"k is"+k);
    
        }
        
       //numberOfClick();
    }
    
    //calculate and assign number of clicks in one session to ssesionID//
     public void numberOfClick() throws IndexOutOfBoundsException, ParseException, IOException, Exception{
     
       int  click=0;
        int X=0;
    
        for (int p=0;p<y;p++){
        
            if (Datapoints.get(p).getSession()<=X){
        
                if(Datapoints.get(p).getItemRank().equalsIgnoreCase("-1")){
            
        }
             else{
                click++;
        }
     
     }
            else {
            
                numberOfClick.add(click);
                X++;
                click=0;
     
     
     }
    
    }
    for(int k=0 ;k<Datapoints.get(y).getSession();k++){
        System.out.println(" number of cliks "+numberOfClick.get(k));
    
    }
    int W=0;
    for(int R=0;R<Datapoints.get(y).getSession(); R++){
    for (;W<y;W++){
   
           if(Datapoints.get(R).getSession()==Datapoints.get(R).getSession())

       {
           int temp1=Integer.parseInt(numberOfClick.get(R).toString());
                   Datapoints.get(W).SetNumberOfClicks(temp1);
       }
           else
               break;
   
   }

  }
        //termsCalculator();
 }
    //Calculates number of terms and average number of terms in one session//
    public void termsCalculator()throws IndexOutOfBoundsException, ParseException, IOException, Exception
 {
    
     double temp2=0,cnt=0;
     int i=0;
     for(;i<y;i++){
         
         temp2+= Datapoints.get(i).calculteTerms(Datapoints.get(i).getQuery());
         cnt++;
      
         if(Datapoints.get(i).getSession()!=Datapoints.get(i+1).getSession())
            {
         
                calculatedterms.add(temp2);
                avgCalculatedterms.add(temp2/cnt);
                temp2=0;
                cnt=0;
      
      }
     
     }
 
    for(int r=0;r<calculatedterms.size();r++){
	 System.out.println(calculatedterms.get(r)+"-------!!--"+" "+ avgCalculatedterms.get(r));
    }
    
//    calculateSessionDuration();
//    Print();
    
    
//    createArffFile();
//    SimpleKmeans();
 }
    
 //calculates the duration of one session in seconds //
 public void calculateSessionDuration() throws ParseException, IOException{
	 
    int j=0;
    int k=0;
    int temp3;
    int temp4;
    Datapoint ab = new Datapoint();
    Date temp1= new Date();
    Date temp2= new Date();

    for(int i=0;i<Datapoints.get(y).getSession();i++){
	     
	     temp1=Datapoints.get(j).GetDate();
	     temp3=j;
        for(;j<y-1;j++){
	       
            if(Datapoints.get(j).getSession()==Datapoints.get(j+1).getSession()){
	        
            }
            else{
	             temp2=Datapoints.get(j).GetDate();
//	             sessionDuration[j]=ab.sessionTime(temp1,temp2);
//                     sessiondurations.add(ab.sessionTime(temp1,temp2));
	             temp4=j;
//	             for(int u=temp3;u<temp4;u++){
//	             
//                         sessionDuration[u]=ab.sessionTime(temp1, temp2);
//                         
//	             }
//                         System.out.println("sessiontime is "+"   "+ab.sessionTime(temp1,temp2) + " "+temp1 + " "+temp2);
                         j++;
	                 sessionDurations.add(ab.sessionTime(temp1, temp2));
	              
	                break;
	         }
	     }
	   }
	      
	 for(int e=0;e<=sessionDurations.size();e++)
		 CountNumberOfSession.add(e);
        
	  
	  }
 
//Returns number of session and passed to Instance dataset//
 public int GetSessionRetrieval(int h){
	 
     return (int) CountNumberOfSession.get(h);
 }
 
 //Returns number of clicks and passed to Instance dataset//
 public int GetClick (int w){
	 
     return (int) numberOfClick.get(w);
 }
 
 //Returns number of queries in one session and passed to Instance dataset//
 public int GetSessionNumber (int e){
	 
     return (int)numbQueriesInOneSession.get(e);
 }

 //Returns duration of a session and passed to Instance dataset//
 public double sessionDurationRetrieval(int i){
 
     return (double) sessionDurations.get(i);
 
 }
 //Returns number of terms in one session and passed to Instance dataset//
 public double GetTerms (int t){
	 
     return (double)calculatedterms.get(t);
 }
 
 //Returns average number of terms in one session and passed to Instance dataset//
 public double GetAverageTerms (int t){
	
     return (double)avgCalculatedterms.get(t);
 }
 
// Print methods//
    public void Print() throws ParseException, IOException{
//       for(int q=0;q<y;q++){
//       System.out.print("session duration is"+sessiondurations.get(i));
//       
//       }
        System.out.print(Datapoints.size()/5+"size is"+"");
                
        
       for(int f=0;f<y;f++)  
        System.out.print("  "+ Datapoints.get(f).getUserId()+"---->"+ Datapoints.get(f).getSession());
        System.out.println();
        for(int i=0;i<y;i++)
            System.out.println(Datapoints.get(i).getSession()+"---->"+Datapoints.get(i).getUserId()+"---->"+Datapoints.get(i).GetNumberOfClicks());
    for(int i=0;i<y;i++){
       System.out.println(Datapoints.get(i).calculteTerms(Datapoints.get(i).getQuery())+"-----------------------"+Datapoints.get(i).getQuery());
    }
      
    
    }
    
//    
//    public void createArffFile(){
//    
//    
//    arf.valueSession(this, y);
//    arf.ArffCreator();
//    
//    
//    
//    }
//    
//    public void SimpleKmeans() throws FileNotFoundException, IOException, Exception{
//   Properties prop= new Properties();
//        InputStream input= null;
//       
//        input= new FileInputStream("config.properties");
//        
//        prop.load(input);
//        String numberOfCluster=prop.getProperty("numberOfcluster");
//        int number=Integer.parseInt(numberOfCluster);
//        arf.SimpleKmeans(number);
//    
//    }
    
    
    
    
    
    
}