package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.Backend;
import org.vaadin.demo.crm.data.Account;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

public class AccountListView extends NavigationView {
	
	JPAContainer<Account> accounts = JPAContainerFactory.make(Account.class, Backend.PERSISTENCE_UNIT);
	
	public AccountListView() {
		getNavigationBar().setCaption("Accounts");
		setContent(new AccountList());		
	}
	
	class AccountList extends Table implements ItemClickListener {
		
		public AccountList() {
			setSizeFull();
			setContainerDataSource(accounts);
			setVisibleColumns(new Object[]{"name","sales"});
			addItemClickListener(this);
		}

		@Override
		public void itemClick(ItemClickEvent event) {
			EntityItem<Account> account = accounts.getItem(event.getItemId());
			Component accountView = new AccountView(account);
			getNavigationManager().navigateTo(accountView);
			
		}
		
	}

}
