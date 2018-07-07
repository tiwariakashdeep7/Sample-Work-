package com.payroll.beans;

import javax.persistence.Embeddable;

@Embeddable
public class BankDetails {
	private int acccountNo;
	private String bankName, ifscCode;
	public BankDetails() {}
	public BankDetails(int acccountNo, String bankName, String ifscCode) {
		super();
		this.acccountNo = acccountNo;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
	}
	public int getAcccountNo() {
		return acccountNo;
	}
	public void setAcccountNo(int acccountNo) {
		this.acccountNo = acccountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acccountNo;
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result
				+ ((ifscCode == null) ? 0 : ifscCode.hashCode());
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
		BankDetails other = (BankDetails) obj;
		if (acccountNo != other.acccountNo)
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (ifscCode == null) {
			if (other.ifscCode != null)
				return false;
		} else if (!ifscCode.equals(other.ifscCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BankDetails [acccountNo=" + acccountNo + ", bankName="
				+ bankName + ", ifscCode=" + ifscCode + "]";
	}
}
