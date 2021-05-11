package com.capgemini.quote.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Policy Question Entity Class
@Entity
@Table(name="policyQuestion")
public class PolicyQuestion {

	@Id
	@Column(name="polQId")
	private String polQId;
	@Column(name="polQSeq")
	private int polQSeq;
	@Column(name="busSegId")
	private String busSegId;
	@Column(name="polQDesc")
	private String polQDesc;
	@Column(name="polQAns1")
	private String polQAns1;
	@Column(name="polAns1Wtg")
	private int polAns1Wtg;
	@Column(name="polQAns2")
	private String polQAns2;
	@Column(name="polAns2Wtg")
	private int polAns2Wtg;
	@Column(name="polQAns3")
	private String polQAns3;
	@Column(name="polAns3Wtg")
	private int polAns3Wtg;

	public PolicyQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PolicyQuestion(String polQId, int polQSeq, String busSegId, String polQDesc, String polQAns1,
			int polQAns1Wtg, String polQAns2, int polQAns2Wtg, String polQAns3, int polQAns3Wtg)
	{
		super();
		this.polQId = polQId;
		this.polQSeq = polQSeq;
		this.busSegId = busSegId;
		this.polQDesc = polQDesc;
		this.polQAns1 = polQAns1;
		this.polAns1Wtg = polQAns1Wtg;
		this.polQAns2 = polQAns2;
		this.polAns2Wtg = polQAns2Wtg;
		this.polQAns3 = polQAns3;
		this.polAns3Wtg = polQAns3Wtg;

	}

	public String getPolQId() {
		return polQId;
	}

	public void setPolQId(String polQId) {
		this.polQId = polQId;
	}

	public int getPolQSeq() {
		return polQSeq;
	}

	public void setPolQSeq(int polQSeq) {
		this.polQSeq = polQSeq;
	}

	public String getBusSegId() {
		return busSegId;
	}

	public void setBusSegId(String busSegId) {
		this.busSegId = busSegId;
	}

	public String getPolQDesc() {
		return polQDesc;
	}

	public void setPolQDesc(String polQDesc) {
		this.polQDesc = polQDesc;
	}

	public String getPolQAns1() {
		return polQAns1;
	}

	public void setPolQAns1(String polQAns1) {
		this.polQAns1 = polQAns1;
	}

	public int getPolQAns1Wtg() {
		return polAns1Wtg;
	}

	public void setPolQAns1Wtg(int polQAns1Wtg) {
		this.polAns1Wtg = polQAns1Wtg;
	}

	public String getPolQAns2() {
		return polQAns2;
	}

	public void setPolQAns2(String polQAns2) {
		this.polQAns2 = polQAns2;
	}

	public int getPolQAns2Wtg() {
		return polAns2Wtg;
	}

	public void setPolQAns2Wtg(int polQAns2Wtg) {
		this.polAns2Wtg = polQAns2Wtg;
	}

	public String getPolQAns3() {
		return polQAns3;
	}

	public void setPolQAns3(String polQAns3) {
		this.polQAns3 = polQAns3;
	}

	public int getPolQAns3Wtg() {
		return polAns3Wtg;
	}

	public void setPolQAns3Wtg(int polQAns3Wtg) {
		this.polAns3Wtg = polQAns3Wtg;
	}


	@Override
	public String toString() {
		return "\nPolicyQuestion [polQId=" + polQId + ", polQSeq=" + polQSeq + ", busSegId=" + busSegId + ", polQDesc="
				+ polQDesc + ", polQAns1=" + polQAns1 + ", polAns1Wtg=" + polAns1Wtg + ", polQAns2=" + polQAns2
				+ ", polAns2Wtg=" + polAns2Wtg + ", polQAns3=" + polQAns3 + ", polAns3Wtg=" + polAns3Wtg + "]";
	}

}
