

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewAllRequestedBooks {
	private Connection conn=LibraryManagementSystem.connection;
	private DefaultTableModel tbm;
	private JTable jt;
	private JDialog viewissuebookdialog;

	void ViewAllRequestedBooks(){
		try {	
			PreparedStatement ps=conn.prepareStatement("Select * from IssuedBook where pending=1;");
			ResultSet rs=ps.executeQuery();
			tbm=new DefaultTableModel(new String[]{"Order ID","ISBN","Member ID","Date of Issue","Due Date"},0);
			while(rs.next())
			{
				tbm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
			} 
		jt=new JTable();
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setModel(tbm);
		JScrollPane pane = new JScrollPane(jt);
		viewissuebookdialog=new JDialog();
		viewissuebookdialog.setTitle("Book List");
		jt.setEnabled(false);
		viewissuebookdialog.setSize(400,400);
		jt.setBounds(5,40,390,360);
		viewissuebookdialog.add(pane);
		viewissuebookdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		viewissuebookdialog.setVisible(true);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
