package com.api.pojo;

public class Address {

	private String first_name;
	private String last_name;
	private String address_line1;
	private String address_line2;
	private String city;
	private String state;
	private String zip;



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

	public String getAddress_line2() {
		return address_line2;
	}

	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
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



	public Address(String first_name, String last_name, String address_line1, String address_line2, String city, String state, String zip) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.address_line1 = address_line1;
		this.address_line2 = address_line2;
		this.city = city;
		this.state = state;
		this.zip = zip;

	}
	public String toString() {
		return "" + this.first_name + " " + this.last_name+" "+this.address_line1+""+this.address_line2+""+this.city+""+this.state+""+this.zip;
	}


}
