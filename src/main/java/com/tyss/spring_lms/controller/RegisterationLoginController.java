package com.tyss.spring_lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.spring_lms.beans.LMSResponse;
import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.service.RegisterationLoginService;

@CrossOrigin(origins= "*", allowedHeaders="*")
@RestController
public class RegisterationLoginController {

	@Autowired
	private RegisterationLoginService registerationLoginService;
	
	@PostMapping(path="/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse addUser(@RequestBody UserBean bean) {
		boolean isAdded = registerationLoginService.register(bean);
		LMSResponse lmsResponse = new LMSResponse();
		if(isAdded) {
			lmsResponse.setMessage("User added successfully");
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("User is not added");
		}
		return lmsResponse;
	}
	
	@PostMapping(path="/login", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public LMSResponse login(@RequestBody UserBean userBean) {
		UserBean bean = registerationLoginService.login(userBean.getEmail(), userBean.getPassword());
		LMSResponse lmsResponse = new LMSResponse();
		if(bean != null) {
			lmsResponse.setMessage("Login Successful");
			lmsResponse.setUserBean(bean);
		} else {
			lmsResponse.setError(true);
			lmsResponse.setMessage("Invalid user");
		}
		return lmsResponse;
	}
	

}
