package org.vaadin.demo.crm.ui;

import java.util.HashMap;

import org.vaadin.demo.crm.Backend;
import org.vaadin.demo.crm.data.Account;
import org.vaadin.demo.crm.data.Record;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class AccountView extends NavigationView {

	public AccountView(final EntityItem<Account> account) {
		getNavigationBar().setCaption(account.getEntity().getRecordName());

		getNavigationBar().setRightComponent(
				new Button("Edit", new ClickListener() {
					public void buttonClick(ClickEvent event) {
						((CrmApp) getApplication()).showDetails(account);
					}
				}));

		VerticalLayout lo = new VerticalLayout();
		setContent(lo);

		@SuppressWarnings("rawtypes")
		HashMap<Class, VerticalComponentGroup> groups = new HashMap<Class, VerticalComponentGroup>();
		for (final Record r : Backend.getRecords((Account) account.getEntity())) {
			VerticalComponentGroup g = groups.get(r.getClass());
			if (g == null) {
				g = new VerticalComponentGroup();
				g.setCaption(r.getRecordTypePlural());
				groups.put(r.getClass(), g);
				lo.addComponent(g);
			}
			Button b = new Button(r.getRecordName(), new ClickListener() {
				public void buttonClick(ClickEvent event) {
					((CrmApp) getApplication()).showDetails(JPAContainerFactory
							.make(r.getClass(), Backend.PERSISTENCE_UNIT)
							.getItem(r.getId()));
				}
			});
			g.addComponent(b);
			b.setStyleName("nav");
		}
	}
}
