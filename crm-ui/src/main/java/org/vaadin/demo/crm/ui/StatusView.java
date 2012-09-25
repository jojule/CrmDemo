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

	VerticalLayout layout = new VerticalLayout();

	public StatusView() {
		setContent(layout);
		getNavigationBar().setCaption("Status");

		buildPipelineStatus();
		buildLeadList();
	}

	private void buildLeadList() {
		VerticalComponentGroup leadList = new VerticalComponentGroup(
				"Unprocessed Leads");
		layout.addComponent(leadList);

		for (Lead lead : Backend.getLeads()) {
			// Create view for lead details
			NavigationView leadDetailsView = new NavigationView();
			leadDetailsView.setCaption(lead.getTitle());
			VerticalComponentGroup descriptionBox = new VerticalComponentGroup();
			descriptionBox.addComponent(new Label(lead.getDescriptionHtml(),
					Label.CONTENT_XHTML));
			leadDetailsView.setContent(descriptionBox);

			// Create button to navigate to the details view
			NavigationButton showLeadButton = new NavigationButton();
			showLeadButton.setCaption(lead.getTitle());
			showLeadButton.setTargetView(leadDetailsView);
			leadList.addComponent(showLeadButton);
		}
	}

	private void buildPipelineStatus() {
		PipelineStage[] stages = Backend.getPipelineReport();

		DistributionBar graph = new DistributionBar(stages.length);
		VerticalComponentGroup stageList = new VerticalComponentGroup(
				"My Pipeline");

		// Add sales pipeline stage statistics from the backend
		for (int i = 0; i < stages.length; i++) {
			graph.setPartSize(i, stages[i].getTotalValue());
			graph.setPartTooltip(i, stages[i].getName());
			String html = "<div><b>" + stages[i].getName() + "</b> "
					+ +stages[i].getOpportunityCount() + " opportunities, $"
					+ stages[i].getTotalValue() + " total value, $"
					+ stages[i].getProbabilityAdjustedValue() + " expected</div>";
			stageList.addComponent(new Label(html, Label.CONTENT_XHTML));
		}
		layout.addComponent(stageList);

		// Add pipeline graph to view
		VerticalLayout wrapper = new VerticalLayout();
		wrapper.addComponent(graph);
		wrapper.setMargin(true);
		layout.addComponent(wrapper);
		graph.setWidth("100%");
		wrapper.setComponentAlignment(graph, Alignment.MIDDLE_CENTER);

	}
}
