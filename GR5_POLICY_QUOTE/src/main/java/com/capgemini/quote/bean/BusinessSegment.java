package com.capgemini.quote.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//Business Segment Entity Class
@Entity
@Table(name = "BusinessSegment")
@NamedQueries(@NamedQuery(name = "getAllBusinessSegment", query = "SELECT businesssegment FROM BusinessSegment businesssegment"))
public class BusinessSegment {
	@Id
	private String busSegId;
	private int busSegSeq;
	private String busSegName;

	public BusinessSegment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessSegment(String busSegId, int busSegSeq, String busSegName) {
		super();
		this.busSegId = busSegId;
		this.busSegSeq = busSegSeq;
		this.busSegName = busSegName;
	}

	public String getBusSegId() {
		return busSegId;
	}

	public void setBusSegId(String busSegId) {
		this.busSegId = busSegId;
	}

	public int getBusSegSeq() {
		return busSegSeq;
	}

	public void setBusSegSeq(int busSegSeq) {
		this.busSegSeq = busSegSeq;
	}

	public String getBusSegName() {
		return busSegName;
	}

	public void setBusSegName(String busSegName) {
		this.busSegName = busSegName;
	}

	@Override
	public String toString() {
		return "\nBusinessSegments [busSegId=" + busSegId + ", busSegSeq=" + busSegSeq + ", busSegName=" + busSegName
				+ "]";
	}

}
