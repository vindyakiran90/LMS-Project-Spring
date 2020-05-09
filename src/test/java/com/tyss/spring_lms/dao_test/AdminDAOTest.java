package com.tyss.spring_lms.dao_test;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.IssueBook;
import com.tyss.spring_lms.beans.RequestBook;
import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.dao.AdminDAO;

public class AdminDAOTest {

	@Autowired
	private AdminDAO dao;

	@Test 
	public void testAddBook() { 
		BookBean bookBean = new BookBean();
		bookBean.setBookId(2000); 
		bookBean.setBookTitle("Antennas and Propagation");
		bookBean.setCategory("ECE"); 
		bookBean.setAuthor("Ramakrishna Janaswamy");
		bookBean.setNumberOfAvailableBooks(20); 
		bookBean.setNumberOfBooks(20);
		bookBean.setNumberOfIssuedBooks(1);
		bookBean.setPublisherName("IEEE Society"); 
		boolean status =  dao.addBook(bookBean);

		/*
		 * if(status) { System.out.println("Book added"); } else {
		 * System.out.println("Book not added"); }
		 */		
		Assertions.assertTrue(status); 
	}



	@Test 
	public void testDeleteBook() { 
		int bookId = 2001; 
		boolean status = dao.deleteBook(bookId); 
		Assertions.assertTrue(status); 
	}


	@Test 
	public void testIssueBook() { 
		IssueBook issueBook = new IssueBook();
		issueBook.setBookId(3002); 
		issueBook.setIssueDate(LocalDate.now());
		issueBook.setReturnDate(LocalDate.now().plusDays(10));
		issueBook.setUserId(14003); 
		boolean status = dao.issueBook(issueBook);
		Assertions.assertTrue(status); 
	}


	@Test public void testUpdateBook() { 
		String bookTitle = "Logic Design"; 
		int	numberOfBooks = 10;
		boolean status = dao.updateBook(bookTitle, numberOfBooks); 
		Assertions.assertTrue(status); 
	}

	@Test
	public void testShowRequest() {
		List<RequestBook> requestBooks = dao.showRequest();
		Assertions.assertNotNull(requestBooks);
	}

	@Test
	public void testShowUsers() {
		List<UserBean> requestBooks = dao.showUsers();
		Assertions.assertNotNull(requestBooks);
	}

	@Test
	public void testBookReturn() {
		int userId = 10002;
		int bookId = 2001;
		boolean status = dao.bookReturn(userId, bookId);
		Assertions.assertTrue(status);
	}
}

