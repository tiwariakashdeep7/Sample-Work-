package com.banking.ServicesProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.banking.daoservices.BankingDAOServices;
//import com.banking.daoservices.BankingDAOServicesImpl;
import com.banking.exceptions.BankingServicesDownException;
import com.banking.services.BankingServices;

public class ServicesProvider {

	private static Properties bankingProperties;

	static {
		try {
			bankingProperties = new Properties();
			FileInputStream src = new FileInputStream(new File(".\\resource\\bankingsystem.properties"));
			bankingProperties.load(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BankingServices getBankingServices() throws BankingServicesDownException {

		String bankingServices = bankingProperties.getProperty("bankingServices");
		Class c;
		try {
			c = Class.forName(bankingServices);
			return (BankingServices) c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BankingServicesDownException("Banking Services are down plz try later", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new BankingServicesDownException("Banking Services are down plz try later", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new BankingServicesDownException("Banking Services are down plz try later", e);
		}

	}

	public static BankingDAOServices getBankingDAOServices() throws BankingServicesDownException {
		try {
			String bankingServices = bankingProperties.getProperty("bankingDAOServices");
			Class c = Class.forName(bankingServices);
			return (BankingDAOServices) c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BankingServicesDownException("Banking Services are down plz try later", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new BankingServicesDownException("Banking Services are down plz try later", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new BankingServicesDownException("Banking Services are down plz try later", e);
		}

	}

	public static Connection getDBConnection() throws ClassNotFoundException {

		Class.forName(bankingProperties.getProperty("driver"));
		try {
			return DriverManager.getConnection(bankingProperties.getProperty("url"),
					bankingProperties.getProperty("userName"), bankingProperties.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClassNotFoundException("Banking Services Down");
		}

	}

}
