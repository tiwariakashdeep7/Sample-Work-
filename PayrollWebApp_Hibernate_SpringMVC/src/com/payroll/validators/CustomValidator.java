package com.payroll.validators;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.payroll.beans.Associate;
@Component
public class CustomValidator implements Validator {
	public void validate(Object obj, Errors errors) {
		/*UserBean user = (UserBean)obj;
		if(!user.getCity().equalsIgnoreCase("Pune")){
			errors.rejectValue("city", "userBean.city");
		}*/
	}
	@Override
	public boolean supports(Class<?> refClass) {
		return Associate.class.equals(refClass);
	}
	
}