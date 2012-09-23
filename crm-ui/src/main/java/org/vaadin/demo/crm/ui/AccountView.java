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

public class AccountView extends NavigationView implements
		DetailsView.UpdateListener {

	EntityItem<Account> account;

	VerticalLayout layout = new VerticalLayout();
	Button editButton = new Button("Edit", new ClickListener() {
		public void buttonClick(ClickEvent event) {
			((CrmApp) getApplication()).showDetailsView(account, AccountView.this);
		}
	});

	public AccountView(EntityItem<Account> account) {
		this.account = account;

		setContent(layout);
		getNavigationBar().setCaption(account.getEntity().getRecordName());
		getNavigationBar().setRightComponent(editButton);

		updateNavigationButtonsForRelatedRecords();
	}

	public void updateNavigationButtonsForRelatedRecords() {

		layout.removeAllComponents();

		@SuppressWarnings("rawtypes")
		HashMap<Class, VerticalComponentGroup> buttonGroups = new HashMap<Class, VerticalComponentGroup>();

		for (Record r : Backend.getRecords((Account) account.getEntity())) {
			VerticalComponentGroup buttonGroup = buttonGroups.get(r.getClass());

			if (buttonGroup == null) {
				buttonGroup = new VerticalComponentGroup();
				buttonGroup.setCaption(r.getRecordTypePlural());
				buttonGroups.put(r.getClass(), buttonGroup);
				layout.addComponent(buttonGroup);
			}

			// Create a button to show the given records
			final Long recordId = r.getId();
			final Class<? extends Record> recordClass = r.getClass();
			Button showRecordButton = new Button(r.getRecordName(),
					new ClickListener() {
						public void buttonClick(ClickEvent event) {
							EntityItem<? extends Record> item = JPAContainerFactory
									.make(recordClass, Backend.PERSISTENCE_UNIT)
									.getItem(recordId);
							((CrmApp) getApplication()).showDetailsView(item,
									AccountView.this);
						}
					});
			buttonGroup.addComponent(showRecordButton);
			showRecordButton.setStyleName("nav");
		}
	}

	public void recordUpdatedByDetailsView() {
		updateNavigationButtonsForRelatedRecords();
		getNavigationBar().setCaption(account.getEntity().getRecordName());
	}
}
