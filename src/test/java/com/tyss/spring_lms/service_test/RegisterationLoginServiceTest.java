package com.tyss.spring_lms.service_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.service.RegisterationLoginService;

public class RegisterationLoginServiceTest {
	
	@Autowired
	private RegisterationLoginService registerationLoginService;
	
	@Test
	public void loginTest() {
		String email = "kushi@gmail.com";
		String password = "Lavana456@";
		UserBean userBean = registerationLoginService.login(email, password);
		Assertions.assertNotNull(userBean);
	}
	
	@Test
	public void registerTest() {
		UserBean userBean = new UserBean();
		userBean.setUserId(10003);
		userBean.setFirstName("Manu");
		userBean.setLastName("Manju");
		userBean.setRole("Student");
		userBean.setPhoneNo(9362149674L);
		userBean.setEmail("manu@gmail.com");
		userBean.setPassword("Kumar#123");
		boolean status =registerationLoginService.register(userBean);
		Assertions.assertTrue(status);
	}


	
}
