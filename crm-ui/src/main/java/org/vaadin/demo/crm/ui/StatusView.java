package org.vaadin.demo.crm.ui;

import org.vaadin.alump.distributionbar.DistributionBar;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class StatusView extends NavigationView {

	VerticalLayout content = new VerticalLayout();

	public StatusView() {

		setContent(content);
		getNavigationBar().setCaption("Status");
		getNavigationBar().setLeftComponent(null);

		VerticalComponentGroup pipeline = new VerticalComponentGroup(
				"My Pipeline");
		content.addComponent(pipeline);
		DistributionBar graph = new DistributionBar(5);
		VerticalLayout wrapper = new VerticalLayout();
		wrapper.addComponent(graph);
		wrapper.setMargin(true);
		content.addComponent(wrapper);
		graph.setWidth("100%");
		wrapper.setComponentAlignment(graph, Alignment.MIDDLE_CENTER);

		for (int i = 0; i < 5; i++) {
			graph.setPartSize(i, (int) (Math.random() * 1000));
			graph.setPartTooltip(i, "Stage " + (i + 1));
			pipeline.addComponent(new Label("Stage " + (i + 1)
					+ "; 18 opportunities, total $100392, propability $10032"));
		}

		VerticalComponentGroup leads = new VerticalComponentGroup(
				"Unprocessed Leads");
		content.addComponent(leads);

		for (int i = 0; i < 15; i++) {
			NavigationButton b = new NavigationButton();
			b.setCaption("Lead name " + i);
			NavigationView leadDetails = new NavigationView();
			leadDetails.setCaption(b.getCaption());
			VerticalComponentGroup leadInfo = new VerticalComponentGroup();
			leadInfo.addComponent(new Label("<h1>foo</h2><p>hee<b>joo</b></p>",
					Label.CONTENT_XHTML));
			leadDetails.setContent(leadInfo);
			b.setTargetView(leadDetails);
			leads.addComponent(b);
		}
	}
}
