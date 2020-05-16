package com.tyss.spring_lms.service;

import java.util.List;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.IssueBook;
import com.tyss.spring_lms.beans.RequestBook;
import com.tyss.spring_lms.beans.UserBean;

public interface AdminService {
	
	boolean addBook(BookBean bean);
	boolean deleteBook(int bookId);
	boolean issueBook(int userId, int bookId);
	boolean updateBook(String bookTitle, int numberOfBooks);
	List<RequestBook> showRequest();
	List<UserBean> showUsers();
	List<IssueBook> issuedBooks();
	boolean isBookReceived(int userId, int bookId);
}
