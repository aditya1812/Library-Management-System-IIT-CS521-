

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ManageBooks implements ActionListener 
{
	Connection conn=LibraryManagementSystem.connection;
	private DefaultTableModel tbm;
	private JTable jt;
	private JDialog viewbooklistdialog;
	private static JDialog addbookdialog;
	private static JLabel ISBN;
	private static JLabel count;
	private static JLabel Name;
	private static JLabel Author;
	private static JLabel domain;
	private static JTextField bookName;
	private static JTextField bookAuthor;
	private static JTextField bookCount;
	private static JTextField bookDomain;
	private static JTextField bookISBN;
	private static JButton Add;
	private static JDialog deletebookdialog;
	private static JButton delete;
	StrategySearch search;
	public void AddBook() 
	{
		addbookdialog=new JDialog();
		addbookdialog.setTitle("Book Details");
		addbookdialog.setModal(true);
		addbookdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addbookdialog.setResizable(false);
		addbookdialog.setLayout(null);
		addbookdialog.setSize(300, 300);
		ISBN=new JLabel("Enter ISBN");
		Name=new JLabel("Enter Book Name");
		Author=new JLabel("Enter Author Name");
		count=new JLabel("Enter Book Count");
		domain=new JLabel("Enter Domain Name");
		bookISBN=new JTextField(); 
		bookName=new JTextField(); 
		bookAuthor=new JTextField(); 
		bookCount=new JTextField();
		bookDomain=new JTextField();
		ISBN.setBounds(10,10,100,25);
		Name.setBounds(10,45,100,25);
		Author.setBounds(10, 80, 120, 25);
		count.setBounds(10,115,120,25);
		domain.setBounds(10,150,120,25);
		bookISBN.setBounds(120, 10, 100, 25);
		bookName.setBounds(120, 45, 100, 25);
		bookAuthor.setBounds(120, 80, 100,25);
		bookCount.setBounds(120, 115, 50, 25);
		bookDomain.setBounds(130, 150, 100, 25);
		Add=new JButton("Add Book");
		Add.setBounds(60, 185,120, 30);
		Add.addActionListener(this);
		addbookdialog.add(domain);
		addbookdialog.add(bookDomain);
		addbookdialog.add(Add);
		addbookdialog.add(bookAuthor);
		addbookdialog.add(bookCount);
		addbookdialog.add(bookISBN);
		addbookdialog.add(bookName);
		addbookdialog.add(ISBN);
		addbookdialog.add(Name);
		addbookdialog.add(Author);
		addbookdialog.add(count);
		addbookdialog.setVisible(true);
	}
	public void DeleteBook() {
		deletebookdialog=new JDialog();
		deletebookdialog.setTitle("Book ISBN");
		deletebookdialog.setModal(true);
		deletebookdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		deletebookdialog.setLayout(null);
		deletebookdialog.setResizable(false);
		deletebookdialog.setSize(300, 150);
		ISBN=new JLabel("Enter ISBN");
		bookISBN=new JTextField(); 
		ISBN.setBounds(20,10,100,25);
		bookISBN.setBounds(120, 10, 150, 25);
		delete = new JButton("Delete Book");
		delete.addActionListener(this);
		delete.setBounds(50,45,120,30);
		deletebookdialog.add(ISBN);
		deletebookdialog.add(bookISBN);
		deletebookdialog.add(delete);
		deletebookdialog.setVisible(true);
	}
	public void SearchBook(String searchtype){
		if(searchtype.equalsIgnoreCase("Name"))
			search=new ByName();
		if(searchtype.equalsIgnoreCase("Domain"))
			search=new ByDomain();
		if(searchtype.equalsIgnoreCase("ISBN"))
			search=new ByISBN();
		if(searchtype.equalsIgnoreCase("Author"))
			search=new ByAuthor();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Add){
			try{
				Book book = new Book();
				book.setBookName(bookName.getText());
				book.setAuthor(bookAuthor.getText());
				book.setISBN(bookISBN.getText());
				book.setTotalCount(Integer.parseInt(bookCount.getText()));
				book.setAvaiableCount(book.getTotalCount());
				book.setDomain(bookDomain.getText());
				PreparedStatement pst = null;
				String insertIntoMemberQuery = "INSERT INTO Book VALUES "
						+ "(?, ?, ?, ?, ?,?);";
				pst = conn.prepareStatement(insertIntoMemberQuery);
				pst.setString(1, book.getISBN());
				pst.setString(2, book.getBookName());
				pst.setString(3, book.getAuthor());
				pst.setInt(4, book.getTotalCount());
				pst.setInt(5, book.getAvaiableCount());
				pst.setString(6, book.getDomain());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Book Added", "Book Added", 1);
				addbookdialog.dispose();
				CheckDomain checkDomain = new CheckDomain();
				checkDomain.Check(book);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,"No fields can be empty", "Error",0);
				ex.printStackTrace();
			}
		}
		if(e.getSource()==delete){
			System.out.println("delte");
			PreparedStatement pst = null;
			String deleteQuery = "DELETE FROM Book WHERE ISBN = ?;";
			try{
				pst = conn.prepareStatement(deleteQuery);
				pst.setString(1,bookISBN.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Book Deleted", "Book Deleted", 1);
				deletebookdialog.dispose();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	public void ViewBookList() {
		try{
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("Select * from book");
		tbm=new DefaultTableModel(new String[]{"ISBN","Book Name","Author","Total Count","Available Count","Domain"},0);
		while(rs.next())
		{
			tbm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
		}
		jt=new JTable();
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setModel(tbm);
		JScrollPane pane = new JScrollPane(jt);
		viewbooklistdialog=new JDialog();
		viewbooklistdialog.setTitle("Book List");
		jt.setEnabled(false);
		viewbooklistdialog.setSize(400,400);
		jt.setBounds(5,40,390,360);
		viewbooklistdialog.add(pane);
		viewbooklistdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		viewbooklistdialog.setVisible(true);}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
