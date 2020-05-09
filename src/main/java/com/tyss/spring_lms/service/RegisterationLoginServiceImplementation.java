package com.tyss.spring_lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.dao.RegisterationLoginDAO;

@Service
public class RegisterationLoginServiceImplementation implements RegisterationLoginService {

	@Autowired
	RegisterationLoginDAO dao;
	@Override
	public UserBean login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public boolean register(UserBean bean) {
		return dao.register(bean);
	}

}
