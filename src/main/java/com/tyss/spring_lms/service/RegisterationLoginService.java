package com.tyss.spring_lms.service;

import com.tyss.spring_lms.beans.UserBean;

public interface RegisterationLoginService {
	UserBean login(String email, String password);
	boolean register(UserBean bean);
}
