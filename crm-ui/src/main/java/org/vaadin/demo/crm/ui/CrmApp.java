package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.data.Record;
import org.vaadin.demo.crm.data.mockup.Generator;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;
import com.vaadin.ui.HorizontalLayout;

public class CrmApp extends TouchKitApplication {
	
	static { Generator.ensureAvailabilityOfMockupData(); }

	HorizontalLayout lo = new HorizontalLayout();

	NavigationManager left = new NavigationManager();
	NavigationManager right = new NavigationManager();

	DetailsView detailsView = new DetailsView();
	AccountListView accountListView = new AccountListView();
	StatusView dashboardView = new StatusView();

	public void onBrowserDetailsReady() {
		TouchKitWindow w = new TouchKitWindow();
		setMainWindow(w);

		w.setCaption("CRM");

		HorizontalLayout lo = new HorizontalLayout();
		lo.setSizeFull();
		w.setContent(lo);

		lo.addComponent(left);
		lo.addComponent(right);

		left.setCurrentComponent(accountListView);
		right.setPreviousComponent(detailsView);
		right.setCurrentComponent(dashboardView);
	}

	public void showDetails(EntityItem<? extends Record> record) {
		if (right.getPreviousComponent() == detailsView)
			right.navigateBack();
		detailsView.setRecord(record);
	}

	public void hideDetails() {
		right.navigateTo(dashboardView);
	}

}
