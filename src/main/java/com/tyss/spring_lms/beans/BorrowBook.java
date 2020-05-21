package com.tyss.spring_lms.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name="borrowBook")
@SequenceGenerator(name="generator3", initialValue = 1, allocationSize = 200)
public class BorrowBook implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5905964161779021200L;
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "generator3")
	private int borrowId;
	@Column
	private int bookId;
	@Column
	private int userId;
	@Column
	private int noOfBooksBorrowed;
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBorrowed;
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfReturn;
	@Column
	private double fees;
	
	@Override
	public String toString() {
		return String.format("%-10s %-10s %-10s %-20s %-15s %-15s %-5s", userId, bookId, noOfBooksBorrowed, 
				dateOfBorrowed, dateOfReturn, fees);
	}
}
