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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ByAuthor extends StrategySearch implements ActionListener
{
	Statement statement = null;
	ResultSet rs;
	Connection connection =LibraryManagementSystem.connection;
	private final JTextField searchtext;
	private final JButton searchButton;
	JDialog byAuthor;
	private JLabel searchlabel;
	JTable list;
	JDialog dialog;
	TableModel tbm;
	ByAuthor() {
		searchlabel=new JLabel("Enter Author:");
		byAuthor=new JDialog();
		byAuthor.setResizable(false);
		byAuthor.setLayout(null);
		byAuthor.setModal(true);
		byAuthor.setTitle("Search By Author");
		byAuthor.setSize(280,150);
		byAuthor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		searchtext=new JTextField();
		searchButton=new JButton("Search Book");
		searchButton.addActionListener(this);
		byAuthor.add(searchButton);
		byAuthor.add(searchtext);
		byAuthor.add(searchlabel);
		this.searchlabel.setBounds(10,20,100,30);
		this.searchtext.setBounds(90,20,170,30);
		this.searchButton.setBounds(60, 70, 150, 30);
		byAuthor.setVisible(true);
	}
	public void search() throws SQLException {
		String authorname=this.searchtext.getText();
			String searchByAuthorQuery = "SELECT * FROM book WHERE Author=?";
			PreparedStatement ps = connection.prepareStatement(searchByAuthorQuery);
			ps.setString(1, authorname);
			rs=ps.executeQuery();
			tbm=new DefaultTableModel(new String[]{"ISBN","Book Name","Author","Total Count","Available Count"},0);
			while(rs.next())
			{
				((DefaultTableModel) this.tbm).addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
			}
			list=new JTable(tbm);
			list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			JScrollPane pane = new JScrollPane(list);
			dialog=new JDialog();
			dialog.add(pane);
			dialog.setTitle("Book List");
			list.setEnabled(false);
			dialog.setSize(500,500);
			list.setBounds(0,0,500,500);
			dialog.add(pane);
			dialog.setVisible(true);  
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==searchButton)
		{
				byAuthor.dispose();
				try {
					search();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
}