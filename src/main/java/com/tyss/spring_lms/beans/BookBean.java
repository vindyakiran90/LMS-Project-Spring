package com.tyss.spring_lms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="bookBean")
@SequenceGenerator(name="generator2", initialValue = 1000, allocationSize = 500)
public class BookBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2364086599925512608L;
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "generator2")
	private int bookId;
	@Column
	private String bookTitle;
	@Column
	private String category;
	@Column
	private String author;
	@Column
	private String publisherName;
	@Column
	private int numberOfBooks;
	@Column
	private int numberOfAvailableBooks;
	@Column
	private int numberOfIssuedBooks;
	
	
	@Override
	public String toString() {
		return String.format("%-10s %-15s %-15s %-15s %-15s %-20s %-20s %-20s", bookId, bookTitle, 
				category, author, publisherName, numberOfBooks, numberOfAvailableBooks, numberOfIssuedBooks);
	}
}
