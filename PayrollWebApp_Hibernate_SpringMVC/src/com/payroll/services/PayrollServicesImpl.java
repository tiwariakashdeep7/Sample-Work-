package com.payroll.services;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.beans.Associate;
import com.payroll.daoservices.PayrollDAOServices;
import com.payroll.exceptions.AssociateDetailsNotFoundException;
import com.payroll.exceptions.PayrollServicesDownException;

@Service(value="payrollServices")
public class PayrollServicesImpl implements PayrollServices{
	
	@Autowired(required=true)
	private PayrollDAOServices daoServices ;
	
	@Override
	public int acceptAssociateDetails(Associate associate) throws PayrollServicesDownException{
		try {
			return daoServices.insertAssociate(associate);	
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services down plz try later", e);
		}
	}

	@Override
	public int calculateNetSalary(int associateId) throws PayrollServicesDownException,AssociateDetailsNotFoundException{
		try {
			Associate associate = this.getAssociateDetails(associateId);
			//calculate monthly Net sal;
			daoServices.updateAssociate(associate);
			return associate.getSalary().getNetSalary();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services down plz try later", e);
		}
	}
	@Override
	public Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		try {
			Associate associate = daoServices.getAssociate(associateId);
			if(associate ==null)throw new AssociateDetailsNotFoundException("Associate details for Id "+associateId+" not found");
			return associate;
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services down plz try later", e);
		}
	}
	@Override
	public List<Associate> getAllAssociatesDetails()throws PayrollServicesDownException {
		try {
			return daoServices.getAssociates();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services down plz try later", e);
		}
	}
	@Override
	public boolean authenticateAssociate(Associate associate)
			throws AssociateDetailsNotFoundException,
		PayrollServicesDownException {
			Associate associate2= this.getAssociateDetails(associate.getAssociateId());
		
		if(associate.getPassword().equals(associate2.getPassword()))return true;
		return false;
			
	}
}
