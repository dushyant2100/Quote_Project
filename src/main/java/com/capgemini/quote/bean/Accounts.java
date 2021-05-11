package com.capgemini.quote.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//Accounts Entity Class
@Entity
@Table(name = "Accounts")
@NamedQueries(@NamedQuery(name = "getAllAccounts", query = "SELECT accounts FROM Accounts accounts"))
public class Accounts {
	@Id
	@GeneratedValue(generator = "acc_seq")
	@SequenceGenerator(name = "acc_seq", sequenceName = "ACC_SEQ", allocationSize = 1)
	@Column(name = "accountNumber")
	private long accountNumber;
	@Column(name = "insuredName")
	private String insuredName;
	@Column(name = "insuredStreet")
	private String insuredStreet;
	@Column(name = "insuredCity")
	private String insuredCity;
	@Column(name = "insuredState")
	private String insuredState;
	@Column(name = "insuredZip")
	private long insuredZip;
	@Column(name = "businessSegment")
	private String businessSegment;
	@Column(name = "UserName")
	private String userName;
	@Column(name = "createdBy")
	private String createdBy;

	public Accounts() {
		super();
		
	}


	public Accounts(String insuredName, String insuredStreet, String insuredCity, String insuredState, long insuredZip,
			String businessSegment, String userName, String createdBy) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.businessSegment = businessSegment;
		this.userName = userName;
		this.createdBy = createdBy;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNo(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredStreet() {
		return insuredStreet;
	}

	public void setInsuredStreet(String insuredStreet) {
		this.insuredStreet = insuredStreet;
	}

	public String getInsuredCity() {
		return insuredCity;
	}

	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}

	public String getInsuredState() {
		return insuredState;
	}

	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}

	public long getInsuredZip() {
		return insuredZip;
	}

	public void setInsuredZip(long insuredZip) {
		this.insuredZip = insuredZip;
	}

	public String getBusinessSegment() {
		return businessSegment;
	}

	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "\nAccounts [accountNumber=" + accountNumber + ", insuredName=" + insuredName + ", insuredStreet="
				+ insuredStreet + ", insuredCity=" + insuredCity + ", insuredState=" + insuredState + ", insuredZip="
				+ insuredZip + ", businessSegment=" + businessSegment + ", userName=" + userName + ", createdBy="
				+ createdBy + "]";
	}

}
