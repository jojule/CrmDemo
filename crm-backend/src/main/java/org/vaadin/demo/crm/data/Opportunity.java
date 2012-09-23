package org.vaadin.demo.crm.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Opportunity extends Record {

	@Temporal(TemporalType.DATE)
	Date expected;
	int amount;
	String name;
	String description;
	@ManyToOne
	Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getExpected() {
		return expected;
	}

	public void setExpected(Date expected) {
		this.expected = expected;
 }

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecordName() {
		return getName();
	}

	public String getRecordTypePlural() {
		return "Opportunities";
	}
}
