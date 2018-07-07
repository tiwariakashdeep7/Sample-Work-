package com.payroll.services;
import java.util.List;

import com.payroll.beans.Associate;
import com.payroll.exceptions.AssociateDetailsNotFoundException;
import com.payroll.exceptions.PayrollServicesDownException;
public interface PayrollServices {
	public int acceptAssociateDetails(Associate associate)
					throws PayrollServicesDownException;
	
	public int calculateNetSalary(int associateId) 
			throws AssociateDetailsNotFoundException,PayrollServicesDownException;
	
	public Associate getAssociateDetails(int associateId)
			throws AssociateDetailsNotFoundException,PayrollServicesDownException;
	
	public List<Associate> getAllAssociatesDetails()
			throws PayrollServicesDownException;
	
	public boolean authenticateAssociate(Associate associate) throws AssociateDetailsNotFoundException,PayrollServicesDownException;
}