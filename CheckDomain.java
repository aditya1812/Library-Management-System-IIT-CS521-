


import java.util.*;

public class CheckDomain{
    public void Check(Book book) {
        Subject subj = new Subject(book.getDomain());
        subj.notifyUsers(book);
    }

}