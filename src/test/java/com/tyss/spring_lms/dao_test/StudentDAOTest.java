package com.tyss.spring_lms.dao_test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.BorrowBook;
import com.tyss.spring_lms.dao.StudentDAO;

public class StudentDAOTest {

	@Autowired
	StudentDAO dao;
	
	@Test 
	public void testBookRequest() {
		int userId = 14001;
		int bookId = 2001;
		boolean status = dao.bookRequest(userId, bookId);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testBorrowedBook() {
		int userId = 14001;
		List<BorrowBook> borrowBook = dao.borrowedBook(userId);
		Assertions.assertNotNull(borrowBook);
	}
	
	@Test
	public void testSearchBookById() {
		int id = 2001;
		BookBean bookBean = dao.searchBookById(id);
		Assertions.assertNotNull(bookBean);
	}
	
	@Test
	public void testSearchBookByTitle() {
		String bookTitle = "Antennas Propagation";
		BookBean bookBean = dao.searchBookByTitle(bookTitle);
		Assertions.assertNotNull(bookBean);
	}
	
	@Test
	public void testSearchBookByAuthor() {
		String author = "Hall";
		BookBean bookBean = dao.searchBookByAuthor(author);
		Assertions.assertNotNull(bookBean);
	}
	
	@Test
	public void testGetBooksInfo() {
		List<BookBean> bookBean = dao.getBooksInfo();
		Assertions.assertNotNull(bookBean);
	}
	
	@Test
	public void testBookReturn() {
		int userId = 10002;
		int bookId = 2001;
		boolean status = dao.bookReturn(userId, bookId);
		Assertions.assertTrue(status);
	}
}
