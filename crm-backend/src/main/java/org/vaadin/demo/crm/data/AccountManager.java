package org.vaadin.demo.crm.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountManager extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	String firstName;
	String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRecordName() {
		return "" + getFirstName() + " " + getLastName();
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof AccountManager) {
            AccountManager p = (AccountManager) obj;
            if (this == p) {
                return true;
            }
            if (this.id == null || p.id == null) {
                return false;
            }
            return this.id.equals(p.id);
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
