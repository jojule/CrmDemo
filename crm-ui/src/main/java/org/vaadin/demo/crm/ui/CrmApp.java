package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.data.Record;
import org.vaadin.demo.crm.data.mockup.Generator;
import org.vaadin.demo.crm.ui.DetailsView.UpdateListener;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;
import com.vaadin.ui.HorizontalLayout;

public class CrmApp extends TouchKitApplication {

	static {
		Generator.ensureAvailabilityOfMockupData();
	}

	HorizontalLayout layout = new HorizontalLayout();
	NavigationManager leftNavigation = new NavigationManager();
	NavigationManager rightNavigation = new NavigationManager();
	DetailsView detailsView = new DetailsView();
	StatusView dashboardView = new StatusView();

	public void onBrowserDetailsReady() {
		TouchKitWindow w = new TouchKitWindow();
		w.setContent(layout);
		setMainWindow(w);
		w.setCaption("CRM");

		layout.setSizeFull();
		layout.addComponent(leftNavigation);
		layout.addComponent(rightNavigation);

		leftNavigation.setCurrentComponent(new AccountListView());
		rightNavigation.setCurrentComponent(dashboardView);
	}

	public void showDetailsView(EntityItem<? extends Record> recordToEdit,
			UpdateListener listener) {
		rightNavigation.navigateTo(detailsView);
		detailsView.setRecord(recordToEdit, listener);
	}

	public void hideDetailsView() {
		while (rightNavigation.getCurrentComponent() != dashboardView) 
			rightNavigation.navigateBack();
	}

}
