package com.api.pojo;

public class Email {

	String first_name;
	String last_name;
	String email;
	String client_id;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public Email(String first_name, String last_name, String email, String client_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.client_id = client_id;
	}



}
