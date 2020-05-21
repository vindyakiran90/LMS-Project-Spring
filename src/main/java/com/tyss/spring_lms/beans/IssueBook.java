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
@Table(name="issueBook")
@SequenceGenerator(name="generator4", initialValue = 1, allocationSize = 200)
public class IssueBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8039576523218094099L;

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "generator4")
	private int issueId;
	@Column
	private int bookId;
	@Column
	private int userId;
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	@Override
	public String toString() {
		return String.format("%-6s %-5s %-10s %-15s", userId, bookId, issueDate, returnDate);
	}
}
