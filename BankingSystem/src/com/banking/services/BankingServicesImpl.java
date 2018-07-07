package com.banking.services;

import java.sql.SQLException;
import java.util.List;
//import java.util.Random;





import com.banking.ServicesProvider.ServicesProvider;
import com.banking.beans.Account;
import com.banking.beans.Address;
import com.banking.beans.Customer;
import com.banking.beans.Transaction;
import com.banking.daoservices.BankingDAOServices;
import com.banking.exceptions.AccountBlockedException;
import com.banking.exceptions.AccountNotFoundException;
import com.banking.exceptions.BankingServicesDownException;
import com.banking.exceptions.CustomerNotFoundException;
import com.banking.exceptions.InsufficientAmountException;
import com.banking.exceptions.InvalidAccountTypeException;
import com.banking.exceptions.InvalidAmountException;
import com.banking.exceptions.InvalidPinNumberException;

public class BankingServicesImpl implements BankingServices {
	private BankingDAOServices daoServices;

	private static int PIN_ID_COUNTER;

	public BankingServicesImpl() throws BankingServicesDownException {
		daoServices = ServicesProvider.getBankingDAOServices();
	}

	@Override
	public int acceptCustomerDetails(String firstName, String lastName, String customerEmailId, String panCard,
			String localAddressCity, String localAddressState, int localAddressPinCode, String homeAddressCity,
			String homeAddressState, int homeAddressPinCode) throws BankingServicesDownException, SQLException {
		Customer customer = new Customer(firstName, lastName, customerEmailId, panCard,
				new Address(localAddressPinCode, localAddressCity, localAddressState),
				new Address(homeAddressPinCode, homeAddressCity, homeAddressState));
		// System.out.println("Track 1");
		int customerID = daoServices.insertCustomer(customer);
		return customerID;
	}

	@Override
	public List<Account> openAccount(int customerId, String accountType, float initBalance)
			throws InvalidAmountException, CustomerNotFoundException, InvalidAccountTypeException,
			BankingServicesDownException, SQLException {
		// Account account = new Account(account, accountType, initBalance)
		this.getCustomerDetails(customerId);
		Account account = new Account();
		account.setStatus("Active");
		account.setPinNumber(PIN_ID_COUNTER);

		return daoServices.insertAccount(customerId, new Account(accountType, initBalance));

		// Customer customer = new Customer Customer(firstName, lastName,
		// emailId, panCard, localAddress, homeAddress)

	}

	@Override
	public float depositAmount(int customerId, int accountNo, float amount)
			throws CustomerNotFoundException, AccountNotFoundException, BankingServicesDownException, SQLException {
		// daoServices.insertAccount(customerId, new Account(amount));
		// this.getCustomerDetails(customerId);
		// daoServices.insertTransaction(customerId, accountNo, new
		// Transaction(amount));
		// System.out.println("Print Trace");
		// // daoServices.updateAccount(customerId, account)
		// amount = amount +
		// this.getCustomerDetails(customerId).getAccounts().get(accountNo).getAccountBalance();
		// Account account = this.getAccountDetails(customerId, accountNo);
		// daoServices.updateAccount(customerId, amount);
		//
		// daoServices.insertTransaction(customerId, accountNo, new
		// Transaction(amount, "deposit"));
		// daoServices.updateAccount(customerId, account);
		// return account.getAccountBalance();

		// return amount;

		 Account account=this.getAccountDetails(customerId, accountNo);
		
		account.setAccountBalance(account.getAccountBalance() + amount);
		System.out.println("Deposited");
		daoServices.insertTransaction(customerId, accountNo, new Transaction(amount, "deposit"));
		daoServices.updateAccount(customerId, account);
		return account.getAccountBalance();
	}

