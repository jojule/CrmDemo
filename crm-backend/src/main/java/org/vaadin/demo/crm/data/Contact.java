package org.vaadin.demo.crm.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contact extends Record {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecordName() {
		return getFirstName() + " " + getLastName();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Contact) {
			Contact p = (Contact) obj;
			if (this == p) {
				return true;
			}
			if (this.id == null || p.id == null) {
				return false;
			}
			return this.id.equals(p.id);
		}

		return super.equals(obj);
	}

	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
