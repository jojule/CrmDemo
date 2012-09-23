package org.vaadin.demo.crm.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Record {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (obj == null || obj.getClass()!=getClass()) return false;
			Record p = (Record) obj;
			if (this.id == null || p.id == null) {
				return false;
			}
			return this.id.equals(p.id);
	}

	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
	
	public abstract String getRecordName();

	public String getRecordType() {
		return getClass().getSimpleName();
	}

	public String getRecordTypePlural() {
		return getRecordType() + "s";
	}
}
