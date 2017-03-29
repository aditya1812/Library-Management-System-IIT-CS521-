

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Librarian implements ActionListener,ItemListener
{
	JFrame LibrarianFrame;
	JTabbedPane tabs;
	JPanel panel;
	JTextField Bookname;
	JButton IssueBook,ViewAllrequestedBooks,AddMember,RemoveMember,ViewMemberList,AddBook,DeleteBook,ViewBookList,SearchBook,Logout;
	JPanel tab1,tab2,tab3;
	JComboBox<String> list;
	JLabel SearchBy;
	String searchtype;
	public Librarian() 
	{
		LibrarianFrame=new JFrame("Librarian Login");
		LibrarianFrame.setTitle("Librarian Login");
		LibrarianFrame.setVisible(true);
		LibrarianFrame.setSize(380,350);
		LibrarianFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tabs=new JTabbedPane();
		tab1=new JPanel();
		tab2=new JPanel();
		tab3=new JPanel();
		tabs.addTab("Manage Orders",tab1);
		tabs.addTab("Manage Members", tab2);
		tabs.addTab("Manage Books", tab3);
		LibrarianFrame.add(tabs);
		//Tab1 all components
		IssueBook=new JButton("Issue Book");
		Logout=new JButton("Logout");
		IssueBook.setToolTipText("Issue All Requested Books");
		ViewAllrequestedBooks=new JButton("View All Requested Books");
		ViewAllrequestedBooks.setToolTipText("View all requested book list and Issue Books");
		tab1.setLayout(null);
		tab1.add(IssueBook);
		tab1.add(ViewAllrequestedBooks);
		IssueBook.setBounds(10, 10, 340, 80);
		ViewAllrequestedBooks.setBounds(10,100,340,80);
		Logout.setBounds(90,200,170,40);
		tab1.add(Logout);
		Logout.addActionListener(this);
		IssueBook.addActionListener(this);
		ViewAllrequestedBooks.addActionListener(this);
		//Tab2 all components
		AddMember=new JButton("Add Member");
		RemoveMember=new JButton("Remove Member");
		ViewMemberList=new JButton("View Member List");
		tab2.setLayout(null);
		tab2.add(AddMember);
		tab2.add(ViewMemberList);
		tab2.add(RemoveMember);
		AddMember.setBounds(10, 10, 340, 80);
		RemoveMember.setBounds(10,100, 340, 80);
		ViewMemberList.setBounds(10, 190, 340, 80);
		AddMember.addActionListener(this);
		RemoveMember.addActionListener(this);
		ViewMemberList.addActionListener(this);
		//Tab3 all Components
		AddBook=new JButton("Add Book");
		DeleteBook=new JButton("DeleteBook");
		SearchBook=new JButton("Search Book");
		ViewBookList=new JButton("View Book List");
		list=new JComboBox<>(new String[]{"(Select)","Name","Author","ISBN","Domain"});
		SearchBy=new JLabel("Search by:");
		tab3.setLayout(null);
		tab3.add(AddBook);
		tab3.add(DeleteBook);
		tab3.add(SearchBook);
		tab3.add(ViewBookList);
		tab3.add(list);
		tab3.add(list);
		tab3.add(SearchBy);
		AddBook.setBounds(10, 10, 340, 50);
		DeleteBook.setBounds(10, 70, 340, 50);
		ViewBookList.setBounds(10, 130, 340, 50);
		SearchBook.setBounds(10, 190, 340, 50);
		SearchBy.setBounds(50,250,150,30);
		list.setBounds(150, 250, 150, 25);
		list.addItemListener(this);
		AddBook.addActionListener(this);
		DeleteBook.addActionListener(this);
		ViewBookList.addActionListener(this);
		SearchBook.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==AddBook){
				ManageBooks manageBooks=new ManageBooks();
				manageBooks.AddBook();
			}
			if(e.getSource()==DeleteBook){
				ManageBooks manageBooks=new ManageBooks();
				manageBooks.DeleteBook();
			}
			if(e.getSource()==ViewBookList){
				ManageBooks manageBooks=new ManageBooks();
				manageBooks.ViewBookList();
			}
			if(e.getSource()==SearchBook){
				ManageBooks manageBooks=new ManageBooks();
				manageBooks.SearchBook(searchtype);
			}
			if(e.getSource()==AddMember){
				ManageUser manageUser=new AddUser();
				manageUser.AddUser();
			}
			if(e.getSource()==RemoveMember){
				ManageUser manageUser=new RemoveUser();
				manageUser.RemoveUser();
			}
			if(e.getSource()==ViewMemberList){
				try {
					ManageUser manageUser=new ViewUserList();
					manageUser.ViewUserList();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getSource()==IssueBook){
				IssueBook issueBook=new IssueBook();
				issueBook.IssueBook();
			}
			if(e.getSource()==ViewAllrequestedBooks){
				ViewAllRequestedBooks viewallrequestedbooks = new ViewAllRequestedBooks();
				viewallrequestedbooks.ViewAllRequestedBooks();
			}
			if(e.getSource()==Logout){
				LibrarianFrame.dispose();
				new LibraryManagementSystem();
			}
		}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==list)
		{
			searchtype=list.getSelectedItem().toString();
		}
	}
}
