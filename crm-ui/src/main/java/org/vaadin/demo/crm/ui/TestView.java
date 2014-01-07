package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.Backend;
import org.vaadin.demo.crm.data.Account;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TestView extends NavigationView {

	public TestView() {

		VerticalLayout lo = new VerticalLayout();
		setContent(lo);

	final JPAContainer<Account>accounts = JPAContainerFactory.make(Account.class, Backend.PERSISTENCE_UNIT);
		Table t = new Table(null, accounts);
		lo.addComponent(t);
		t.setWidth("100%");
		
		
		final TextField filter = new TextField("Filter");
		filter.setImmediate(true);
		lo.addComponent(filter);
		filter.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				accounts.removeAllContainerFilters();
				accounts.addContainerFilter(new SimpleStringFilter("name", (String) filter.getValue(), true, false));
				
			}
		});

		setCaption("Options");

		for (int j = 0; j < 5; j++) {
			VerticalComponentGroup g = new VerticalComponentGroup();
			g.setWidth("100%");;
			g.setCaption("Group " + j);
			lo.addComponent(g);

			for (int i = 0; i < 5; i++) {
				NavigationButton b = new NavigationButton("Option " + i);
				g.addComponent(b);

				NavigationView emptyView = new NavigationView();
				emptyView.setCaption("Empty " + i);

				b.setTargetView(emptyView);
			}
		}
		
		Button b = new Button("Test", new Button.ClickListener() {			
			public void buttonClick(ClickEvent event) {
				Notification.show("Hello YOU");
			}

		});
		getNavigationBar().setRightComponent(b);
		
	}
}