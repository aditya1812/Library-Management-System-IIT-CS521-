

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sun.security.action.GetBooleanAction;

public class Member implements User,Observer
{
	int memberID;
	int cardInfo;
	String ISBN;
	private String FName;
	private String LName;
	private String Email;
	private String Password;
	private String SecQuestion;
	private String SecAnswers;
	MemberState s;
	String query;
	Connection conn= LibraryManagementSystem.connection;
	private int x;
	public Member() {
	}
	public Member(int ID) {
		setMemberID(ID);
		memberID=ID;
		query="SELECT * FROM IssuedBook WHERE memberID="+memberID+";";
		try{
			PreparedStatement ps= conn.prepareStatement(query);
			ResultSet rs=ps.executeQuery(query);
			if(rs.next()){
				ISBN=rs.getString(2);
			}
			query="SELECT DueDate FROM IssuedBook WHERE ISBN="+ISBN+" and memberID="+memberID+";";
				PreparedStatement ps2= conn.prepareStatement(query);
				ResultSet rs2= ps.executeQuery(query);
				if(rs2.next()){
					d=rs2.getDate("DueDate");
					x = checkDate(d);
					if(x==1){
						setState(getOverdueState());
					}
					if(x==0){
						setState(getMemState());
					}
				}
			}
		catch(Exception ex){
			ex.printStackTrace();
		}
		}
	
		int checker=0;
		Date d;
		Date current=new Date();
		public int checkDate(Date dueDate){
			Date dNow = new Date( );
			if(dNow.compareTo(dueDate)>0){
				checker=1;
			}
			return checker;
		}
		public String getFName() {
			return FName;
		}
		public void setFName(String fName) {
			FName = fName;
		}
		public String getLName() {
			return LName;
		}
		public void setLName(String lName) {
			LName = lName;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
		public String getSecQuestion() {
			return SecQuestion;
		}
		public void setSecQuestion(String secQuestion) {
			SecQuestion = secQuestion;
		}
		public String getSecAnswers() {
			return SecAnswers;
		}
		public void setSecAnswers(String secAnswers) {
			SecAnswers = secAnswers;
		}
		public void setState(MemberState ms)
		{
			this.s = ms;
		}
		public MemberState getMemState(){
			return new MemState(memberID);
		}
		public MemberState getOverdueState(){
			return new OverdueState(memberID);
		}	
		public int getCardInfo(){
			return cardInfo;
		}
		public void setCardInfo(int cardInfo) {
			this.cardInfo=cardInfo;
		}
		public void RequestBook(String ISBN){
			s.RequestBook(ISBN);
		}
		public void RenewBook(){
			s.RenewBook();
		}
		public void ReturnBook(String ISBN){
			s.ReturnBook(ISBN);
		}
		public void AddCard(){
			s.AddCard();
		}
		public void ViewIssuedBooks(){
			s.ViewIssuedBooks();
		}
		public void ViewAccountInfo(int memberID){
			s.ViewAccountInfo(memberID);
		}
		public void PayPenalty(int memberID){
			s.PayPenalty(memberID);
		}
		public void getBookISBN(){
		}
		public void setMemberID(int ID) {
			this.memberID=ID;
		}
		public int getMemberID() {
			return memberID;
		}
		@Override
		public void PayPenalty() {
		}
		@Override
		public void update(List<Subscription> subscribers, Book book) {
			final String username = "LibraryManagementSystem9@gmail.com";
			final String password = "Library@1234";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				for (Subscription subscription : subscribers) {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(subscription.getEmailId()));
					message.setSubject("New Book Added");
					String bookDetails = "Book Name :" + book.getBookName() + "\n" + "ISBN : " + book.getISBN() + "\n"
							+ "Total Count : " + book.getTotalCount() + "\n";
					message.setText("Dear User," + "\n\n The following book is added to the system: "+ bookDetails + "\n From \n Your Library");

					Transport.send(message);

					System.out.println("Done");
				}
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}


	}
