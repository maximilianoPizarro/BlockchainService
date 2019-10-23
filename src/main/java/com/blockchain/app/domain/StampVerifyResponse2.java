package com.blockchain.app.domain;

import java.io.Serializable;

public class StampVerifyResponse2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String whostamped;
	private String blocknumber;
	private String blocktimestamp;
	
	public String getWhostamped() {
		return whostamped;
	}
	public void setWhostamped(String whostamped) {
		this.whostamped = whostamped;
	}
	public String getBlocknumber() {
		return blocknumber;
	}
	public void setBlocknumber(String blocknumber) {
		this.blocknumber = blocknumber;
	}
	public String getBlocktimestamp() {
		return blocktimestamp;
	}
	public void setBlocktimestamp(String blocktimestamp) {
		this.blocktimestamp = blocktimestamp;
	}
	
	

}
