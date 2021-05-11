package com.capgemini.quote.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Policy Details Entity Class
@Entity
@Table(name="PolicyDetails")
public class PolicyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="policydetailsId")
	private int policyDetailsId;
	@Column(name="policyNumber")
	private long policyNumber;
	@Column(name="qId")
	private String qId;
	@Column(name="answer")
	private int answer;

	public PolicyDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PolicyDetails(long policyNumber, String qId, int answer) {
		super();
		this.policyNumber = policyNumber;
		this.qId = qId;
		this.answer = answer;
	}

	public long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getqId() {
		return qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "\nPolicyDetails [policyNumber=" + policyNumber + ", qId=" + qId + ", answer=" + answer + "]";
	}

}
