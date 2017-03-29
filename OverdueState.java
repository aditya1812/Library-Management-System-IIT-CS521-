

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OverdueState extends MemberState implements ActionListener
{
	private JFrame Overdueframe;
	private JDialog addcarddialog;
	private JButton Pay;
	private JButton ViewIssuedBooks;
	private JButton ViewAccountInfo;
	private JButton AddCard,addcard1;
	JFrame AccountInfoframe;
	JLabel memberIDLabel;
	JLabel firstNameLabel;
	JLabel lastNameLabel;
	JLabel emailIDLabel;
	JLabel memberIDTextfield;
	JLabel firstNameText;
	JLabel lastNameText;
	JLabel emailIDText;
	private JTable jt;
	private JDialog issuedbookdialog;
	private JLabel memberidlabel;
	DefaultTableModel tbm;
	Connection conn=LibraryManagementSystem.connection;
	String ISBN;
	String query,query2;
	int checker=0;
	int memberID;
	int x;
	Date d=null;
	public OverdueState(){
		
	}
	public OverdueState(int ID) 
	{	
		memberID=ID;
		Overdueframe=new JFrame("Member Overdue");
		Overdueframe.setSize(280,240);
		Overdueframe.setResizable(false);
		Overdueframe.setLayout(null);
		Pay=new JButton("Pay");
		ViewIssuedBooks=new JButton("View Issued Book");
		ViewAccountInfo=new JButton("View Account Info");
		AddCard=new JButton("Add Card");
		Pay.setBounds(10,10,250,40);
		ViewIssuedBooks.setBounds(10, 60, 250, 40);
		ViewAccountInfo.setBounds(10, 110, 250, 40);
		AddCard.setBounds(10, 160, 250, 40);
		Pay.addActionListener(this);
		ViewIssuedBooks.addActionListener(this);
		ViewAccountInfo.addActionListener(this);
		AddCard.addActionListener(this);
		Overdueframe.add(Pay);
		Overdueframe.add(ViewAccountInfo);
		Overdueframe.add(ViewIssuedBooks);
		Overdueframe.add(AddCard);
		Overdueframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Overdueframe.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Pay){
			PayPenalty();
		}
		if(e.getSource()==ViewAccountInfo){
			ViewAccountInfo();
		}
		if(e.getSource()==ViewIssuedBooks){
			ViewIssuedBooks();
		}
		if(e.getSource()==AddCard){
			AddCard();
		}
		if(e.getSource()==addcard1){
			setCardInfo(cardInfo);
			JOptionPane.showMessageDialog(null,"Payment method Added", "Card Added",1);
			addcarddialog.dispose();
		}
	}

	public void CheckPenalty(Date dueDate)
	{
		Date dNow = new Date( );
		String dateStart =dueDate.toString();
		String dateStop = dNow.toString();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
			long diff = d2.getTime() - d1.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			long penalty= diffDays*2;
			JOptionPane.showMessageDialog(null,("Amount = $"+penalty),"Payment Done",1);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void PayPenalty() 
	{
		try{
			query="SELECT DueDate FROM IssuedBook WHERE memberID="+memberID+";";
			PreparedStatement ps= conn.prepareStatement(query);
			ResultSet rs= ps.executeQuery(query);
			while(rs.next()){
				d= rs.getDate("DueDate");
				CheckPenalty(d);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"Amount Paid","Payment Done",1);
		Overdueframe.dispose();
		setState(getMemState());
	}
	public void ViewIssuedBooks()
	{	
		query="SELECT * FROM IssuedBook WHERE memberID="+memberID+";";
		try{
			PreparedStatement ps= conn.prepareStatement(query);
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
				issuedbookdialog=new JDialog(Overdueframe, "Issued Book List");
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
		System.out.println("View Account info");
		AccountInfoframe = new JFrame("Member Information");
		AccountInfoframe.setSize(300,260);
		AccountInfoframe.setLayout(null);
		AccountInfoframe.setResizable(false);
		query="SELECT * FROM Member WHERE memberID="+memberID+";";
		try{
			PreparedStatement ps= conn.prepareStatement(query);
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
	public void AddCard(){
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
}