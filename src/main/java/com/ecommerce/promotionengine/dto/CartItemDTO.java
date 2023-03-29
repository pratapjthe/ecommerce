package com.ecommerce.promotionengine.dto;

import java.io.Serializable;

public class CartItemDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SKUitemDTO Item;
	private boolean PromotionApplied;
	private float FinalPrice;
	public SKUitemDTO getItem() {
		return Item;
	}
	public void setItem(SKUitemDTO item) {
		Item = item;
	}
	public boolean isPromotionApplied() {
		return PromotionApplied;
	}
	public void setPromotionApplied(boolean promotionApplied) {
		PromotionApplied = promotionApplied;
	}
	public float getFinalPrice() {
		return FinalPrice;
	}
	public void setFinalPrice(float finalPrice) {
		FinalPrice = finalPrice;
	}
	
}
