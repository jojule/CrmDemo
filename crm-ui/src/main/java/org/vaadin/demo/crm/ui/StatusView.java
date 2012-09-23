package org.vaadin.demo.crm.ui;

import org.vaadin.alump.distributionbar.DistributionBar;
import org.vaadin.demo.crm.Backend;
import org.vaadin.demo.crm.data.Lead;
import org.vaadin.demo.crm.data.PipelineStage;

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
		PipelineStage[] stages = Backend.getPipelineReport();
		DistributionBar graph = new DistributionBar(stages.length);
		VerticalLayout wrapper = new VerticalLayout();
		wrapper.addComponent(graph);
		wrapper.setMargin(true);
		content.addComponent(wrapper);
		graph.setWidth("100%");
		wrapper.setComponentAlignment(graph, Alignment.MIDDLE_CENTER);

		for (int i = 0; i < stages.length; i++) {
			graph.setPartSize(i, stages[i].getTotalValue());
			graph.setPartTooltip(
					i,
					stages[i].getName() + ": "
							+ stages[i].getOpportunityCount());
			pipeline.addComponent(new Label("<b>" + stages[i].getName()
					+ "</b> " + +stages[i].getOpportunityCount()
					+ " opportunities, $" + stages[i].getTotalValue()
					+ " total value, $"
					+ stages[i].getProbabilityAdjustedValue()
					+ " expected", Label.CONTENT_XHTML));
		}

		VerticalComponentGroup leads = new VerticalComponentGroup(
				"Unprocessed Leads");
		content.addComponent(leads);

		for (Lead l : Backend.getLeads()) {
			NavigationButton b = new NavigationButton();
			b.setCaption(l.getTitle());
			NavigationView leadDetails = new NavigationView();
			leadDetails.setCaption(b.getCaption());
			VerticalComponentGroup leadInfo = new VerticalComponentGroup();
			leadInfo.addComponent(new Label(l.getDescriptionHtml(),
					Label.CONTENT_XHTML));
			leadDetails.setContent(leadInfo);
			b.setTargetView(leadDetails);
			leads.addComponent(b);
		}
	}
}
