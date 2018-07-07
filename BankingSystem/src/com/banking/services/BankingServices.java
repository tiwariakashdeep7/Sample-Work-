package com.banking.services;
import java.sql.SQLException;
import java.util.List;

import com.banking.beans.Account;
import com.banking.beans.Customer;
import com.banking.beans.Transaction;
import com.banking.exceptions.AccountBlockedException;
import com.banking.exceptions.AccountNotFoundException;
import com.banking.exceptions.BankingServicesDownException;
import com.banking.exceptions.CustomerNotFoundException;
import com.banking.exceptions.InsufficientAmountException;
import com.banking.exceptions.InvalidAccountTypeException;
import com.banking.exceptions.InvalidAmountException;
import com.banking.exceptions.InvalidPinNumberException;
public interface BankingServices {

	List<Account> openAccount(int customerId,String accountType,float initBalance)
			throws InvalidAmountException,CustomerNotFoundException,
			InvalidAccountTypeException,
			BankingServicesDownException, SQLException;


	float depositAmount(int customerId,int accountNo,float amount)
			throws CustomerNotFoundException,
			AccountNotFoundException,BankingServicesDownException, SQLException;

	float withdrawAmount(int customerId,int accountNo,float amount,int pinNumber)
			throws InsufficientAmountException,CustomerNotFoundException,
			AccountNotFoundException,InvalidPinNumberException,
			BankingServicesDownException ,AccountBlockedException, SQLException;

	boolean fundTransfer(int customerIdTo,int accountNoTo,int customerIdFrom,int accountNoFrom,float transferAmount,int pinNumber)
			throws InsufficientAmountException,
			CustomerNotFoundException,
			AccountNotFoundException,InvalidPinNumberException,
			BankingServicesDownException, SQLException  ;



	Customer getCustomerDetails(int customerId)
			throws CustomerNotFoundException,BankingServicesDownException, SQLException;

	Account getAccountDetails(int customerId,int accountNo)
			throws 
			CustomerNotFoundException,AccountNotFoundException,BankingServicesDownException, SQLException;

	public int generateNewPin(int customerId,int accountNo)
			throws
			CustomerNotFoundException,AccountNotFoundException ,
			BankingServicesDownException, SQLException;


	public boolean changeAccountPin(int customerId,int accountNo,int oldPinNumber,int newPinNumber)

			throws CustomerNotFoundException,
			AccountNotFoundException,
			InvalidPinNumberException,BankingServicesDownException, SQLException  ;

	List<Customer> getAllCustomerDetails()throws BankingServicesDownException, SQLException;

	List<Account> getcustomerAllAccountDetails(int customerId)
			throws BankingServicesDownException,CustomerNotFoundException, SQLException;

	List<Transaction> getAccountAllTransaction(int customerId,int accountNo)
			throws BankingServicesDownException,
			CustomerNotFoundException,
			AccountNotFoundException, SQLException;

	public String accountStatus(int customerId,int accountNo)
			throws BankingServicesDownException,
			CustomerNotFoundException,
			AccountNotFoundException, SQLException;


	void close()throws BankingServicesDownException;



	int acceptCustomerDetails(String firstName, String lastName,
			String customerEmailId, String panCard, String localAddressCity,
			String localAddressState, int localAddressPinCode,
			String homeAddressCity, String homeAddressState,
			int homeAddressPinCode) throws BankingServicesDownException, SQLException;


	
}