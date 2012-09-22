package org.vaadin.demo.crm.data;

public class AccountManager extends Record {

	String firstName;
	String lastName;

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

	public String getRecordName() {
		return "" + getFirstName() + " " + getLastName();
	}

}
