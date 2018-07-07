package com.payroll.beans;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTables;
import javax.persistence.SecondaryTable;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.validator.constraints.NotEmpty;
@Entity @SecondaryTables(value={
		@SecondaryTable(name="Salary",pkJoinColumns=@PrimaryKeyJoinColumn(name="AssociateId")),
		@SecondaryTable(name="BankDetails" , pkJoinColumns=@PrimaryKeyJoinColumn(name="AssociateId"))
})
public class Associate {

	@NotEmpty
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private int associateId;
	int yearlyInvestmentUnder80C;
	private String firstName,lastName,department,designation,pancard,emailId;
	@NotEmpty
	private String password;
	@Embedded @AttributeOverrides(value={
			@AttributeOverride(name="basicSalary",column=@Column(name="basicSalary" , table="Salary")),
			@AttributeOverride(name="hra",column=@Column(name="hra" , table="Salary")),
			@AttributeOverride(name="conveyanceAllowance",column=@Column(name="conveyanceAllowance" , table="Salary")),
			@AttributeOverride(name="otherAllowances",column=@Column(name="otherAllowances" , table="Salary")),
			@AttributeOverride(name="personalAllowances",column=@Column(name="personalAllowances" , table="Salary")),
			@AttributeOverride(name="monthlyTax",column=@Column(name="monthlyTax" , table="Salary")),
			@AttributeOverride(name="epf",column=@Column(name="epf" , table="Salary")),
			@AttributeOverride(name="companyPf",column=@Column(name="companyPf" , table="Salary")),
			@AttributeOverride(name="gratuity",column=@Column(name="gratuity" , table="Salary")),
			@AttributeOverride(name="grossSalary",column=@Column(name="grossSalary" , table="Salary")),
			@AttributeOverride(name="netSalary",column=@Column(name="netSalary" , table="Salary"))
	})
	private Salary salary;
	
	@Embedded
	@AttributeOverrides(value={
			@AttributeOverride(name="acccountNo",column=@Column(name="acccountNo",table="BankDetails")),
			@AttributeOverride(name="bankName",column=@Column(name="bankName",table="BankDetails")),
			@AttributeOverride(name="ifscCode",column=@Column(name="ifscCode",table="BankDetails"))
	})
	private BankDetails bankDetails;
	
	public Associate() {}
	
	public Associate(int associateId, String password) {
		super();
		this.associateId = associateId;
		this.password = password;
	}
	public Associate(int yearlyInvestmentUnder80C, String firstName,
			String lastName, String department, String designation,
			String pancard, String emailId, Salary salary,
			BankDetails bankDetails) {
		super();
		this.yearlyInvestmentUnder80C = yearlyInvestmentUnder80C;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.pancard = pancard;
		this.emailId = emailId;
		this.salary = salary;
		this.bankDetails = bankDetails;
	}
	
	public Associate(int associateId,String password , int yearlyInvestmentUnder80C,
			String firstName, String lastName, String department,
			String designation, String pancard, String emailId, Salary salary,
			BankDetails bankDetails) {
		super();
		this.associateId = associateId;
		this.password=password;
		this.yearlyInvestmentUnder80C = yearlyInvestmentUnder80C;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.pancard = pancard;
		this.emailId = emailId;
		this.salary = salary;
		this.bankDetails = bankDetails;
	}
	
	
	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAssociateId() {
		return associateId;
	}
	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}
	public int getYearlyInvestmentUnder80C() {
		return yearlyInvestmentUnder80C;
	}
	public void setYearlyInvestmentUnder80C(int yearlyInvestmentUnder80C) {
		this.yearlyInvestmentUnder80C = yearlyInvestmentUnder80C;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public BankDetails getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateId;
		result = prime * result
				+ ((bankDetails == null) ? 0 : bankDetails.hashCode());
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result
				+ ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((pancard == null) ? 0 : pancard.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime * result + yearlyInvestmentUnder80C;
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
		Associate other = (Associate) obj;
		if (associateId != other.associateId)
			return false;
		if (bankDetails == null) {
			if (other.bankDetails != null)
				return false;
		} else if (!bankDetails.equals(other.bankDetails))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pancard == null) {
			if (other.pancard != null)
				return false;
		} else if (!pancard.equals(other.pancard))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (yearlyInvestmentUnder80C != other.yearlyInvestmentUnder80C)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Associate [associateId=" + associateId
				+ ", yearlyInvestmentUnder80C=" + yearlyInvestmentUnder80C
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", department=" + department + ", designation=" + designation
				+ ", pancard=" + pancard + ", emailId=" + emailId + ", salary="
				+ salary + ", bankDetails=" + bankDetails + "]";
	}
	
}
