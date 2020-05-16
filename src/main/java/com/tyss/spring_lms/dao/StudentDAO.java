package com.tyss.spring_lms.dao;

import java.util.List;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.BorrowBook;

public interface StudentDAO {
	boolean bookRequest(int userId, int bookId);
	List<BorrowBook> borrowedBook(int userId);
	BookBean searchBookById(int bookId);
	BookBean searchBookByTitle(String bookTitle);
	BookBean searchBookByAuthor(String author);
	List<BookBean> getBooksInfo();
	boolean bookReturn(int userId, int bookId);
}
