package org.vaadin.demo.crm.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Opportunity extends Record {

	// TODO
//	Date expected;
	int amount;
	String name;
	String description;
	@ManyToOne
	Account account;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

//	public Date getExpected() {
//		return expected;
//	}
//
//	public void setExpected(Date expected) {
//		this.expected = expected;
//	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Opportunity) {
			Opportunity p = (Opportunity) obj;
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
