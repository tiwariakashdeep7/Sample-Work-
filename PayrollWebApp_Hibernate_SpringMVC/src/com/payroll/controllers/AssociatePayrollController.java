package com.payroll.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.beans.Associate;
import com.payroll.exceptions.AssociateDetailsNotFoundException;
import com.payroll.exceptions.PayrollServicesDownException;
import com.payroll.services.PayrollServices;
@Controller
public class AssociatePayrollController {
	
	@Autowired(required =true)
	PayrollServices payrollServices;
		
	@RequestMapping(value="/authenticateAssociate",method=RequestMethod.POST)
	public ModelAndView authenticateAssociate(@Valid @ModelAttribute Associate associate){
		try {
			if(payrollServices.authenticateAssociate(associate)){
				associate =payrollServices.getAssociateDetails(associate.getAssociateId());
				return new ModelAndView("payrollPage", "associate", associate);
			}
			else{
				return new ModelAndView("loginPage", "errorMessage","Password is wrong");
			}
		} catch (AssociateDetailsNotFoundException e) {
			return new ModelAndView("loginPage","errorMessage","Associate details not found");
		} catch (PayrollServicesDownException e) {
			return new ModelAndView("globalErrorPage");
		}
	}
	@RequestMapping(value="/acceptAssociateDetails" ,method=RequestMethod.POST)
	public ModelAndView acceptAssociateDetails(@ModelAttribute Associate associate){
		try {
			associate.setAssociateId(payrollServices.acceptAssociateDetails(associate));
			return new ModelAndView("welcomePage","associate",associate);
		} catch (PayrollServicesDownException e) {
			return new ModelAndView("globalErrorPage");
		}
	}

}
