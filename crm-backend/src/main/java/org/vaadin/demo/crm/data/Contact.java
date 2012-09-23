package org.vaadin.demo.crm.data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Contact extends Record {

	String firstName;
	String lastName;
	String email;
	String mobilePhone;
	String officePhone;
	
	@ManyToOne
	Account account;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getRecordName() {
		return getFirstName() + " " + getLastName();
	}
}
