package com.payroll.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payroll.beans.Associate;
@Controller
public class AllUriController {

	@ModelAttribute("associate")
	public Associate getAssociate(){
		return new Associate();
	}
	
	@RequestMapping("/")
	public String getIndexPage(){
		return "indexPage";
	}
	
	@RequestMapping("/loginPage")
	public String  getLoginPage() {
		return "loginPage";
	}
	
	@RequestMapping("/registrationPage")
	public String  getRegistrationPage() {
		return "registrationPage";
	}

	@RequestMapping("/payrollPage")
	public String getPayrollPage(){
		return "payrollPage";
	}
	
	@RequestMapping("/welcomePage")
	public String getWelcomePage(){
		return "welcomePage";
	}
	
	@RequestMapping("/globalErrorPage")
	public String getGlobalErrorPage(){
		return "globalErrorPage";
	}

}
