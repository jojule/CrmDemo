package org.vaadin.demo.crm.ui;

import java.util.HashMap;
import java.util.LinkedList;

import org.vaadin.demo.crm.data.Account;
import org.vaadin.demo.crm.data.Opportunity;
import org.vaadin.demo.crm.data.Record;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AccountView extends NavigationView {

	HashMap<Component, Record> relatedRecord = new HashMap<Component, Record>();

	public AccountView(Account account) {
		getNavigationBar().setCaption(account.getRecordName());

		VerticalLayout lo = new VerticalLayout();
		lo.setMargin(true);
		setContent(lo);
		
		HashMap<Class, VerticalComponentGroup> groups = new HashMap<Class, VerticalComponentGroup>();
		for (final Record r : getRecords(account)) {
			VerticalComponentGroup g = groups.get(r.getClass());
			if (g == null) {
				g = new VerticalComponentGroup();
				g.setCaption(r.getRecordTypePlural());
				groups.put(r.getClass(), g);
				lo.addComponent(g);
				g.addListener(new LayoutClickListener() {
					public void layoutClick(LayoutClickEvent event) {
						((CrmApp) getApplication()).showDetails(relatedRecord
								.get(event.getChildComponent()));
					}
				});
			}
			Label b = new Label(r.getRecordName());
			relatedRecord.put(b, r);
			g.addComponent(b);
		}
	}

	private Record[] getRecords(Account account) {

		LinkedList<Record> records = new LinkedList<Record>();

		for (int i = 0; i < 10; i++) {
			Opportunity o = new Opportunity();
			o.setAccount(account);
			o.setName("Opportunity " + i);
			records.add(o);
		}
			
		return records.toArray(new Record[] {});
	}
}
