package com.blockchain.app.domain;

import java.io.Serializable;

public class StampVerifyResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String status;
	private String permanent_rd;
	private String messages;
	private String attestation_time;

	public Boolean isOK() {
		return getStatus().compareTo("success") == 0;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPermanent_rd() {
		return permanent_rd;
	}

	public void setPermanent_rd(String permanent_rd) {
		this.permanent_rd = permanent_rd;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getAttestation_time() {
		return attestation_time;
	}

	public void setAttestation_time(String attestation_time) {
		this.attestation_time = attestation_time;
	}

	@Override
	public String toString() {
		return "{\"status\":" + "\"" + getStatus() + "\", \"permanent_rd\": " + "\"" + getPermanent_rd()
				+ "\", \"messages\": " + "\"" + getMessages() + "\", \"attestation_time\": " + "\""
				+ getAttestation_time() + "\"}";
	}

}
