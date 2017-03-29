import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Subject {

   private List<Subscription> observerList;
   
   
   public Subject(String domain) {
	
	this.observerList = getSubscriberList(domain);
}
public List<Subscription> getSubscriberList(String domain){
	   
	   Connection connection = LibraryManagementSystem.connection;
       //PreparedStatement pst = null;
       String bookListQuery = "SELECT * FROM Subscriber WHERE domain =\""+ domain +"\";";
       ResultSet result = null;
       List<Subscription> subsList = new ArrayList<>();
       
				
		try{
			 Statement statement = connection.createStatement();
             result = statement.executeQuery(bookListQuery);
             while(result.next()) {

            	    String userEmail = result.getString("emailID");
            	    String userDomain = result.getString("domain");
            	    Subscription subs = new Subscription();
            	    subs.setEmailId(userEmail);
            	    subs.setDomain(userDomain);
            	    subsList.add(subs);
            	}

          }catch(SQLException e){
			e.printStackTrace();
		}
		return subsList;
   }
    public void Register(String emailId, String domain) {
        
        Connection connection = LibraryManagementSystem.connection;
        PreparedStatement pst = null;
        String insertIntoSubscriptionQuery = "INSERT INTO Subscriber VALUES "
				+ "(NULL, ?, ?);";
		try{
				pst = connection.prepareStatement(insertIntoSubscriptionQuery);
				pst.setString(1, emailId);
				pst.setString(2, domain);
				pst.executeUpdate();
                }
                catch(SQLException e){
			e.printStackTrace();
		}
    }
    public void Unregister(String emailId, String domain) {
    	Connection connection = LibraryManagementSystem.connection;
        PreparedStatement pst = null;
        String deleteQuery = "DELETE FROM Subscriber WHERE emailID = "
				+ emailId +"AND domain ="+domain +";";
		try{
				pst = connection.prepareStatement(deleteQuery);
				pst.executeUpdate();
                }
                catch(SQLException e){
			e.printStackTrace();
		}
    }
    public void notifyUsers(Book book) {
    	
    	Observer obs = new Member();
    	obs.update(observerList,book);
      
    }
    public List<Subscription> getObserverList() {
    	
    	return observerList;
    }
    public void setObserverList(List<Subscription> observerList) {
    	this.observerList = observerList;
    }

}