package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.data.Account;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FilterView extends NavigationView {

	public FilterView(final JPAContainer<Account> accounts) {
		VerticalLayout l = new VerticalLayout();
		final TextField filter = new TextField();
		filter.setWidth("100%");
		filter.setHeight("3em");
		filter.setInputPrompt("Filter by name");
		l.addComponent(filter );
		Button apply = new Button("apply", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				accounts.removeAllContainerFilters();
				accounts.addContainerFilter(new SimpleStringFilter("name",
						(String) filter.getValue(), true, false));
				getNavigationManager().navigateBack();
				
			}
		});
		l.addComponent(apply);
		setContent(l);
	}
}
