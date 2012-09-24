package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.Backend;
import org.vaadin.demo.crm.data.Account;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

public class AccountListView extends NavigationView {

	JPAContainer<Account> accounts = JPAContainerFactory.make(Account.class,
			Backend.PERSISTENCE_UNIT);

	Button findButton = new Button("Find", new ClickListener() {
		public void buttonClick(ClickEvent event) {
			accounts.removeAllContainerFilters();
			new FindDialog();
		}
	});

	public AccountListView() {
		getNavigationBar().setCaption("Accounts");
		getNavigationBar().setLeftComponent(findButton);
		setContent(new AccountList());
	}
	
	protected void onBecomingVisible() {
		((CrmApp) getApplication()).hideDetailsView();
		super.onBecomingVisible();
	}

	// List of accounts filling the view
	class AccountList extends Table implements ItemClickListener {
		public AccountList() {
			setSizeFull();
			setImmediate(true);
			setContainerDataSource(accounts);
			setVisibleColumns(new Object[] { "name", "sales" });
			addListener((ItemClickListener) this);
		}

		public void itemClick(ItemClickEvent event) {
			if (getNavigationManager().getCurrentComponent() != AccountListView.this)
				return;

			EntityItem<Account> account = accounts.getItem(event.getItemId());
			AccountView accountView = new AccountView(account);
			getNavigationManager().navigateTo(accountView);

			((CrmApp) getApplication()).showDetailsView(account, accountView);
		}

	}

	// Dialog shown when Find button is clicked
	class FindDialog extends Popover implements ClickListener {
		TextField nameFilter = new TextField();
		Button searchButton = new Button("Search");
		HorizontalLayout layout = new HorizontalLayout();

		public FindDialog() {
			showRelativeTo(findButton);

			setContent(layout);
			layout.setMargin(true);
			layout.setSpacing(true);
			layout.addComponent(nameFilter);
			layout.setComponentAlignment(nameFilter, Alignment.MIDDLE_CENTER);
			layout.addComponent(searchButton);

			nameFilter.setInputPrompt("Customer name");
			searchButton.addListener(this);
		}

		public void buttonClick(ClickEvent event) {
			accounts.addContainerFilter(new SimpleStringFilter("name",
					(String) nameFilter.getValue(), true, false));
			AccountListView.this.getWindow().removeWindow(this);
		}
	}
}
