package com.api.pojo;

public class Addressemailphone {
	
	String first_name;
	String last_name;
	String address_line1;
	String address_line2;
	String city;
	String state;
	String zip;
	String email;
	String phone;
	String client_id;

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}




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
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress_line2() {
		return address_line2;
	}

	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}


	
	public Addressemailphone(String fname, String lname, String addressline, String city, String state, String zip)
	{
		this.first_name=fname;
		this.last_name=lname;
		this.address_line1=addressline;
		this.city=city;
		this.state=state;
		this.zip=zip;
	}
	public Addressemailphone(String first_name, String last_name, String email) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}
	public Addressemailphone(String first_name, String last_name, String phone, String zip) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
	}
	public Addressemailphone(String first_name, String last_name, String address_line1, String address_line2, String city, String state, String zip, String email, String phone) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.address_line1 = address_line1;
		this.address_line2 = address_line2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.phone = phone;
	}



}
