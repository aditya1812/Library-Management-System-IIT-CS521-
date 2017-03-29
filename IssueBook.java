

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class IssueBook {
	 private int OrderID;
	private String ISBN;
	 private int memberID;
	 private Date DateOfIssue;
	 private Date DueDate;
	 Connection conn=LibraryManagementSystem.connection;
	 	public int getOrderID() {
			return OrderID;
		}
		public void setOrderID(int orderID) {
			OrderID = orderID;
		}
		public String getISBN() {
			return ISBN;
		}
		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}
		public int getMemberID() {
			return memberID;
		}
		public void setMemberID(int memberID) {
			this.memberID = memberID;
		}
		public Date getDateOfIssue() {
			return DateOfIssue;
		}
		public void setDateOfIssue(Date dateOfIssue) {
			DateOfIssue = dateOfIssue;
		}
		public Date getDueDate() {
			return DueDate;
		}
		public void setDueDate(Date dueDate) {
			DueDate = dueDate;
		}
		void IssueBook(){
			try{
			String issuebookquery="update issuedbook set duedate= adddate(now(),14), dateofissue=now(), pending=0 where pending=1;";
			PreparedStatement ps= conn.prepareStatement(issuebookquery);
			ps.executeUpdate(issuebookquery);
			JOptionPane.showMessageDialog(null, "Books Issued","Book Issued",1);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
}
