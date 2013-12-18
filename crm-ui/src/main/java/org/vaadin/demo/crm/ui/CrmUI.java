package org.vaadin.demo.crm.ui;

import javax.servlet.annotation.WebServlet;

import org.vaadin.demo.crm.data.mockup.Generator;

import com.vaadin.addon.touchkit.extensions.TouchKitIcon;
import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
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

	@Override
	protected void init(VaadinRequest request) {

		TabBarView tabs = new TabBarView();
		setContent(tabs);

		NavigationManager n = new NavigationManager();
		AccountListView accountList = new AccountListView();
		n.setCurrentComponent(accountList);
		TouchKitIcon.list.addTo(tabs.addTab(n));
		
		TouchKitIcon.barChart.addTo(tabs.addTab(new Stats()));

	}

}
