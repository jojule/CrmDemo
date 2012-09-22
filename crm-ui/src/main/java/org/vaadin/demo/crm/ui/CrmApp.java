package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.data.Record;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;
import com.vaadin.ui.HorizontalLayout;

public class CrmApp extends TouchKitApplication {

	HorizontalLayout lo = new HorizontalLayout();

	NavigationManager left = new NavigationManager();
	NavigationManager right = new NavigationManager();

	DetailsView detailsView = new DetailsView();
	AccountListView accountListView = new AccountListView();
	DashboardView dashboardView = new DashboardView();

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

	public void showDetails(Record record) {
		if (right.getPreviousComponent() == detailsView)
			right.navigateBack();
		detailsView.setRecord(record);
	}

	public void hideDetails() {
		right.navigateTo(dashboardView);
	}

}
