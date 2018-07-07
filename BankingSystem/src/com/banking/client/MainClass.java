package com.banking.client;

import java.sql.SQLException;
import java.util.Scanner;

import com.banking.ServicesProvider.ServicesProvider;
import com.banking.exceptions.AccountBlockedException;
import com.banking.exceptions.AccountNotFoundException;
import com.banking.exceptions.BankingServicesDownException;
import com.banking.exceptions.CustomerNotFoundException;
import com.banking.exceptions.InsufficientAmountException;
import com.banking.exceptions.InvalidAccountTypeException;
import com.banking.exceptions.InvalidAmountException;
import com.banking.exceptions.InvalidPinNumberException;
import com.banking.services.BankingServices;

public class MainClass {
	public static void main(String[] args) throws BankingServicesDownException, SQLException {
		// BankingServices bankingservices = new BankingServicesImpl();
		BankingServices bankingservices = ServicesProvider.getBankingServices();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String accountType, customerEmailId, localAddressCity, firstName, localAddressState, lastName, panCard,
				homeAddressCity, homeAddressState;
		int choice, accountNo, pinNumber, accountFrom, CustomerIdFrom, customerId = 0, amount, customerIdTo, accountTo,
				localAddressPinCode, homeAddressPinCode;
		float transAmount;
		do {
			System.out.println(
					"1. Add Customer \n 2. Details of Customer \n 3. Open Account \n 4.Deposit Amount \n 5. Withdraw Amount \n 6. Fund Transfer \n 7. Change Pin");
			System.out.println("Enter your choice");
			choice = scan.nextInt();
			try {
				switch (choice) {
				case 1:

					System.out.println("Enter Customer Details");
					System.out.println(" First Name");
					firstName = scan.next();
					System.out.println(" Last Name");
					lastName = scan.next();
					System.out.println(" Customer Email Id");
					customerEmailId = scan.next();
					System.out.println("Pan Card");
					panCard = scan.next();
					System.out.println("Local Address City");
					localAddressCity = scan.next();
					System.out.println("Local Address State");
					localAddressState = scan.next();
					System.out.println("Local Address Pincode");
					localAddressPinCode = scan.nextInt();
					System.out.println("Home Address City");
					homeAddressCity = scan.next();
					System.out.println("Home Address State");
					homeAddressState = scan.next();
					System.out.println("Home Address Pincode");
					homeAddressPinCode = scan.nextInt();
					customerId = bankingservices.acceptCustomerDetails(firstName, lastName, customerEmailId, panCard,
							localAddressCity, localAddressState, localAddressPinCode, homeAddressCity, homeAddressState,
							homeAddressPinCode);
					// customerId =
					// bankingservices.acceptCustomerDetails(firstName,
					// lastName, emailId, panCard, city,
					// state, pincode, homeAddress, homeAddress, customerId);
					// System.out.println(customerId);

				case 2:

					System.out.println("Enter Customer id");
					// System.out.println("Print Trace");
					customerId = scan.nextInt();
					// System.out.println("Customer Details");
					System.out.println(bankingservices.getCustomerDetails(customerId));

					break;

				case 3:
					System.out.println("Open the Account");
					System.out.println("Enter the Customer Id");
					customerId = scan.nextInt();
					System.out.println("Enter the Type of account " + " \n1.Savings " + " 2.Current ");
					// System.out.println("Account Type");
					// customerId = scan.nextInt();
					accountType = scan.next();

					System.out.println("Initial Balance");
					float initBalance = scan.nextFloat();
					System.out.println();

					try {
						System.out.println(bankingservices.openAccount(customerId, accountType, initBalance));
					} catch (InvalidAmountException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidAccountTypeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;

				case 4:

					System.out.println("Enter Customer Id");
					customerId = scan.nextInt();
					System.out.println("Enter Account No");
					accountNo = scan.nextInt();
					System.out.println("Deposit Amount");
					amount = scan.nextInt();

					try {
						System.out.println(bankingservices.depositAmount(customerId, accountNo, amount));
					} catch (AccountNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// System.out.println(bankingservices.depositAmount(customerId,
					// accountNo, amount));

					break;

				case 5:

					System.out.println("Enter Customer Id");
					customerId = scan.nextInt();
					System.out.println("Enter Account No");
					accountNo = scan.nextInt();
					System.out.println("Withdraw Amount");
					amount = scan.nextInt();
					System.out.println("Pin Number");
					pinNumber = scan.nextInt();

					try {
						System.out.println(bankingservices.withdrawAmount(customerId, accountNo, amount, pinNumber));
					} catch (InsufficientAmountException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (AccountNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidPinNumberException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (AccountBlockedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;

				case 6:
					System.out.println("Enter the Customer Id To");
					customerIdTo = scan.nextInt();
					System.out.println("Enter account number To");
					accountTo = scan.nextInt();
					System.out.println("Enter the Customer Id From");
					CustomerIdFrom = scan.nextInt();
					System.out.println("Enter account number From");
					accountFrom = scan.nextInt();
					System.out.println("Enter transfer ammount");
					transAmount = scan.nextFloat();
					System.out.println("Enter pin number");
					pinNumber = scan.nextInt();

					try {
						System.out.println(bankingservices.fundTransfer(customerIdTo, accountTo, CustomerIdFrom,
								accountFrom, transAmount, pinNumber));
					} catch (InsufficientAmountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AccountNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidPinNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				case 7:
					System.out.println("Enter the Customer Id To");
					customerIdTo = scan.nextInt();
					System.out.println("Change Pin");
					System.out.println("Enter account No");
					accountNo = scan.nextInt();
					System.out.println("Enter the old Pin");
					int oldPinNumber = scan.nextInt();
					System.out.println("Enter the new Pin");
					int newPinNumber = scan.nextInt();
					try {
						System.out.println(
								bankingservices.changeAccountPin(customerIdTo, accountNo, oldPinNumber, newPinNumber));
					} catch (AccountNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidPinNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				}
			} catch (CustomerNotFoundException cust) {
				System.out.println("customer doesnot exist");
			}

		} while (true);
	}

}