	@Override
	public float withdrawAmount(int customerId, int accountNo, float amount, int pinNumber)
			throws InsufficientAmountException, CustomerNotFoundException, AccountNotFoundException,
			InvalidPinNumberException, BankingServicesDownException, AccountBlockedException, SQLException {
		//
		if (this.getAccountDetails(customerId, accountNo) == null)
			throw new CustomerNotFoundException();
		if (this.getAccountDetails(customerId, accountNo) == null)
			throw new AccountNotFoundException();
		Account account = this.getAccountDetails(customerId, accountNo);
		if (account.getPinNumber() != pinNumber) {
			account.setPinCounter(this.getAccountDetails(customerId, accountNo).getPinCounter() + 1);
			if (account.getPinCounter() == 3) {
				account.setStatus("deactive");
			}
			throw new InvalidPinNumberException();
		}

		/*
		 * if (this.getAccountDetails(customerId, accountNo).getPinNumber() !=
		 * pinNumber) { this.getAccountDetails(customerId, accountNo)
		 * .setPinCounter(this.getAccountDetails(customerId,
		 * accountNo).getPinCounter() + 1); if
		 * (this.getAccountDetails(customerId, accountNo).getPinCounter() == 3)
		 * this.getAccountDetails(customerId, accountNo).setStatus("Deactive");
		 * throw new InvalidPinNumberException("invalid pin"); }
		 */

		// if((this.getCustomerDetails(customerId).getAccounts().get(pinNumber))==
		// pinNumber);
		this.getCustomerDetails(customerId).getAccounts().get(pinNumber);
		daoServices.insertTransaction(customerId, accountNo, new Transaction(amount));
		amount = this.getCustomerDetails(customerId).getAccounts().get(accountNo).getAccountBalance() - amount;
		return amount;

	}

	@Override
	public boolean fundTransfer(int customerIdTo, int accountNoTo, int customerIdFrom, int accountNoFrom,
			float transferAmount, int pinNumber) throws InsufficientAmountException, CustomerNotFoundException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, SQLException {
		// daoServices.getCustomer(customerIdFrom).getAccounts().get(accountNoFrom);
		// daoServices.getCustomer(customerIdTo).getAccounts().get(accountNoTo);
		// return transferAmount =
		// this.getCustomerDetails(customerIdFrom).getAccounts().get(accountNoFrom);

		/*
		 * Account accountFrom = this.getAccountDetails(customerIdTo,
		 * accountNoTo);
		 * 
		 * Account accountTo = this.getAccountDetails(customerIdFrom,
		 * accountNoFrom); if (accountFrom.getAccountBalance() < transferAmount)
		 * throw new InsufficientAmountException(); if
		 * (accountFrom.getPinNumber() != pinNumber) { if
		 * (this.getAccountDetails(customerIdFrom,
		 * accountNoFrom).getPinCounter() != pinNumber) {
		 * this.getAccountDetails(customerIdFrom, accountNoFrom)
		 * .setPinCounter(this.getAccountDetails(customerIdFrom,
		 * accountNoFrom).getPinCounter() + 1); if
		 * (this.getAccountDetails(customerIdFrom,
		 * accountNoFrom).getPinCounter() == 3)
		 * this.getAccountDetails(customerIdFrom,
		 * accountNoFrom).setStatus("Deactive"); throw new
		 * InvalidPinNumberException("invalid pin");
		 * 
		 * } System.out.println("Stack trace");
		 * this.getAccountDetails(customerIdFrom,
		 * accountNoFrom).setAccountBalance(
		 * this.getAccountDetails(customerIdFrom,
		 * accountNoFrom).getAccountBalance() - transferAmount);
		 * daoServices.insertTransaction(customerIdFrom, accountNoFrom, new
		 * Transaction(transferAmount, "withdrawl")); System.out.println(
		 * "Stack trace"); this.getAccountDetails(customerIdTo,
		 * accountNoTo).setAccountBalance( this.getAccountDetails(customerIdTo,
		 * accountNoTo).getAccountBalance() + transferAmount);
		 * daoServices.insertTransaction(customerIdTo, accountNoTo, new
		 * Transaction(transferAmount, "deposit")); } return true;
		 */

		// this.depositAmount(customerIdTo, accountNoTo, transferAmount);

		Account account1 = this.getAccountDetails(customerIdTo, accountNoTo);
		Account account2 = this.getAccountDetails(customerIdFrom, accountNoFrom);

		if (account1 == null)
			throw new AccountNotFoundException();
		if (account2 == null)
			throw new AccountNotFoundException();
		if (account2.getAccountBalance() < transferAmount)
			throw new InsufficientAmountException();
		if (account2.getPinNumber() != pinNumber) {
			account2.setPinCounter(this.getAccountDetails(customerIdFrom, accountNoFrom).getPinCounter() + 1);
			if (account2.getPinCounter() == 3) {
				account2.setStatus("deactive");
			}
			throw new InvalidPinNumberException();
		}
		account2.setAccountBalance(account2.getAccountBalance() - transferAmount);
		daoServices.insertTransaction(customerIdFrom, accountNoFrom, new Transaction(transferAmount, "withdraw"));
		daoServices.updateAccount(customerIdFrom, account2);
		account1.setAccountBalance(account1.getAccountBalance() + transferAmount);
		daoServices.insertTransaction(customerIdTo, accountNoTo, new Transaction(transferAmount, "deposit"));
		daoServices.updateAccount(customerIdTo, account1);
		return true;

	}

