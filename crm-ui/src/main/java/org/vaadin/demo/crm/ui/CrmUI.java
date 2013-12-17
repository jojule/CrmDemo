package org.vaadin.demo.crm.ui;

import javax.servlet.annotation.WebServlet;

import org.vaadin.demo.crm.data.Record;
import org.vaadin.demo.crm.data.mockup.Generator;
import org.vaadin.demo.crm.ui.DetailsView.UpdateListener;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@Widgetset("org.vaadin.demo.crm.ui.CrmWidgetSet")
@Theme("touchkit")
public class CrmUI extends UI {

	static {
		Generator.ensureAvailabilityOfMockupData();
	}

	@WebServlet("/*")
	public static class MyServlet extends TouchKitServlet {
	}

	HorizontalLayout layout = new HorizontalLayout();
	NavigationManager leftNavigation = new NavigationManager();
	NavigationManager rightNavigation = new NavigationManager();
	DetailsView detailsView = new DetailsView();
	StatusView dashboardView = new StatusView();

	public void onBrowserDetailsReady() {
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

	@Override
	protected void init(VaadinRequest request) {
		setContent(layout);

		layout.setSizeFull();
		layout.addComponent(leftNavigation);
		layout.addComponent(rightNavigation);

		leftNavigation.setCurrentComponent(new AccountListView());
		rightNavigation.setCurrentComponent(dashboardView);

	}

}
