package com.ecommerce.promotionengine.domain;

import java.io.Serializable;

public class SKUitem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private float unitPrice;

	public String getID() {
		return id;
	}

	public void setID(String iD) {
		id = iD;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public final void updateUnitPrice(float price) {
		setUnitPrice(price);
	}

	public SKUitem(String id, float unitPrice) {
		if (id.isBlank()||id.isEmpty()) {
			throw new IllegalArgumentException("Invalid or missing SKU id!");
		}
		if (unitPrice <= 0) {
			throw new IllegalArgumentException("Invalid unit price! It must be grater than zero!");
		}

		this.id = id;
		this.unitPrice = unitPrice;
	}

}