	@Override
	public Customer getCustomerDetails(int customerId)
			throws CustomerNotFoundException, BankingServicesDownException, SQLException {
		
		Customer customer=daoServices.getCustomer(customerId);
		if(customer==null)
			throw new CustomerNotFoundException("customer not found");
		return customer;
	}

	@Override
	public Account getAccountDetails(int customerId, int accountNo)
			throws CustomerNotFoundException, AccountNotFoundException, BankingServicesDownException, SQLException {
		this.getCustomerDetails(customerId);
		Account account=daoServices.getAccount(customerId, accountNo);
		if (account== null)
			throw new AccountNotFoundException();

		return account;
	}

	@Override
	public int generateNewPin(int customerId, int accountNo)
			throws CustomerNotFoundException, AccountNotFoundException, BankingServicesDownException, SQLException {
		// daoServices.generatePin(customerId, accountNo);
		return daoServices.getCustomer(customerId).getAccounts().get(accountNo).getPinNumber();

		/*
		 * daoServices.getCustomer(customerId).getAccounts().get(accountNo);
		 * Random random = new Random(); for (int i = 0; i < 100; i++) { long
		 * fraction = (long) (1000 * random.nextDouble()); int PIN = (int)
		 * (fraction + 1000); System.out.println(PIN);
		 * 
		 * }
		 */
		// return 0;
	}

	@Override
	public boolean changeAccountPin(int customerId, int accountNo, int oldPinNumber, int newPinNumber)
			throws CustomerNotFoundException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, SQLException {
		if (getAccountDetails(customerId, accountNo).getPinNumber() != oldPinNumber)
			throw new InvalidPinNumberException();
		this.getAccountDetails(customerId, accountNo).setPinNumber(newPinNumber);
		daoServices.updateAccount(customerId, this.getAccountDetails(customerId, accountNo));

		return true;
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BankingServicesDownException, SQLException {
		return daoServices.getCustomers();
	}

	@Override
	public List<Account> getcustomerAllAccountDetails(int customerId)
			throws BankingServicesDownException, CustomerNotFoundException, SQLException {
		return daoServices.getAccounts(customerId);
	}

	@Override
	public List<Transaction> getAccountAllTransaction(int customerId, int accountNo)
			throws BankingServicesDownException, CustomerNotFoundException, AccountNotFoundException, SQLException {
		return (List<Transaction>) daoServices.getTransaction(accountNo);

	}

	@Override
	public String accountStatus(int customerId, int accountNo)
			throws BankingServicesDownException, CustomerNotFoundException, AccountNotFoundException, SQLException {
		return daoServices.getAccount(customerId, accountNo).getStatus();
	}

	@Override
	public void close() throws BankingServicesDownException {

	}

}
