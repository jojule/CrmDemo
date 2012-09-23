package org.vaadin.demo.crm.data;

public class PipelineStage {
	int opportunityCount;
	int totalValue;
	int probabilityAdjustedValue;
	String name;

	public int getOpportunityCount() {
		return opportunityCount;
	}

	public void setOpportunityCount(int opportunityCount) {
		this.opportunityCount = opportunityCount;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public int getProbabilityAdjustedValue() {
		return probabilityAdjustedValue;
	}

	public void setProbabilityAdjustedValue(int probabilityAdjustedValue) {
		this.probabilityAdjustedValue = probabilityAdjustedValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
