package com.tyss.spring_lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.BorrowBook;
import com.tyss.spring_lms.beans.LMSResponse;
import com.tyss.spring_lms.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(path="/userBookborrowedList", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse borrowedBook(int userId) {
		List<BorrowBook> borrowBook= studentService.borrowedBook(userId);
		LMSResponse lmsResponse = new LMSResponse();
		if(!borrowBook.isEmpty()) {
			lmsResponse.setMessage("Book borrowed successfully");
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not borrowed");
		}
		return lmsResponse;
	}

	@GetMapping(path="/requestBook", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse requestBook(int userId, int bookId) {
		boolean isRequested = studentService.bookRequest(userId, bookId);
		LMSResponse lmsResponse = new LMSResponse();
		if(isRequested) {
			lmsResponse.setMessage("Book requested successfully");
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not requested");
		}
		return lmsResponse;
	}
	
	@PostMapping(path="/bookBorrow", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse bookBorrow(BorrowBook book) {
		BorrowBook borrowBook = studentService.bookBorrow(book);
		LMSResponse lmsResponse = new LMSResponse();
		if(borrowBook.getBookId() != 0) {
			lmsResponse.setMessage("Book borrowed successfully");
			lmsResponse.setBorrowBook(borrowBook);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not borrowed");
		}
		return lmsResponse;
	}
	
	@GetMapping(path="/searchBookById", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse searchBookById(int bookId) {
		BookBean bookBean = studentService.searchBookById(bookId);
		LMSResponse lmsResponse = new LMSResponse();
		if(bookBean != null) {
			lmsResponse.setMessage("Book is available");
			lmsResponse.setBookBean(bookBean);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not available");
		}
		return lmsResponse;
	}
	
	@GetMapping(path="/searchBookByTitle", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse searchBookByTitle(String bookTitle) {
		BookBean bookBean = studentService.searchBookByTitle(bookTitle);
		LMSResponse lmsResponse = new LMSResponse();
		if(bookBean != null) {
			lmsResponse.setMessage("Book is available");
			lmsResponse.setBookBean(bookBean);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not available");
		}
		return lmsResponse;
	}
	
	@GetMapping(path="/searchBookByAuthor", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse searchBookByAuthor(String author) {
		BookBean bookBean = studentService.searchBookByAuthor(author);
		LMSResponse lmsResponse = new LMSResponse();
		if(bookBean != null) {
			lmsResponse.setMessage("Book is available");
			lmsResponse.setBookBean(bookBean);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not available");
		}
		return lmsResponse;
	}
	
	@GetMapping(path="/bookList", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse bookList() {
		List<BookBean> bookBean = studentService.getBooksInfo();
		LMSResponse lmsResponse = new LMSResponse();
		if(!bookBean.isEmpty()) {
			lmsResponse.setMessage("Books are available");
			lmsResponse.setBookList(bookBean);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Books are not available");
		}
		return lmsResponse;
	}
}
