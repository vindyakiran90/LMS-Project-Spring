package com.tyss.spring_lms.service_test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.IssueBook;
import com.tyss.spring_lms.beans.RequestBook;
import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.service.AdminService;

public class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;
	
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
		boolean status =  adminService.addBook(bookBean);

		/*
		 * if(status) { System.out.println("Book added"); } else {
		 * System.out.println("Book not added"); }
		 */		
		Assertions.assertTrue(status); 
	}



	@Test 
	public void testDeleteBook() { 
		int bookId = 1001; 
		boolean status = adminService.deleteBook(bookId); 
		Assertions.assertTrue(status); 
	}


	@Test 
	public void testIssueBook() {
		int userId = 14001;
		int bookId = 1003;
		boolean status = adminService.issueBook(userId, bookId);
		Assertions.assertTrue(status); 
	}


	@Test public void testUpdateBook() { 
		String bookTitle = "Logic Design"; 
		int	numberOfBooks = 10;
		boolean status = adminService.updateBook(bookTitle, numberOfBooks); 
		Assertions.assertTrue(status); 
	}

	@Test
	public void testShowRequest() {
		List<RequestBook> requestBooks = adminService.showRequest();
		Assertions.assertNotNull(requestBooks);
	}

	@Test
	public void testShowUsers() {
		List<UserBean> requestBooks = adminService.showUsers();
		Assertions.assertNotNull(requestBooks);
	}
	
	@Test
	public void testIssuedBooks() {
		List<IssueBook> issuedBooks = adminService.issuedBooks();
		Assertions.assertNotNull(issuedBooks);
	}
	
	@Test
	public void testIsBookReceived() {
		int userId = 10002;
		int bookId = 1000;
		boolean status = adminService.isBookReceived(userId, bookId);
		Assertions.assertTrue(status);
	}
}
