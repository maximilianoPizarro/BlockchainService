package com.blockchain.app.domain;

import java.util.ArrayList;
import java.util.List;

public class Stamp2 {

	List<String> hashes = new ArrayList<String>();

	public Stamp2(String hash) {
		this.hashes.add(hash);
	}
	
	public Stamp2(List<String> hashes) {
		this.hashes=hashes;
	}
}
