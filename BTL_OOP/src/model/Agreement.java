package model;

import java.sql.Date;

public class Agreement extends Entity {
	protected Date contract_date;
	
	public Agreement() {
		super();
	}
	public Date getContract_date() {
		return contract_date;
	}

	public void setContract_date(Date contract_date) {
		this.contract_date = contract_date;
	}
	
	public Agreement(String name, String description, Date contract_date) {
		super(name, description);
		this.name = name;
		this.contract_date = contract_date;
		this.description = description;
	}
}
