package com.tyss.spring_lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.IssueBook;
import com.tyss.spring_lms.beans.RequestBook;
import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.dao.AdminDAO;

@Service
public class AdminServiceImplementation implements AdminService {

	@Autowired
	AdminDAO dao;

	@Override
	public boolean addBook(BookBean bean) {
		return dao.addBook(bean);
	}

	@Override
	public boolean deleteBook(int bookId) {
		return dao.deleteBook(bookId);
	}

	@Override
	public boolean issueBook(IssueBook bean) {
		return dao.issueBook(bean);
	}

	@Override
	public boolean updateBook(String bookTitle, int numberOfBooks) {
		return dao.updateBook(bookTitle, numberOfBooks);
	}

	@Override
	public List<RequestBook> showRequest() {
		return dao.showRequest();
	}

	@Override
	public List<UserBean> showUsers() {
		return dao.showUsers();
	}

	@Override
	public boolean bookReturn(int userId, int bookId) {
		return dao.bookReturn(userId, bookId);
	}
}
