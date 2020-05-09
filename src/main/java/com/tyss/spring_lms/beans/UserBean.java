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
@Table(name="userBean")
@SequenceGenerator(name="generator1", initialValue = 10000, allocationSize = 1000)
public class UserBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4870333319024922458L;
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "generator1")
	private int userId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String role;
	@Column
	private long phoneNo;
	@Column
	private String email;
	@Column
	private String password;
	
	@Override
	public String toString() {
		return String.format("%-10s %-15s %-15s %-10s %-12s %-20s %-10s", userId, firstName, 
				lastName, role, phoneNo, email, password);
	}
}