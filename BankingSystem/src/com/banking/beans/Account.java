package com.banking.beans;

import java.util.HashMap;

public class Account {
	private int accountNo;
	private String accountType;
	private float accountBalance;

	HashMap<Integer, Account> accounts = new HashMap<>();
	

	public Account(int accountNo, String accountType, float accountBalance, int pinNumber) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.pinNumber = pinNumber;
	}

	public float getInitBalance() {
		return initBalance;
	}

	public void setInitBalance(float initBalance) {
		this.initBalance = initBalance;
	}

	public Account(float initBalance) {
		super();
		this.initBalance = initBalance;
	}

	private int pinNumber;
	private String status;
	private int pinCounter;
	private float initBalance;
	private HashMap<Integer, Transaction> transactions = new HashMap<>();

	public Account() {
	}

	public Account(int accountNo, String accountType, float accountBalance, int pinNumber, String status,
			int pinCounter) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.pinNumber = pinNumber;
		this.status = status;
		this.pinCounter = pinCounter;
		// this.transactions = transactions;
	}

	public Account(String accountType, float accountBalance) {
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPinCounter() {
		return pinCounter;
	}

	public void setPinCounter(int pinCounter) {
		this.pinCounter = pinCounter;
	}

	public HashMap<Integer, Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(HashMap<Integer, Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(accountBalance);
		result = prime * result + accountNo;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + pinCounter;
		result = prime * result + pinNumber;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Float.floatToIntBits(accountBalance) != Float.floatToIntBits(other.accountBalance))
			return false;
		if (accountNo != other.accountNo)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (pinCounter != other.pinCounter)
			return false;
		if (pinNumber != other.pinNumber)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountType=" + accountType + ", accountBalance=" + accountBalance
				+ ", pinNumber=" + pinNumber + ", status=" + status + ", pinCounter=" + pinCounter + ", transactions="
				+ transactions + "]";
	}

	public void setTransactions(int tRANSACTION_ID_COUNTER, Transaction transaction) {
		// TODO Auto-generated method stub

	}

}
