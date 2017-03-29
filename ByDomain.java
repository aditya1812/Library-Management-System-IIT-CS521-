
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ByDomain extends StrategySearch implements ActionListener
{
	Statement statement = null;
	ResultSet rs;
	Connection connection = LibraryManagementSystem.connection;
	private final JTextField searchtext;
	private final JButton searchButton;
	JDialog byDomain;
	private JLabel searchlabel;
	JTable list;
	JDialog dialog;
	TableModel tbm;
	ByDomain() {
		searchlabel=new JLabel("Enter Domain:");
		byDomain=new JDialog();
		byDomain.setResizable(false);
		byDomain.setLayout(null);
		byDomain.setModal(true);
		byDomain.setTitle("Search By Domain");
		byDomain.setSize(280,150);
		byDomain.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		searchtext=new JTextField();
		searchButton=new JButton("Search Book");
		searchButton.addActionListener(this);
		byDomain.add(searchButton);
		byDomain.add(searchtext);
		byDomain.add(searchlabel);
		this.searchlabel.setBounds(10,20,100,30);
		this.searchtext.setBounds(90,20,170,30);
		this.searchButton.setBounds(60, 70, 150, 30);
		byDomain.setVisible(true);
	}
	public void search() {
		String domainname=this.searchtext.getText();
		try{
			String searchbyDomainQuery = "SELECT * FROM book WHERE domain=?";
			PreparedStatement ps = connection.prepareStatement(searchbyDomainQuery);
			ps.setString(1, domainname);
			rs=ps.executeQuery();
			dialog=new JDialog(byDomain, "Search Results");
			tbm=new DefaultTableModel(new String[]{"ISBN","Book Name","Author","Total Count","Available Count"},0);
			while(rs.next())
			{
				((DefaultTableModel) this.tbm).addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
			}
			list=new JTable(tbm);
			list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			JScrollPane pane=new JScrollPane(list);
			dialog.add(pane);
			dialog.setSize(500,500);
			dialog.setModal(true);
			dialog.setVisible(true);  
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==searchButton)
		{
			byDomain.dispose();
			search();
		}
	}
}