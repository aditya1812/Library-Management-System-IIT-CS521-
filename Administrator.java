import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
public class Administrator extends Staff implements ActionListener
{
	JFrame adminframe;
	JDialog addbookdialog,deletebookdialog;
	private JButton AddStaff;
	private JButton RemoveStaff;
	private JButton ViewStaffList,Logout;
	private JButton AddBooks;
	private JButton DeleteBooks;
	Connection conn=LibraryManagementSystem.connection;
	JTextField bookISBN,bookName,bookAuthor,bookCount,bookDomain;
	ManageStaff staffManage;
	//Staff Interface
	public Administrator() 
	{
		adminframe=new JFrame("Admin Login");
		adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminframe.setSize(310,270);
		adminframe.setResizable(false);
		Logout=new JButton("Logout");
		JTabbedPane admintabs=new JTabbedPane();
		admintabs.setSize(310,230);
		JPanel managestaff=new JPanel();
		managestaff.setLayout(null);
		JPanel managebooks=new JPanel();
		managebooks.setLayout(null);
		admintabs.add("Manage Staff", managestaff);
		admintabs.add("Manage Books", managebooks);
		AddStaff=new JButton("Add Staff");
		RemoveStaff=new JButton("Remove Staff");
		ViewStaffList=new JButton("View Staff List");
		AddStaff.setBounds(20, 10, 250, 40);
		RemoveStaff.setBounds(20,60,250,40);
		ViewStaffList.setBounds(20, 110, 250, 40);
		managestaff.add(AddStaff);
		managestaff.add(RemoveStaff);
		managestaff.add(ViewStaffList);
		// Manage Books Interface
		AddBooks=new JButton("Add Book");
		DeleteBooks=new JButton("Delete Book");
		AddBooks.setBounds(20, 20, 250, 50);
		DeleteBooks.setBounds(20, 90, 250, 50);
		Logout.setBounds(60, 180, 170, 40);
		adminframe.add(Logout);
		managebooks.add(AddBooks);
		managebooks.add(DeleteBooks);
		adminframe.add(admintabs);
		adminframe.setVisible(true);
		AddStaff.addActionListener(this);
		RemoveStaff.addActionListener(this);
		ViewStaffList.addActionListener(this);
		AddBooks.addActionListener(this);
		Logout.addActionListener(this);
		DeleteBooks.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		try{
			if(e.getSource()==AddBooks){
				AddBook addBook=new AddBook();
				addBook.AddBook();
			}
			if(e.getSource()==DeleteBooks){
				DeleteBook deleteBook=new DeleteBook();
				deleteBook.DeleteBook();
			}
			if(e.getSource()==AddStaff){
				staffManage=new ManageStaff();
				staffManage.AddStaff();
			}
			if(e.getSource()==RemoveStaff){
				staffManage=new ManageStaff();
				staffManage.RemoveStaff();
			}
			if(e.getSource()==ViewStaffList){
				staffManage=new ManageStaff();
				staffManage.ViewStaffList();
			}
			if(e.getSource()==Logout){
				adminframe.dispose();
				new LibraryManagementSystem();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}