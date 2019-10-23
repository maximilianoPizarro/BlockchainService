package com.blockchain.app.domain;

import java.util.ArrayList;
import java.util.List;

public class StampVerify2 {

    Boolean stamped;
    List<StampVerifyResponse2> stamps = new ArrayList<StampVerifyResponse2>();
    public StampVerify2(Boolean stamped, List<StampVerifyResponse2> stamps) {
        super();
        this.stamped=stamped;
        this.stamps=stamps;
    }
	public Boolean getStamped() {
		return stamped;
	}
	public void setStamped(Boolean stamped) {
		this.stamped = stamped;
	}
	public List<StampVerifyResponse2> getStamps() {
		return stamps;
	}
	public void setStamps(List<StampVerifyResponse2> stamps) {
		this.stamps = stamps;
	}
    
    
}
