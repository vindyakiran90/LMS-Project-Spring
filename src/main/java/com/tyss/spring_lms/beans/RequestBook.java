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
@Table(name="requestBook")
@SequenceGenerator(name="generator5", initialValue = 1, allocationSize = 200)
public class RequestBook implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7852690686684404531L;
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "generator5")
	private int requestId;
	@Column
	private int bookId;
	@Column
	private int userId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String bookTitle;

	@Override
	public String toString() {
		return String.format("%-10s %-10s %-10s %-15s %-15s %-10s", requestId, userId, bookId, firstName, 
				lastName, bookTitle);
	}
}
