package com.tyss.spring_lms.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LMSResponse {
	private boolean error;
	private String message;
	private BookBean bookBean;
	private List<BookBean> bookList;
	private BorrowBook borrowBook;
	private List<RequestBook> requestBook;
	private List<UserBean> userBean;
	private List<IssueBook> issueBook;
}
