package org.vaadin.demo.crm.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	String name;
	int sales;
	String country;
	String zip;
	String street;
	String website;
	String phone;
	boolean active;
	AccountManager accountManager;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public String getRecordName() {
		return getName();
	}
	
	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account p = (Account) obj;
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

    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

}
