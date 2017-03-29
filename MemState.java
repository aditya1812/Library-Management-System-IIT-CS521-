
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class MemState extends MemberState implements ActionListener,ItemListener
{
	JFrame memstateframe;
	private JButton requestbook,requestbook2;
	private JButton returnbook,returnbook2;
	private JButton renewbook,Logout;
	private JButton searchbook;
	private JButton addcard,subscribe,unsubscribe;
	private JButton viewaccountinfo;
	private JButton viewissuedbook;
	private JDialog requestbookdialog,renewbookdialog,returnbookdialog,addcarddialog,issuedbookdialog;
	private JLabel emailIDLabel,lastNameLabel,firstNameLabel,ISBNlabel,memberidlabel;
	private JTextField ISBNText;
	JDialog subscribedomaindialog;
	private JTextField EmailIDtext;
	private JLabel EmailIDlabel;
	private JButton subscribe2;
	private JLabel domainlabel;
	private JTextField domaintext;
	private JDialog unsubscribedomaindialog;
	private JButton unsubscribe2;
	private JButton renewbook2,addcard1;
	private JDialog searchbookdialog;
	private JComboBox<String> searchBy;
	private JLabel searchbylabel;
	String searchtype;
	int x;
	private DefaultTableModel tbm;
	private JTable jt;
	private JFrame AccountInfoframe;
	Connection connection = LibraryManagementSystem.connection;
	String ISBN;
	String query,query2;
	public MemState(){

	}
	int memberID;
	public MemState(int ID) 
	{
		memberID=ID;
		System.out.println("ID====="+memberID);
		memstateframe=new JFrame("Member Login");
		memstateframe.setSize(340, 290);
		memstateframe.setLayout(null);
		memstateframe.setResizable(false);
		requestbook=new JButton("Request Book");
		returnbook=new JButton("Return Book");
		renewbook=new JButton("Renew Book");
		searchbook=new JButton("Search Book");
		viewissuedbook=new JButton("View Issued Book");
		viewaccountinfo=new JButton("View Account Info");
		unsubscribe=new JButton("Unsubscribe");
		subscribe=new JButton("Suscribe");
		addcard=new JButton("Add Card");
		Logout=new JButton("Logout");
		requestbook.setBounds(10, 10, 150, 40);
		returnbook.setBounds(10, 60, 150, 40);
		renewbook.setBounds(10, 110, 150, 40);
		searchbook.setBounds(170,10,150,40);
		viewaccountinfo.setBounds(170, 60, 150, 40);
		viewissuedbook.setBounds(170, 110, 150, 40);
		addcard.setBounds(170, 160, 150, 40);
		Logout.setBounds(170, 210, 150, 40);
		subscribe.setBounds(10,160,150,40);
		unsubscribe.setBounds(10,210,150,40);
		memstateframe.add(requestbook);
		memstateframe.add(renewbook);
		memstateframe.add(returnbook);
		memstateframe.add(searchbook);
		memstateframe.add(viewaccountinfo);
		memstateframe.add(viewissuedbook);
		memstateframe.add(addcard);
		memstateframe.add(Logout);
		memstateframe.add(subscribe);
		memstateframe.add(unsubscribe);
		requestbook.addActionListener(this);
		returnbook.addActionListener(this);
		renewbook.addActionListener(this);
		searchbook.addActionListener(this);
		viewaccountinfo.addActionListener(this);
		viewissuedbook.addActionListener(this);
		addcard.addActionListener(this);
		unsubscribe.addActionListener(this);
		subscribe.addActionListener(this);
		Logout.addActionListener(this);
		memstateframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		memstateframe.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==requestbook){
			requestbookdialog=new JDialog(memstateframe, "Request Book");
			requestbookdialog.setSize(320,120);
			requestbookdialog.setLayout(null);
			ISBNlabel=new JLabel("Enter ISBN");
			ISBNlabel.setBounds(10,10,120,25);
			requestbookdialog.add(ISBNlabel);
			ISBNText=new JTextField();
			ISBNText.setBounds(140,10,150,25);
			requestbookdialog.add(ISBNText);
			requestbook2=new JButton("Request Book");
			requestbook2.setBounds(10,45,150,25);
			requestbook2.addActionListener(this);
			requestbookdialog.add(requestbook2);
			requestbookdialog.setVisible(true);
		}
		if(e.getSource()==requestbook2){
			RequestBook(ISBNText.getText());
		}
		if(e.getSource()==renewbook){
			renewbookdialog=new JDialog(memstateframe, "Renew Book");
			renewbookdialog.setSize(320,160);
			renewbookdialog.setLayout(null);
			ISBNlabel=new JLabel("Enter ISBN");
			ISBNlabel.setBounds(10,10,120,25);
			renewbookdialog.add(ISBNlabel);
			memberidlabel=new JLabel("Enter Your ID");
			ISBNText=new JTextField();
			ISBNText.setBounds(140,10,150,25);
			renewbookdialog.add(ISBNText);
			renewbook2=new JButton("Renew Book");
			renewbook2.setBounds(10,80,150,25);
			renewbook2.addActionListener(this);
			renewbookdialog.add(renewbook2);
			renewbookdialog.setVisible(true);
		}
		if(e.getSource()==renewbook2){
			RenewBook(ISBNText.getText());
		}
		if(e.getSource()==returnbook){
			returnbookdialog=new JDialog(memstateframe, "Return Book");
			returnbookdialog.setSize(320,160);
			returnbookdialog.setLayout(null);
			ISBNlabel=new JLabel("Enter ISBN");
			ISBNlabel.setBounds(10,10,120,25);
			returnbookdialog.add(ISBNlabel);
			ISBNText=new JTextField();
			ISBNText.setBounds(140,10,150,25);
			returnbookdialog.add(ISBNText);
			returnbook2=new JButton("Return Book");
			returnbook2.setBounds(10,80,150,25);
			returnbook2.addActionListener(this);
			returnbookdialog.add(returnbook2);
			returnbookdialog.setVisible(true);
		}
		if(e.getSource()==returnbook2){
			ReturnBook(ISBNText.getText());
		}
		if(e.getSource()==searchbook){
			SearchBook();
		}
		if(e.getSource()==viewaccountinfo){
			ViewAccountInfo();
		}
		if(e.getSource()==viewissuedbook){
			ViewIssuedBooks();
		}
		if(e.getSource()==addcard){
			AddCard();
		}
		if(e.getSource()==addcard1){
			JOptionPane.showMessageDialog(null, "Card Added","Payment method Added",1);
			addcarddialog.dispose();
		}
		if(e.getSource()==subscribe){
			Subscriber();
		}
		if(e.getSource()==Logout){
			memstateframe.dispose();
			new LibraryManagementSystem();
		}
		if(e.getSource()==unsubscribe){
			Unsubscribe();
		}
		if(e.getSource()==subscribe2){
			PreparedStatement pst = null;
	        String insertIntoSubscriptionQuery = "INSERT INTO Subscriber VALUES "
					+ "(NULL, ?, ?);";
			try{
					pst = conn.prepareStatement(insertIntoSubscriptionQuery);
					pst.setString(1, EmailIDtext.getText());
					pst.setString(2, domaintext.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Subscribed to your domain","Subscribed",1);
					subscribedomaindialog.dispose();
	                }
	                catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		if(e.getSource()==unsubscribe2){
			PreparedStatement pst = null;
	        String deleteQuery = "DELETE FROM Subscriber WHERE emailID = ? AND domain = ?;";
			try{
					pst = connection.prepareStatement(deleteQuery);
					pst.setString(1, EmailIDtext.getText());
					pst.setString(2, domaintext.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Unsubscribed from your domain","Unsubscribed",1);
					unsubscribedomaindialog.dispose();
	                }
	                catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	private void Subscriber() {
		subscribedomaindialog=new JDialog();
		subscribedomaindialog.setTitle("Subscribe to Domain");
		subscribedomaindialog.setModal(true);
		subscribedomaindialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		subscribedomaindialog.setLayout(null);
		subscribedomaindialog.setResizable(false);
		subscribedomaindialog.setSize(300, 150);
		EmailIDlabel=new JLabel("Enter Email:");
		EmailIDtext=new JTextField(); 
		EmailIDlabel.setBounds(20,10,100,25);
		EmailIDtext.setBounds(120, 10, 150, 25);
		domainlabel=new JLabel("Enter Domain:");
		domaintext=new JTextField(); 
		domainlabel.setBounds(20,45,100,25);
		domaintext.setBounds(120, 45, 150, 25);
		subscribe2 = new JButton("Subscribe");
		subscribe2.addActionListener(this);
		subscribe2.setBounds(50,80,120,30);
		subscribedomaindialog.add(EmailIDlabel);
		subscribedomaindialog.add(EmailIDtext);
		subscribedomaindialog.add(domainlabel);
		subscribedomaindialog.add(domaintext);
		subscribedomaindialog.add(subscribe2);
		subscribedomaindialog.setVisible(true);
	}
	private void Unsubscribe() {
		unsubscribedomaindialog=new JDialog();
		unsubscribedomaindialog.setTitle("Unubscribe to Domain");
		unsubscribedomaindialog.setModal(true);
		unsubscribedomaindialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		unsubscribedomaindialog.setLayout(null);
		unsubscribedomaindialog.setResizable(false);
		unsubscribedomaindialog.setSize(300, 150);
		EmailIDlabel=new JLabel("Enter Email:");
		EmailIDtext=new JTextField(); 
		EmailIDlabel.setBounds(20,10,100,25);
		EmailIDtext.setBounds(120, 10, 150, 25);
		domainlabel=new JLabel("Enter Domain:");
		domaintext=new JTextField(); 
		domainlabel.setBounds(20,45,100,25);
		domaintext.setBounds(120, 45, 150, 25);
		unsubscribe2 = new JButton("Unsubscribe");
		unsubscribe2.addActionListener(this);
		unsubscribe2.setBounds(50,80,120,30);
		unsubscribedomaindialog.add(EmailIDlabel);
		unsubscribedomaindialog.add(EmailIDtext);
		unsubscribedomaindialog.add(domainlabel);
		unsubscribedomaindialog.add(domaintext);
		unsubscribedomaindialog.add(unsubscribe2);
		unsubscribedomaindialog.setVisible(true);
	}
	private void SearchBook() {
		searchbookdialog=new JDialog();
		searchbookdialog.setTitle("Search Book by:");
		searchbookdialog.setSize(300, 100);
		searchbookdialog.setResizable(false);
		searchbookdialog.setLayout(null);
		searchBy=new JComboBox<>(new String[]{"(Select)","Name","Author","ISBN","Domain"});
		searchbylabel=new JLabel("Search By:");
		searchbylabel.setBounds(20,20,150,25);
		searchBy.setBounds(150, 20, 120, 25);
		searchbookdialog.add(searchbylabel);
		searchbookdialog.add(searchBy);
		searchBy.addItemListener(this);
		searchbookdialog.setVisible(true);
	}
	public void RequestBook(String ISBN) 
	{
		try{
			query2="Update book set AvailableCount=AvailableCount-1 where ISBN=?;";
			PreparedStatement ps1=connection.prepareStatement(query2);
			ps1.setString(1, ISBN);
			ps1.executeUpdate();
			query="Insert into issuedbook Values(null,?,?,now(),addDate(now(),14),1);";
			PreparedStatement ps= connection.prepareStatement(query);
			ps.setString(1, ISBN);
			ps.setInt(2, memberID);
			ps.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Book Requested","Book Requested",1);
			renewbookdialog.dispose();
		}catch(SQLException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Book Not Available","Book Not Available",0);
		}
	}
	public void AddCard() 
	{
		addcarddialog=new JDialog();
		addcarddialog.setTitle("Add Card");
		addcarddialog.setSize(250,180);
		addcarddialog.setResizable(false);
		addcarddialog.setLayout(null);
		JLabel cardlabel=new JLabel("Enter Card Number");
		cardlabel.setBounds(20,10,150,30);
		addcarddialog.add(cardlabel);
		JTextField cardtext=new JTextField();
		cardtext.setBounds(20, 50, 200, 30);
		addcarddialog.add(cardtext);
		addcard1=new JButton("Add Card");
		addcard1.setBounds(20, 90, 120, 30);
		addcard1.addActionListener(this);
		addcarddialog.add(addcard1);
		addcarddialog.setVisible(true);	
	}
	public void RenewBook(String ISBN) {
		// TODO implement here

		query="SELECT DueDate FROM IssuedBook WHERE ISBN="+ ISBN +" and memberID="+memberID+";";
		try{
			PreparedStatement ps= connection.prepareStatement(query);
			ResultSet rs= ps.executeQuery(query);
			while(rs.next()){
				d=rs.getDate("DueDate");
				x= checkDate(d);
				System.out.println("X=========="+x);
				if(x==1){
					setState(getOverdueState());
				}
				if(x==0){
					query="UPDATE IssuedBook SET DueDate=ADDDATE(DueDate,14)WHERE ISBN="+ISBN+" and memberID="+memberID+";";
					JOptionPane.showMessageDialog(null, "Book Renewed","Book Renewed",1);
					PreparedStatement ps2= connection.prepareStatement(query);
					ps2.executeUpdate();
					renewbookdialog.dispose();
				}
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Wrong ISBN","Book cannot be renewed",1);
		}
	}

	public void ReturnBook(String ISBN) {
		// TODO implement here
		this.ISBN=ISBN;
		query="UPDATE Book SET AvailableCount=AvailableCount+1 WHERE ISBN="+ISBN+";";
		try{
			PreparedStatement ps= connection.prepareStatement(query);
			ps.executeUpdate(query) ;
			returnbookdialog.dispose();
		}
		catch(SQLException e){

		}
		query="SELECT DueDate FROM IssuedBook WHERE ISBN="+ISBN+" and memberID="+memberID+";";
		try{
			PreparedStatement ps= connection.prepareStatement(query);
			ResultSet rs= ps.executeQuery(query);
			while(rs.next()){
				d=rs.getDate("DueDate");
				x= checkDate(d);
				if(x==1){
					setState(getOverdueState());
				}
			}
			query="Delete from issuedbook where ISBN="+ISBN+" and memberID="+memberID+";";
			PreparedStatement ps2= connection.prepareStatement(query);
			ps2.executeUpdate();
			JOptionPane.showMessageDialog(null, "Book Returned","Book Returned",1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void ViewIssuedBooks()
	{	
		query="SELECT * FROM IssuedBook WHERE memberID="+memberID+";";
		try{
			PreparedStatement ps= connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery(query);
			if(rs.next())
			{
				tbm=new DefaultTableModel(new String[]{"Order ID","ISBN","Date of Issue","Due Date"},0);
				rs.beforeFirst();
				while(rs.next()){
					tbm.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5)});
				}
				jt=new JTable();
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				jt.setModel(tbm);
				JScrollPane pane = new JScrollPane(jt);
				issuedbookdialog=new JDialog(memstateframe, "Issued Book List");
				jt.setEnabled(false);
				issuedbookdialog.setSize(400,400);
				jt.setBounds(5,40,390,360);
				issuedbookdialog.add(pane);
				issuedbookdialog.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null,"No issued book","No issued book",0);
			}
		}
		catch(SQLException e){
		}
	}
	public void ViewAccountInfo()
	{
		AccountInfoframe = new JFrame("Member Information");
		AccountInfoframe.setSize(300,260);
		AccountInfoframe.setLayout(null);
		AccountInfoframe.setResizable(false);
		System.out.println("XXXXXXXXXXXXXX"+memberID);
		query="SELECT * FROM Member WHERE memberID="+memberID+";";
		try{
			PreparedStatement ps= connection.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				memberidlabel = new JLabel("Member ID : "+rs.getString(1));
				firstNameLabel=new JLabel("First Name : "+rs.getString(2));
				lastNameLabel=new JLabel("Last Name	: "+rs.getString(3));
				emailIDLabel=new JLabel("Email ID : "+rs.getString(4));
				memberidlabel.setBounds(10, 10, 250, 40);
				AccountInfoframe.add(memberidlabel);
				firstNameLabel.setBounds(10, 60, 250, 40);
				AccountInfoframe.add(firstNameLabel);
				lastNameLabel.setBounds(10, 110, 250, 40);
				AccountInfoframe.add(lastNameLabel);
				emailIDLabel.setBounds(10, 160, 250, 40);
				AccountInfoframe.add(emailIDLabel);
				AccountInfoframe.setVisible(true);
			}
		}
		catch(SQLException e){
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==searchBy){
			searchtype=searchBy.getSelectedItem().toString();
			SearchBook searchBook=new SearchBook();
			searchBook.SearchBook(searchtype);
		}
	}
}



