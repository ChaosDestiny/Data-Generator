package model;

import java.sql.Date;

public class Agreement extends Entity {
	protected Date contractDate;
	
	public Agreement() {
		super();
	}
	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contract_date) {
		this.contractDate = contract_date;
	}
	
	public Agreement(String name, String description, Date contract_date) {
		super(name, description);
		this.name = name;
		this.contractDate = contract_date;
		this.description = description;
	}
}
