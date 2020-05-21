package com.tyss.spring_lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.IssueBook;
import com.tyss.spring_lms.beans.LMSResponse;
import com.tyss.spring_lms.beans.RequestBook;
import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.service.AdminService;

@CrossOrigin(origins= "*", allowedHeaders="*")
@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping(path="/addBook", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse addBook(@RequestBody BookBean bean) {
		boolean isAdded = adminService.addBook(bean);
		LMSResponse lmsResponse = new LMSResponse();
		if(isAdded) {
			lmsResponse.setMessage("Book added successfully");
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not added");
		}
		return lmsResponse;
	}

	@DeleteMapping(path="/deleteBook/{bookId}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse deleteBook(@PathVariable(name="bookId") int bookId) {
		boolean isDeleted = adminService.deleteBook(bookId);
		LMSResponse lmsResponse = new LMSResponse();
		if(isDeleted) {
			lmsResponse.setMessage("Book deleted successfully");
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book deletion is unsuccessful");
		}
		return lmsResponse;
	}

	@PutMapping(path="/updateBook", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public LMSResponse updateBook(@RequestBody BookBean bean) {
		boolean isUpdated = adminService.updateBook(bean.getBookTitle(), bean.getNumberOfBooks());
		LMSResponse lmsResponse = new LMSResponse();
		if(isUpdated) {
			lmsResponse.setMessage("Book updated successfully");
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book updation is unsuccessful");
		}
		return lmsResponse;
	}

	@PostMapping(path="/issueBook", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse issueBook(@RequestBody RequestBook requestBook) {
		boolean isIssued = adminService.issueBook(requestBook.getUserId(), requestBook.getBookId());
		LMSResponse lmsResponse = new LMSResponse();
		if(isIssued) {
			lmsResponse.setMessage("Book issued successfully");
		} else {                                      
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book issuing is unsuccessful");
		}
		return lmsResponse;
	}

	@GetMapping(path="/showRequest", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse showRequest() {
		List<RequestBook> requestBookList =  adminService.showRequest();
		LMSResponse lmsResponse = new LMSResponse();
		if(!requestBookList.isEmpty()) {
			lmsResponse.setMessage("Books are requested");
			lmsResponse.setRequestBookList(requestBookList);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Books are not requested");
		}
		return lmsResponse;
	}

	@GetMapping(path="/showStudentUsers", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse showStudentUsers() {
		List<UserBean> userList =  adminService.showUsers();
		LMSResponse lmsResponse = new LMSResponse();
		if(!userList.isEmpty()) {
			lmsResponse.setMessage("Users are available");
			lmsResponse.setUserList(userList);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Users are not available");
		}
		return lmsResponse;
	}

	@GetMapping(path="/showIssuedBooks", produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse showIssuedBooks() {
		List<IssueBook> issueBookList =  adminService.issuedBooks();
		LMSResponse lmsResponse = new LMSResponse();
		if(!issueBookList.isEmpty()) {
			lmsResponse.setMessage("Books are issued");
			lmsResponse.setIssueBookList(issueBookList);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Books are not issued");
		}
		return lmsResponse;
	}
	
	@PostMapping(path="/isBookReceived", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse isReceived(int userId, int bookId) {
		boolean isBookReceived = adminService.isBookReceived(userId, bookId);
		LMSResponse lmsResponse = new LMSResponse();
		if(isBookReceived) {
			lmsResponse.setMessage("Book received successfully");
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Book is not received");
		}
		return lmsResponse;
	}
}
