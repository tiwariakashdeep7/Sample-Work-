package com.banking.daoservices;
import java.sql.SQLException;
import java.util.List;

import com.banking.beans.Account;
import com.banking.beans.Customer;
import com.banking.beans.Transaction;
public interface BankingDAOServices {
	int insertCustomer(Customer customer) throws SQLException;
	List<Account> insertAccount(int customerId,Account account) throws SQLException;
	boolean updateAccount(int customerId,Account account) throws SQLException;
	int generatePin(int customerId,Account account) throws SQLException;
	boolean insertTransaction(int customerId,int accountNo,Transaction transaction) throws SQLException;
	boolean deleteCustomer(int customerId) throws SQLException;
	boolean deleteAccount(int customerId,int accountNo) throws SQLException;
	Customer getCustomer(int customerId) throws SQLException;
	Account getAccount(int customerId,int accountNo) throws SQLException;
	 List<Customer> getCustomers() throws SQLException;
	 List<Account> getAccounts(int customerId) throws SQLException;
	 Transaction getTransaction(int accountNo) throws SQLException;
	 void updateAccount(int customerId, float amount) throws SQLException;
	void close1();
	
}
