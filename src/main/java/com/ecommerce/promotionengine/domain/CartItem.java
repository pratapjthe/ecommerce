package com.ecommerce.promotionengine.domain;

import java.io.Serializable;

public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SKUitem item;
	private boolean promotionApplied;
	private float finalPrice;

	public SKUitem getItem() {
		return item;
	}

	public void setItem(SKUitem item) {
		this.item = item;
	}

	public boolean isPromotionApplied() {
		return promotionApplied;
	}

	public void setPromotionApplied(boolean promotionApplied) {
		this.promotionApplied = promotionApplied;
	}

	public float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}

}
