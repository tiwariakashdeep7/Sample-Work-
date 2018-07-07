package com.banking.daoservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.banking.ServicesProvider.ServicesProvider;
import com.banking.beans.Account;
import com.banking.beans.Address;
import com.banking.beans.Customer;
import com.banking.beans.Transaction;
//import com.banking.exceptions.*;

public class BankingDAOServicesImpl implements BankingDAOServices {
	HashMap<Integer, Customer> customers = new HashMap<>();
	// private static int CUSTOMER_ID_COUNTER = 112;
	// private static int CUSTOMER_IDX=0;
	// private static int ACCOUNT_NO_COUNTER = 202610;
	// private static int TRANSACTION_ID_COUNTER = 1324;
	// private static int PIN_ID_COUNTER = 1111;
	// private static int PIN_NUMBER = 1010;
	public Connection conn;

	public BankingDAOServicesImpl() throws ClassNotFoundException {
		conn = ServicesProvider.getDBConnection();
	}

	@Override
	public int insertCustomer(Customer customer) throws SQLException {
		try {
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into Customer (firstName,lastName,emailId,panCard) values(?,?,?,?)");
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmailId());
			pstmt.setString(4, customer.getPanCard());
			pstmt.executeUpdate();
			ResultSet rs = conn.prepareStatement("select max(customerId)  from Customer").executeQuery();
			rs.next();
			int customerId = rs.getInt(1);
			System.out.println(customerId);

			pstmt = conn.prepareStatement(
					"insert into address (localAddressState,localAddressCity,localAddressPinCode,homeAddressCity,homeAddressState,homeAddressPinCode,customerId) values(?,?,?,?,?,?,?)");
			pstmt.setString(1, customer.getLocalAddress().getState());
			pstmt.setString(2, customer.getLocalAddress().getCity());
			pstmt.setInt(3, customer.getLocalAddress().getPincode());
			pstmt.setString(4, customer.getHomeAddress().getCity());
			pstmt.setString(5, customer.getHomeAddress().getState());
			pstmt.setInt(6, customer.getHomeAddress().getPincode());
			pstmt.setInt(7, customerId);

			pstmt.executeUpdate();
			conn.commit();

			return customerId;
		} catch (SQLException e) {

			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}

	}

	@Override
	public List<Account> insertAccount(int customerId, Account account) throws SQLException {
		conn.setAutoCommit(false);

		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into Account (accountType,accountBalance,customerId,PinNumber) values(?,?,?,?)");
			pstmt.setString(1, account.getAccountType());
			pstmt.setFloat(2, account.getAccountBalance());
			pstmt.setInt(3, customerId);
			int pin = 1000 + (int) (Math.random() * (9999 - 1000));
			pstmt.setInt(4, pin);
			pstmt.executeUpdate();
			conn.commit();
			ResultSet rs = conn.prepareStatement("select max(accountNo)  from Account").executeQuery();
			rs.next();
			int accountNo = rs.getInt(1);
			System.out.println(accountNo);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		}

		return null;
	}

	@Override
	public boolean updateAccount(int customerId, Account account) throws SQLException {
		// conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("update account set accountBalance="
				+ account.getAccountBalance() + ",pinNumber=" + account.getPinNumber() + ",pinCounter="
				+ account.getPinCounter() + ",status=\"" + account.getStatus() + "\" where customerId=" + customerId);
		pstmt.executeUpdate();
		// pstmt.setInt(1,account.getAccountNo());
		// pstmt.setFloat(2,account.getAccountBalance());
		// pstmt.setString(3, account.);

		return true;
	}

	@Override
	public int generatePin(int customerId, Account account) throws SQLException {

		conn.setAutoCommit(false);
		int pin = 1000 + (int) (Math.random() * (9999 - 1000));
		PreparedStatement pstmt = conn
				.prepareStatement("insert into Account (accountType,accountBalance,customerId) values(?,?,?)");
		ResultSet rst = conn.prepareStatement("select pinNumber from account where customerId=" + customerId)
				.executeQuery();
		rst.next();
		return rst.getInt(1);
	}

	@Override
	public boolean insertTransaction(int customerId, int accountNo, Transaction transaction) throws SQLException {
		try {
			System.out.println("Transaction");
			conn.setAutoCommit(true);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into transaction (amount,transactionType,accountNo) values(?,?,?)");
			pstmt.setFloat(1, transaction.getAmount());
			pstmt.setString(2, transaction.getTransactionType());
			pstmt.setInt(3, accountNo);
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("");
		}
	}

	@Override
	public boolean deleteCustomer(int customerId) {

		return false;
	}

	@Override
	public boolean deleteAccount(int customerId, int accountNo) {

		return false;
	}

	@Override
	public Customer getCustomer(int customerId) throws SQLException {
		System.out.println("Trace 1");
		try {
			ResultSet rst = conn.prepareStatement(
					"select c.customerId,firstName,lastName,emailId,panCard,localAddressPinCode,localAddressCity,localAddressState,homeAddressPinCode,homeAddressCity,homeAddressState from customer c,address a where a.customerId=c.customerId and a.customerId="
							+ customerId)
					.executeQuery();
			rst.next();
			System.out.println("customer found");
			return new Customer(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
					new Address(rst.getInt(6), rst.getString(7), rst.getString(8)),
					new Address(rst.getInt(9), rst.getString(10), rst.getString(11)));

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("");
		}

	}

	@Override
	public Account getAccount(int customerId, int accountNo) throws SQLException {
		try {
			System.out.println("trace 2");
			ResultSet rst = conn.prepareStatement(
					"select accountNo,accountType,accountBalance,pinNumber,status,pinCounter from account where customerId="
							+ customerId + " and accountNo= " + accountNo)
					.executeQuery();
			rst.next();
			System.out.println("trace account found");
			return new Account(rst.getInt("accountNo"), rst.getString("accountType"), rst.getFloat("accountBalance"),
					rst.getInt("pinNumber"), rst.getString("status"), rst.getInt("pinCounter"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("", e);
		}

	}

	@Override

	public List<Customer> getCustomers() throws SQLException {
		List<Customer> customerList = new ArrayList<Customer>();
		ResultSet rst = conn
				.prepareStatement(
						"select c.customerId,firstName,lastName,emailId,panCard,localAddressPinCode,localAddressCity,localAddressState,homeAddressPinCode,homeAddressCity,homeAddressState from customer c,address a where a.customerId=c.customerId")
				.executeQuery();
		while (rst.next())
			customerList.add(new Customer(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
					rst.getString(5), new Address(rst.getInt(6), rst.getString(7), rst.getString(8)),
					new Address(rst.getInt(9), rst.getString(10), rst.getString(11))));
		return customerList;

	}

	@Override
	public List<Account> getAccounts(int customerId) throws SQLException {

		List<Account> accountList = new ArrayList<>();
		ResultSet rst = conn.prepareStatement(
				"select accountNo,accountType,accountBalance,pinNumber,pinCounter,status from account where customerId="
						+ customerId)
				.executeQuery();
		while (rst.next())
			accountList.add(
					new Account(rst.getInt("accountNo"), rst.getString("accountType"), rst.getFloat("accountBalance"),
							rst.getInt("pinNumber"), rst.getString("status"), rst.getInt("pinCounter")));
		return accountList;

	}

	@Override
	public Transaction getTransaction(int accountNo) throws SQLException {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		ResultSet rst = conn
				.prepareStatement(
						"select amount,transactionType,transactionId from transaction where accountNo=" + accountNo)
				.executeQuery();
		while (rst.next()) {
			transactionList.add(new Transaction(rst.getInt(3), rst.getFloat(1), rst.getString(2)));
		}
		return getTransaction(accountNo);

	}

	@Override
	public void updateAccount(int customerId, float amount) {

	}

	@Override
	public void close1() {

	}

}
