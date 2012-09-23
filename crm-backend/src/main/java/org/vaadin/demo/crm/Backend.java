package org.vaadin.demo.crm;

import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.vaadin.demo.crm.data.Account;
import org.vaadin.demo.crm.data.Contact;
import org.vaadin.demo.crm.data.Lead;
import org.vaadin.demo.crm.data.Opportunity;
import org.vaadin.demo.crm.data.PipelineStage;
import org.vaadin.demo.crm.data.Record;
import org.vaadin.demo.crm.data.mockup.Generator;

public class Backend {
	public static final String PERSISTENCE_UNIT = "org.vaadin.demo.crm";

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(Backend.PERSISTENCE_UNIT)
				.createEntityManager();
	}

	public static Account[] getAllAccounts() {
		EntityManager em = getEntityManager();
		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a",
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

	/* Mockup report */
	public static PipelineStage[] getPipelineReport() {

		final int meanValue = 30000;

		PipelineStage[] stages = new PipelineStage[6];
		for (int i = 0; i < stages.length; i++)

			stages[i] = new PipelineStage();
		int opportunityCount = (int) (Math.random() * 25);

		stages[0].setName("Prospecting");
		stages[0].setOpportunityCount(opportunityCount);
		stages[0].setTotalValue((int) (opportunityCount * meanValue * 2 * Math
				.random()));
		stages[0].setProbabilityAdjustedValue(stages[0].getTotalValue() / 10);

		opportunityCount *= Math.random() * 1.8;
		stages[1].setName("Needs Analysis");
		stages[1].setOpportunityCount(opportunityCount);
		stages[1].setTotalValue((int) (opportunityCount * meanValue * 2 * Math
				.random()));
		stages[1].setProbabilityAdjustedValue(stages[1].getTotalValue() / 5);

		opportunityCount *= Math.random()  * 1.8;
		stages[2].setName("Value Proposition");
		stages[2].setOpportunityCount(opportunityCount);
		stages[2].setTotalValue((int) (opportunityCount * meanValue * 2 * Math
				.random()));
		stages[2].setProbabilityAdjustedValue(stages[2].getTotalValue() / 3);

		opportunityCount *= Math.random()  * 1.8;
		stages[3].setName("Perception Analysis");
		stages[3].setOpportunityCount(opportunityCount);
		stages[3].setTotalValue((int) (opportunityCount * meanValue * 2 * Math
				.random()));
		stages[3]
				.setProbabilityAdjustedValue((int) (stages[3].getTotalValue() * 0.45));

		opportunityCount *= Math.random() * 1.8;
		stages[4].setName("Proposal/Price Quote");
		stages[4].setOpportunityCount(opportunityCount);
		stages[4].setTotalValue((int) (opportunityCount * meanValue * 2 * Math
				.random()));
		stages[4]
				.setProbabilityAdjustedValue((int) (stages[4].getTotalValue() * 0.65));

		opportunityCount *= Math.random() * 1.8;
		stages[5].setName("Negotiation/Review");
		stages[5].setOpportunityCount(opportunityCount);
		stages[5].setTotalValue((int) (opportunityCount * meanValue * 2 * Math
				.random()));
		stages[5]
				.setProbabilityAdjustedValue((int) (stages[5].getTotalValue() * 0.85));

		return stages;
	}

	public static Lead[] getLeads() {
		int number = (int)(Math.random()*30)+2;
		Lead[] leads = new Lead[number];
		for (int i = 0; i < leads.length; i++) {
			leads[i] = new Lead();
			leads[i].setTitle(Generator.randomTitle((int) (Math.random() * 5)));
			leads[i].setDescriptionHtml(Generator.randomHTML(20 + (int) (Math
					.random() * 300)));
		}
		return leads;
	}

}
