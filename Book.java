public class Book {
	private String ISBN;
    private String BookName;
    private String Author;
    private String domain;
    public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	private int TotalCount;
    private int AvaiableCount;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public int getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}
	public int getAvaiableCount() {
		return AvaiableCount;
	}
	public void setAvaiableCount(int avaiableCount) {
		AvaiableCount = avaiableCount;
	}

}
