package org.vaadin.demo.crm.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DashboardView extends NavigationView {

	public DashboardView() {

		VerticalLayout content = new VerticalLayout();
		VerticalComponentGroup pipeline = new VerticalComponentGroup(
				"My Pipeline");
		VerticalComponentGroup leads = new VerticalComponentGroup(
				"Unprocessed Leads");

		getNavigationBar().setCaption("Status");
		getNavigationBar().setLeftComponent(null);

		setContent(content);
		content.addComponent(pipeline);
		content.addComponent(leads);

		for (int i = 0; i < 5; i++)
			pipeline.addComponent(new Label(
					"Prospecting: 18 opportunities, total size $100392, propability weighted size "
							+ i));
		for (int i = 0; i < 15; i++)
			leads.addComponent(new Label(
					"Lead "
							+ i
							+ " please call to 3289 98 9, joonas.lehtinen@foo.bar interested in burchasing dskjdskjdk."));
	}
}
