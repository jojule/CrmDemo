package org.vaadin.demo.crm.data;

public abstract class Record {
	
	public abstract String getRecordName();

	public String getRecordType() {
		return getClass().getSimpleName();
	}

	public String getRecordTypePlural() {
		return getRecordType() + "s";
	}
}
