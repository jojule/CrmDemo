package org.vaadin.demo.crm;

import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.vaadin.demo.crm.data.Account;
import org.vaadin.demo.crm.data.Contact;
import org.vaadin.demo.crm.data.Opportunity;
import org.vaadin.demo.crm.data.Record;
import org.vaadin.demo.crm.data.mockup.Generator;

public class Backend {
	public static final String PERSISTENCE_UNIT = "org.vaadin.demo.crm";

	static {
		Generator.ensureAvailabilityOfMockupData();
	}

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(Backend.PERSISTENCE_UNIT)
				.createEntityManager();
	}

	public static Account[] getAllAccounts() {
		EntityManager em = getEntityManager();
		TypedQuery<Account> query = em.createQuery(
				"SELECT a FROM Account a",
				Account.class);
		return (Account[]) query.getResultList().toArray(new Account[] {});
	}

	public static Record[] getRecords(Account account) {
		EntityManager em = getEntityManager();
		LinkedList<Record> records = new LinkedList<Record>();
		
		TypedQuery<Opportunity> opportunityQuery = em.createQuery(
				"SELECT o FROM Opportunity o WHERE o.account=:account",
				Opportunity.class);
		opportunityQuery.setParameter("account", account);
		records.addAll(opportunityQuery.getResultList());
		
		TypedQuery<Contact> contactQuery = em.createQuery(
				"SELECT c FROM Contact c WHERE c.account=:account",
				Contact.class);
		contactQuery.setParameter("account", account);
		records.addAll(contactQuery.getResultList());
		
		return (Record[]) records.toArray(new Record[] {});
	}
}
