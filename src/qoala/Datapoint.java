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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

//this class consist of exclusively of methods that operates on or return attributes from AOL.  
public class Datapoint {
        private int sessionStage;
	private String UserId; 
	private String ItemRank; 
	private String QueryLength;
	private String Query, date, ClickURL;
	Date datetime;
        private char[] string;
        private int term=1;
        private int numberOfClickPerSe=0;
        int numberOfQueriesInOneSession;
	Datapoint()
	{
		
            UserId=ItemRank=QueryLength=Query=date=ClickURL="";
	}
	
        //Read from Aol log and sets the values// 
        public void setUserId(String fu) {
		
            UserId = fu;
	}
        
        //retrieve the userID values//
	public String getUserId() {
		
            return UserId;
	}
        
        
        //Read from Aol log and sets the values of Item Rank//
	public void setItemRank(String itemRank) {
		
            ItemRank = itemRank;
	}
        
        //retrieve the item rank values//
	public String getItemRank() {
		
            return ItemRank;
	}
	
        //Read from Aol log and sets the values of QueryLength//
        public void setQueryLength(String queryLength) {
		
            QueryLength = queryLength;
        }
        //retrieve the query-length values//
	public String getQueryLength() {
		
            return QueryLength;
	}
	
	
        //sets the dates format to date-hour-minutes-seconds//
	public void setdate(String dt) throws ParseException{
		
            date = dt;
	    datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
				
		
	}
        //retrieve values of date in string format//
        public String getdate()
	{
		
            return date;
	}
        //retrieve values of date in string dateformat//
        public Date GetDate(){
        
            return datetime;
        
        }
        //Read from AOL serach LOG and setts the clicked URL//
        public void setClickURL(String clickURL) {
		
            if (clickURL==null)
			ClickURL="";
		else
		ClickURL = clickURL;
	}
        //Retrieves the query that was clicked//
	public String getClickURL() {
		
            return ClickURL;
	}
	
        //Read from AOL Search LOG and sets the query//
        public void setQuery(String query) {
		
            Query = query;
	}
        
        //Retrieves Query//
	public String getQuery() {
		
            return Query;
	}
       
        //this method will calculate number of terms based on spaces//
        public int calculteTerms(String s){
        string=s.toCharArray();
        String trimmed = s.trim();
        int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
        return words;
        }
       

    //calculates diffrence between two times.If the result is negative then it will change it to positive.//
    public double sessionTime(Date F , Date G) throws ParseException{
      
        double Diff=F.getTime()-G.getTime();
        double Hdiff=Diff/1000;
          if (Hdiff>=0){
     
            return Hdiff;
         }
          else 
           return -Hdiff;
      
    }
    
    // Checks whether two times are in one session or not//
      public boolean SessionState(Date h, Date j,double sessionDuration) throws ParseException{
         
          
          double sessionTime=sessionTime(h,j);
          if (sessionTime<=sessionDuration){
             return true;
          //returns true if they are in same session//
          } else 
              return false;
          //returns false if they are in same session//
      
      }
  
      // Sets the session ID number//
      public void setSession(int a){
      
          sessionStage=a;
       }
      //Retrives the Session ID number//
      public int getSession(){
      
          return sessionStage;
     }
      //sets the number of Sessions//
      public void SetnumberOfSession(int Q){
     
          numberOfQueriesInOneSession=Q;
      
      }
      //Retrives the number sessions//
      public int getNumberOFsession(){
      
          return numberOfQueriesInOneSession;
      
      }
      //Sets the number of clicks in one session//
      public void SetNumberOfClicks(int clicks){
      
          numberOfClickPerSe=clicks;
      
      }
      //Retrieves the number of clicks in one session//
      public int GetNumberOfClicks(){
      
          return numberOfClickPerSe;
     
      }
      
     
      
      

}
