package com.tyss.spring_lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.BorrowBook;
import com.tyss.spring_lms.dao.StudentDAO;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentDAO dao;

	@Override
	public boolean bookRequest(int userId, int bookId) {
		return dao.bookRequest(userId, bookId);
	}

	@Override
	public List<BorrowBook> borrowedBook(int userId) {
		return dao.borrowedBook(userId);
	}

	@Override
	public BorrowBook bookBorrow(BorrowBook books) {
		return dao.bookBorrow(books);
	}

	@Override
	public BookBean searchBookById(int bookId) {
		return dao.searchBookById(bookId);
	}

	@Override
	public BookBean searchBookByTitle(String bookTitle) {
		return dao.searchBookByTitle(bookTitle);
	}

	@Override
	public BookBean searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}
}
