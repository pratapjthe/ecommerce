package com.ecommerce.promotionengine.dto;

import java.io.Serializable;

public class SKUitemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private float UnitPrice;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public float getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		UnitPrice = unitPrice;
	}

}
