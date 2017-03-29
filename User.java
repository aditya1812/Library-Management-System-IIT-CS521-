
import java.util.*;
public interface User{
	public String getFName();
	public void setFName(String fName);
	public String getLName();
	public void setLName(String lName);
	public String getEmail();
	public void setEmail(String email);
	public String getPassword();
	public void setPassword(String password);
	public String getSecQuestion();
	public void setSecQuestion(String secQuestion);
	public String getSecAnswers();
	public void setSecAnswers(String secAnswers);
	public void setState(MemberState s);
	public MemberState getMemState();
	public MemberState getOverdueState();
	public int getMemberID();
	public void setMemberID(int memberID);
	public int getCardInfo();
	public void setCardInfo(int cardInfo);
	public void RequestBook(String ISBN);
	public void RenewBook();
	public void ReturnBook(String ISBN);
	public void AddCard();
	public void ViewIssuedBooks();
	public void ViewAccountInfo(int memberID);
	public void getBookISBN();
	void PayPenalty();
}