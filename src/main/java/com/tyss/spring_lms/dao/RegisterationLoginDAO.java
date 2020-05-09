package com.tyss.spring_lms.dao;

import com.tyss.spring_lms.beans.UserBean;

public interface RegisterationLoginDAO {
	UserBean login(String email, String password);
	boolean register(UserBean bean);
}
