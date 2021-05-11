package com.capgemini.quote.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//Policy Entity Class
@Entity
@Table(name = "Policy")
public class Policy {

	@Id
	@GeneratedValue(generator = "pol_seq")
	@SequenceGenerator(name = "pol_seq", sequenceName = "POL_SEQ", allocationSize = 1)
	@Column(name = "policyNumber")
	private long policyNumber;
	@Column(name = "PolicyPremium")
	private double policyPremium;
	@Column(name = "AccountNumber")
	private long accountNumber;

	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Policy(double policyPremium, long accountNumber) {
		super();
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
	}

	public long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public double getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(double policyPremium) {
		this.policyPremium = policyPremium;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNo) {
		accountNumber = accountNo;
	}

	@Override
	public String toString() {
		return "\nPolicy [policyNumber=" + policyNumber + ", policyPremium=" + policyPremium + ", accountNumber="
				+ accountNumber + "]";
	}

}
